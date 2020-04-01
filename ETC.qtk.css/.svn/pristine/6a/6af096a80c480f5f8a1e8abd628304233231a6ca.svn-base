package cn.com.taiji.css.config.manager;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.aspectj.AnnotationTransactionAspect;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;

import cn.com.taiji.common.manager.AbstractManager;
import cn.com.taiji.common.pub.ProjectEnv;
import cn.com.taiji.common.repo.jpa.MyJpaRespositoryFactoryBean;
import spring.cn.com.taiji.common.annotation.PostInitializerRunner;

/**
 * 
 * @author Peream <br>
 *         Create Time：2009-12-18 上午09:55:00<br>
 *         <a href="mailto:peream@gmail.com">peream@gmail.com</a>
 * @since 1.0
 * @version 1.0
 */
@Configuration
// 启用spring data jpa
@EnableJpaRepositories(basePackages = {"cn.com.taiji." + AppConfig.APP_NAME
		+ ".repo.jpa","cn.com.taiji.qtk.repo.jpa"}, entityManagerFactoryRef = "entityManager", repositoryFactoryBeanClass = MyJpaRespositoryFactoryBean.class)
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ, proxyTargetClass = true)
@ComponentScan(basePackages = { "cn.com.taiji." + AppConfig.APP_NAME + ".manager",
		"cn.com.taiji." + AppConfig.APP_NAME + ".dao.jpa", "cn.com.taiji." + AppConfig.APP_NAME + ".dao.jdbc",
		"cn.com.taiji.dsi.manager.comm.client","cn.com.taiji.dsi.manager.rechargeoutlierdata"})
public class AppConfig extends AbstractManager
{
	public static final String APP_NAME = "css";
	@Value("#{commonProperties.envEth}")
	private String eth;
	@Value("#{systemProperties['webapp." + AppConfig.APP_NAME + "']}")
	private String webappPath;

	@Bean
	public ProjectEnv projectEnv()
	{
		ProjectEnv projectEnv = new ProjectEnv(eth);
		projectEnv.setWebappPath(webappPath);
		return projectEnv;
	}

	@Bean(name = "messageSource")
	@Autowired
	public MessageSource messageSource(ProjectEnv projectEnv)
	{
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames(new String[] { "common", "ValidationMessages" });
		return source;
	}

	@Value("#{jdbcProperties.driverClassName}")
	private String driverClassName;
	@Value("#{jdbcProperties.url}")
	private String jdbcUrl;
	@Value("#{jdbcProperties.username}")
	private String username;
	@Value("#{jdbcProperties.password}")
	private String password;
//	@Value("#{jdbcProperties.schema}")
//	private String schema;
	@Value("#{jdbcProperties.validationQuery}")
	private String validationQuery;
	@SuppressWarnings("unused")
	private boolean useHsqldb = false;

	@Bean(name = "dataSource", destroyMethod = "close")
	@Autowired
	public DataSource dataSource(ProjectEnv projectEnv)
	{
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(driverClassName);
		if (jdbcUrl.trim().equalsIgnoreCase("jdbc:hsqldb:file:"))
		{
			jdbcUrl = jdbcUrl + ProjectEnv.getDataPath() + "/hsqldb/";
			useHsqldb = true;
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
		dataSource.setValidationQuery(validationQuery);
		dataSource.setTestWhileIdle(true);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
		// 打开PSCache，并且指定每个连接上PSCache的大小
		dataSource.setPoolPreparedStatements(true);
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
		// 配置监控统计拦截的filters
		StatFilter statFilter = new StatFilter();
		// statFilter.setMergeSql(true);
		// statFilter.setLogSlowSql(true);
		// statFilter.setSlowSqlMillis(10000);
		List<Filter> filters = Lists.newArrayList();
		filters.add(statFilter);
		dataSource.setProxyFilters(filters);
		logger.info("Init dataSource success:{}", dataSource);
		return dataSource;
	}

//	@Value("#{jdbcProperties.enableDrds}")
//	private boolean enableDrds;

//	@Bean
//	public GtsDao gtsDao()
//	{
//		return new GtsDao(dataSource(null), enableDrds);
//	}

	@Value("#{jdbcProperties.dialect}")
	private String hibernateDialect;

	@Bean(name = "entityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean()
	{
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(dataSource(null));
		factoryBean.setPackagesToScan(new String[] { "cn.com.taiji." + AppConfig.APP_NAME + ".entity","cn.com.taiji.qtk.entity" });
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabasePlatform(hibernateDialect);

		if (logger.isDebugEnabled())
		{
			// vendorAdapter.setShowSql(true);
			// vendorAdapter.setGenerateDdl(true);
		}
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		factoryBean.setJpaProperties(this.additionlProperties());
		return factoryBean;
	}

	private Properties additionlProperties()
	{
		Properties props = new Properties();
//		if (!useHsqldb) props.setProperty("hibernate.default_schema", schema);// 设置默认schema
		props.setProperty("hibernate.connection.handling_mode", "DELAYED_ACQUISITION_AND_HOLD");// PhysicalConnectionHandlingMode
		return props;
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager()
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(this.entityManagerFactoryBean().getObject());
		return transactionManager;
	}

	@Bean(name = "annotationTransactionAspect")
	public AnnotationTransactionAspect annotationTransactionAspect() throws Exception
	{
		AnnotationTransactionAspect aspect = AnnotationTransactionAspect.aspectOf();
		aspect.setTransactionManager(transactionManager());
		return aspect;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
	{
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public PostInitializerRunner postInitializerRunner()
	{
		return new PostInitializerRunner();
	}
}
