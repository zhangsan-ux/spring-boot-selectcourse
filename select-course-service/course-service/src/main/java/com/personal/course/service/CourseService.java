package com.personal.course.service;

import com.github.pagehelper.PageInfo;
import com.personal.course.dto.sys.req.CourseQueryReq;
import com.personal.course.model.Course;

import java.util.List;

/**
 * @author cgc6828
 * @className CourseService
 * @description 课程服务层
 * @date 2020/4/10 19:10
 */

public interface CourseService {

    /**
     * 查出所有课程
     * @return
     */
    List<Course> selectAllCourse();

    /**
     * 根据课程名称查找课程
     * @param courseName  课程名称
     * @return 课程实信息
     */
    Course findCourseByCourseName(String courseName);

    /**
     * 根据CourseId删除课程
     * @param courseId 课程ID
     * @return 删除的条数
     */
    int deleteCourseByCourseId(Long courseId);

    /**
     * 根据courseId查找课程
     * @param courseId 课程ID
     * @return 课程
     */
    Course findCourseByCourseId(Long courseId);

    /**
     * 根据课程ID更新课程
     *
     * @param course 课程
     * @return 更新的条数
     */
    int updateCourseByCourseId(Course course);

    /**
     * 分页查询
     *
     * @param courseQueryReq
     * @return
     */
    PageInfo<Course> listCourse(CourseQueryReq courseQueryReq);

    /**
     * 插入课程
     *
     * @param course 课程实体类
     * @return
     */
    int addCourse(Course course);
}

