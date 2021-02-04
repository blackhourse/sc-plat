package cn.boot.st.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-02-04
 **/
@Data
@AllArgsConstructor
public class LikedCountDTO {

    private String key;
    private Integer count;

}
