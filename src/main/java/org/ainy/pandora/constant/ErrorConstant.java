package org.ainy.pandora.constant;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-11-09 21:46
 * @description 错误码枚举类
 */
public enum ErrorConstant {

    /**
     * success
     */
    SYSTEM_SUCCESS(200, "success"),
    /**
     * bad requesst
     */
    BAD_REQUEST(400, "bad request"),
    /**
     * un authorized
     */
    UN_AUTHORIZED(401, "un authorized"),
    /**
     * system error
     */
    SYSTEM_ERROR(500, "system error"),

    /**
     * user is not exist
     */
    USER_IS_NOT_EXIST(1001, "user is not exist"),
    /**
     * user is exist
     */
    USER_IS_EXIST(1002, "user is exist"),
    /**
     * login password is error
     */
    PASSWORD_IS_ERROR(1003, "login password is error"),
    /**
     * requst token is not exist
     */
    TOKEN_IS_NOT_EXIST(1004, "request token is not exist"),
    /**
     * token is invalid
     */
    TOKEN_IS_INVALID(1005, "request token is invalid"),

    /**
     * operation is failed
     */
    OPERATION_IS_FAILED(2001, "operation is failed"),
    ;


    private Integer code;
    private String msg;

    ErrorConstant(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
