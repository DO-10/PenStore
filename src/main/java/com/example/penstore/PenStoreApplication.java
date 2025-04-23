package com.example.penstore;


//import com.baomidatis.spring.annotation.MapperScan;

//import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class PenStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PenStoreApplication.class, args);
    }

}
//@SpringBootApplication(exclude = {
//        MybatisPlusAutoConfiguration.class
//})
//public class PenStoreApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(PenStoreApplication.class, args);
//    }
//}
//@SpringBootApplication(exclude = {
//        DataSourceAutoConfiguration.class,
//        MybatisPlusAutoConfiguration.class
//})
//public class PenStoreApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(PenStoreApplication.class, args);
//    }
//}