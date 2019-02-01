package com.awakeee.hodgepodge;

import com.awakeee.hodgepodge.netty.server.proxy.ServiceProxy;
import com.awakeee.hodgepodge.netty.server.services.TestService;
import com.awakeee.hodgepodge.testbean.SpringUtil;
import com.awakeee.hodgepodge.testbean.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HodgepodgeApplicationTests {

    @Test
    public void contextLoads() {
        UserService userService = (UserService) SpringUtil.getBean("testDemo");
        userService.test();

        System.out.println(userService.getClass().getSimpleName().toLowerCase());
    }

    //client并未分开 直接执行会提示地址被占用 因为服务器已经启动 无需再次启动
    @Test
    public void testnetty(){
        TestService testService = (TestService) ServiceProxy.getService(TestService.class);
        System.out.println(testService.test());
    }



}

