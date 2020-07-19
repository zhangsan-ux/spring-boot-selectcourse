package com.personal.course.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**


@description 
@author cgc6828
@date 2020/6/16 22:34
*/
@Slf4j
public class MyInit implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("MyInit init==");
    }
}
