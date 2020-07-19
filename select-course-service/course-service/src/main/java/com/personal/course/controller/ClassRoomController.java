package com.personal.course.controller;

import com.personal.common.util.FastJsonUtil;
import com.personal.core.enums.ResultEnum;
import com.personal.core.util.ResultUtil;
import com.personal.course.annotation.PermissionCheck;
import com.personal.course.bo.CourseClassRoomInfo;
import com.personal.course.dto.sys.req.ClassRoomQueryReq;
import com.personal.course.model.ClassRoom;
import com.personal.course.model.UserCourseInfo;
import com.personal.course.service.ClassRoomService;
import com.personal.course.util.ViewUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cgc6828
 * @description
 * @date 2020/4/13 21:20
 */
@RestController
@RequestMapping("/classroom")
@Slf4j
public class ClassRoomController {

    @Resource
    private ClassRoomService classRoomService;

    @PermissionCheck(value = {"sys:classroom:list"})
    @PostMapping("/listClassRoomPage")
    public Object listClassRoomPage(@RequestBody ClassRoomQueryReq classRoomQueryReq) {
        log.info("获取用户分页列表参数:{}", FastJsonUtil.toJson(classRoomQueryReq));

        return ViewUtil.getPageInfo(this.classRoomService.listClassRoom(classRoomQueryReq));
    }

    @PermissionCheck(value = {"sys:classroom:add", "sys:classroom:edit"})
    @PostMapping("/saveClassRoom")
    public Object saveClassRoom(@RequestBody ClassRoom classRoom) {
        log.info("获取教室参数:{}", FastJsonUtil.toJson(classRoom));
        if (StringUtils.isEmpty(classRoom.getClassroomName())) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "教室名不可以为空");
        }
        ClassRoom classRoom1 = classRoomService.findClassRoomByName(classRoom.getClassroomName());
        if (classRoom1 != null) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "教室名字重复");
        }
        if (classRoom.getClassroomId() == null) {
            classRoomService.addClassRoom(classRoom);
            return ResultUtil.getResult(ResultEnum.SUCCESS);
        }
        classRoomService.updateClassRoomById(classRoom);
        return ResultUtil.getResult(ResultEnum.SUCCESS);
    }

    @PermissionCheck(value = {"sys:classroom:edit"})
    @GetMapping("/findClassroomByClassRoomId")
    public Object findCourseByClassRoomId(Long classroomId) {
        if (classroomId ==null || classroomId < 0 ) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "教室Id非法！");
        }
        ClassRoom classRoom1 = classRoomService.findClassRoomById(classroomId);
        if (classRoom1 == null) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "教室Id不存在");
        }

        return ResultUtil.getSuccessResult(classRoom1);
    }

    @PermissionCheck(value = {"sys:classroom:delete"})
    @GetMapping("deleteCourseByCourseId")
    public Object deleteCourseByCourseId(Long classroomId) {
        log.info("获取教室参数:{}", classroomId);
        if (classroomId ==null || classroomId < 0) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "教室Id非法");
        }
        ClassRoom classRoom = classRoomService.findClassRoomById(classroomId);
        if (classRoom == null) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "教室不存在");
        }
        classRoomService.deleteCourseByCourseId(classroomId);
        return ResultUtil.getSuccessResult(classRoom);
    }

    @PermissionCheck(value = {"sys:classroom:find"})
    @RequestMapping("/showCourseClassRoomInfo")
    public Object showCourseClassRoomInfo() {
        List<CourseClassRoomInfo> courseClassRoomInfo = this.classRoomService.showCourseClassRoomInfo();
        return ResultUtil.getSuccessResult(courseClassRoomInfo);
    }

    @PermissionCheck(value = "sys:classroom:update")
    @PostMapping("/updateUserClassroomById")
    public Object updateUserClassroomById(@RequestBody UserCourseInfo userCourseInfo) {
        ClassRoom classRoom1 = classRoomService.findClassRoomById(userCourseInfo.getClassroomId());
        if (classRoom1 == null) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "教室Id不存在");
        }
        this.classRoomService.updateUserClassroomById(userCourseInfo);
        return ResultUtil.getResult(ResultEnum.SUCCESS);
    }
}
