package com.zzmx.springboot.atomikos.web.controller;

import com.zzmx.springboot.atomikos.web.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ITestService testService;

    @Transactional
    @RequestMapping("ok")
    public String test() {
        return testService.ok();
    }


    @Transactional
    @RequestMapping("fail")
    public String failTest() {
        return testService.fail();
    }
}
