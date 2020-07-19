package com.personal.course.service;

import com.personal.course.model.Banner;

import java.util.List;

/**
 * @author cgc6828
 * @description
 * @date 2020/6/27 9:37
 */

public interface BannerService {

    /**
     * 上传图片文件
     * @param banner banner类
     * @return
     */
    int savePhoto(Banner banner);

    /**
     * 统计每个类型的条数
     * @param bannerType
     * @return
     */
    public int countRow (Integer bannerType);
    /**
     * 根据文件名 查找 新路径
     * @param fileName 文件名
     * @return 路径
     */
    public  String selectNewPathByFileName(String fileName);

    /**
     * 根据Id 删除图片
     * @param bannerId id
     * @return 删除的条数
     */
    public int deletePhotoById(Integer bannerId);
    /**
     * 查找所有banner
     * @return banner集合
     */
    public List<Banner> selectAllBanner();
    /**
     * 根据bannerId查找banner
     * @param bannerId bannerId
     * @return banner
     */
    public Banner selectBannerByBannerId(Integer bannerId);
    /**
     * 根据Id刪除banner
     * @param bannerId bannerId
     * @return  條數
     */
    public int deleteByBannerId(Integer bannerId);
}
