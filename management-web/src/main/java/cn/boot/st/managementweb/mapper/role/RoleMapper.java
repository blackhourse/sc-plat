package cn.boot.st.managementweb.mapper.role;


import cn.boot.st.managementweb.dataobject.domain.RoleDO;
import cn.boot.st.managementweb.dataobject.dto.RolePageDTO;
import cn.boot.st.mybatis.core.query.QueryWrapperX;
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
