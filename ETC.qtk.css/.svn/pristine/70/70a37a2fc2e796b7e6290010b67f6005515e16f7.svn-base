/* Copyright © 2009 ccc
 * All rights reserved.
 * 
 * Date: 2009-12-18
 * author: Peream  (peream@gmail.com)
 *
 */
package tests.cn.com.taiji.css.config.hsqldb;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.pub.ProjectEnv;
import cn.com.taiji.css.config.manager.AppConfig;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @author Peream <br>
 *         Create Time：2009-12-18 上午09:55:00<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackages = { "cn.com.taiji." + AppConfig.APP_NAME + ".dao.jdbc" })
public class HsqldbConfig extends AbstractManager
{
	@Value("#{commonProperties.envEth}")
	private String eth;
	@Value("#{systemProperties['webapp." + AppConfig.APP_NAME + "']}")
	private String webappPath;

	@Bean
	public ProjectEnv projectEnv()
	{
		return new ProjectEnv(eth, webappPath);
	}

	@Value("#{jdbcProperties.driverClassName}")
	private String driverClassName;
	@Value("#{jdbcProperties.url}")
	private String jdbcUrl;
	@Value("#{jdbcProperties.username}")
	private String username;
	@Value("#{jdbcProperties.password}")
	private String password;
	@Value("#{jdbcProperties.schema}")
	private String schema;

	@Bean(name = "dataSource", destroyMethod = "close")
	@Autowired
	public DataSource dataSource(ProjectEnv projectEnv)
	{
		DruidDataSource dataSource = new DruidDataSource();
		// dataSource.setDriverClass(driverClassName);
		if (jdbcUrl.trim().equalsIgnoreCase("jdbc:hsqldb:file:"))
		{
			jdbcUrl = jdbcUrl + ProjectEnv.getDataPath() + "/hsqldb/" + schema;
			logger.info("使用HSQLDB作为数据库:{}", jdbcUrl);
		}
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		// 配置初始化大小、最小、最大
		dataSource.setInitialSize(1);
		dataSource.setMinIdle(5);
		dataSource.setMaxActive(100);
		// 配置获取连接等待超时的时间
		dataSource.setMaxWait(60000);
		// 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
		dataSource.setTimeBetweenEvictionRunsMillis(180000);
		// 配置一个连接在池中最小生存的时间，单位是毫秒
		dataSource.setMinEvictableIdleTimeMillis(300000);
		dataSource.setTestWhileIdle(false);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
		// 打开PSCache，并且指定每个连接上PSCache的大小
		dataSource.setPoolPreparedStatements(true);
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
		return dataSource;
	}
}
