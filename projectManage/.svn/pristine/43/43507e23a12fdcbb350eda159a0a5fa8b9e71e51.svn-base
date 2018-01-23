package com.linkGap.projectManage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@MapperScan("com.linkGap.projectManage.dao")
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
//@EnableRedisHttpSession
//@ServletComponentScan
@EnableAsync
public class ProjectManageApplication {
	
	public static void main(String[] args) {
		//禁止命令行设置参数
		SpringApplication springApplication = new SpringApplication(ProjectManageApplication.class);
        //禁止命令行设置参数
        springApplication.setAddCommandLineProperties(true);
        springApplication.run(args);
		//SpringApplication.run(projectManageApplication.class, args);
		System.out.println("-------------------project projectManage start success-----------------------------------");
	}
}
