package com.personal.course.dto.sys.req;

import lombok.Data;

/**


@description  上传头像的请求类
@author cgc6828
@date 2020/5/30 11:58
*/
@Data
public class UploadPhotoReq {

    /**
     * 图片路径
     */
    private   String imagePath ;
    /**
     * 用户Id
     */
    private Long  userId;
}
