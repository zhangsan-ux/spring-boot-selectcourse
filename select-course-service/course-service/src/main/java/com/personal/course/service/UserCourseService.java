package com.personal.course.service;

import com.personal.course.bo.StudentInfo;
import com.personal.course.model.Course;
import com.personal.course.model.UserCourse;

import java.util.List;

/**
 * @author cgc6828
 * @description
 * @date 2020/4/14 19:18
 */
public interface UserCourseService {


    /**
     * 更据课程ID集合查出课程
     *
     * @param courseIds 课程ID集合
     * @return
     */
    List<Course> listCourseByIds(List<Long> courseIds);

    /**
     * 根据userId 查出已经选的课程
     *
     * @param userId 用户Id
     * @return
     */
    List<Course> listCourseByUserId(Long userId);

    /**
     * 根据课程ID集合查出 课程集合
     *
     * @param courseIds 课程ID集合
     * @return 课程集合
     */
    List<Course> listCourseByCourseIds(List<Long> courseIds);

    /**
     * 根据 UserCourse 集合插入课程
     *
     * @param userCourseList 实体类集合
     * @return
     */
    int insertChooseCourse(List<UserCourse> userCourseList);

    /**
     * 根据userId 查出已经选好的课程
     *
     * @param userId 用户Id
     * @return 已经选好的课程
     */
    List<Course> showChooseCourseByUserId(Long userId);

    /**
     * 根据课程Id 用户Id删除课程
     *
     * @param courseIds 课程id list
     * @return userId 用户id
     */
    /**
     *   根据课程Id 用户Id删除课程
     * @param courseIds 课程id list
     * @param userId 用户Id
     * @return 删除条数
     */
    int deleteCourseByCourseIdAndUserId(List<Long> courseIds, Long userId);

    /**
     * 根据UserId查出详细用户信息
     *
     * @param userId 用户Id
     * @return
     */
    StudentInfo selectStudentInfoByUserId(Long userId);
}
