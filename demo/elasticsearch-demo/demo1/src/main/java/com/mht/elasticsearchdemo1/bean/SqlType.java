package com.mht.elasticsearchdemo1.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class SqlType implements Serializable {
    private int id;
    private int commodity_name;
    private int commodity_price;
    private int number;
    private int description;
}
