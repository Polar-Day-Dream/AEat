package com.wucheng.aeat;

import cn.hutool.core.lang.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class AEatApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void getpassword(){
        BCryptPasswordEncoder b=new BCryptPasswordEncoder();
        String p=b.encode("123456");
        System.out.println(p);
    }
    @Test
    void test2(){
        System.out.println("测试git分支");;
    }


}
