//package com.example.my_blog.config;
//
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Info;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@OpenAPIDefinition(
//    info = @Info(title="my_blog API",
//        description = "my_blog REST API 명세서",
//        version = "v1")
//)
//@RequiredArgsConstructor
//@Configuration
//public class SwaggerConfig {
//    private static final String API_NAME = "my_blog API";
//    private static final String API_DESCRIPTION = "my_blog Rest API 명세서";
//
//    @Bean
//    public ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title(API_NAME)
//                .description(API_DESCRIPTION)
//                .contact(new Contact(
//                        "[my_blog]",
//                        "https://github.com/holyPigeon/my_blog",
//                        "awfjol2008@gmail.com"
//                ))
//                .build();
//    }
//
//    @Bean
//    public Docket swagger() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.my_blog.controller"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//}
