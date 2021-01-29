package cn.boot.st.portal.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-27
 **/
@Data
@Accessors(chain = true)
public class UmsLoginCodeVo {

    private Integer code;
    private Integer reamingTime;

}
