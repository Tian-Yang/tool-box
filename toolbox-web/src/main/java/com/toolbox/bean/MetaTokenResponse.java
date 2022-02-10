package com.toolbox.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * facebook获取token返回结果
 */
@Data
public class MetaTokenResponse extends MetaBaseResponse implements Serializable {
    private static final long serialVersionUID = 867931806370151102L;
    private String accessToken;
    private String tokenType;
    private String expiresIn;
}
