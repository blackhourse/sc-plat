package cn.boot.st.system.dto.home;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-02-01
 **/
@Data
@ApiModel("VisitReqDTO ")
public class VisitReqDTO {
    /**
     * 应用区分
     */
    private String app;

    /**
     * 访问者ip
     */
    private String ip;

    /**
     * 访问的URI
     */
    private String uri;

    private Integer userId;
}
