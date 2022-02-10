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
@TableName("meta_info")
public class MetaInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String token;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "MetaInfo{" +
        "id=" + id +
        ", token=" + token +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
