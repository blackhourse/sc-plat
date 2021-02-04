package cn.boot.st.portal.enums;

import lombok.Getter;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-02-04
 **/
@Getter
public enum LikedStatusEnum {
    LIKE(1, "点赞"),

    UNLIKE(0, "取消点赞/未点赞");

    private Integer code;

    private String msg;

    LikedStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
