package com.sandyz.bobtimerserver.controller;

import com.sandyz.bobtimerserver.pojo.UserDTO;
import com.sandyz.bobtimerserver.util.ResultMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/register")
    ResultMessage register(@RequestBody UserDTO userDTO) {

        return ResultMessage.success(200, "注册成功", userDTO);
    }

    @PostMapping("/login")
    ResultMessage login(@RequestBody UserDTO userDTO) {
        return ResultMessage.success(200, "注册成功", userDTO);
    }
}
