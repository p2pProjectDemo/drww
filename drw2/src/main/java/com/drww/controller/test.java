package com.drww.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * className:test
 * descriptive:
 * author:荣恒
 * createTime:2018/11/20 17:00
 */

@Controller
public class test {

	@RequestMapping(value = "test1")
	public String test1(){
		System.out.println("途径此处");
		System.out.println("构建完成");
		System.out.println("构建完成1");
		return "user";
	}
}
