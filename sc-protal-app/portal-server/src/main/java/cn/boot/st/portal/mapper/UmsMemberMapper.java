package cn.boot.st.portal.mapper;

import cn.boot.st.portal.dataobject.UmsMember;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-27
 **/

@Repository
public interface UmsMemberMapper extends BaseMapper<UmsMember> {
    int deleteByPrimaryKey(Long id);

    int insert(UmsMember record);

    int insertOrUpdate(UmsMember record);

    int insertOrUpdateSelective(UmsMember record);

    int insertSelective(UmsMember record);

    UmsMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsMember record);

    int updateByPrimaryKey(UmsMember record);

    int updateBatch(List<UmsMember> list);

    int batchInsert(@Param("list") List<UmsMember> list);

    /**
     * 通过手机号获取用户
     *
     * @param phone
     * @return
     */
    default UmsMember selectOneByPhone(String phone) {
        LambdaQueryWrapper<UmsMember> qw = new LambdaQueryWrapper<UmsMember>()
                .eq(UmsMember::getPhone, phone);
        return this.selectOne(qw);
    }

}