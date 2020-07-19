package com.personal.course.dao;

import com.personal.course.model.CreditConfig;

/**


@description 
@author cgc6828
@date 2020/4/15 10:56
*/
public interface CreditConfigDao {
    /**
     * 根据id查找最低，最高可以选的学分
     * @return  最低，最高可以选的学分
     */
    CreditConfig selectById();
}
