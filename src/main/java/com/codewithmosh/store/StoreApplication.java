package com.codewithmosh.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {

//        var context = SpringApplication.run(StoreApplication.class, args);
//        var userMapper = context.getBean(UserMapper.class);
//
//        var user = User.builder()
//                .name("John Doe")
//                .email("john@example.com")
//                .password("password")
//                .build();
//
//        System.out.println(userMapper.toDto(user));
        SpringApplication.run(StoreApplication.class, args);
    }
}
