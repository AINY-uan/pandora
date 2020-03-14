package org.ainy.pandora.exception;

import lombok.extern.slf4j.Slf4j;
import org.ainy.pandora.constant.ErrorConstant;
import org.ainy.pandora.model.ResponseData;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-11-18 23:11
 * @description 全局异常捕获
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 校验@RequestParam注解参数为空
     */
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseData<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {

        return new ResponseData<>(ErrorConstant.BAD_REQUEST.getCode(), e.getMessage());
    }



    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseData<?> handleConstraintViolationException(ConstraintViolationException ex) {

        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

        Iterator<?> iterator = constraintViolations.iterator();
        StringBuilder errorMsg = new StringBuilder();

        while (iterator.hasNext()) {
            ConstraintViolation<?> constraintViolation = (ConstraintViolation<?>) iterator.next();
            String error = constraintViolation.getMessage();
            errorMsg.append(iterator.hasNext() ? error + "; " : error);
        }
        if (!constraintViolations.isEmpty()) {
            return new ResponseData<>(ErrorConstant.BAD_REQUEST.getCode(), errorMsg.toString());
        }

        return new ResponseData<>(ErrorConstant.BAD_REQUEST.getCode(), ex.getMessage());
    }


    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseData<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<ObjectError> validErrors = ex.getBindingResult().getAllErrors();

        Iterator<?> iterator = validErrors.iterator();
        StringBuilder errorMsg = new StringBuilder();

        while (iterator.hasNext()) {
            ObjectError objectError = (ObjectError) iterator.next();
            String error = objectError.getDefaultMessage();
            errorMsg.append(iterator.hasNext() ? error + "; " : error);
        }
        if (!validErrors.isEmpty()) {
            return new ResponseData<>(ErrorConstant.BAD_REQUEST.getCode(), errorMsg.toString());
        }
        return new ResponseData<>(ErrorConstant.BAD_REQUEST.getCode(), ex.getMessage());
    }

    /**
     * 业务异常捕获
     */
    @ResponseBody
    @ExceptionHandler({BusinessException.class})
    public ResponseData<?> handleBusinessException(BusinessException e) {
        log.error("系统错误", e);
        return new ResponseData<>(e.getCode(), e.getMsg());
    }

    /**
     * 系统异常捕获
     */
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResponseData<?> handleOtherException(Exception e) {
        log.error("系统错误", e);
        return new ResponseData<>(ErrorConstant.SYSTEM_ERROR.getCode(), ErrorConstant.SYSTEM_ERROR.getMsg());
    }
}
