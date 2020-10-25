package com.mht.elasticsearchdemo1.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CmsArticle implements Serializable {
    /**
     * $column.columnComment
     */
    private Long courseId;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String abstractX;

    /**
     * 内容
     */
    private String content;

    /**
     * 年龄段
     */
    private String ageRange;

    /**
     * 图片
     */
    private String image;

    /**
     * 查看次数
     */
    private Long viewNumber;

    /**
     * 作者
     */
    private String author;

    /**
     * 来源
     */
    private String source;

    /**
     * 所属分类
     */
    private Long classId;

    /**
     * 关键字
     */
    private String keyWords;

    /**
     * 描述
     */
    private String description;

    /**
     * 文章url
     */
    private String url;

    /**
     * 文章状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
