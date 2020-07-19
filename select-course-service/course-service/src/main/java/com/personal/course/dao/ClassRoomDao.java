package com.personal.course.dao;

import com.personal.course.bo.CourseClassRoomInfo;
import com.personal.course.dto.sys.req.ClassRoomQueryReq;
import com.personal.course.model.ClassRoom;
import com.personal.course.model.UserCourseInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**


@description 
@author cgc6828
@date 2020/4/13 21:23
*/
public interface ClassRoomDao {

    /**
     * 分页查找教室
     * @param classRoomQueryReq 查询教室请求类
     * @return 教室的分页列表
     */
    List<ClassRoom> listClassRoom(ClassRoomQueryReq classRoomQueryReq);


    /**
     * 添加教室
     * @param classRoom 教室实体类
     * @return 添加教室的条数
     */
    int addClassRoom (ClassRoom classRoom);

    /**
     * 根据教室名查找教室
     * @param classRoomName 教室名
     * @return 教室实体
     */
    ClassRoom findClassRoomByName( @Param("classroomName") String classRoomName);
    /**
     * 根据教室ID名查找教室
     * @param classroomId 教室ID
     * @return 教室实体
     */
    ClassRoom findClassRoomById(@Param("classroomId") Long classroomId);

    /**
     * 根据教室ID名更新教室
     * @param classRoom 教室
     * @return 更新教室的条数
     */
    int updateClassRoomById(ClassRoom classRoom);

    /**
     * 根据教室ID名删除教室
     * @param classroomId 教室ID
     * @return 删除教室的条数
     */
    int deleteCourseByCourseId(@Param("classroomId") Long classroomId);

    /**
     * 课程教室的详情
     * @return  课程教室信息
     */
    List<CourseClassRoomInfo>  showCourseClassRoomInfo();

    /**
     *更新教室 根据userID classroomId
     * @param userCourseInfo 更新实体类
     * @return 更新教室的条数
     */
    int updateUserClassroomById(UserCourseInfo userCourseInfo);
}
