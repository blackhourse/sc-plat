package com.mht.sc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mht.common.api.CommonResult;
import com.mht.sc.dto.SysUserDto;
import com.mht.sc.dto.SysUserListDto;
import com.mht.sc.dto.SysUserLoginPhoneDto;
import com.mht.sc.dto.SysUserLoginPwdDto;
import com.mht.sc.entity.SysUser;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author maht
 * @since 2020-11-17
 */
public interface SysUserService extends IService<SysUser> {


    List<SysUser> sysUserList(SysUserListDto sysUserListDto);


    /**
     * 获取验证码
     *
     * @param telephone
     * @return
     */
    String generateAuthCode(String telephone);

    /**
     * 用户名 密码  登录返回token
     *
     * @param sysUserLoginPwdDto
     * @return
     */
    String login(SysUserLoginPwdDto sysUserLoginPwdDto);

    /**
     * 手机号-验证码登录
     *
     * @param sysUserLoginPhoneDto
     * @return
     */
    String loginPhone(SysUserLoginPhoneDto sysUserLoginPhoneDto);

    /**
     * 用户退出
     */
    void loginOut() throws Exception;

    /**
     * 新增/修改
     *
     * @param sysUser
     * @return
     */
    Long saveOrUpdateUser(SysUserDto sysUser);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    Boolean delUser(Long id);

    /**
     * 修改密码
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    CommonResult<String> updatePassword(Long id, String oldPassword, String newPassword);

    /**
     * 修改用户状态
     *
     * @param id
     * @param enable
     * @return
     */
    CommonResult<String> updateEnable(Long id, Integer enable);

    /**
     * 给用户分配角色
     *
     * @param id
     * @param roleIds
     */
    void setRoleToUser(Long id, Set<Long> roleIds);


}
