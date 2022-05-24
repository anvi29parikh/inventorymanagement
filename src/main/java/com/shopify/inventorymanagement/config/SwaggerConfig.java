package com.shopify.inventorymanagement.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shopify.inventorymanagement.enums.SwaggerEnum;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <h1>SwaggerConfig</h1>
 * <p>
 * This SwaggerConfig will be used to configure swagger ui
 * </p>
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
      .title(SwaggerEnum.API_TITLE.getValue())
      .description(SwaggerEnum.API_DESCRIPTION.getValue())
      .build();
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage(SwaggerEnum.CONTROLLER_BASE_PACKAGE.getValue()))
      .paths(regex("/.*"))
      .build()
      .enable(true)
      .apiInfo(apiInfo());
  }

}