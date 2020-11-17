package com.mht.sc.scmbg;

import com.baomidou.mybatisplus.annotation.DbType;
import com.mht.sc.scmbg.utils.CommonUtils;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-11-17 10:11
 **/
public class MySQL8CodeGenerator {
    public static void main(String[] args) {
        DbType dbType = DbType.MYSQL;
        String dbUrl = "jdbc:mysql://localhost:3306/sc-plat?serverTimezone=GMT%2B8&characterEncoding=utf8&useSSL=false&allowMultiQueries=true&zeroDateTimeBehavior=CONVERT_TO_NULL";
        String username = "root";
        String password = "123456";
        String driver = "com.mysql.cj.jdbc.Driver";
        // 表前缀，生成的实体类，不含前缀
        String [] tablePrefixes = {};
        // 表名，为空，生成所有的表
        String [] tableNames = {};
        // 字段前缀
        String [] fieldPrefixes = {};
        // 基础包名
        String packageName = "com.mht.sc.scadmin";
        CommonUtils.execute(dbType, dbUrl, username, password, driver, tablePrefixes, tableNames, packageName, fieldPrefixes);
    }

}
