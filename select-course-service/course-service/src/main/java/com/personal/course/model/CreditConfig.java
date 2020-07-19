package com.personal.course.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author cgc6828
 * @description
 * @date 2020/4/15 10:46
 */
@Data
public class CreditConfig implements Serializable {
    private static final long serialVersionUID = 7775761400024136266L;

    private Long id;
    /**
     * 选修的最低学分
     *
     */
    private Double minCredit;

    /**
     * 可以选修的最高学分
     */
    private Double maxCredit;
}
