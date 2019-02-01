package com.awakeee.hodgepodge.netty.server.services;

import org.springframework.stereotype.Service;

@Service("testservice")
public class TestServiceImpl implements TestService {
    @Override
    public String test() {
        return "aaaaa";
    }
}
