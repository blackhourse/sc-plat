package cn.boot.st.system.mapper;


import cn.boot.st.mybatis.core.query.QueryWrapperX;
import cn.boot.st.system.dataobject.RoleDO;
import cn.boot.st.system.dto.RolePageDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper extends BaseMapper<RoleDO> {

    default IPage<RoleDO> selectPage(RolePageDTO pageBO) {
        return selectPage(new Page<>(pageBO.getPageNo(), pageBO.getPageSize()),
                new QueryWrapperX<RoleDO>().likeIfPresent("name", pageBO.getName()));
    }


    default RoleDO selectByName(String name) {
        return selectOne(new LambdaQueryWrapper<RoleDO>().eq(RoleDO::getName, name));
    }

    default RoleDO selectByCode(String code) {
        return selectOne(new LambdaQueryWrapper<RoleDO>().eq(RoleDO::getCode, code));
    }


}
