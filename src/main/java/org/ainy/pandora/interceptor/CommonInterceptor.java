package org.ainy.pandora.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.ainy.pandora.constant.ErrorConstant;
import org.ainy.pandora.exception.BusinessException;
import org.ainy.pandora.util.AuthUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 阿拉丁省油的灯
 * @Date 2019-11-23 22:27
 * @Description 公共拦截器
 */
@Slf4j
@Configuration
public class CommonInterceptor implements HandlerInterceptor {

    private static final String USER_KEY = "userId";

    /**
     * 在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String token = request.getHeader("token");

        if (StringUtils.isEmpty(token)) {
            throw new BusinessException(ErrorConstant.TOKEN_IS_NOT_EXIST);
        }
        if (!AuthUtils.decryptToken(token)) {
            throw new BusinessException(ErrorConstant.TOKEN_IS_INVALID);
        }
        log.info("request token is {}, userId is {}", token, AuthUtils.getUserId(token));
        request.setAttribute(USER_KEY, AuthUtils.getUserId(token));

        return true;
    }

    /**
     * 在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object arg2,
                           ModelAndView modelview) throws Exception {

    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）；
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object arg2,
                                Exception arg3)
            throws Exception {


    }
}
