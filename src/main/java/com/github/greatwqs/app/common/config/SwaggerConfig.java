package com.github.greatwqs.app.common.config;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.greatwqs.app.common.AppConstants;
import com.github.greatwqs.app.utils.collection.Lists;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger configuration.
 * URI: /swagger-ui.html
 * http://127.0.0.1:8080/swagger-ui.html#/
 *
 * @author greatwqs
 * Create on 2020/6/25
 */
@Configuration
public class SwaggerConfig {

	@Value("${swagger.enable}")
	private boolean enableSwagger;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.enable(enableSwagger)
			.apiInfo(apiInfo())
			.alternateTypeRules(AlternateTypeRules.newRule(Map.class, List.class))
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.github.greatwqs.app.controller"))
			.paths(PathSelectors.any())
			.build()
			.securitySchemes(Lists.newArrayList(apiKey()))
			.securityContexts(Lists.newArrayList(securityContext()));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("Demo API")
			.description("Demo API Base on SpringBoot")
			.contact(new Contact(
					"springboot-demo",
					"https://github.com/greatwqs/springboot-demo",
					"greatwqs@gmail.com"))
			.version("1.0")
			.build();
	}

	private ApiKey apiKey() {
		return new ApiKey(AppConstants.APP_LOGIN_TOKEN, AppConstants.APP_LOGIN_TOKEN, "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
			.securityReferences(defaultAuth())
			.forPaths(PathSelectors.regex("^(?!/users/login)(?!/jsp/)(?!/static/).*$"))
			.build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope
			= new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(
			new SecurityReference(AppConstants.APP_LOGIN_TOKEN, authorizationScopes));
	}

}
