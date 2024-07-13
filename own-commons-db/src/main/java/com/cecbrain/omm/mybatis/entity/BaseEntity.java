package com.cecbrain.omm.mybatis.entity;

import com.mybatisflex.annotation.Column;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Mr.Lee
 * @description: 实体类
 * @date 2023-10-23 16:14
 */
public class BaseEntity implements Serializable {

    @Column(value = "create_time", onInsertValue = "now()")
    private LocalDateTime createTime;
    @Column(value = "update_time", onInsertValue = "now()", onUpdateValue = "now()")
    private LocalDateTime updateTime;

    @Column(isLogicDelete = true, onInsertValue = "0")
    private Integer deleted;

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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
