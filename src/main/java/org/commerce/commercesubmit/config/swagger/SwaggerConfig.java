package org.commerce.commercesubmit.config.swagger;

import com.fasterxml.classmate.TypeResolver;
import lombok.RequiredArgsConstructor;
import org.commerce.commercesubmit.common.swagger.Page;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

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
@Profile(value = "!prod")
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    private final TypeResolver typeResolver;
    
    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.OAS_30)
                .alternateTypeRules(
                        AlternateTypeRules.newRule(
                                typeResolver.resolve(Pageable.class),
                                typeResolver.resolve(Page.class)
                        )
                )
                .groupName("v1")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .globalResponses(HttpMethod.GET, responseMessages())
                .globalResponses(HttpMethod.POST, responseMessages())
                .globalResponses(HttpMethod.PUT, responseMessages())
                .globalResponses(HttpMethod.DELETE, responseMessages())
                .globalResponses(HttpMethod.PATCH, responseMessages())
                .apiInfo(apiInfoV1());
    }
    
    private ApiInfo apiInfoV1() {
        return new ApiInfoBuilder()
                .title("Commerce Submit API Version 1")
                .version("v1")
                .description("Commerce Submit API Version 1")
                .build();
    }
    
    private List<Response> responseMessages() {
        return Arrays.asList(
                new ResponseBuilder().code("200").description("Ok").build(),
                new ResponseBuilder().code("201").description("Created").build(),
                new ResponseBuilder().code("400").description("잘못된 요청").build(),
                new ResponseBuilder().code("401").description("인증 오류").build(),
                new ResponseBuilder().code("403").description("Forbidden").build(),
                new ResponseBuilder().code("404").description("데이터를 찾을 수 없음").build(),
                new ResponseBuilder().code("500").description("내부 서버 에러").build()
        );
    }
}