package com.sandyz.bobtimerserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class BobTimerServerApplicationTests {

    @Test
    void getHash() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "123";  // 这里是你的明文密码
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("账号密码哈希: " + encodedPassword);
    }

}
