package cn.boot.st.securityserver.service;


import cn.boot.common.framework.constant.AuthConstants;
import cn.boot.st.securityserver.domain.User;
import cn.boot.st.securityserver.domain.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


/**
 * 自定义用户认证和授权
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

//    private UserFeignService userFeignService;
//    private MemberFeignService memberFeignService;

    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter(AuthConstants.JWT_CLIENT_ID_KEY);
        User user = null;
        switch (clientId) {
            case AuthConstants.ADMIN_CLIENT_ID: // 后台用户
//                Result<UserDTO> userRes = userFeignService.loadUserByUsername(username, 2);
//                if (ResultCode.USER_NOT_EXIST.getCode().equals(userRes.getCode())) {
//                    throw new UsernameNotFoundException(ResultCode.USER_NOT_EXIST.getMsg());
//                }
                UserDTO userDTO = new UserDTO()
                        .setId(1L)
                        .setUsername("admin")
                        .setPassword("$2a$10$dLq3.pXNwTNqWabsRfJX4ej8Htk/vUWuHh.LvITq5BrU8u.dYvZpC")
                        .setStatus(1)
                        .setClientId("test");
                userDTO.setClientId(clientId);
                user = new User(userDTO);
                break;
        }
        if (!user.isEnabled()) {
            throw new DisabledException("该账户已被禁用!");
        } else if (!user.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定!");
        } else if (!user.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期!");
        }
        return user;
    }

}
