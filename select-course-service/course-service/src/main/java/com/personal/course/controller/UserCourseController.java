package com.personal.course.controller;

import com.personal.common.util.FastJsonUtil;
import com.personal.core.enums.ResultEnum;
import com.personal.core.exception.BusinessException;
import com.personal.core.util.ResultUtil;
import com.personal.course.annotation.PermissionCheck;
import com.personal.course.bo.StudentInfo;
import com.personal.course.constant.UserConstant;
import com.personal.course.dto.UserTokenDTO;
import com.personal.course.dto.sys.req.DeleteCourseReq;
import com.personal.course.dto.sys.req.UserCourseDTO;
import com.personal.course.model.Course;
import com.personal.course.model.CreditConfig;
import com.personal.course.model.UserCourse;
import com.personal.course.service.CreditConfigService;
import com.personal.course.service.UserCourseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cgc6828
 * @description
 * @date 2020/4/14 20:25
 */
@RestController
@RequestMapping("/userCourse")
@Slf4j
public class UserCourseController extends BaseController {


    @Resource
    private UserCourseService userCourseService;

    @Resource
    private CreditConfigService creditConfigService;
    @Value("${config.image.prefix}")
    private String configImagePrefix;

   @PermissionCheck(value = {"sys:choosecourse:insert","sys:choosecourse:list"})
    @PostMapping("/insertChooseCourse")
    public Object insertChooseCourse(@RequestBody UserCourseDTO userCourseDTO) {
       if (userCourseDTO.getCourseIds().size() == 0 || userCourseDTO.getCourseIds() == null) {
           return ResultUtil.getResult(ResultEnum.PARAMETER_RESOLVE_FAIL.getCode(), "courseId不可以为空");
       }
        log.info("请求的userCourseDTO是 ： {}", FastJsonUtil.toJson(userCourseDTO));
        UserTokenDTO userTokenDTO = this.getLoginUserCourse();
        log.info("请求的useId是 ： {}", userTokenDTO.getUserId());

        //根据传入的IDs 查出勾选的courseId 是否存在
        List<Course> listCourseIsExist = userCourseService.listCourseByIds(userCourseDTO.getCourseIds());
        if (listCourseIsExist.size() != userCourseDTO.getCourseIds().size()) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_RESOLVE_FAIL.getCode(), "courseId非法");
        }
        //根据学生ID 查出已经选过的课程：
        List<Course> courseList = this.userCourseService.listCourseByUserId(userTokenDTO.getUserId());
        //1根据前端传来的courseIds 查找课程
        List<Course> listCourse = userCourseService.listCourseByCourseIds(userCourseDTO.getCourseIds());
        //判断是否选了重复了的课程
        if (courseList.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Course course : courseList) {
                //1.重复
                if (userCourseDTO.getCourseIds().contains(course.getCourseId())) {
                    stringBuilder.append("你已重复选了courseId:[").append(course.getCourseId()).append("]  \n");
                }
            }
            if (stringBuilder.length() > 0) {
                throw new BusinessException(ResultEnum.PARAMETER_ERROR.getCode(), stringBuilder.toString());
            }
            //2.不重复
            //选了的课加起来多少分
            List<Double> courseCreditList = new ArrayList<>(courseList.size());
            for (Course courseCredit : courseList) {
                courseCreditList.add(courseCredit.getCourseCredit());
            }
            Double sumRepeatCredit = 0D;
            for (Double courseCredit : courseCreditList) {
                sumRepeatCredit += courseCredit;
            }
            //前端选的课多少分
            //1根据前端传来的courseIds 查找课程
            List<Double> courseCreditListChoose = new ArrayList<>();
            for (Course courseCredit : listCourse) {
                courseCreditListChoose.add(courseCredit.getCourseCredit());
            }
            Double remainCredit = 0D;
            for (Double courseCredit : courseCreditListChoose) {
                remainCredit += courseCredit;
            }
            Double sum = sumRepeatCredit + remainCredit;
            CreditConfig creditConfig = creditConfigService.selectById();
            if (sum > creditConfig.getMaxCredit()) {
                StringBuilder stringBuilder1 = new StringBuilder();
                String stringBuilderToString = stringBuilder1.append("总学分已经超过").append(creditConfig.getMaxCredit()).append("分啦").toString();
                throw new BusinessException(ResultEnum.PARAMETER_ERROR.getCode(), stringBuilderToString);
            }
            if (sum < creditConfig.getMinCredit()) {
                StringBuilder stringBuilder2 = new StringBuilder();
                String stringBuilderToString = stringBuilder2.append("总学分至少超过").append(creditConfig.getMinCredit()).append("分").toString();
                throw new BusinessException(ResultEnum.PARAMETER_ERROR.getCode(), stringBuilderToString);
            }
            List<UserCourse> userCourseList = new ArrayList<>();
            for (Course userCourseExist : listCourseIsExist) {
                UserCourse userCourse = new UserCourse();
                userCourse.setCourseId(userCourseExist.getCourseId());
                userCourse.setUserId(userTokenDTO.getUserId());
                userCourseList.add(userCourse);
            }
            if (userCourseList.size() > 0) {
                //插入
                this.userCourseService.insertChooseCourse(userCourseList);
            }
            return ResultUtil.getResult(ResultEnum.SUCCESS);
        }
        //未选任何课
        //前端选的课多少分
        List<Double> remainCourseCreditListChoose = new ArrayList<>();
        for (Course courseCredit : listCourse) {
            remainCourseCreditListChoose.add(courseCredit.getCourseCredit());
        }
        Double remainSumCredit = 0D;
        for (Double courseCredit : remainCourseCreditListChoose) {
            remainSumCredit += courseCredit;
        }
        CreditConfig creditConfig = creditConfigService.selectById();
        if (remainSumCredit > creditConfig.getMaxCredit()) {
            throw new BusinessException(ResultEnum.PARAMETER_ERROR.getCode(), "总学分已经超过" + creditConfig.getMaxCredit() + "分啦");
        }
        if (remainSumCredit < creditConfig.getMinCredit()) {
            String stringBuilderToString = "总学分至少超过" + creditConfig.getMinCredit() + "分";
            throw new BusinessException(ResultEnum.PARAMETER_ERROR.getCode(), stringBuilderToString);
        }
        //之前未选有课 直接插入
        List<UserCourse> userCourseList = new ArrayList<>();
        for (Course userCourseExist : listCourseIsExist) {
            UserCourse userCourse = new UserCourse();
            userCourse.setCourseId(userCourseExist.getCourseId());
            userCourse.setUserId(userTokenDTO.getUserId());
            userCourseList.add(userCourse);
        }
        //插入
        this.userCourseService.insertChooseCourse(userCourseList);
        return ResultUtil.getResult(ResultEnum.SUCCESS);
    }

    /**
     * 学生删除课程
     *
     * @return
     */
    @PermissionCheck(value = {"sys:choosecourse:remove","sys:choosecourse:list"})
    @PostMapping("/deleteCourseByCourseIdAndUserId")
    public Object deleteCourseByCourseIdAndUserId(@RequestBody DeleteCourseReq deleteCourseReq) {
        log.info("接收的参数为:deleteCourseReq={}", FastJsonUtil.toJson(deleteCourseReq));
        UserTokenDTO userTokenDTO = this.getLoginUserCourse();
        log.info("请求的UserId 是:{}", FastJsonUtil.toJson(deleteCourseReq.getUserId()));
        if (CollectionUtils.isEmpty(deleteCourseReq.getCourseIds())) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_RESOLVE_FAIL.getCode(), "至少选择一项");
        }
        //根据传入的IDs 查出勾选的courseId 是否存在
        List<Course> listCourseIsExist = userCourseService.listCourseByIds(deleteCourseReq.getCourseIds());
        if (listCourseIsExist.size() != deleteCourseReq.getCourseIds().size()) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_RESOLVE_FAIL.getCode(), "courseId非法");
        }

        this.userCourseService.deleteCourseByCourseIdAndUserId(deleteCourseReq.getCourseIds(), userTokenDTO.getUserId());

        return ResultUtil.getResult(ResultEnum.SUCCESS);
    }

    /**
     * 根据UserId 查出已经选的课程
     *
     * @return
     */
    @PermissionCheck(value = {"sys:choosecourse:have","sys:choosecourse:list"})
    @PostMapping("/showChooseCourseByUserId")
    public Object showChooseCourseByUserId(HttpServletRequest httpServletRequest) {
        Long userId = this.getLoginUserCourse().getUserId();
        log.info("请求的UserId 是:{}", userId);

        if (userId == null || userId < 0) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "用户Id非法");
        }
        List<Course> courses = userCourseService.showChooseCourseByUserId(userId);
        return ResultUtil.getSuccessResult(courses);
    }

    @GetMapping("/selectStudentInfoByUserId")
    public Object selectStudentInfoByUserId() {
        Long userId = this.getLoginUserCourse().getUserId();
        log.info("请求的UserId 是:{}", userId);
        if (userId == null || userId < 0) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "用户Id非法");
        }
        StudentInfo studentInfo = this.userCourseService.selectStudentInfoByUserId(userId);
        String suffix = studentInfo.getImageUrl();
        studentInfo.setImageUrl(configImagePrefix+suffix);
        return ResultUtil.getSuccessResult(studentInfo);
    }
}
