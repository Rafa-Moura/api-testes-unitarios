package br.com.apitestesunitarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.apitestesunitarios"))
                .paths(any())
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Cadastro de usuários",
                "API REST para cadastro de usuários - Estudo de documentação com swagger e testes unitários",
                "1.0",
                "Terms of service",
                new Contact("Rafael Moura",
                        "https://github.com/Rafa-Moura",
                        "rafaelfelipe.peixoto@gmail.com"),
                "Apache License",
                "Https://www.apache.org/licensen.html", new ArrayList<VendorExtension>());

        return apiInfo;

    }
}
