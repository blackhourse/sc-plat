package cn.boot.st.securityserver.api;


import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.securityserver.domain.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("sc-admin")
public interface UserFeignService {

    @GetMapping("/users/{id}")
    CommonResult<UserDTO> loadUserByUsername(@PathVariable Object id, @RequestParam Integer queryMode);
}
