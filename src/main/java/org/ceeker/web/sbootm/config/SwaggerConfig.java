package org.ceeker.web.sbootm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.ceeker.web.sbootm"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
    	//2.5.0支持  new Contact("张小玲", "http://weibo.com/p/1005052200755687", "409273291@qq.com")
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("这只是一个demo程序")
                .termsOfServiceUrl("http://weibo.com/p/1005052200755687")
                .contact("夕花朝拾")
                .version("1.0")
                .build();
    }

}