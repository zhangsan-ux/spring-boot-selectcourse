package com.personal.course.controller;

import com.personal.common.util.DateUtil;
import com.personal.core.enums.ResultEnum;
import com.personal.core.util.ResultUtil;
import com.personal.course.constant.ImageConstant;
import com.personal.course.model.Banner;
import com.personal.course.model.ImageShowModel;
import com.personal.course.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author cgc6828
 * @description
 * @date 2020/6/26 17:35
 */
@RestController
@Slf4j
public class BannerController {

    @Value("${config.image.upload.path}")
    private String configImageUploadPath;

    @Value("${config.image.format}")
    private String imageFormat;

    @Value("${config.realmName}")
    private String realmName;

    @Resource
    private BannerService bannerService;
    @Value("${config.deletePath}")
    private String deletePath;

    @RequestMapping("/saveBanner")
    public Object saveBanner(@RequestBody MultipartFile photo, Integer bannerType) {

        if ( !bannerType.equals(1) ) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "类型错误");
        }
        if ( !bannerType.equals(2) ) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "类型错误");
        }
        if ( !bannerType.equals(3) ) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "类型错误");
        }
        if (photo.getSize() == 0) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "图片不可以为空");
        }
        if (photo.getSize() > ImageConstant.IMAGE_SIZE) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "上传图片的大小不可以超过10M");
        }
        if (StringUtils.isEmpty( photo.getOriginalFilename() )) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "文件名不可以为空");
        }
        //获取文件大小
        Long bannerSize = photo.getSize();
        Integer countRow = bannerService.countRow(bannerType);

        if (countRow >= bannerType) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "超出可上传的个数");
        }
        //获取文件大小
        //获取文件后缀
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1);
        if (!imageFormat.toUpperCase().contains(suffix.toUpperCase())) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "图片的格式只可以：jpg,jpeg,gif,png");
        }
        String bannerName = photo.getOriginalFilename();
        String dateStr = DateUtil.format(new Date(), DateUtil.YYYYMMDD);

        String path = configImageUploadPath + dateStr + "\\";
        File savePathFile = new File(path);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdirs();
        }
        String filename = UUID.randomUUID().toString();
        try {
            //将文件保存指定目录
            photo.transferTo(new File(path + filename));
        } catch (Exception e) {
            return ResultUtil.getResult(ResultEnum.BUSINESS_ERROR.getCode(), "上传图片失败");
        }
        String imagePath = dateStr + "/" + filename;
        String imagePathNew = realmName + imagePath;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("newPath", imagePath);
        resultMap.put("newFilePath", imagePathNew);
        resultMap.put("bannerType", bannerType);
        resultMap.put("bannerName", bannerName);
        resultMap.put("suffix", suffix);
        resultMap.put("bannerSize", bannerSize);


        return ResultUtil.getSuccessResult(resultMap);
    }

    @PostMapping("/saveBannerPath")
    public Object saveBannerPath(@RequestBody Banner banner) {
        //"" null

        if (StringUtils.isEmpty(banner.getNewPath())) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "图片的新路径不可以为空");
        }

        ;
        if (banner.getBannerType() == null || banner.getBannerType() != 1 || banner.getBannerType() != 2||banner.getBannerType() != 3) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "类型错误");
        }
        if (banner.getBannerSize() > ImageConstant.IMAGE_SIZE) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "上传图片的大小不可以超过10M");
        }
        if (StringUtils.isEmpty(banner.getOldName())) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "文件名不可以为空");
        }

        //获取文件后缀
        String suffix = banner.getSuffix();
        if (!imageFormat.toUpperCase().contains(suffix.toUpperCase())) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "图片的格式只可以：jpg,jpeg,gif,png");
        }

        Integer countRow = bannerService.countRow(banner.getBannerType());

        if (countRow >= banner.getBannerType()) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "超出可上传的个数");
        }

        banner.setBannerType(banner.getBannerType());
        banner.setNewPath(banner.getNewPath());
        banner.setOldName(banner.getOldName());
        banner.setSuffix(suffix);
        banner.setBannerSize(banner.getBannerSize());
        banner.setCreateBy("管理员");
        banner.setCreateTime(new Date());
        banner.setUpdateBy("管理员");
        banner.setUpdateTime(new Date());

        bannerService.savePhoto(banner);
        Integer id = banner.getBannerId();
        return ResultUtil.getSuccessResult(id);
    }

    @RequestMapping("/deleteBannerPath")
    public Object deleteBannerPath(Integer bannerId) {
        if (bannerId ==null && bannerId <0) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(),"參數錯誤");
        }

        Banner banner = bannerService.selectBannerByBannerId(bannerId);
        if (banner == null) {
            return ResultUtil.getResult(ResultEnum.PARAMETER_ERROR.getCode(), "刪除的banner不存在");
        }
        bannerService.deleteByBannerId(bannerId);
        log.info("刪除數据庫的路徑成功！！");
        File file = new File(configImageUploadPath + banner.getNewPath());

        if (file != null && file.exists()) {
            boolean flag = file.delete();
            if (!flag) {
                return ResultUtil.getResult(ResultEnum.BUSINESS_ERROR.getCode(), "删除失敗");
            }
        }
        return ResultUtil.getResult(ResultEnum.SUCCESS.getCode(), "删除成功");

    }

    @GetMapping("/selectAllBanner")
    public Object selectAllBanner() {
        List<Banner> bannerList = bannerService.selectAllBanner();
        if (bannerList == null || bannerList.size() == 0) {
            return ResultUtil.getSuccessResult();
        }
        bannerList.sort(Comparator.comparing(Banner::getBannerType));
        Map<Integer, List<Banner>> map = bannerList.stream().collect(Collectors.groupingBy(Banner::getBannerType));
        List<ImageShowModel> imageShowModelList = new ArrayList<>();
        map.forEach((k, v) -> {
            ImageShowModel imageShowModel = new ImageShowModel();
            imageShowModel.setType(k);
            List<Map<String, Object>> list = new ArrayList<>();
            for (Banner banner : v) {
                Map<String, Object> hashMap = new HashMap<>(8);
                hashMap.put("newPath", banner.getNewPath());
                hashMap.put("assessPath", realmName + banner.getNewPath());
                hashMap.put("bannerId", banner.getBannerId());
                list.add(hashMap);
            }
            imageShowModel.setBannerList(list);
            imageShowModelList.add(imageShowModel);
        });

        return ResultUtil.getSuccessResult(imageShowModelList);
    }
}
