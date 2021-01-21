package cn.boot.st.system.mapper;

import cn.boot.st.system.dataobject.DepartmentDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentMapper extends BaseMapper<DepartmentDO> {

    default DepartmentDO selectByPidAndName(Integer pid, String name) {
        return selectOne(new LambdaQueryWrapper<DepartmentDO>().eq(DepartmentDO::getPid, pid)
                .eq(DepartmentDO::getName, name));
    }

}
