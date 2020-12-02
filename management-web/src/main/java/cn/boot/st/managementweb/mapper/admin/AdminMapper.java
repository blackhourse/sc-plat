package cn.boot.st.managementweb.mapper.admin;

import cn.boot.st.managementweb.dataobject.admin.AdminDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper extends BaseMapper<AdminDO> {


    default AdminDO selectByUsername(String username) {
        return selectOne(new QueryWrapper<AdminDO>()
                .eq("username", username)
        );
    }

}
