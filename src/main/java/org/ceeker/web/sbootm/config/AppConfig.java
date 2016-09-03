package org.ceeker.web.sbootm.config;

import javax.servlet.Filter;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * Spring boot 配置类
 * 
 * @author zhangxiaoling01
 * @date 2016年5月5日 下午2:54:25
 * @see
 */
@Configuration
// @MapperScan("org.ceeker.web.sbootm.entity.mapper")
// 注解事务
@EnableTransactionManagement
// 导入其他配置类
@Import(value = {})
// @PropertySources({ @PropertySource(value =
// "classpath:application.properties", ignoreResourceNotFound = true),
// @PropertySource(value = "file:./application.properties",
// ignoreResourceNotFound = true) })
public class AppConfig {

	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	/**
	 * HikariDataSourceConfiguration
	 * 参考：https://github.com/brettwooldridge/HikariCP
	 * 
	 * @return
	 */
	// @Bean(destroyMethod = "close")
	// public DataSource dataSource() {
	// HikariConfig hikariConfig = new HikariConfig();
	// hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
	// hikariConfig.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/springboot?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&zeroDateTimeBehavior=convertToNull");
	// hikariConfig.setUsername("root");
	// hikariConfig.setPassword("root");
	// hikariConfig.setPoolName("springHikariCP");
	// //关掉自动提交.否则事务部生效
	// hikariConfig.setAutoCommit(false);
	//
	// hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
	// hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
	// hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
	// hikariConfig.addDataSourceProperty("useServerPrepStmts", "true");
	//
	// hikariConfig.setMinimumIdle(20);
	// hikariConfig.setMaximumPoolSize(20);
	// hikariConfig.setConnectionInitSql("SELECT 1");
	//
	// HikariDataSource dataSource = new HikariDataSource(hikariConfig);
	// return dataSource;
	// }

	// @Bean
	// public PlatformTransactionManager transactionManager() {
	// return new DataSourceTransactionManager(dataSource());
	// }
	//
	// //默认数据源
	// @Primary
	// @Bean(initMethod = "init", destroyMethod = "close")
	// @ConfigurationProperties(prefix = "spring.druid")
	// public DataSource dataSource() {
	// return new DruidDataSource();
	// }

	/**
	 * druid监控相关配置
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean statViewServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),
				"/druid/*");
		// 添加初始化参数：initParams
		// 白名单：
		servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
		// IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not
		// permitted to view this page.
		servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
		// 登录查看信息的账号密码.
		servletRegistrationBean.addInitParameter("loginUsername", "admin");
		servletRegistrationBean.addInitParameter("loginPassword", "admin");
		// 是否能够重置数据.
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
		
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}

}