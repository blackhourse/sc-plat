package com.mht.sc.scadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mht.common.api.CommonResult;
import com.mht.common.api.PageResult;
import com.mht.common.exception.BusinessException;
import com.mht.sc.scadmin.convert.SysRoleConvert;
import com.mht.sc.scadmin.dto.SysRoleAddOrUpdateDto;
import com.mht.sc.scadmin.entity.SysRole;
import com.mht.sc.scadmin.mapper.SysRoleMapper;
import com.mht.sc.scadmin.mapper.SysRoleMenuMapper;
import com.mht.sc.scadmin.mapper.SysRoleUserMapper;
import com.mht.sc.scadmin.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysRoleUserMapper userRoleMapper;

    @Resource
    private SysRoleMenuMapper roleMenuMapper;


    @Override
    public PageResult<SysRole> findRoles(Map<String, Object> params) {
        Integer curPage = MapUtils.getInteger(params, "page");
        Integer limit = MapUtils.getInteger(params, "limit");
        Page<SysRole> page = new Page<>(curPage == null ? 0 : curPage, limit == null ? -1 : limit);
        List<SysRole> list = baseMapper.findList(page, params);
        return PageResult.<SysRole>builder().data(list).code(0).count(page.getTotal()).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveRole(SysRole sysRole) {
        String roleCode = sysRole.getCode();
        QueryWrapper<SysRole> wrapper = new QueryWrapper<SysRole>().eq("code", roleCode);
        Integer count = this.baseMapper.selectCount(wrapper);
        if (count == 0) {
            this.save(sysRole);
        } else {
            // role已存在 抛出异常
            throw new BusinessException("角色code已存在");
        }

    }

    @Override
    public CommonResult saveOrUpdateRole(SysRoleAddOrUpdateDto sysRoleAddOrUpdateDto) {

        SysRole sysRole = SysRoleConvert.MAPPER.convert(sysRoleAddOrUpdateDto);
        Date date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        if (sysRole.getId() == null) {
            sysRole.setCreateTime(date);
            this.saveRole(sysRole);
        } else {
            baseMapper.updateById(sysRole);
        }
        return CommonResult.success("操作成功");

    }

    @Override
    public List<SysRole> findAll() {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<SysRole>().orderByDesc(SysRole::getCreateTime);
        return this.list(queryWrapper);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRole(Long id) {
        baseMapper.deleteById(id);
        roleMenuMapper.delete(id, null);
        userRoleMapper.deleteUserRole(null, id);
    }

}
