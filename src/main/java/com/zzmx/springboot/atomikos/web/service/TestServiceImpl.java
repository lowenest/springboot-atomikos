package com.zzmx.springboot.atomikos.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dennis
 * @version 1.0
 * @date 2018-09-14- 9:19
 */
@Service
public class TestServiceImpl implements ITestService {

    @Autowired
    private JdbcTemplate sysJdbcTemplate;

    @Autowired
    private JdbcTemplate busJdbcTemplate;

    @Transactional
    @Override
    public String ok() {

        System.out.println("begin.....");
        sysJdbcTemplate.execute("insert into sys_a(id) values(1)");
        busJdbcTemplate.execute("insert into bus_b(id) values(2)");
        System.out.println("end.....");
        return "success";
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String fail() {

        try {

            System.out.println("begin.....");
            sysJdbcTemplate.execute("insert into sys_a(id) values(11)");
            busJdbcTemplate.execute("insert into bus_b(id) values(20)");
            int a = 10 - 10;
            a = 1 / a;
            System.out.println("end.....");
        } catch (ArithmeticException e) {
            throw e;
            //throw new RuntimeException(e);
        }
        return "success";
    }

}
