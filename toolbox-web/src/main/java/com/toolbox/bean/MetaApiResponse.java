package com.toolbox.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "返回数据")
public class MetaApiResponse implements Serializable {
    private static final long serialVersionUID = 3108282883278411902L;
    @ApiModelProperty(value = "用户ID")
    private String id;
    @ApiModelProperty(value = "邮箱")
    private String email;
}
