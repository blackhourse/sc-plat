package cn.boot.st.managementweb.service.api;

import cn.boot.st.managementweb.dataobject.domain.AdminRoleDO;
import cn.boot.st.managementweb.mapper.role.AdminRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-31
 **/
@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;


    @Override
    public List<Integer> getUserRoleInfo(Integer userId) {
        List<AdminRoleDO> adminRoleDOS = adminRoleMapper.selectListByAdminId(userId);
        return adminRoleDOS.stream().map(AdminRoleDO::getRoleId).collect(Collectors.toList());
    }
}
