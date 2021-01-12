package cn.boot.st.tradeserver.mapper;

import cn.boot.st.tradeserver.dataobject.TradeOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-12
 **/

@Repository
public interface TradeOrderMapper extends BaseMapper<TradeOrder> {
    int updateBatch(List<TradeOrder> list);

    int updateBatchSelective(List<TradeOrder> list);

    int batchInsert(@Param("list") List<TradeOrder> list);

    int insertOrUpdate(TradeOrder record);

    int insertOrUpdateSelective(TradeOrder record);


}