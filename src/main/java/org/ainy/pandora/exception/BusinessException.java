package org.ainy.pandora.exception;

import org.ainy.pandora.constant.ErrorConstant;

/**
 * @Author 阿拉丁省油的灯
 * @Date 2019-11-18 23:10
 * @Description 业务异常
 */
public class BusinessException extends RuntimeException {

    private Integer code;
    private String msg;
    private Throwable cause;

    public BusinessException() {

    }

    public BusinessException(String msg) {
        super(msg);
        this.setCode(ErrorConstant.SYSTEM_ERROR.getCode());
        this.setMsg(msg);
    }

    public BusinessException(ErrorConstant businessCode) {
        super(businessCode.getMsg());
        this.setCode(businessCode.getCode());
        this.setMsg(businessCode.getMsg());
    }

    public BusinessException(String msg, Throwable e) {
        super(msg);
        this.setCode(ErrorConstant.SYSTEM_ERROR.getCode());
        this.setMsg(msg);
        this.cause = e;
    }

    public BusinessException(ErrorConstant businessCode, String msg) {
        super(msg);
        this.setCode(businessCode.getCode());
        this.setMsg(msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
