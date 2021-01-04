package cn.boot.common.framework.dataobject.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-30
 **/
@Data
@Accessors(chain = true)
public class PermissionVo implements Serializable {

    private String url;
    private String name;

}
