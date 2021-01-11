package cn.boot.st.productservice.enums;


/**
 * 商品 SKU 明细的字段枚举
 *
 * @see
 */
public enum ProductSkuDetailFieldEnum {

    SPU("spu"),
    ATTR("attr");

    /**
     * 字段
     */
    private final String field;

    ProductSkuDetailFieldEnum(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

}
