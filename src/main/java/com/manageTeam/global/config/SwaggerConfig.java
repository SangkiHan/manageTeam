package com.manageTeam.global.config;

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
    Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.manageTeam.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());

    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("manage Team")
                .description("농구팀 체육관 예약 및 대회참가 시스템")
                .version("1.0.0")
                .build();
    }

}
