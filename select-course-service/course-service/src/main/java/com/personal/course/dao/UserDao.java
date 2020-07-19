package com.personal.course.dao;

import com.personal.course.dto.sys.req.QueryStudentReq;
import com.personal.course.dto.sys.req.UploadPhotoReq;
import com.personal.course.dto.sys.req.UserQueryReq;
import com.personal.course.model.QueryStudentInfo;
import com.personal.course.model.SysMenu;
import com.personal.course.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author cgc6828
 * @className UserDao
 * @description TODO
 * @date {DATE}{TIME}
 */
public interface UserDao {


    /**
     * 根据useId 查找用户
     * @param userId 用户Id
     * @return 用户信息
     */
    SysUser selectByPrimaryKey(Long userId);

    /**
     * 根据 businessNumber
     *
     * @param businessNumber 学号或者教工号
     * @return 用户信息
     */
    SysUser selectUserByBusinessNumber(String businessNumber);


    /**
     *  查出所有学生 角色Id 1-系统管理员 2-老师 3-学生
     * @param userQueryReq 用户请求类
     * @return 学生列表
     */
    List<SysUser> listStudent(UserQueryReq userQueryReq);

    /**
     * 添加学生
     *
     * @param sysUser 学生实体类
     * @return 增加额条数
     */
    int addStudent(SysUser sysUser);

    /**
     * 根据IdCard 查找用户
     *
     * @param idCard 身份证号
     * @return 用户
     */
    SysUser selectUserByIdCard(String idCard);

    /**
     * 更新学生信息
     *
     * @param sysUser 用户
     * @return 更新条数
     */
    int updateStudent(SysUser sysUser);

    /**
     * 删除学生
     *
     * @param businessNumber 学号
     * @return 条数
     */
    int deleteStudentByBusinessNumber(@Param("businessNumber") String businessNumber);

    /**
     * 根据条件查询 学生信息
     * @param queryStudentReq 学生条件
     * @return 学生信息的分页结果
     */
    List<QueryStudentInfo> queryStudentInfo(QueryStudentReq queryStudentReq);

    /**
     * 根据用户Id查找权限
     * @param userId 用户Id
     * @return 菜单列表
     */
    List<String> selectMenuByUserId(Long userId);


    /**
     * 跟新头像图片路径
     * @param uploadPhotoReq 跟新头像路径的请求类
     * @return 跟新的条数
     */
    int savePhoto(UploadPhotoReq uploadPhotoReq);
}


