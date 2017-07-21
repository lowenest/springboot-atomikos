package com.zzmx.springboot.atomikos.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private JdbcTemplate sysJdbcTemplate;

	@Autowired
	private JdbcTemplate busJdbcTemplate;

	@Transactional
	@RequestMapping("")
	public void test() {
		System.out.println("begin.....");
		sysJdbcTemplate.execute("insert into sys_a(id) values(1)");
		busJdbcTemplate.execute("insert into bus_b(id) values(2)");
		System.out.println("end.....");
	}
}
