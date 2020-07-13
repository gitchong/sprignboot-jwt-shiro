package com.shadow.springboot.application.domain.bo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志
 *
 * @author tomsun28
 * @date 8:22 2018/4/22
 */
@Entity
@Table(name = "sys_operation_log")
public class OperationLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logName;
    private String userId;
    private String api;
    private String method;
    private Short succeed;
    private String message;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Short getSucceed() {
        return succeed;
    }

    public void setSucceed(Short succeed) {
        this.succeed = succeed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
