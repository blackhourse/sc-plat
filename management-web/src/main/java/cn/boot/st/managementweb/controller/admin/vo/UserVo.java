package cn.boot.st.managementweb.controller.admin.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UserVo {

    private Long id;
    private String username;
    private String password;
    private Integer status;
    private String clientId;
    private List<Integer> roles;

}
