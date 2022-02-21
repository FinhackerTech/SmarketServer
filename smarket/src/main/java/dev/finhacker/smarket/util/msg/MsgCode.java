package dev.finhacker.smarket.util.msg;


public enum MsgCode {

    SUCCESS(1, "成功"),

    USER_NOT_FOUND(101, "没有该用户"),
    USER_PASSWORD_NOT_CORRECT(102, "密码错误"),
    USER_HAS_EXISTED(103, "用户名已存在"),
    USER_FAVOURITE_EXISTED(104, "收藏已存在"),
    USER_FAVOURITE_NOT_EXISTED(105, "收藏不存在"),
    USER_LOGIN_FAILED(106, "用户名或密码错误"),
    USER_CURRENT_NULL(107, "未登录"),

    ENTERPRISE_NOT_FOUND(201, "没有该企业"),
    ENTERPRISE_FILTER_TYPE_NOT_FOUND(202, "企业过滤类型不存在"),
    ENTERPRISE_FILTER_PARAM_ERROR(203, "企业过滤参数错误"),
    ENTERPRISE_PAGE_ERROR(204, "搜索页错误"),

    UNKNOWN(1001, "未知错误"),
    ;

    private Integer code;
    private String message;

    MsgCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
