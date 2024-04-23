package org.commerce.commercesubmit.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * packageName    : org.commerce.commercesubmit.config.swagger
 * fileName       : SwaggerConfig
 * author         : ipeac
 * date           : 24. 4. 23.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 23.        ipeac       최초 생성
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("v1")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfoV1());
    }
    
    private ApiInfo apiInfoV1() {
        return new ApiInfoBuilder()
                .title("Commerce Submit API Version 1")
                .version("v1")
                .description("Commerce Submit API Version 1")
                .build();
    }
}