package com.meow.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        final String swaggerToken = "";
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.meow.controllers"))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(ResponseEntity.class, Void.class)
                .securitySchemes(List.of(apiKey()))
                .apiInfo(apiEndPointsInfo())
                .globalOperationParameters(
                        Collections.singletonList(
                                new ParameterBuilder()
                                        .name("Authorization")
                                        .modelRef(new ModelRef("string"))
                                        .parameterType("header")
                                        .required(true)
                                        .hidden(true)
                                        .defaultValue("Bearer " + swaggerToken)
                                        .build()));
    }
    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }
    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder()
                .title("CRM")
                .description("CRM REST API")
                .contact(new Contact("CRM", "", ""))
                .license("Apache 2.0")
                .termsOfServiceUrl("#")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("0.0.1-SNAPSHOT")
                .build();
    }
}
