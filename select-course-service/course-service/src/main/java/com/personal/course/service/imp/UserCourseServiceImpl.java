package com.personal.course.service.imp;

import com.personal.course.bo.CourseIsExistBO;
import com.personal.course.bo.StudentInfo;
import com.personal.course.dao.UserCourseDao;
import com.personal.course.dto.sys.req.DeleteCourseReq;
import com.personal.course.model.Course;
import com.personal.course.model.DeleteCourseByStudent;
import com.personal.course.model.UserCourse;
import com.personal.course.service.UserCourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cgc6828
 * @description
 * @date 2020/4/14 19:18
 */
@Service
public class UserCourseServiceImpl implements UserCourseService {


    @Resource
    private UserCourseDao userCourseDao;

    @Override
    public List<Course> listCourseByIds(List<Long> courseIds) {
        return userCourseDao.listCourseByIds(courseIds);
    }

    @Override
    public List<Course> listCourseByUserId(Long userId) {
        return userCourseDao.listCourseByUserId(userId);
    }

    @Override
    public List<Course> listCourseByCourseIds(List<Long> courseIds) {
        return userCourseDao.listCourseByCourseIds(courseIds);
    }

    @Override
    public int insertChooseCourse(List<UserCourse> userCourseList) {
        return userCourseDao.insertChooseCourse(userCourseList);
    }

    @Override
    public List<Course> showChooseCourseByUserId(Long userId) {
        return userCourseDao.showChooseCourseByUserId(userId);
    }

    @Override
    public int deleteCourseByCourseIdAndUserId(List<Long> courseIds, Long userId) {
        return userCourseDao.deleteCourseByCourseIdAndUserId(courseIds, userId);
    }

    @Override
    public StudentInfo selectStudentInfoByUserId(Long userId) {
        return userCourseDao.selectStudentInfoByUserId(userId);
    }
}
