package com.personal.course.service.imp;

import com.personal.course.dao.BannerDao;
import com.personal.course.model.Banner;
import com.personal.course.service.BannerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**


@description 
@author cgc6828
@date 2020/6/27 9:41
*/
@Service
public class BannerServiceImpl implements BannerService {

    @Resource
    private BannerDao bannerDao;


    @Override
    public int savePhoto(Banner banner) {
        return bannerDao.savePhoto(banner);
    }

    @Override
    public int countRow(Integer bannerType) {
        return bannerDao.countRow(bannerType);
    }

    @Override
    public String selectNewPathByFileName(String fileName) {
        return bannerDao.selectNewPathByFileName(fileName);
    }

    @Override
    public int deletePhotoById(Integer bannerId) {
        return bannerDao.deletePhotoById(bannerId);
    }

    @Override
    public List<Banner> selectAllBanner() {
        return bannerDao.selectAllBanner();
    }

    @Override
    public Banner selectBannerByBannerId(Integer bannerId) {
        return bannerDao.selectBannerByBannerId(bannerId);
    }

    @Override
    public int deleteByBannerId(Integer bannerId) {
        return bannerDao.deletePhotoById(bannerId);
    }
}
