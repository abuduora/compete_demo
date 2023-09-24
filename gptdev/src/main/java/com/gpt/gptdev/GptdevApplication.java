package com.gpt.gptdev;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.gpt"})
@MapperScan("com.gpt.gptdev.mapper")
@ServletComponentScan
public class GptdevApplication {
	public static void main(String[] args) {
		SpringApplication.run(GptdevApplication.class, args);
	}
}
