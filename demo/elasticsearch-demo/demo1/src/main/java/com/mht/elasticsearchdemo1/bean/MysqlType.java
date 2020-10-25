package com.mht.elasticsearchdemo1.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class MysqlType implements Serializable {
    private String id;
    private String commodity_name;
    private String commodity_price;
    private String number;
    private String description;
}
