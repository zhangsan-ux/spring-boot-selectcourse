package com.personal.course.service;

import com.github.pagehelper.PageInfo;
import com.personal.course.dto.sys.req.QueryStudentReq;
import com.personal.course.dto.sys.req.UploadPhotoReq;
import com.personal.course.dto.sys.req.UserQueryReq;
import com.personal.course.model.QueryStudentInfo;
import com.personal.course.model.SysMenu;
import com.personal.course.model.SysUser;

import java.util.List;

/**
 * 说明
 *
 * @author cgc 6828
 * @date 2020/4/12 19:12
 */
public interface SysUserService {
   /**
    * 根据主键查找用户
    * @param usrId 用户ID
    * @return 用户
    */
   SysUser selectByPrimaryKey(Long usrId);

   /**
    * 根据学号或者教工号查找用户
    * @param businessNumber 学号或者教工号
    * @return 用户
    */
   SysUser selectUserByBusinessNumber (String businessNumber);

   /**
    * 分页查询的学生信息
    * @param userQueryReq 分页请求类
    * @return 分页结果
    */
   PageInfo<SysUser> listStudent(UserQueryReq userQueryReq);

   /**
    * 增加学生
    * @param sysUser 学生
    * @return 添加的条数
    */
   int addStudent(SysUser sysUser);

   /**
    * 根据身份证号找用户
    * @param idCard 身份证号
    * @return 用户
    */
   SysUser selectUserByIdCard(String idCard);

   /**
    * 更新学生信息
    * @param sysUser 学生信息
    * @return 更新的条数
    */
   int updateStudent(SysUser sysUser);

   /**
    * 根据学号删除学生
    * @param businessNumber 学号或者教工号
    * @return 删除的条数
    */
   int deleteStudentByBusinessNumber(String businessNumber);

   /**
    * 生成token
    * @param sysUser 用户实体
    * @return
    */
   String getTokenByUser(SysUser sysUser);

   /**
    * 根据条件查询 学生信息
    * @param queryStudentReq 学生条件
    * @return
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


