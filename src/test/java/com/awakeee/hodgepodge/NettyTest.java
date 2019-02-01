package com.awakeee.hodgepodge;

import com.awakeee.hodgepodge.netty.server.proxy.ServiceProxy;
import com.awakeee.hodgepodge.netty.server.services.TestService;

public class NettyTest {

    public static void main(String[] args) {
        TestService testService = (TestService) ServiceProxy.getService(TestService.class);
        System.out.println(testService.test());
    }
}
