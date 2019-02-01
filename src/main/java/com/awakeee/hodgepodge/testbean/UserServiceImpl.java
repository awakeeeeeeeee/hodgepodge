package com.awakeee.hodgepodge.testbean;


import org.springframework.stereotype.Service;

@Service("testDemo")
public class UserServiceImpl implements UserService{

    @Override
    public void test() {
        System.out.println("test");
    }
}
