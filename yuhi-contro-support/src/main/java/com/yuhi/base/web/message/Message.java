//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yuhi.base.web.message;
import com.yuhi.base.web.message.MessageConstants.Type;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class Message implements Serializable {
    private Type type;
    private String content;
    private Object result;
    private String errorCode;

    public Message() {
    }

    public Message(Type type, String content) {
        this.type = type;
        this.content = content;
    }

    public Message(Type type, String content, Object... args) {
        this.type = type;
        String i18inMsg = I18Util.getMessage(content, args);
        if(StringUtils.isNotBlank(i18inMsg)) {
            this.content = i18inMsg;
        } else {
            this.content = content;
        }

        if(StringUtils.isNotBlank(this.getContent()) && this.getContent().contains("{0}")) {
            for(int i = 0; i < args.length; ++i) {
                content = content.replaceAll("\\{" + i + "\\}", String.valueOf(args[i]));
            }

            this.content = content;
        }

    }

    public static Message success(String content, Object... args) {
        StringUtils.defaultIfBlank(content, Type.success.getMessage());
        return new Message(Type.success, content, args);
    }

    public static Message success() {
        return success("", (Object[])null);
    }

    public static Message warn() {
        return warn("", (Object[])null);
    }

    public static Message error() {
        return error("", (Object[])null);
    }

    public static Message warn(String content, Object... args) {
        return new Message(Type.warn, StringUtils.defaultIfBlank(content, Type.warn.getMessage()), args);
    }

    public static Message error(String content, Object... args) {
        return new Message(Type.error, StringUtils.defaultIfBlank(content, Type.error.getMessage()), args);
    }

    public Message addResult(Object result) {
        this.setResult(result);
        return this;
    }

    public Message addErrorCode(String errorCode) {
        this.setErrorCode(errorCode);
        return this;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getResult() {
        return this.result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String toString() {
        return I18Util.getMessage(this.content, new Object[0]);
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
