package com.wanting.service;

import org.springframework.stereotype.Service;

@Service("indexService")
public class IndexServiceImpl implements IndexService{

    @Override
    public void index(){
        System.out.println("com.wanting.service.IndexServiceImp.index");
    }
}
