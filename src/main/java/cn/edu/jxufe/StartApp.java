package cn.edu.jxufe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;

@SpringBootApplication
public class StartApp {
    public static void main(String[] args) {
        System.out.println("网上商城服务正在启动！！！");
        SpringApplication.run(StartApp.class);
        System.out.println("商城服务启动成功！！！");
    }
}
