package org.beanpod.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/* 用于自动生成在线API文档 */
@Configuration
@EnableSwagger2
public class ServerSwagger {
	
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.beanpod.auth"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("鉴权服务器RESTful APIs")
                .description("更多描述文字")
                .termsOfServiceUrl("http://docs.beanpod.org/")
                .contact("www.beanpod.org")
                .version("1.0")
                .build();
    }
}
