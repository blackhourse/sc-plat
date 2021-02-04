package cn.boot.st.portal.service.impl;

import cn.boot.st.portal.dataobject.UserLike;
import cn.boot.st.portal.dto.LikedCountDTO;
import cn.boot.st.portal.enums.LikedStatusEnum;
import cn.boot.st.portal.mapper.UserLikeMapper;
import cn.boot.st.portal.service.UserLikeService;
import cn.boot.st.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-02-04
 **/
@Service
public class UserLikeServiceImpl implements UserLikeService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UserLikeMapper userLikeMapper;

    //保存用户点赞数据的key
    public static final String MAP_KEY_USER_LIKED = "MAP_USER_LIKED";
    //保存用户被点赞数量的key
    public static final String MAP_KEY_USER_LIKED_COUNT = "MAP_USER_LIKED_COUNT";

    /**
     * 拼接被点赞的用户id和点赞的人的id作为key。格式 222222::333333
     *
     * @param likedUserId 被点赞的人id
     * @param likedPostId 点赞的人的id
     * @return
     */
    public static String getLikedKey(String likedUserId, String likedPostId) {
        StringBuilder builder = new StringBuilder();
        builder.append(likedUserId);
        builder.append("::");
        builder.append(likedPostId);
        return builder.toString();
    }

    @Override
    public void saveLiked2Redis(String likedUserId, String likedPostId) {
        String key = getLikedKey(likedUserId, likedPostId);
        redisService.hset(MAP_KEY_USER_LIKED, key, LikedStatusEnum.LIKE.getCode());
    }

    @Override
    public void unlikeFromRedis(String likedUserId, String likedPostId) {
        String key = getLikedKey(likedUserId, likedPostId);
        redisService.hset(MAP_KEY_USER_LIKED, key, LikedStatusEnum.UNLIKE.getCode());
    }

    @Override
    public void deleteLikedFromRedis(String likedUserId, String likedPostId) {
        String key = getLikedKey(likedUserId, likedPostId);
        redisService.hdel(MAP_KEY_USER_LIKED, key);
    }

    @Override
    public void incrementLikedCount(String likedUserId) {
        redisService.hincr(MAP_KEY_USER_LIKED_COUNT, likedUserId, 1);
    }

    @Override
    public void decrementLikedCount(String likedUserId) {
        redisService.hincr(MAP_KEY_USER_LIKED_COUNT, likedUserId, -1);
    }

    @Override
    public List<UserLike> getLikedDataFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisService.hscan(MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<UserLike> list = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> entry = cursor.next();
            String key = (String) entry.getKey();
            //分离出 likedUserId，likedPostId
            String[] split = key.split("::");
            String likedUserId = split[0];
            String likedPostId = split[1];
            Integer value = (Integer) entry.getValue();

            //组装成 UserLike 对象
            UserLike userLike = new UserLike(likedUserId, likedPostId, value);
            list.add(userLike);

            //存到 list 后从 Redis 中删除
            redisService.hdel(MAP_KEY_USER_LIKED, key);

        }
        return list;
    }

    @Override
    public List<LikedCountDTO> getLikedCountFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisService.hscan(MAP_KEY_USER_LIKED_COUNT, ScanOptions.NONE);
        List<LikedCountDTO> list = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> map = cursor.next();
            //将点赞数量存储在 LikedCountDT
            String key = (String) map.getKey();
            LikedCountDTO dto = new LikedCountDTO(key, (Integer) map.getValue());
            list.add(dto);
            //从Redis中删除这条记录
            redisService.hdel(MAP_KEY_USER_LIKED_COUNT, key);
        }
        return list;


    }

    @Override
    public UserLike getByLikedUserIdAndLikedPostId(String likedUserId, String likedPostId) {
        return userLikeMapper.selectByLikedPostIdAndLikedUserId(likedUserId, likedPostId);
    }

    @Override
    public void transLikedFromRedis2DB() {
        List<UserLike> list = this.getLikedDataFromRedis();
        for (UserLike like : list) {
            UserLike ul = this.getByLikedUserIdAndLikedPostId(like.getLikedUserId(), like.getLikedPostId());
            if (ul == null) {
                //没有记录，直接存入
                userLikeMapper.insert(like);
            } else {
                //有记录，需要更新
                ul.setStatus(like.getStatus());
                userLikeMapper.updateById(ul);
            }
        }
    }

    @Override
    public void transLikedCountFromRedis2DB() {

       /* List<LikedCountDTO> list = this.getLikedCountFromRedis();
        for (LikedCountDTO dto : list) {
            UserInfo user = userService.findById(dto.getId());
            //点赞数量属于无关紧要的操作，出错无需抛异常
            if (user != null){
                Integer likeNum = user.getLikeNum() + dto.getCount();
                user.setLikeNum(likeNum);
                //更新点赞数量
                userService.updateInfo(user);
            }
        }*/

    }


}
