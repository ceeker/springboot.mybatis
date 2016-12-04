package org.ceeker.web.sbootm;

import de.codecentric.boot.admin.config.EnableAdminServer;
import lombok.extern.log4j.Log4j;
import org.ceeker.web.sbootm.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

/**
 * Spring boot 的启动程序 所有的组件，比如controller，service，dao都必须位于该类同包或者子包下面 否则将不会被加载
 * 参见：http://spring.io/guides/gs/spring-boot/
 * 
 * @ComponentScan tells Spring to look for other components, configurations, and
 *                services in the hello package, allowing it to find the
 *                HelloController.
 * @author zhangxiaoling01
 * @date 2016年4月26日 下午4:53:53
 * @see
 */
@SpringBootApplication
@EnableAdminServer
@EnableScheduling
@EnableCaching
// @ImportResource(value = { "classpath:config/spring-task.xml" })
// @PropertySources(value = { @PropertySource(value = {
// "classpath:config/application.properties" }, ignoreResourceNotFound = true)
// })
@Log4j
public class Application {


	public static void main(String[] args) throws Exception {
		SpringApplication app = new SpringApplication(Application.class);
		// 设置命令行参数不加入environment中
		app.setAddCommandLineProperties(false);
		// 设置激活何种配置
		app.setAdditionalProfiles();
		ApplicationContext ctx = app.run(args);
		
		//动态控制spring管理的类
		User user = ctx.getBean(User.class);
		user.setId(0);
		// getAllBean(ctx);
	}

	/**
	 * 获取所有被springboot管理的bean
	 * 
	 * @param applicationContext
	 * @throws Exception
	 */
	public void getAllBean(ApplicationContext applicationContext) throws Exception {
		log.info("Let's inspect the beans provided by Spring Boot:");
		String[] beanNames = applicationContext.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			log.info(beanName);
		}
	}

}
