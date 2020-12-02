package cn.boot.st.managementweb.mapper.admin;

import cn.boot.st.managementweb.dataobject.admin.DepartmentDO;
import cn.boot.st.mybatis.core.query.QueryWrapperX;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentMapper extends BaseMapper<DepartmentDO> {

    default DepartmentDO selectByPidAndName(Integer pid, String name) {
        return selectOne(new QueryWrapperX<DepartmentDO>().eqIfPresent("pid", pid)
                .eqIfPresent("name", name));
    }

}
