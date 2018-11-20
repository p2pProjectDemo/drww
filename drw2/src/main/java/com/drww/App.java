package com.drww;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * className:com.drww.App
 * descriptive:
 * author:荣恒
 * createTime:2018/11/20 15:37
 */


@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.drww.dao")
public class App {
	//主函数
	public static void main(String[] args) {
		//SpringApplication 可以读取不同种类的源文件
		SpringApplication.run(App.class);
	}
}

