package cn.boot.st.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-08 10:15
 **/
@ApiModel("管理员信息 VO")
@Data
@Accessors(chain = true)
public class PassportAdminVO {

    @ApiModelProperty(value = "真实名字", required = true, example = "小王")
    private String name;
    @ApiModelProperty(value = "头像", required = true, example = "http://www.iocoder.cn/xxx.jpg")
    private String avatar;

}
