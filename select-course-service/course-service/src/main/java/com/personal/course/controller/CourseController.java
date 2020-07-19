package com.personal.course.controller;

import com.personal.common.util.FastJsonUtil;
import com.personal.core.enums.ResultEnum;
import com.personal.core.util.ResultUtil;
import com.personal.course.annotation.PermissionCheck;
import com.personal.course.dto.CourseDTO;
import com.personal.course.dto.sys.req.CourseQueryReq;
import com.personal.course.dto.sys.req.SaveCourseReq;
import com.personal.course.model.Course;
import com.personal.course.service.CourseService;
import com.personal.course.util.ViewUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

import static com.personal.course.constant.UserConstant.MIN_CREDIT;

/**
 * @author cgc6828
 * @className CourseController
 * @description
 * @date 2020/4/10 18:44
 */
@RestController
@RequestMapping("/course")
@Slf4j
public class CourseController {

    @Resource
    private CourseService courseService;

    @PermissionCheck(value = {"sys:course:list"})
    @PostMapping("/listCoursePage")
    public Object listStudentPage(@Valid @RequestBody CourseQueryReq courseQueryReq) {
        log.info("获取用户分页列表参数:{}", FastJsonUtil.toJson(courseQueryReq));

        return ViewUtil.getPageInfo(this.courseService.listCourse(courseQueryReq));
    }

    @PermissionCheck(value = {"sys:course:add", "sys:course:edit"})
    @PostMapping("/saveCourse")
    public Object saveCourse(@RequestBody @Valid SaveCourseReq saveCourseReq) {
        log.info("获取 保存参数:{}", FastJsonUtil.toJson(saveCourseReq));

        if (saveCourseReq.getCourseCredit() < MIN_CREDIT) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "课程最低学分是0.5！");
        }
        if (StringUtils.isEmpty(saveCourseReq.getCourseName())) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "课程名字不可以为空！");
        }
        Course courseTable = courseService.findCourseByCourseName(saveCourseReq.getCourseName());
        if (saveCourseReq.getCourseId() == null && courseTable != null) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "课程名称重复！");
        }
        Course course = new Course();
        course.setCourseName(saveCourseReq.getCourseName());
        course.setUpdateBy(saveCourseReq.getUpdateBy());
        course.setUpdateTime(new Date());
        course.setCourseCredit(saveCourseReq.getCourseCredit());
        if (saveCourseReq.getCourseId() == null) {
            course.setCreateBy(saveCourseReq.getCreateBy());
            course.setCreateTime(new Date());
            courseService.addCourse(course);
            return ResultUtil.getResult(ResultEnum.SUCCESS);
        }
        course.setCourseId(saveCourseReq.getCourseId());
        courseService.updateCourseByCourseId(course);
        return ResultUtil.getResult(ResultEnum.SUCCESS);
    }

    @PermissionCheck(value = {"sys:course:delete"})
    @GetMapping("/deleteCourseByCourseId")
    public Object deleteCourseByCourseId(Long courseId) {
        log.info("请求的CourseId : {}", courseId);
        if (courseId == null || courseId < 0) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR, "课程ID 非法！");
        }
        Course course = courseService.findCourseByCourseId(courseId);
        if (course == null) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR, "课程ID 不存在！");
        }
        courseService.deleteCourseByCourseId(courseId);
        return ResultUtil.getResult(ResultEnum.SUCCESS);
    }

    @PermissionCheck(value = {"sys:course:edit"})
    @PostMapping("/updateCourseByCourseId")
    public Object updateCourseByCourseId(@RequestBody @Valid CourseDTO courseDTO) {
        log.info("请求的参数是:{}", courseDTO);
        Course courseUpdate = new Course();
        courseUpdate.setCourseId(courseDTO.getCourseId());
        courseUpdate.setCourseName(courseDTO.getCourseName());
        courseUpdate.setCourseCredit(courseDTO.getCourseCredit());
        courseUpdate.setCreateBy(courseDTO.getCreateBy());
        courseUpdate.setUpdateBy(courseDTO.getUpdateBy());

        courseService.updateCourseByCourseId(courseUpdate);
        return ResultUtil.getResult(ResultEnum.SUCCESS);
    }

    @PermissionCheck(value = {"sys:course:add", "sys:course:edit"})
    @GetMapping("/findCourseByCourseId")
    public Object findCourseByCourseId(Long courseId) {
        if (courseId == null || courseId < 0) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "课程ID非法！");
        }
        Course course = courseService.findCourseByCourseId(courseId);
        if (course == null) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "该课程不存在");
        }

        return ResultUtil.getSuccessResult(course);

    }

}
