package com.wanting.service.impl;

import com.wanting.service.TestService;

public class TestServiceImpl implements TestService {

    @Override
    public void selectData() {

        System.out.println("这里假装查询数据");
    }
}
