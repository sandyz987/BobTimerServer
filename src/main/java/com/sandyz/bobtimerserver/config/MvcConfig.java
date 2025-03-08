package com.sandyz.bobtimerserver.config;

import com.sandyz.bobtimerserver.interceptor.LoginInterceptor;
import com.sandyz.bobtimerserver.interceptor.RefreshTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RefreshTokenInterceptor(redisTemplate))
                .addPathPatterns("/**");
        /**
         * 不需要使用用户信息UserHolder的路由都放在这里：
         */
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/static/**",
                        "/error",
                        "/user/login",
                        "/user/register",
                        "/user/logout"
                );
    }
}
