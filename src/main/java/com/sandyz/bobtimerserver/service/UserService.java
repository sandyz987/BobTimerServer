package com.sandyz.bobtimerserver.service;


import com.sandyz.bobtimerserver.pojo.User;
import com.sandyz.bobtimerserver.vo.UserVO;

public interface UserService {

    User userLoginTest(int userId);

    User userLogin(UserVO userVo);

    void userRegister(UserVO userVo);
}
