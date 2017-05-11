package com.yuhi.base.entity;

import com.yuhi.base.web.message.MessageConstants;

import java.io.Serializable;

/**
 * ClassName BaseResult.
 * @coment: 统一返回结果类
 * @user: 李森林
 * @category @author www.justintoForest@Gamil.com
 * @since: 2017-05-10 下午 01:44
 */
public class BaseResult implements Serializable {
    // 状态码：1成功，其他为失败
    public int code;

    // 成功为success，其他为失败原因
    public String message;

    // 数据结果集
    public Object data;

    public BaseResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
