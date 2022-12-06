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

@Configuration	//��Spring�Ӹ��J�����O�]�w
@EnableSwagger2	//�ҥ�Swagger2.createRestApi��ƫإ�Docket��Bean
public class SwaggerConfig {
	
	/**
	 * �إ�API���򥻸�T(�o�ǰ򥻸�T�|��ܦb��󸭭�)
	 * �y�X���}:http://�M�׹��IP:port/swagger-ui.html
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
				.apiInfo(DEFAULT_API_INFO)	//���API�򥻸�T�A�i���[
				.select()	//�^�Ǥ@��ApiSelectotBuilder��ҡA�Ψӱ�����i�H��Swagger�Ӯi�{
				//�]�w�M�󱽴y���|
				//Swagger�����y�M��U�Ҧ�controller�w�q��API�ò��ͤ��
				//�Y���QAPI���ͬ������A�i�bAPI�[�W@ApiIgnore
				.apis(RequestHandlerSelectors.basePackage("com.example.demo_project.controller"))
				.paths(PathSelectors.any())//
				.build();
	}
}
