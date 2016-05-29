package org.ceeker.web.sbootm;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;

import de.codecentric.boot.admin.config.EnableAdminServer;

/**
 * Spring boot 的启动程序
 * 所有的组件，比如controller，service，dao都必须位于该类同包或者子包下面
 * 否则将不会被加载
 * 参见：http://spring.io/guides/gs/spring-boot/
 * @ComponentScan tells Spring to look for other components, configurations,
 *  and services in the hello package, allowing it to find the HelloController.
 * @author zhangxiaoling01
 * @date  2016年4月26日 下午4:53:53
 * @see
 */
@SpringBootApplication
@EnableAdminServer
@EnableScheduling
//@ImportResource(value = { "classpath:config/spring-task.xml" })
@PropertySources(value = { @PropertySource(value = { "classpath:config/application.properties" }, ignoreResourceNotFound = true) })
public class AppStart extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppStart.class);
    }

    public static void main(String[] args) throws Exception {
        //        SpringApplication.run(AppStart.class, args);
        SpringApplication app = new SpringApplication(AppStart.class);
        app.setAdditionalProfiles();
        //                app.setBannerMode(Banner.Mode.LOG);
        ApplicationContext ctx = app.run(args);
        //        getAllBean(ctx);
    }

    /**
     * 获取所有被springboot管理的bean
     * @param applicationContext
     * @throws Exception
     */
    public static void getAllBean(ApplicationContext applicationContext) throws Exception {
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

}
