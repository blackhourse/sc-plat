package cn.boot.st.tradeserver.constants;

import cn.boot.common.framework.exception.util.ErrorCode;

/*
 *  商品模块 1-003-000-000
 * */

/**
 * @author mht
 */
public interface TradeErrorCodeConstants {


    ErrorCode GET_PRODUCT_SKU_FAIL = new ErrorCode(1004001001, "获取商品信息失败");
    ErrorCode CARD_ITEM_SKU_NOT_FOUND = new ErrorCode(1004001002, "商品不存在");
    ErrorCode CARD_ITEM_SKU_QUANTITY_NOT_ENOUGH = new ErrorCode(1004001003, "商品库存不足");
    ErrorCode CARD_ITEM_NOT_FOUND = new ErrorCode(1004001004, "购物车项不存在");


}
