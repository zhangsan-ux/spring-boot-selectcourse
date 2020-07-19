package com.personal.course.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.personal.common.util.DateUtil;
import com.personal.common.util.FastJsonUtil;
import com.personal.common.util.JwtUtil;
import com.personal.course.constant.RedisConstant;
import com.personal.course.dao.UserDao;
import com.personal.course.dto.UserTokenDTO;
import com.personal.course.dto.sys.req.QueryStudentReq;
import com.personal.course.dto.sys.req.UploadPhotoReq;
import com.personal.course.dto.sys.req.UserQueryReq;
import com.personal.course.model.QueryStudentInfo;
import com.personal.course.model.SysMenu;
import com.personal.course.model.SysUser;
import com.personal.course.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author cgc6828
 * @className SysUserServiceImpl
 * @description TODO
 * @date {DATE}{TIME}
 */
@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private UserDao userDao;

    @Override
    public SysUser selectByPrimaryKey(Long usrId) {
        return userDao.selectByPrimaryKey(usrId);
    }

    @Override
    public SysUser selectUserByBusinessNumber(String businessNumber) {
        return userDao.selectUserByBusinessNumber(businessNumber);
    }

    @Override
    public PageInfo<SysUser> listStudent(UserQueryReq userQueryReq) {
        //设置分页信息
        PageHelper.startPage(userQueryReq.getPageNum(), userQueryReq.getPageSize());

        return new PageInfo<>(this.userDao.listStudent(userQueryReq));
    }

    @Override
    public int addStudent(SysUser sysUser) {
        return userDao.addStudent(sysUser);
    }

    @Override
    public SysUser selectUserByIdCard(String idCard) {
        return userDao.selectUserByIdCard(idCard);
    }

    @Override
    public int updateStudent(SysUser sysUser) {
        return userDao.updateStudent(sysUser);
    }

    @Override
    public int deleteStudentByBusinessNumber(String businessNumber) {
        return userDao.deleteStudentByBusinessNumber(businessNumber);
    }

    @Override
    public String getTokenByUser(SysUser sysUser) {
        log.info("获取token的userId={}", sysUser.getUserId());

        UserTokenDTO userTokenDTO = new UserTokenDTO();
         userTokenDTO.setUserId(sysUser.getUserId());
         userTokenDTO.setName(sysUser.getName());
         userTokenDTO.setBusinessNumber(sysUser.getBusinessNumber());
         userTokenDTO.setMobile(sysUser.getMobile());
         userTokenDTO.setRoleId(sysUser.getRoleId());

         String key = RedisConstant.KEY_USER_LOGIN + sysUser.getUserId();
         userTokenDTO.setRedisTokenKey(key);

        Date expireDate = DateUtil.add(new Date(),12,30);
        userTokenDTO.setExpireDate(expireDate);

        String json = FastJsonUtil.toJson(userTokenDTO);

        log.info("待生成token的json:{}", json);
        String token = JwtUtil.createToken(json);
        log.info("用户id:{},生成token:{}", sysUser.getUserId(), token);

        return  token ;
    }

    @Override
    public List<QueryStudentInfo> queryStudentInfo(QueryStudentReq queryStudentReq) {
        return userDao.queryStudentInfo(queryStudentReq);
    }

    @Override
    public List<String> selectMenuByUserId(Long userId) {
        return userDao.selectMenuByUserId(userId);
    }

    @Override
    public int savePhoto(UploadPhotoReq uploadPhotoReq) {
        return userDao.savePhoto(uploadPhotoReq);
    }
}

