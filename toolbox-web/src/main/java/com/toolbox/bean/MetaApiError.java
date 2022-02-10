package com.toolbox.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * facebook 图谱API返回错误信息
 */
@Data
public class MetaApiError implements Serializable {
    private static final long serialVersionUID = 867931806370151102L;
    private String message;
    private String token;
    private String code;
    private String fbtraceId;
}
