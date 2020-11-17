package com.mht.sc.scadmin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mht.sc.scadmin.constant.CommonConstant;
import com.mht.sc.scadmin.entity.SysUser;
import com.mht.sc.scadmin.enums.UserType;
import com.mht.sc.scadmin.mapper.SysUserMapper;
import com.mht.sc.scadmin.service.SysUserService;
import com.mht.sc.scadmin.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author maht
 * @since 2020-11-17
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Long saveOrUpdateUser(SysUser sysUser) {
        if (sysUser.getId() == null) {
            if (StringUtils.isBlank(sysUser.getType())) {
                sysUser.setType(UserType.BACKEND.name());
            }
            sysUser.setPassword(passwordEncoder.encode(CommonConstant.DEF_USER_PASSWORD));
            sysUser.setEnabled(1);
        }
        this.saveOrUpdate(sysUser);
        return sysUser.getId();
    }

    @Override
    public Boolean delUser(Long id) {
        SysUser sysUser = new SysUser().setId(id).setIsDel(1);
        return this.updateById(sysUser);
    }

    @Transactional
    @Override
    public Result<String> updatePassword(Long id, String oldPassword, String newPassword) {
        SysUser sysUser = baseMapper.selectById(id);
        if (StrUtil.isNotBlank(oldPassword)) {
            if (!passwordEncoder.matches(oldPassword, sysUser.getPassword())) {
                return Result.failed("旧密码错误");
            }
        }
        if (StrUtil.isBlank(newPassword)) {
            newPassword = CommonConstant.DEF_USER_PASSWORD;
        }
        SysUser user = new SysUser();
        user.setId(id);
        user.setPassword(passwordEncoder.encode(newPassword));
        baseMapper.updateById(user);
        return Result.succeed("修改成功");
    }

    @Override
    public Result<String> updateEnable(Long id, Integer enable) {
        SysUser user = new SysUser();
        user.setId(id);
        user.setEnabled(enable);
        this.updateById(user);
        return Result.succeed("修改成功");
    }

}
