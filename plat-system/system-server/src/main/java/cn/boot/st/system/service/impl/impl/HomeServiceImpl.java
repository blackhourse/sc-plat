package cn.boot.st.system.service.impl.impl;

import cn.boot.common.framework.util.DateUtils;
import cn.boot.st.redis.RedisService;
import cn.boot.st.system.dto.home.VisitReqDTO;
import cn.boot.st.system.service.HomeService;
import cn.boot.st.system.vo.home.SiteVisitDTO;
import cn.boot.st.system.vo.home.VisitVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Objects;
import java.util.Set;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-02-01
 **/
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private RedisService redisService;

    @Override
    public SiteVisitDTO add(VisitReqDTO reqDTO) {
        ImmutablePair<String, String> uri = foramtUri(reqDTO.getUri());

        // 获取站点的访问记录
        VisitVO uriVisit = doVisit(reqDTO.getApp(), uri.getRight(), reqDTO.getIp());
        VisitVO siteVisit;
        if (uri.getLeft().equals(uri.getRight())) {
            siteVisit = new VisitVO(uriVisit);
        } else {
            siteVisit = doVisit(reqDTO.getApp(), uri.getLeft(), reqDTO.getIp());
        }
        return new SiteVisitDTO(siteVisit, uriVisit);
    }

    /**
     * 每日访问统计
     *
     * @param app
     * @param uri
     * @return
     */
    private String buildUriTagKey(String app, String uri) {
        return "uri_tag_" + DateUtils.getToday() + "_" + app + "_" + uri;
    }


    /**
     * 获取pv
     * <p>
     * pv存储结果为hash，一个应用一个key; field 为uri； value为pv
     *
     * @return null表示首次有人访问；这个时候需要+1
     */
    public Long getPv(String key, String uri) {
        Long result = null;
        Object hget = redisService.hget(key, uri);
        if (Objects.nonNull(hget)) {
            result = Long.valueOf(String.valueOf(hget));
        }
        return result;

    }

    /**
     * pv 次数+1
     *
     * @param key
     * @param uri
     */
    public void addPv(String key, String uri) {
        redisService.hincr(key, uri, 1);
    }

    /**
     * 热度，每访问一次，计数都+1
     *
     * @param key
     * @param uri
     * @return
     */
    public Long addHot(String key, String uri) {
        return redisService.hincr(key, uri, 1);
    }

    /**
     * 获取uri对应的uv，以及当前访问ip的历史访问排名
     * 使用zset来存储，key为uri唯一标识；value为ip；score为访问的排名
     *
     * @param key : 由app与URI来生成，即一个uri维护一个uv集
     * @param ip: 访问者ip
     * @return 返回uv/rank, 如果对应的值为0，表示没有访问过
     */
    public ImmutablePair</** uv */Long, /** rank */Long> getUv(String key, String ip) {
        // 获取总uv数，也就是最大的score

        Set<ZSetOperations.TypedTuple<Object>> typedTuples = redisService.zRangeWithScore(key, -1, -1);
        Long uv = typedTuples.stream().findFirst().get().getScore().longValue();

        if (uv == null || uv == 0L) {
            // 表示还没有人访问过
            return ImmutablePair.of(0L, 0L);
        }
        // 获取ip对应的访问排名
        Long rank = redisService.zScore(key, ip);
        if (rank == null) {
            return ImmutablePair.of(null,0L);
        }
        return ImmutablePair.of(uv, rank);
    }

    /**
     * uv +1
     *
     * @param key
     * @param ip
     * @param rank
     */
    public void addUv(String key, String ip, Long rank) {
        redisService.zAdd(key, ip, rank);
    }

    /**
     * 判断ip今天是否访问过
     * 采用bitset来判断ip是否有访问，key由app与uri唯一确定
     *
     * @return true 表示今天访问过/ false 表示今天没有访问过
     */
    public boolean visitToday(String key, String ip) {
        // ip地址进行分段 127.0.0.1

        String[] segments = ip.split(".");
        for (int i = 0; i < segments.length; i++) {
            if (!contain(key + "_" + i, Integer.valueOf(segments[i]))) {
                return false;
            }
        }
        return true;
    }

    private boolean contain(String key, Integer val) {
        return redisService.getBit(key, val);
    }


    /**
     * 标记ip访问过这个key
     *
     * @param key
     * @param ip
     */
    public void tagVisit(String key, String ip) {
        redisService.setBit(key, ip);
    }

    public static ImmutablePair</**host*/String, /**uri*/String> foramtUri(String uri) {
        URI u = URI.create(uri);
        String host = u.getHost();
        if (u.getPort() > 0 && u.getPort() != 80) {
            host = host + ":80";
        }

        String baseUri = u.getPath();
        if (u.getFragment() != null) {
            baseUri = baseUri + "#" + u.getFragment();
        }

        if (StringUtils.isNotBlank(baseUri)) {
            baseUri = host + baseUri;
        } else {
            baseUri = host;
        }

        return ImmutablePair.of(host, baseUri);
    }

    /**
     * 应用的pv统计计数
     *
     * @param app
     * @return
     */
    private String buildPvKey(String app) {
        return "site_cnt_" + app;
    }

    /**
     * 应用的热度统计计数
     *
     * @param app
     * @return
     */
    private String buildHotKey(String app) {
        return "hot_cnt_" + app;
    }

    /**
     * app+uri 对应的uv
     *
     * @param app
     * @param uri
     * @return
     */
    private String buildUvKey(String app, String uri) {
        return "uri_rank_" + app + "_" + uri;
    }

    /**
     * uri 访问统计
     *
     * @param reqDTO
     * @return
     */
    public SiteVisitDTO visit(VisitReqDTO reqDTO) {
        ImmutablePair<String, String> uri = foramtUri(reqDTO.getUri());

        // 获取站点的访问记录
        VisitVO uriVisit = doVisit(reqDTO.getApp(), uri.getRight(), reqDTO.getIp());
        VisitVO siteVisit;
        if (uri.getLeft().equals(uri.getRight())) {
            siteVisit = new VisitVO(uriVisit);
        } else {
            siteVisit = doVisit(reqDTO.getApp(), uri.getLeft(), reqDTO.getIp());
        }

        return new SiteVisitDTO(siteVisit, uriVisit);
    }

    private VisitVO doVisit(String app, String uri, String ip) {
        String pvKey = buildPvKey(app);
        String hotKey = buildHotKey(app);
        String uvKey = buildUvKey(app, uri);
        String todayVisitKey = buildUriTagKey(app, uri);

        Long hot = this.addHot(hotKey, uri);

        // 获取pv数据
        Long pv = this.getPv(pvKey, uri);
        if (pv == null || pv == 0) {
            // 历史没有访问过，则pv + 1, uv +1
            this.addPv(pvKey, uri);
            this.addUv(uvKey, ip, 1L);
            this.tagVisit(todayVisitKey, ip);
            return new VisitVO(1L, 1L, 1L, hot);
        }


        // 判断ip今天是否访问过
        boolean visit = this.visitToday(todayVisitKey, ip);

        // 获取uv及排名
        ImmutablePair</**uv*/Long, /**rank*/Long> uv = this.getUv(uvKey, ip);

        if (visit) {
            // 今天访问过，则不需要修改pv/uv；可以直接返回所需数据
            return new VisitVO(pv, uv.getLeft(), uv.getRight(), hot);
        }

        // 今天没访问过
        if (uv.left == 0L) {
            // 首次有人访问, pv + 1; uv +1
            this.addPv(pvKey, uri);
            this.addUv(uvKey, ip, 1L);
            this.tagVisit(todayVisitKey, ip);
            return new VisitVO(pv + 1, 1L, 1L, hot);
        } else if (uv.right == 0L) {
            // 这个ip首次访问, pv +1; uv + 1
            this.addPv(pvKey, uri);
            this.addUv(uvKey, ip, uv.left + 1);
            this.tagVisit(todayVisitKey, ip);
            return new VisitVO(pv + 1, uv.left + 1, uv.left + 1, hot);
        } else {
            // 这个ip的今天第一次访问， pv + 1 ; uv 不变
            this.addPv(pvKey, uri);
            this.tagVisit(todayVisitKey, ip);
            return new VisitVO(pv + 1, uv.left, uv.right, hot);
        }
    }

}
