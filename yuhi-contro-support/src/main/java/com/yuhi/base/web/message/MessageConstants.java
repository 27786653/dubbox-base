package com.yuhi.base.web.message;

public class MessageConstants {
    public MessageConstants() {
    }

    public static enum Type {
        success("common.message.success"),
        warn("common.message.error"),
        error("common.message.warn");

        private String message;

        private Type(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
