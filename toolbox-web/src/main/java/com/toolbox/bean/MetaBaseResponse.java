package com.toolbox.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class MetaBaseResponse implements Serializable {
    private static final long serialVersionUID = 867931806370151102L;
    private MetaApiError error;

}
