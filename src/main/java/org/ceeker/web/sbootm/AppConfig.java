package org.ceeker.web.sbootm;

import java.util.Properties;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.github.pagehelper.PageHelper;

/**
 * Spring boot 配置类
 * @author zhangxiaoling01
 * @date  2016年5月5日 下午2:54:25
 * @see
 */
@Configuration
@MapperScan("org.ceeker.web.sbootm.entity.mapper")
//注解事务
@EnableTransactionManagement
//@PropertySources({ @PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true), @PropertySource(value = "file:./application.properties", ignoreResourceNotFound = true) })
public class AppConfig {

    @Value("${name:}")
    private String name;

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
     * @return
     */
    //    @Bean(destroyMethod = "close")
    //    public DataSource dataSource() {
    //        HikariConfig hikariConfig = new HikariConfig();
    //        hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
    //        hikariConfig.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/springboot?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&zeroDateTimeBehavior=convertToNull");
    //        hikariConfig.setUsername("root");
    //        hikariConfig.setPassword("root");
    //        hikariConfig.setPoolName("springHikariCP");
    //        //关掉自动提交.否则事务部生效
    //        hikariConfig.setAutoCommit(false);
    //
    //        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
    //        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
    //        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    //        hikariConfig.addDataSourceProperty("useServerPrepStmts", "true");
    //
    //        hikariConfig.setMinimumIdle(20);
    //        hikariConfig.setMaximumPoolSize(20);
    //        hikariConfig.setConnectionInitSql("SELECT 1");
    //
    //        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
    //        return dataSource;
    //    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    //    @Primary //默认数据源 
    //    @Bean(destroyMethod = "close")
    //    @ConfigurationProperties(prefix = "spring.druid")
    //    public DataSource dataSource() {
    //        return DataSourceBuilder.create().build();
    //    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setTypeAliasesPackage("org.ceeker.web.sbootm");
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        //添加插件
        sessionFactory.setPlugins(new Interceptor[] { pageHelper });
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
            return sessionFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Primary //默认数据源 
    @Bean(initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.druid")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Value("${spring.druid.allow}")
    private String druidAllowUrl;

    @Value("${spring.druid.deny}")
    private String druidDenyUrl;

    /**
     * druid监控地址
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        reg.addInitParameter("allow", druidAllowUrl);
        reg.addInitParameter("deny", druidDenyUrl);
        return reg;
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