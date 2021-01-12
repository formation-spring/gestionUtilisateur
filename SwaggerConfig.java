package com.formation.formationspring.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {
public static final String AUTHORIZATION_HEADER = "Authorization";
@Bean
    public Docket api(){
    return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage("com.formation.formationspring.controllers")).build().apiInfo(metaData());

}
    private ApiInfo metaData() {
        return new ApiInfo("Data Protection Tool",
                "Documentation of the Data ",
                "1.0", StringUtils.EMPTY, new Contact(StringUtils.EMPTY, StringUtils.EMPTY, ""),
                StringUtils.EMPTY, StringUtils.EMPTY, new ArrayList<>());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        //enabling swagger-ui part for visual documentation
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}
