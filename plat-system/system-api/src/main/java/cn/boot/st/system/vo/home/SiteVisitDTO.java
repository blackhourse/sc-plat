package cn.boot.st.system.vo.home;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-02-01
 **/
@Data
@AllArgsConstructor
public class SiteVisitDTO {
    /**
     * 站点访问统计
     */
    private VisitVO siteVO;

    /**
     * 页面访问统计
     */
    private VisitVO uriVO;


}
