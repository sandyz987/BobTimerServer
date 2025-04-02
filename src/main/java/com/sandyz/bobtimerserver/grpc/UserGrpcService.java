package com.sandyz.bobtimerserver.grpc;

import cn.hutool.core.bean.BeanUtil;
import com.sandyz.bobtimerserver.pojo.User;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.PostConstruct;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import com.sandyz.bobtimerserver.grpc.UserProto.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.sandyz.bobtimerserver.util.RedisKey.LOGIN_TOKEN;

@GrpcService
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void getUserByToken(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        String token = request.getToken();
        String key = LOGIN_TOKEN + token;

        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        if (map.isEmpty()) {
            responseObserver.onError(io.grpc.Status.NOT_FOUND
                    .withDescription("Token not found").asRuntimeException());
            return;
        }

        User user = new User();
        try {
            BeanUtils.copyProperties(BeanUtil.mapToBean(map, User.class, true), user);
        } catch (Exception e) {
            responseObserver.onError(io.grpc.Status.INTERNAL
                    .withDescription("User parsing failed").asRuntimeException());
            return;
        }

        // 刷新过期时间
        redisTemplate.expire(key, 30, TimeUnit.MINUTES);

        UserResponse response = UserResponse.newBuilder()
                .setId(user.getId())
                .setUserId(user.getUserId())
                .setStuId(user.getStuId())
                .setSchoolId(user.getSchoolId())
                .setNickname(user.getNickname())
                .setPassword(user.getPassword())
                .setRegisterDate(user.getRegisterDate().toString())
                .setSex(user.getSex())
                .setText(user.getText())
                .setAvatarUrl(user.getAvatarUrl())
                .setIsAdmin(user.getIsAdmin())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    @PostConstruct
    public void init() {
        System.out.println("✅ gRPC UserGrpcService 初始化完成");
    }
}