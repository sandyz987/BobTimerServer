package com.sandyz.bobtimerserver.interceptor;

import com.sandyz.bobtimerserver.auth.UserHolder;
import com.sandyz.bobtimerserver.pojo.User;
import com.sandyz.bobtimerserver.util.BeanUtil;
import com.sandyz.bobtimerserver.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.sandyz.bobtimerserver.util.RedisKey.LOGIN_TOKEN;

public class RefreshTokenInterceptor implements HandlerInterceptor {

    RedisTemplate<String, Object> redisTemplate;

    public RefreshTokenInterceptor(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String cookieToken = CookieUtil.getCookieValue(request, "BOB_TOKEN");
        if (cookieToken == null) {
            return true;
        }
        String key = LOGIN_TOKEN + cookieToken;
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        if (map.isEmpty()) {
            return true;
        }
        User user = null;
        try {
            user = BeanUtil.map2bean(map, User.class);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        }
        UserHolder.setUser(user);
        redisTemplate.expire(key, 30 * 60, TimeUnit.SECONDS);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.removeUser();
    }
}
