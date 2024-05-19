package com.lxc.elevateapi.model.dto.interfaceinfo;

import lombok.Data;

import java.io.Serializable;


/**
 * 创建请求
 * 数据库如果设置了，默认值和自动生成的id，，那么给前端输入的属性字段，就没必要 给实体类
 *
 * @TableName product
 */
@Data
public class InterfaceInfoAddRequest implements Serializable {

    /**
     * 接口名称
     */
    private String name;

    /**
     * 接口描述
     */
    private String description;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 响应头
     */
    private String responseHeader;

    /**
     * 请求方法
     */
    private String method;



    private static final long serialVersionUID = 1L;
}