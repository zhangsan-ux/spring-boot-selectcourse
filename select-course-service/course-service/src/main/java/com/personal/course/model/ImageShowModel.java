package com.personal.course.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author cgc6828
 * @description
 * @date 2020/6/28 22:26
 */
@Data
public class ImageShowModel implements Serializable {
    private static final long serialVersionUID = 2278040429621086167L;

    private Integer type;


    private List<Map<String, Object>> bannerList;
}
