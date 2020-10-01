package com.example.study.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *swagger的配置类
 * @author 沉香微风
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.study.core.controller"))
                .paths(PathSelectors.any()).build();
    }
    @Deprecated
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("com.example.study")
                .description("相关描述")
                .termsOfServiceUrl("http:......")
                .contact("沉香微风")
                .version("1.0")
                .build();
    }


}
