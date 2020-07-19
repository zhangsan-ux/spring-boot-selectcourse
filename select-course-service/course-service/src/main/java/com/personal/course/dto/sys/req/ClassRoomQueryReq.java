package com.personal.course.dto.sys.req;

import com.personal.course.dto.BasePageQueryDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**


@description 
@author cgc6828
@date 2020/4/14 9:04
*/
@Data
public class ClassRoomQueryReq extends BasePageQueryDTO implements Serializable {
    private static final long serialVersionUID = -871444382401952910L;

    /**
     * 教室ID
     */
    private Long classRoomId;
    /**
     * 教室名字
     */
    private String classRoomName;
}
