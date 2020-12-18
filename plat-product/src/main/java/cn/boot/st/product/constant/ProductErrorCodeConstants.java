package cn.boot.st.product.constant;

import cn.boot.common.framework.exception.util.ErrorCode;

/*
 *  商品模块 1-003-000-000
 * */

/**
 * @author mht
 */
public interface ProductErrorCodeConstants {

    /**
     * 商品模块 1002005000
     */
    /**
     * 品牌
     */
    ErrorCode PRODUCT_BRAND_NAME_EXIST = new ErrorCode(1003001001, "品牌已存在");
    ErrorCode PRODUCT_BRAND_NOT_FOUND = new ErrorCode(1003001002, "商品品牌不存在");
    ErrorCode PRODUCT_BRAND_IS_THIS = new ErrorCode(1003001003, "品牌已是此的状态");
    /**
     * 分类
     */
    ErrorCode BRAND_PARENT_NOT_FOUNT = new ErrorCode(1003002001, "父分类未找到");
    ErrorCode PRODUCT_CATEGORY_PARENT_CAN_NOT_BE_LEVEL2 = new ErrorCode(1003002002, "父分类必须是一级分类");
    ErrorCode PRODUCT_CATEGORY_NOT_SET_OWN = new ErrorCode(1003002003, "不能设置自己为父分类");
    ErrorCode PRODUCT_CATEGORY_NOT_EXIST = new ErrorCode(1003002004, "商品分类不存在");
    ErrorCode PRODUCT_CATEGORY_CHILD_EXIST = new ErrorCode(1003002005, "子分类存在,不允许删除");

    /**
     * 规格
     */
    ErrorCode PRODUCT_ATTR_NAME_EXIST = new ErrorCode(1003003001, "规格名称已存在");
    ErrorCode PRODUCT_ATTR_NAME_NOR_EXIST = new ErrorCode(1003003002, "规格名称不存在");

    ErrorCode PRODUCT_ATTR_VALUE_EXIST = new ErrorCode(1003003003, "商品属性值已经存在");
    ErrorCode PRODUCT__ATTR_VALUE_NOT_EXIST = new ErrorCode(1003003004, "商品属性值不存在");
    ErrorCode PRODUCT_ATTR_KEY_NOT_EXIST = new ErrorCode(1003003005, "商品规格键不存在");
    ErrorCode PRODUCT_ATTR_KEY_EXISTS = new ErrorCode(1003003006, "商品规格键已经存在");

    /**
     * spu
     */
    ErrorCode PRODUCT_SPU_CATEGORY_MUST_BE_LEVEL2 = new ErrorCode(1003004001, "SKU只能添加在二级分类下");


}
