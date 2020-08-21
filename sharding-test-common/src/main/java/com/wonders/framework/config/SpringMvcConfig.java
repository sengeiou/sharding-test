package com.wonders.framework.config;


import com.wonders.framework.interceptor.CleanThreadLocalInterceptor;
import com.wonders.framework.interceptor.LoggingRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器
 *
 * @author YuChen
 * @date 2020-5-28 14:29:57
 **/
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    /**
     * jwt拦截器
     */
    @Autowired
    private CleanThreadLocalInterceptor cleanThreadLocalInterceptor;
    @Autowired
    private LoggingRequestInterceptor loggingRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 先入后出
        registry
                .addInterceptor(cleanThreadLocalInterceptor)
                .order(Integer.MAX_VALUE - 1)
                .addPathPatterns("/**");

        registry.addInterceptor(loggingRequestInterceptor)
                .order(Integer.MAX_VALUE)
                .addPathPatterns("/**");

    }
}
