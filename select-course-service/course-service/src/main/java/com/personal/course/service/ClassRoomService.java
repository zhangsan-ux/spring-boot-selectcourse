package com.personal.course.service;

import com.github.pagehelper.PageInfo;
import com.personal.course.bo.CourseClassRoomInfo;
import com.personal.course.dto.sys.req.ClassRoomQueryReq;
import com.personal.course.model.ClassRoom;
import com.personal.course.model.UserCourseInfo;

import java.util.List;

/**


@description 
@author cgc6828
@date 2020/4/14 8:59
*/
public interface ClassRoomService {

    /**
     * 分页查询教室
     * @param classRoomQueryReq 教室查询条件
     * @return
     */
    PageInfo<ClassRoom> listClassRoom(ClassRoomQueryReq classRoomQueryReq);


    /**
     * 添加教室
     * @param classRoom 教室实体类
     * @return 添加的条数
     */
     int  addClassRoom(ClassRoom classRoom);

    /**
     * 根据教室名查找教室
     * @param classRoomName 教师名
     * @return
     */
     ClassRoom findClassRoomByName(String classRoomName);
    /**
     * 根据教室ID名查找教室
     * @param classroomId 教师ID
     * @return
     */
     ClassRoom findClassRoomById(Long classroomId);

    /**
     * 根据教室ID名更新教室
     * @param classRoom 教室ID
     * @return
     */
     int updateClassRoomById(ClassRoom classRoom);

    /**
     * 根据教室ID名删除教室
     * @param classroomId 教室ID
     * @return
     */
     int deleteCourseByCourseId(Long classroomId);

    /**
     * 课程教室的详情
     * @return  课程教室信息
     */
    List<CourseClassRoomInfo> showCourseClassRoomInfo();
    /**
     *更新教室 根据userID classroomId
     * @param userCourseInfo 更新实体类
     * @return
     */
    int updateUserClassroomById(UserCourseInfo userCourseInfo);
}
