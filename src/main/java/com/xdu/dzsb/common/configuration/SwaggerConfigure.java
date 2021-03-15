package com.xdu.dzsb.common.configuration;


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
public class SwaggerConfigure {
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("智识体动后端平台API")
                .description("包含小程序及APP部分")
                .termsOfServiceUrl("https://goldenpigeon.top")
                .version("0.0.4")
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xdu.dzsb.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
