package com.personal.course.dao;

import com.personal.course.dto.sys.req.CourseQueryReq;
import com.personal.course.model.Course;

import java.util.List;

/**
 * @author cgc6828
 * @className CourseDao
 * @description
 * @date 2020/4/10 19:01
 */
public interface CourseDao {
    /**
     * 查找所有课程
     * @return 课程列表
     */
     List<Course> selectAllCourse();

    /**
     *根据课程名称查找课程
     * @param courseName 课程名称
     * @return 课程列表
     */
    Course findCourseByCourseName(String courseName);




    /**
     *根据课程Id删除课程
     * @param courseId 课程ID
     * @return 删除的条数；
     */
    int deleteCourseByCourseId(Long courseId);


    /**
     *  根据课程ID 查出课程
     * @param courseId 课程ID
     * @return 课程
     */
    Course findCourseByCourseId(Long courseId);

    /**
     * 根据课程ID更新课程信息
     * @param course 课程ID
     * @return 更新 的条数
     */
    int updateCourseByCourseId(Course course);

    /**
     * 分页查询课程
     * @param courseQueryReq 分页查询的请求类
     * @return 分页查询的课程实体类
     */
    List<Course> listCourse(CourseQueryReq courseQueryReq);

    /**
     * 插入课程
     * @param course 课程实体
     * @return 添加课程的条数
     */
    int  addCourse(Course course);
}
