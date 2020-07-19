package com.personal.course.util;

import com.github.pagehelper.PageInfo;
import com.personal.core.util.ResultUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 说明
 *
 * @author cgc 6828
 * @date 2020/4/20 19:27
 */
public class ViewUtil {

    public static Object getPageInfo(PageInfo<?> pageInfo) {

        Map<String, Object> map = new HashMap<>(8);
        map.put("rows", pageInfo.getList());
        map.put("total", pageInfo.getTotal());
        map.put("pageNum", pageInfo.getPageNum());
        map.put("pageSize", pageInfo.getPageSize());
        return ResultUtil.getSuccessResult(map);
    }


    public static Object getPageInfo(List<?> list, long total, long pageNum, long pageSize) {

        Map<String, Object> map = new HashMap<>(8);
        map.put("rows", list);
        map.put("total", total);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        return ResultUtil.getSuccessResult(map);
    }
}
