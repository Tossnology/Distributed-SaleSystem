package com.software;

import com.software.util.RedisToDBUtil;
import com.software.util.RedisUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties({RedisToDBUtil.class,RedisUtil.class})
public class DisSaleSysApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisSaleSysApplication.class, args);
	}

}
