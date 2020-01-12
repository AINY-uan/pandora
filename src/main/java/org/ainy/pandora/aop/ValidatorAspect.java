//package org.ainy.pandora.aop;
//
//import org.ainy.pandora.constant.ErrorConstant;
//import org.ainy.pandora.exception.BusinessException;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//
//import javax.annotation.Resource;
//import javax.validation.ConstraintViolation;
//import javax.validation.groups.Default;
//import java.util.Iterator;
//import java.util.Set;
//
///**
// * @Author 阿拉丁省油的灯
// * @Date 2019-11-18 23:12
// * @Description 参数校验切面
// */
//@Aspect
//@Component
//public class ValidatorAspect {
//
//    @Resource
//    private LocalValidatorFactoryBean localValidatorFactoryBean;
//
//    public ValidatorAspect() {
//    }
//
//    @Pointcut("execution(* org.ainy.pandora.controller..*.*(..))")
//    private void parameterPointCut() {
//    }
//
//    /**
//     * 处理
//     */
//    @Before("parameterPointCut() && args(object,..)")
//    public void validateParameter(Object object) {
//
//        Set<ConstraintViolation<Object>> validErrors = this.localValidatorFactoryBean.validate(object, Default.class);
//        Iterator iterator = validErrors.iterator();
//        StringBuilder errorMsg = new StringBuilder();
//
//        while (iterator.hasNext()) {
//            ConstraintViolation constraintViolation = (ConstraintViolation) iterator.next();
//            String error = constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage();
//            errorMsg.append(iterator.hasNext() ? error + "; " : error);
//        }
//        if (!validErrors.isEmpty()) {
//            throw new BusinessException(ErrorConstant.NULL_VALUE_EXISTS, errorMsg.toString());
//        }
//    }
//}
