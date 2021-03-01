package com.cqj.project;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.annotation.Resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;
import com.cqj.project.config.AppConfig;

import io.swagger.annotations.Api;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan(value = "com.cqj.project.dao.mapper")
@SpringBootApplication
@EnableSwagger2
@EnableScheduling
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class ErrorManageSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErrorManageSystemApplication.class, args);
	}
	
    @Autowired
    private TypeResolver typeResolver;

    @Resource
    private AppConfig appConfig;

    /**
     * 生成API文档的入口
     */
    @Bean
    public Docket generateApi() {
        Docket docket = null;
        // 可以根据配置决定不做任何API生成
        if (!appConfig.getAllowGenerateApi()) {
            docket = new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.none())
                    .build();
            return docket;
        }

        docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 标示只有被 @Api 标注的才能生成API.
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any()).build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                // 遇到 LocalDate时，输出成String
                .genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(
                        newRule(
                                typeResolver.resolve(DeferredResult.class,
                                        typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                                typeResolver.resolve(WildcardType.class)))
                .useDefaultResponseMessages(false);


        return docket;
    }

    private ApiInfo apiInfo() {
        //Contact contact = new Contact("", "", "");
        return new ApiInfo("错误管理系统操作接口", "错误管理系统操作接口WEB/APP", "1.0.0", "", "", "", "");
        
    }
    
 

}
