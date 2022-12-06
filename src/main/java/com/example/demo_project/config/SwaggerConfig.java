package com.example.demo_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration	//讓Spring來載入該類別設定
@EnableSwagger2	//啟用Swagger2.createRestApi函數建立Docket的Bean
public class SwaggerConfig {
	
	/**
	 * 建立API的基本資訊(這些基本資訊會顯示在文件葉面)
	 * 造訪網址:http://專案實際IP:port/swagger-ui.html
	 */
	
	public static final ApiInfo DEFAULT_API_INFO = new ApiInfoBuilder()//
			.title("Restful APIs")//
//			.description("RESTful APIs")
//			.termsOfServiceUrl("urn:tos")
			//termsOfServiceUrl("http://localhost:8080/")
//			.contact(new Contact("DEFAULT","",""))
//			.license("Apache 2.0")//
//			.version("1.0")
//			.licenseUrl("")
			.build();
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)//
				.apiInfo(DEFAULT_API_INFO)	//顯示API基本資訊，可不加
				.select()	//回傳一個ApiSelectotBuilder實例，用來控制介面可以給Swagger來展現
				//設定套件掃描路徑
				//Swagger曾掃描套件下所有controller定義的API並產生文件
				//若不想API產生相關文件，可在API加上@ApiIgnore
				.apis(RequestHandlerSelectors.basePackage("com.example.demo_project.controller"))
				.paths(PathSelectors.any())//
				.build();
	}
}
