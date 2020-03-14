package org.ainy.pandora.config.pandora;

import org.ainy.pandora.interceptor.CommonInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 阿拉丁省油的灯
 * @date 2019-11-23 22:37
 * @description 潘多拉配置
 */
@Component
public class PandoraConfig implements WebMvcConfigurer {
//public class PandoraConfig extends WebMvcConfigurationSupport {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//        List<String> excludePathPatterns = new ArrayList<>(2);
//        excludePathPatterns.add("/user/login");
//        excludePathPatterns.add("/user/create");
//        excludePathPatterns.add("/error");
//        // 多个拦截器组成一个拦截器链
//        // addPathPatterns 用于添加拦截规则
//        // excludePathPatterns 用户排除拦截
//        registry.addInterceptor(new CommonInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePathPatterns);
//        super.addInterceptors(registry);
//    }

    /**
     * 拦截器配置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        List<String> excludePathPatterns = new ArrayList<>(2);
        excludePathPatterns.add("/user/login");
        excludePathPatterns.add("/user/create");
        excludePathPatterns.add("/error/**");
        excludePathPatterns.add("/v2/**");
        excludePathPatterns.add("/swagger-ui.html/**");
        excludePathPatterns.add("/swagger-resources/**");
        excludePathPatterns.add("/webjars/**");
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new CommonInterceptor()).addPathPatterns("/**").excludePathPatterns(excludePathPatterns);
    }

    /**
     * 跨域支持配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").
                allowCredentials(true).allowedOrigins("*").
                allowedMethods("GET", "PUT", "DELETE", "POST", "OPTIONS").
                maxAge(3600);
    }
}
