package com.zzmx.springboot.atomikos.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSourceConfig {

	@Bean(name = "systemDataSource")
	@Primary
	@Autowired
	public DataSource systemDataSource(Environment env) {
		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		Properties prop = build(env, "spring.datasource.druid.systemDB.");
		ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
		ds.setUniqueResourceName("systemDB");
		ds.setPoolSize(5);
		ds.setXaProperties(prop);
		return ds;

	}

	@Autowired
	@Bean(name = "businessDataSource")
	public AtomikosDataSourceBean businessDataSource(Environment env) {

		AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
		Properties prop = build(env, "spring.datasource.druid.businessDB.");
		ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
		ds.setUniqueResourceName("businessDB");
		ds.setPoolSize(5);
		ds.setXaProperties(prop);

		return ds;
	}

	@Bean("sysJdbcTemplate")
	public JdbcTemplate sysJdbcTemplate(@Qualifier("systemDataSource") DataSource ds) {
		return new JdbcTemplate(ds);
	}

	@Bean("busJdbcTemplate")
	public JdbcTemplate busJdbcTemplate(@Qualifier("businessDataSource") DataSource ds) {
		return new JdbcTemplate(ds);
	}

	private Properties build(Environment env, String prefix) {

		Properties prop = new Properties();
		prop.put("url", env.getProperty(prefix + "url"));
		prop.put("username", env.getProperty(prefix + "username"));
		prop.put("password", env.getProperty(prefix + "password"));
		prop.put("driverClassName", env.getProperty(prefix + "driverClassName", ""));
		prop.put("initialSize", env.getProperty(prefix + "initialSize", Integer.class));
		prop.put("maxActive", env.getProperty(prefix + "maxActive", Integer.class));
		prop.put("minIdle", env.getProperty(prefix + "minIdle", Integer.class));
		prop.put("maxWait", env.getProperty(prefix + "maxWait", Integer.class));
		prop.put("poolPreparedStatements", env.getProperty(prefix + "poolPreparedStatements", Boolean.class));

		prop.put("maxPoolPreparedStatementPerConnectionSize",
				env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));

		prop.put("maxPoolPreparedStatementPerConnectionSize",
				env.getProperty(prefix + "maxPoolPreparedStatementPerConnectionSize", Integer.class));
		prop.put("validationQuery", env.getProperty(prefix + "validationQuery"));
		prop.put("validationQueryTimeout", env.getProperty(prefix + "validationQueryTimeout", Integer.class));
		prop.put("testOnBorrow", env.getProperty(prefix + "testOnBorrow", Boolean.class));
		prop.put("testOnReturn", env.getProperty(prefix + "testOnReturn", Boolean.class));
		prop.put("testWhileIdle", env.getProperty(prefix + "testWhileIdle", Boolean.class));
		prop.put("timeBetweenEvictionRunsMillis",
				env.getProperty(prefix + "timeBetweenEvictionRunsMillis", Integer.class));
		prop.put("minEvictableIdleTimeMillis", env.getProperty(prefix + "minEvictableIdleTimeMillis", Integer.class));
		prop.put("filters", env.getProperty(prefix + "filters"));

		return prop;
	}

}
