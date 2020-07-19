package com.personal.course.model;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**


@description 
@author cgc6828
@date 2020/6/26 18:05
*/
@Data
public class Banner {

    /**
     * 主键Id
     */
    @Min(value = 0,message = "不可以为零")
    private Integer bannerId ;
    /**
     * 类型（1,2,3）
     */
    @NotBlank(message = "类型不可以为空")
    private Integer bannerType;
    /**
     * 文件新路径
     */
    @NotBlank(message = "文件的新路径币可以为空")
    private String newPath;

    /**
     * 原来的文件名
     */
    @NotBlank(message = "文件名不可以为空")
    private String oldName;
    /**
     * 文件后缀名
     */
   private  String suffix;

    /**
     * 文件大小（字节Byte）
     */
   private Long bannerSize;

    /**
     * 创建者
     */
   private String createBy;
    /**
     * 创建时间
     */

   private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;

}
