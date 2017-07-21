package com.zzmx.springboot.atomikos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zzmx.springboot.atomikos.config.DataSourceConfig;
import com.zzmx.springboot.atomikos.config.TransactionManagerConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { DataSourceConfig.class, TransactionManagerConfig.class })
@Transactional(value = "transactionManager", rollbackFor = Exception.class)
public class TransactionTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private JdbcTemplate sysJdbcTemplate;

	@Autowired
	private JdbcTemplate busJdbcTemplate;

	@Test

	public void test() {
		sysJdbcTemplate.execute("insert into sys_a(id) values(1)");
		busJdbcTemplate.execute("insert into bus_b(id) values(1)");
	}

}
