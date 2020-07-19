package com.personal.course.dao;

import com.personal.course.bo.StudentInfo;
import com.personal.course.model.Course;
import com.personal.course.model.DeleteCourseByStudent;
import com.personal.course.model.UserCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cgc6828
 * @description
 * @date 2020/4/14 19:17
 */
public interface UserCourseDao {


    /**
     * 插入选中的课程
     *
     * @param userCourseList userCourseList集合
     * @return
     */
    int insertChooseCourse(@Param("userCourseList") List<UserCourse> userCourseList);

    /**
     * 更据课程ID集合查出课程
     *
     * @param courseIds 课程ID集合
     * @return
     */
    List<Course> listCourseByIds(@Param("courseIds") List<Long> courseIds);

    /**
     * 根据 用户ID 查出已经选好的课程
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
    List<Course> listCourseByCourseIds(@Param("list") List<Long> courseIds);

    /**
     * 根据userId 查出已经选好的课程
     *
     * @param userId 用户Id
     * @return 已经选好的课程
     */
    List<Course> showChooseCourseByUserId(@Param("userId") Long userId);



    /**
     *  根据课程Id 用户Id删除课程
      * @param courseIds 课程id
     * @param userId 用户Id
     * @return 删除的条数
     */
    int deleteCourseByCourseIdAndUserId(@Param(value = "courseIds") List<Long> courseIds, @Param(value = "userId") Long userId);

    /**
     * 根据UserId查出详细用户信息
     *
     * @param userId 用户Id
     * @return 学生个人信息
     */
    StudentInfo selectStudentInfoByUserId(Long userId);
}
