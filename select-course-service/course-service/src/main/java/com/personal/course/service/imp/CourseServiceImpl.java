package com.personal.course.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.personal.course.dao.CourseDao;
import com.personal.course.dto.sys.req.CourseQueryReq;
import com.personal.course.model.Course;
import com.personal.course.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cgc6828
 * @className CourseServiceImpl
 * @description
 * @date 2020/4/10 19:12
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseDao courseDao;

    @Override
    public List<Course> selectAllCourse() {
        return courseDao.selectAllCourse();
    }

    @Override
    public Course findCourseByCourseName(String courseName) {
        return courseDao.findCourseByCourseName(courseName);
    }

    @Override
    public int deleteCourseByCourseId(Long courseId) {
        return courseDao.deleteCourseByCourseId(courseId);
    }

    @Override
    public Course findCourseByCourseId(Long courseId) {
        return courseDao.findCourseByCourseId(courseId);
    }

    @Override
    public int updateCourseByCourseId(Course course) {
        return courseDao.updateCourseByCourseId(course);
    }

    @Override
    public PageInfo<Course> listCourse(CourseQueryReq courseQueryReq) {
        PageHelper.startPage(courseQueryReq.getPageNum(), courseQueryReq.getPageSize());
        return new PageInfo<>(this.courseDao.listCourse(courseQueryReq));
    }

    @Override
    public int addCourse(Course course) {
        return courseDao.addCourse(course);
    }
}
