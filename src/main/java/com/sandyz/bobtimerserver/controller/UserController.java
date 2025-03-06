package com.sandyz.bobtimerserver.controller;

import cn.hutool.core.lang.UUID;
import com.sandyz.bobtimerserver.auth.UserHolder;
import com.sandyz.bobtimerserver.pojo.User;
import com.sandyz.bobtimerserver.pojo.UserDTO;
import com.sandyz.bobtimerserver.service.UserService;
import com.sandyz.bobtimerserver.util.BeanUtil;
import com.sandyz.bobtimerserver.util.CookieUtil;
import com.sandyz.bobtimerserver.util.ResultMessage;
import com.sandyz.bobtimerserver.vo.TokenVo;
import com.sandyz.bobtimerserver.vo.UserVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.sandyz.bobtimerserver.util.RedisKey.LOGIN_TOKEN;
import static com.sandyz.bobtimerserver.util.RedisKey.LOGIN_TOKEN_LIST;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/register")
    ResultMessage register(@RequestBody UserDTO userDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        userService.userRegister(userDTO);
        return ResultMessage.success(200, "注册成功", null);
    }

    @PostMapping("/login")
    ResultMessage login(@RequestBody UserDTO userDTO, HttpServletRequest request, HttpServletResponse response) {
        if (UserHolder.getUser() != null) {
            UserVo userVo = BeanUtil.copyProperties(UserHolder.getUser(), UserVo.class);
            return ResultMessage.success(200, "用户已登录！", userVo);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userService.userLogin(userDTO);
        if (user == null) {
            return ResultMessage.fail(400, "登录失败");
        }
        if (encoder.matches(userDTO.getPassword(), user.getPassword())) {
            String token = UUID.randomUUID().toString(true);
            try {
                redisTemplate.opsForHash().putAll(LOGIN_TOKEN + token, BeanUtil.bean2map(user));
                redisTemplate.opsForList().leftPush(LOGIN_TOKEN_LIST + user.getId(), token);
                redisTemplate.expire(LOGIN_TOKEN + token, 30 * 60, TimeUnit.SECONDS);
                redisTemplate.expire(LOGIN_TOKEN_LIST + user.getId(), 30 * 60, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
                return ResultMessage.fail(500, "登录失败");
            }
            CookieUtil.setCookie(request, response, "BOB_TOKEN", token, 30 * 60);
            return ResultMessage.success(200, "登录成功", new TokenVo(token, new Date().getTime() + 30 * 60 * 1000));

        }
        return ResultMessage.fail(400, "登录失败");
    }

    @PostMapping("/logout")
    ResultMessage logout(HttpServletRequest request) {
        if (UserHolder.getUser() != null) {
            redisTemplate.delete(LOGIN_TOKEN + UserHolder.getUser().getId());
            redisTemplate.delete(LOGIN_TOKEN_LIST + UserHolder.getUser().getId());
            UserHolder.removeUser();
        } else {
            return ResultMessage.success(400, "未登录");
        }
        String token = CookieUtil.getCookieValue(request, "BOB_TOKEN");
        if (token != null) {
            redisTemplate.delete(LOGIN_TOKEN + token);
            CookieUtil.delCookie(request, "BOB_TOKEN");
        }
        return ResultMessage.success(200, "退出成功");
    }
}
