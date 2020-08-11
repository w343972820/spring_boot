package com.vico.xuexi.applications;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//资源文件名及端口号配置法为固定写法
@SpringBootApplication  //标注托管入口类,程序启动类
@ComponentScan("com.vico.xuexi")  //扫描包下的类
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
