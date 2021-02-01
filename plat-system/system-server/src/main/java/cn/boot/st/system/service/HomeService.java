package cn.boot.st.system.service;

import cn.boot.st.system.dto.home.VisitReqDTO;
import cn.boot.st.system.vo.home.SiteVisitDTO;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-02-01
 **/
public interface HomeService {

    SiteVisitDTO add(VisitReqDTO reqDTO);

}
