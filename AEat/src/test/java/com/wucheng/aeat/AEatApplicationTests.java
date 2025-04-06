package com.wucheng.aeat;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.wucheng.aeat.domain.dto.UserDTO;
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
    void test1(){
        System.out.println("提交到git");
    }
    @Test
    void test2(){
        System.out.println("测试git分支");
        System.out.println("00000000000222");
    }
    @Test
    void tt(){
        UserDTO userDTO=new UserDTO(1L,"dd");
        String jsonStr = JSONUtil.toJsonStr(userDTO);
        System.out.println(jsonStr);
        UserDTO bean = JSONUtil.toBean(jsonStr, UserDTO.class);
        System.out.println(bean);
    }
    @Test
    void gitTest1(){
        System.out.println("git测试分支dev");
        System.out.println("第一个提交");
        System.out.println("第二次提交");
    }

    @Test
    void gitMainTest(){
        System.out.println("测试main分支");
    }

}
