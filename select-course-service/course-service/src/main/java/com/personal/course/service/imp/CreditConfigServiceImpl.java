package com.personal.course.service.imp;

import com.personal.course.dao.CreditConfigDao;
import com.personal.course.model.CreditConfig;
import com.personal.course.service.CreditConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.module.Configuration;

/**


@description 
@author cgc6828
@date 2020/4/15 10:57
*/
@Service
public class CreditConfigServiceImpl implements CreditConfigService {


    @Resource
    private CreditConfigDao creditConfigDao;
    @Override
    public CreditConfig selectById() {
        return creditConfigDao.selectById();
    }
}
