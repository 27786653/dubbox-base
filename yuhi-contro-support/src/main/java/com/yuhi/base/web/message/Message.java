//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yuhi.base.web.message;
import com.yuhi.base.web.message.MessageConstants.Type;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
/**
 * ClassName Message.
 * @coment: 返回结果封装
 * @user: 李森林
 * @category @author www.justintoForest@Gamil.com
 * @since: 2017-05-08 下午 01:13
 */
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
    public Message(Type type, String content,Object result) {
        this.type = type;
        this.content = content;
        this.result = result;
    }
    /**
     * @category 国际化消息.
     * @author www.justintoForest@Gamil.com
     * @since 2017-05-08 下午 01:13
     */
    public Message(Type type, HttpServletRequest request, String meskey) {
        this.type = type;
        String i18inMsg = I18Util.getMessage(request,meskey);
        if(StringUtils.isNotBlank(i18inMsg)) {
            this.content = i18inMsg;
        } else {
            this.content = meskey;
        }
    }

    public static Message success(String content) {
        StringUtils.defaultIfBlank(content, Type.success.getMessage());
        return new Message(Type.success, content);
    }

    public static Message warn(String content) {
        return new Message(Type.warn, StringUtils.defaultIfBlank(content, Type.warn.getMessage()));
    }

    public static Message error(String content) {
        return new Message(Type.error, StringUtils.defaultIfBlank(content, Type.error.getMessage()));
    }

    public static Message LocalMessage(Type type, HttpServletRequest request,String meskey) {
        return new Message(type,request,meskey);
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

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
