package com.wanting.strengthen_service;

import com.wanting.service.TestService;

/**
 * 用这个类来增强 我们的目标类
 *
 *
 */
public class StrengthenTestService implements TestService {

    private TestService target;

    public StrengthenTestService(TestService target) {
        this.target = target;
    }

    @Override
    public void selectData() {
        System.out.println("-------log------");
        target.selectData();
    }
}
