package com.personal.course.controller;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageInfo;
import com.personal.common.util.Base64Util;
import com.personal.common.util.DateUtil;
import com.personal.common.util.FastJsonUtil;
import com.personal.common.util.Md5Util;
import com.personal.core.constant.CommonConstant;
import com.personal.core.enums.ResultEnum;
import com.personal.core.util.ResultUtil;
import com.personal.course.annotation.PermissionCheck;
import com.personal.course.constant.ImageConstant;
import com.personal.course.constant.RedisConstant;
import com.personal.course.dto.sys.req.*;
import com.personal.course.dto.sys.resp.ImgValidCodeResp;
import com.personal.course.dto.sys.resp.StudentEditResp;
import com.personal.course.dto.sys.resp.UserLoginResp;
import com.personal.course.enmus.UserSexEnum;
import com.personal.course.model.Menu;
import com.personal.course.model.QueryStudentInfo;
import com.personal.course.model.SysUser;
import com.personal.course.service.MenuManageService;
import com.personal.course.service.RoleService;
import com.personal.course.service.SysUserService;
import com.personal.course.util.GetChildMenuListUtil;
import com.personal.course.util.ValidCodeUtil;
import com.personal.course.util.ViewUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author cgc6828
 * @className UserController
 * @description TODO
 * @date {DATE}{TIME}
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private SysUserService sysUserService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RoleService roleService;

    @Resource
    private MenuManageService menuManageService;

    @Value("${config.image.upload.path}")
    private String configImageUploadPath;

    @Value("${config.image.format}")
    private String imageFormat;

    @PermissionCheck(value = {"sys:user:edit"})
    @RequestMapping("/selectUserById")
    public Object selectUserById(Long userId) {

        if (userId == null || userId < 0) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "学号非法！");
        }
        log.info("线程测试！！！");
        SysUser sysUser = sysUserService.selectByPrimaryKey(userId);

        if (sysUser == null) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "该学生不存在！");
        }
        StudentEditResp studentEditResp = new StudentEditResp();
        studentEditResp.setName(sysUser.getName());
        studentEditResp.setClassName(sysUser.getClassName());
        studentEditResp.setBusinessNumber(sysUser.getBusinessNumber());
        studentEditResp.setUserSex(UserSexEnum.getContentByCode(sysUser.getUserSex()));
        studentEditResp.setIdCard(sysUser.getIdCard());
        studentEditResp.setMobile(sysUser.getMobile());
        studentEditResp.setUserId(sysUser.getUserId());
        return ResultUtil.getSuccessResult(studentEditResp);
    }

    /**
     * 获取验证码
     *
     * @return
     */
    @GetMapping("/getUserValidCode")
    public Object getUserValidCode() {
        int width = 130;
        int height = 40;
        try {
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            String validCode = ValidCodeUtil.drawRandomText(width, height, bufferedImage);
            log.info("生成的验证码为:randomText={}", validCode);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
            String base64Code = "data:image/png;base64," + Base64Util.encode(outputStream.toByteArray());

            String key = Md5Util.md5To16Encrypt(validCode);

            //添加缓存
            this.stringRedisTemplate.opsForValue().set(key, validCode, 1, TimeUnit.MINUTES);

            ImgValidCodeResp imgValidCodeResp = new ImgValidCodeResp();
            imgValidCodeResp.setBase64Code(base64Code);
            imgValidCodeResp.setCodeKey(key);

            return ResultUtil.getSuccessResult(imgValidCodeResp);
        } catch (Exception e) {
            log.error("生成验证码异常", e);
            return ResultUtil.getResult(ResultEnum.ERROR_SYS);
        }

    }


    @PostMapping("/login")
    public Object login(@RequestBody @Valid UserLoginReq userLoginReq, HttpServletResponse response) {
        log.info("user login :{}", userLoginReq);
//        String code = this.stringRedisTemplate.opsForValue().get(userLoginReq.getCodeKey());
        SysUser sysUser = sysUserService.selectUserByBusinessNumber(userLoginReq.getBusinessNumber());
        if (sysUser == null) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "用户名或者密码错误！");
        }
//        if (StringUtils.isBlank(code)) {
//            return ResultUtil.getResult(ResultEnum.VALID_CODE_EXPIRED);
//        }
//        if (!code.equalsIgnoreCase(userLoginReq.getImgCode())) {
//            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR, "验证码错误！");
//        }
        UserLoginResp userLoginResp = new UserLoginResp();

        if (sysUser.getPwdStatus() == 2) {
            if (!sysUser.getUserPassword().equals(Md5Util.md5To32Encrypt(userLoginReq.getUserPassword()) + Md5Util.md5To32Encrypt(sysUser.getSalt()))) {
                return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "用户名或者密码错误！");
            }
        } else {
            if (!sysUser.getUserPassword().equals(Md5Util.md5To32Encrypt(sysUser.getIdCard().substring(sysUser.getIdCard().length() - 6)) + Md5Util.md5To32Encrypt(sysUser.getSalt()))) {
                return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "用户名或者密码错误！");
            }
        }

        String token = this.sysUserService.getTokenByUser(sysUser);

        //登录成功，把token放到响应头
        response.setHeader("Access-Control-Expose-Headers", CommonConstant.KEY_USER_TOKEN);
        response.setHeader(CommonConstant.KEY_USER_TOKEN, token);

        userLoginResp.setName(sysUser.getName());
        userLoginResp.setImageUrl(sysUser.getImageUrl());
        userLoginResp.setClassName(sysUser.getClassName());
        userLoginResp.setMobile(sysUser.getMobile());
        userLoginResp.setUserSex(UserSexEnum.getContentByCode(sysUser.getUserSex()));
        userLoginResp.setRoleId(sysUser.getRoleId());
        userLoginResp.setUserId(sysUser.getUserId());
        //查出菜单列表
        List<Menu> rootMenu = menuManageService.listAllMenuListByRoleId(sysUser.getRoleId());
        // 最后的结果
        List<Menu> menus = new ArrayList<>();
        // 先找到所有的一级菜单
        for (Menu value : rootMenu) {
            // 一级菜单没有parentId
            if (value.getParentId() == 0) {
                menus.add(value);
            }
        }

        // 为一级菜单设置子菜单，getChild是递归调用的
        for (Menu menu : menus) {
            menu.setChildMenus(GetChildMenuListUtil.getMenuChild(menu.getMenuId(), rootMenu));
        }
        userLoginResp.setMenus(menus);


        //将按钮权限加载在Redis
        String permissionKey = RedisConstant.PERMISSION_KEY + sysUser.getRoleId();
        List<String> permissionList = roleService.selectMenuByRoleId(sysUser.getRoleId());
        //将按钮菜单放到登录成功后的userLoginResp
        userLoginResp.setPermissions(permissionList);
        String permissionString = FastJsonUtil.toJson(permissionList);

        this.stringRedisTemplate.opsForValue().set(permissionKey, permissionString);

        log.info("登录成功:userLoginResp={}", FastJsonUtil.toJson(userLoginResp));

        return ResultUtil.getSuccessResult(userLoginResp);
    }

    @PermissionCheck(value = {"sys:user:list"})
    @PostMapping("/listStudentPage")
    public Object listStudentPage(@Valid @RequestBody UserQueryReq userQueryReq) {
        log.info("获取用户分页列表参数:{}", FastJsonUtil.toJson(userQueryReq));

        PageInfo<SysUser> pageInfo = this.sysUserService.listStudent(userQueryReq);
        return ViewUtil.getPageInfo(pageInfo);

    }

    @PermissionCheck(value = {"sys:user:add", "sys:user:edit"})
    @PostMapping("/saveStudent")
    public Object addStudent(@RequestBody @Valid AddStudentReq addStudentReq) {
        log.info("获取 用户参数:{}", FastJsonUtil.toJson(addStudentReq));

        SysUser sysUserAtTable = sysUserService.selectUserByBusinessNumber(addStudentReq.getBusinessNumber());
        SysUser sysUser = new SysUser();
        sysUser.setName(addStudentReq.getName());
        sysUser.setClassName(addStudentReq.getClassName());
        sysUser.setBusinessNumber(addStudentReq.getBusinessNumber());
        sysUser.setUserSex(UserSexEnum.getCodeByContent(addStudentReq.getUserSex()));
        sysUser.setIdCard(addStudentReq.getIdCard());
        sysUser.setMobile(addStudentReq.getMobile());
        if (addStudentReq.getUserId() == null) {
            if (sysUserAtTable != null) {
                return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "学号或者教工号重复！");
            }
            SysUser sysUserSameIdCard = sysUserService.selectUserByIdCard(addStudentReq.getIdCard());
            if (sysUserSameIdCard != null) {
                return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "身份证号重复！");
            }
            sysUser.setSalt(RandomStringUtils.randomAlphabetic(8));
            sysUser.setUserPassword(Md5Util.md5To32Encrypt(addStudentReq.getIdCard().substring(addStudentReq.getIdCard().length() - 6)) + Md5Util.md5To32Encrypt(sysUser.getSalt()));
            sysUser.setImageUrl(addStudentReq.getImageUrl());
            sysUser.setRoleId(addStudentReq.getRoleId());
            sysUser.setPwdStatus(addStudentReq.getPwdStatus());
            sysUser.setCreateBy(addStudentReq.getCreateBy());
            sysUser.setUpdateBy(addStudentReq.getUpdateBy());
            sysUserService.addStudent(sysUser);
        } else {
            sysUser.setUserId(addStudentReq.getUserId());
            sysUserService.updateStudent(sysUser);
        }
        return ResultUtil.getResult(ResultEnum.SUCCESS);
    }

    @PermissionCheck(value = {"sys:user:delete"})
    @GetMapping("/deleteStudentByBusinessNumber")
    public Object deleteStudentByBusinessNumber(String businessNumber) {
        log.info("请求的学号是:{}", businessNumber);
        if (StringUtils.isEmpty(businessNumber)) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "学号非法！");
        }
        SysUser sysUser = sysUserService.selectUserByBusinessNumber(businessNumber);
        if (sysUser == null) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "该学生不存在！");
        }
        sysUserService.deleteStudentByBusinessNumber(businessNumber);
        return ResultUtil.getResult(ResultEnum.SUCCESS);

    }


    @PermissionCheck(value = {"sys:query:studentInfo"})
    @PostMapping("/queryStudentInfo")
    public Object queryStudentInfo(@RequestBody QueryStudentReq queryStudentReq) {
        List<QueryStudentInfo> list = sysUserService.queryStudentInfo(queryStudentReq);
        return ResultUtil.getSuccessResult(list);
    }


    /**
     * 图片上传
     *
     * @param photo
     * @param request
     * @return
     */
    @PostMapping("/uploadPhoto")
    public Object uploadPhoto(MultipartFile photo, HttpServletRequest request) {

        if (photo.getSize() == 0) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "图片不可以为空");
        }
        if (photo.getSize() > ImageConstant.IMAGE_SIZE) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "图片大小不可以超过10M");
        }
        //获取文件后缀
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1);
        if (!imageFormat.toUpperCase().contains(suffix.toUpperCase())) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "图片的格式只可以：jpg,jpeg,gif,png");
        }
        String dateStr = DateUtil.format(new Date(), DateUtil.YYYYMMDD);
        String path = configImageUploadPath + dateStr + "\\";
        File savePathFile = new File(path);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdirs();
        }
        String filename = System.currentTimeMillis() + "." + suffix;
        try {
            //将文件保存指定目录
            photo.transferTo(new File(path + filename));
        } catch (Exception e) {
            return ResultUtil.getResult(ResultEnum.BUSINESS_ERROR.getCode(), "上传图片失败");
        }
        String imagePath = dateStr + "/" + filename;
        String imagePathNew = "http://localhost/image/" + imagePath;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("accessPath", imagePathNew);
        resultMap.put("imagePath", imagePath);
        return ResultUtil.getSuccessResult(resultMap);
    }


    @PostMapping("/savePhoto")
    public Object savePhoto(@RequestBody UploadPhotoReq uploadPhotoReq) {
        sysUserService.savePhoto(uploadPhotoReq);
        return ResultUtil.getResult(ResultEnum.SUCCESS);
    }

    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), QueryStudentInfo.class).sheet("模板").doWrite(data());
    }
    private List<QueryStudentInfo> data() {
        List<QueryStudentInfo> list = sysUserService.queryStudentInfo(null);
        return list;
    }
}


