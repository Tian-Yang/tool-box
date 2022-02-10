package com.toolbox.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author tianYang
 * @since 2022-02-10
 */
@TableName("meta_client")
public class MetaClient implements Serializable {

    private static final long serialVersionUID = 1L;

    private String clientId;

    private String clientSecret;

    private LocalDateTime createTime;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MetaClient{" +
        "clientId=" + clientId +
        ", clientSecret=" + clientSecret +
        ", createTime=" + createTime +
        "}";
    }
}
