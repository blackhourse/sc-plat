package cn.boot.st.system.mapper;

import cn.boot.st.system.dataobject.AdminDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper extends BaseMapper<AdminDO> {


    default AdminDO selectByUsername(String username) {
        return selectOne(new LambdaQueryWrapper<AdminDO>()
                .eq(AdminDO::getUsername, username)
        );
    }

    default Integer selectCountByDepartmentId(Integer departmentId) {
        return selectCount(new LambdaQueryWrapper<AdminDO>()
                .eq(AdminDO::getDepartmentId, departmentId)
        );
    }

}
