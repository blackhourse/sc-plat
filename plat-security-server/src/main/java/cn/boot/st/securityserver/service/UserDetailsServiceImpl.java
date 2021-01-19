package cn.boot.st.securityserver.service;


import cn.boot.common.framework.constant.AuthConstants;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.securityserver.api.ManagementwebFeignService;
import cn.boot.st.securityserver.domain.User;
import cn.boot.st.securityserver.domain.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private ManagementwebFeignService userFeignService;

    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter(AuthConstants.JWT_CLIENT_ID_KEY);
        User user = null;
        switch (clientId) {
            // 后台用户
            case AuthConstants.ADMIN_CLIENT_ID:
                CommonResult<UserDTO> userDTOCommonResult = userFeignService.getUserInfoByUserName(username);
                userDTOCommonResult.checkError();
                UserDTO resultData = userDTOCommonResult.getData();

                UserDTO userDTO = new UserDTO()
                        .setId(resultData.getId())
                        .setUsername(resultData.getUsername())
                        .setPassword(resultData.getPassword())
                        .setStatus(1)
                        .setRoles(resultData.getRoles())
                        .setClientId(clientId);
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
