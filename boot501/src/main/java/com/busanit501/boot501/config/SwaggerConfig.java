package com.busanit501.boot501.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.eclipse.jdt.internal.compiler.problem.ProblemSeverities.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("레스트 API 테스트")
                        .description("Rest 활용해서 댓글도 구현해보기")
                        .version("1.0.0")
                );
    }

}
