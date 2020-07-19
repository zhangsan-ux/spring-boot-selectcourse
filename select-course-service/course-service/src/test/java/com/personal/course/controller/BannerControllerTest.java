package com.personal.course.controller;

import com.personal.course.model.Banner;
import com.personal.course.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author cgc6828
 * @description
 * @date 2020/6/27 10:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BannerControllerTest {

    @Resource
    private BannerService bannerService;

    @Test
    public void saveBanner() {
    }

    @Test
    public void saveBannerPath() {
    }

    @Test
    public void selectBannerById () {
        Integer bannerId= 5;
        Banner banner = bannerService.selectBannerByBannerId(bannerId);
        log.info("banner是 ={}",banner);

    }
}