package com.personal.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 说明
 *
 * @author cgc 6828
 * @date 2020/4/18 11:39
 */
@SpringBootApplication
@MapperScan( "com.personal.course.dao")
public class CourseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseServiceApplication.class, args);
    }

}
