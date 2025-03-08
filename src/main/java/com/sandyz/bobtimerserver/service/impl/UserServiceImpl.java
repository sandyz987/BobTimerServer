package com.sandyz.bobtimerserver.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.sandyz.bobtimerserver.exception.BobException;
import com.sandyz.bobtimerserver.exception.BobExceptionEnum;
import com.sandyz.bobtimerserver.pojo.User;
import com.sandyz.bobtimerserver.vo.UserVO;
import com.sandyz.bobtimerserver.repo.UserRepository;
import com.sandyz.bobtimerserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User userLogin(UserVO userVo) {
        User user = userRepository.findByUserId(userVo.getUserId());
        if (user == null) {
            throw new BobException(BobExceptionEnum.USER_NOT_EXISTED_ERROR);
        }
        return user;
    }

    @Override
    public void userRegister(UserVO userVo) {
        User user = new User();
        user.setUserId(userVo.getUserId());
        user.setPassword(userVo.getPassword());
        user.setNickname("User_" + RandomUtil.randomString(6));
        user.setAvatarUrl("https://picsum.photos/200/300");
        user.setSex("保密");
        user.setSchoolId(1);
        user.setIsAdmin(false);
        user.setStuId("未设置");
        user.setText("这个人很懒，什么都没有留下");
        user.setRegisterDate(Timestamp.valueOf(LocalDateTime.now()));

        try {
            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BobException(BobExceptionEnum.REGISTER_USER_ERROR);
        }
    }
}
