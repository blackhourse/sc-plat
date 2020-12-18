package cn.boot.common.framework.dataobject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-18
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionEntityVO implements Serializable {

    private String name;
    private String permission;
    private String url;
    private String serviceId;
}
