package com.sandyz.bobtimerserver.service;


import com.sandyz.bobtimerserver.pojo.User;
import com.sandyz.bobtimerserver.pojo.UserDTO;

public interface UserService {

        User userLogin(UserDTO userDTO);

        void userRegister(UserDTO userDTO);
}
