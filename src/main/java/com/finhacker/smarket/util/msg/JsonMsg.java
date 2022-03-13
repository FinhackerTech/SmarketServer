package com.finhacker.smarket.util.msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonMsg<T> {

    private Integer code;
    private String message;
    private T data;

    public JsonMsg(MsgCode msgCode) {
        this.code = msgCode.getCode();
        this.message = msgCode.getMessage();
        this.data = null;
    }

    public JsonMsg(T data) {
        this.code = MsgCode.SUCCESS.getCode();
        this.message = MsgCode.SUCCESS.getMessage();
        this.data = data;
    }

}
