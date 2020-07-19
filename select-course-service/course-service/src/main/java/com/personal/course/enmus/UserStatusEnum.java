package com.personal.course.enmus;

/**
 * @author cgc6828
 * @className UserStatusEnum
 * @description TODO
 * @date {DATE}{TIME}
 */
public enum  UserStatusEnum {
    /**
     * 禁用
     */
    DISABLE(0, "禁用"),

    /**
     * 启用
     */
    ENABLE(1, "启用");

    /**
     * 编码
     */
    private int code;

    /**
     * 内容
     */
    private String content;

    UserStatusEnum(int code, String content) {
        this.code = code;
        this.content = content;
    }

    public int getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }

    public static String getContentByCode(int code) {
        for (UserStatusEnum userStatusEnum : UserStatusEnum.values()) {
            if (userStatusEnum.getCode() == code) {
                return userStatusEnum.getContent();
            }
        }

        return null;
    }
}


