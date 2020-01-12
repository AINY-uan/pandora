package org.ainy.pandora.model;

import lombok.Data;
import org.ainy.pandora.constant.ErrorConstant;

/**
 * @Author 阿拉丁省油的灯
 * @Date 2019-11-06 22:40
 * @Description 响应数据Model
 */
@Data
public class ResponseData<T> {

    private Integer code;
    private Boolean status;
    private String message;
    private T data;

    public ResponseData(ErrorConstant error) {

        this.code = error.getCode();
        this.status = false;
        this.message = error.getMsg();
    }

    public ResponseData(T data) {

        this.code = 200;
        this.status = true;
        this.message = "success";
        this.data = data;
    }

    public ResponseData(Integer code, String message) {

        this.code = code;
        this.status = false;
        this.message = message;
    }
}
