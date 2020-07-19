package com.personal.course.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.personal.course.bo.CourseClassRoomInfo;
import com.personal.course.dao.ClassRoomDao;
import com.personal.course.dto.sys.req.ClassRoomQueryReq;
import com.personal.course.model.ClassRoom;
import com.personal.course.model.UserCourseInfo;
import com.personal.course.service.ClassRoomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**


@description 
@author cgc6828
@date 2020/4/14 9:01
*/
@Service
public class ClassRoomServiceImpl implements ClassRoomService {
    @Resource
    private ClassRoomDao classRoomDao;

    /**
     * 分页查询
     * @param classRoomQueryReq 教室查询条件
     * @return
     */
    @Override
    public PageInfo<ClassRoom> listClassRoom(ClassRoomQueryReq classRoomQueryReq) {
        PageHelper.startPage(classRoomQueryReq.getPageNum(),classRoomQueryReq.getPageSize());
        return new PageInfo<>(this.classRoomDao.listClassRoom(classRoomQueryReq));
    }

    /**
     * 添加教室
     * @param classRoom 教室实体类
     * @return 更新的条数
     */
    @Override
    public int addClassRoom(ClassRoom classRoom) {
        return classRoomDao.addClassRoom(classRoom);
    }

    /**
     * 根据教室名查找教室
     * @param classRoomName 教室名
     * @return
     */
    @Override
    public ClassRoom findClassRoomByName(String classRoomName) {
        return classRoomDao.findClassRoomByName(classRoomName);
    }

    @Override
    public ClassRoom findClassRoomById(Long classroomId) {
        return classRoomDao.findClassRoomById(classroomId);
    }

    @Override
    public int updateClassRoomById(ClassRoom classRoom) {
        return classRoomDao.updateClassRoomById(classRoom);
    }

    @Override
    public int deleteCourseByCourseId(Long classroomId) {
        return classRoomDao .deleteCourseByCourseId(classroomId);
    }

    @Override
    public List<CourseClassRoomInfo> showCourseClassRoomInfo() {
        return classRoomDao.showCourseClassRoomInfo();
    }

    @Override
    public int updateUserClassroomById(UserCourseInfo userCourseInfo) {
        return classRoomDao.updateUserClassroomById(userCourseInfo);
    }
}
