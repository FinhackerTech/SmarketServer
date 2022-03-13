package com.finhacker.smarket.util.msg;

public class MsgCodeException extends Exception {

    private MsgCode msgCode;

    public MsgCodeException(MsgCode msgCode) {
        this.msgCode = msgCode;
    }

    public MsgCode getMsgCode() {
        return msgCode;
    }

}
