package com.yuhi.dto;


import com.yuhi.util.MessagesUtil;

/**
 * Created by Qbian on 2017/3/23.
 */
public class Node<T> {
    private int code;
    private String msg;
    private T result;

    public Node() {
        this.code = 0;
        this.msg = "OK";
    }

    public Node(int code) {
        this.code = code;
        if(0 != code) {
            this.msg = MessagesUtil.getErrorMsg(Math.abs(code));
        }
    }

    public Node(T result) {
        this();
        this.result = result;
    }

    public String toJsonString() {
        return "{\"code\": "+ this.code +", \"msg\": \""+ this.msg +"\"}";
    }

    public void setCode(int code) {
        this.code = code;
        if(0 != code) {
            this.msg = MessagesUtil.getErrorMsg(Math.abs(code));
        }
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
