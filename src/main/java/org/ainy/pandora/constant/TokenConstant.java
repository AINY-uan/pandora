package org.ainy.pandora.constant;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-11-06 22:36
 * @description TOKEN常量
 */
public class TokenConstant {

    public static final String SECRET_KEY = "Pandora";
    public static final long TOKEN_EXPIRE_TIME = 10L * 60L * 1000L;

    public static final String HEADER_AUTHORIZATION = "Authorization";

    public static final String TOKEN_DEPRECATED = "身份认证错误，请重新登录";
    public static final String INTERNAL_EXCEPTION = "异常请稍后尝试";
    public static final String WORNGACCOUNT = "账户名或密码错误";
}
