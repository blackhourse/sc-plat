package com.mht.sc.scadmin.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.mht.sc.scadmin.constant.CommonConstant;
import com.mht.sc.scadmin.convert.SysUserConvert;
import com.mht.sc.scadmin.dto.SysUserDto;
import com.mht.sc.scadmin.dto.SysUserListDto;
import com.mht.sc.scadmin.dto.SysUserLoginPhoneDto;
import com.mht.sc.scadmin.dto.SysUserLoginPwdDto;
import com.mht.sc.scadmin.entity.SysRoleUser;
import com.mht.sc.scadmin.entity.SysUser;
import com.mht.sc.scadmin.enums.UserType;
import com.mht.sc.scadmin.mapper.SysUserMapper;
import com.mht.sc.scadmin.service.SysRoleUserService;
import com.mht.sc.scadmin.service.SysUserCacheService;
import com.mht.sc.scadmin.service.SysUserService;
import com.mht.sc.scadmin.util.Asserts;
import com.mht.sc.scadmin.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author maht
 * @since 2020-11-17
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysRoleUserService SysRoleUserService;


    @Autowired
    private SysUserCacheService sysUserCacheService;

    @Override
    public List<SysUser> sysUserList(SysUserListDto sysUserListDto) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getIsDel, 0)
                .eq(Objects.nonNull(sysUserListDto.getEnabled()), SysUser::getEnabled, sysUserListDto.getEnabled())
                .eq(Objects.nonNull(sysUserListDto.getSex()), SysUser::getSex, sysUserListDto.getSex())
                .eq(StrUtil.isNotBlank(sysUserListDto.getPhone()), SysUser::getMobile, sysUserListDto.getPhone())
                .like(StrUtil.isNotBlank(sysUserListDto.getUserName()), SysUser::getUsername, sysUserListDto.getUserName());
        PageHelper.startPage(sysUserListDto.getPageNo(), sysUserListDto.getPageSize());
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public String generateAuthCode(String telephone) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        log.info("[手机号:{}验证码为:{}]", telephone, sb.toString());
        sysUserCacheService.setAuthCode(telephone, sb.toString());
        return sb.toString();
    }

    @Override
    public String login(SysUserLoginPwdDto sysUserLoginPwdDto) {
        LambdaQueryWrapper<SysUser> eq = new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, sysUserLoginPwdDto.getUserName())
                .eq(SysUser::getIsDel, 0);
        SysUser sysUser = this.getOne(eq);
        Asserts.isNull(sysUser, "用户不存在");
        boolean matches = passwordEncoder.matches(sysUserLoginPwdDto.getPwd(), sysUser.getPassword());
        Assert.isTrue(matches, "密码错误");
        //todo 生成token 并返回
        return "token";
    }

    @Override
    public String loginPhone(SysUserLoginPhoneDto sysUserLoginPhoneDto) {
        LambdaQueryWrapper<SysUser> eq = new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getMobile, sysUserLoginPhoneDto.getPhone())
                .eq(SysUser::getIsDel, 0);
        SysUser sysUser = this.getOne(eq);
        Asserts.isNull(sysUser, "用户不存在");
        String authCode = sysUserCacheService.getAuthCode(sysUserLoginPhoneDto.getPhone());
        Assert.notNull(authCode, "验证码已过期");
        Assert.isTrue(authCode.equals(sysUserLoginPhoneDto.getSmsCode()), "验证码已过期");
        //todo 生成token 并返回
        return "token";
    }

    @Transactional
    @Override
    public Long saveOrUpdateUser(SysUserDto sysUserDto) {
        SysUser sysUser = SysUserConvert.MAPPER.convert(sysUserDto);
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
        SysRoleUserService.deleteUserRole(id, null);
        SysUser sysUser = new SysUser().setId(id).setIsDel(1);
        baseMapper.updateById(sysUser);
        return true;
    }

    @Transactional
    @Override
    public CommonResult<String> updatePassword(Long id, String oldPassword, String newPassword) {
        SysUser sysUser = baseMapper.selectById(id);
        if (StrUtil.isNotBlank(oldPassword)) {
            if (!passwordEncoder.matches(oldPassword, sysUser.getPassword())) {
                return CommonResult.failed("旧密码错误");
            }
        }
        if (StrUtil.isBlank(newPassword)) {
            newPassword = CommonConstant.DEF_USER_PASSWORD;
        }
        SysUser user = new SysUser();
        user.setId(id);
        user.setPassword(passwordEncoder.encode(newPassword));
        baseMapper.updateById(user);
        return CommonResult.success("修改成功");
    }

    @Override
    public CommonResult<String> updateEnable(Long id, Integer enable) {
        SysUser user = new SysUser();
        user.setId(id);
        user.setEnabled(enable);
        this.updateById(user);
        return CommonResult.success("修改成功");
    }

    @Override
    public void setRoleToUser(Long id, Set<Long> roleIds) {
        SysUser sysUser = baseMapper.selectById(id);
        if (sysUser == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        SysRoleUserService.deleteUserRole(id, null);
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<SysRoleUser> roleUsers = new ArrayList<>(roleIds.size());
            roleIds.forEach(roleId -> roleUsers.add(new SysRoleUser(id, roleId)));
            SysRoleUserService.saveBatch(roleUsers);
        }
    }

}
