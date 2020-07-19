package com.personal.course.enmus;

/**
 * @author cgc6828
 * @className UserSexEnum
 * @description
 * @date 2020/4/11 18:47
 */
public enum UserSexEnum {
    /**
     * 男
     */
    NAM(1, "男"),

    /**
     *
     * 女
     *
     */
    WEWMEN(2, "女");

    /**
     * 编码
     */
    private int code;

    /**
     * 内容
     */
    private String content;

    UserSexEnum(int code, String content) {
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
        for (UserSexEnum userSexEnum : UserSexEnum.values()) {
            if (userSexEnum.getCode() == code) {
                return userSexEnum.getContent();
            }
        }

        return null;
    }

    public static int getCodeByContent(String content) {
        for (UserSexEnum userSexEnum : UserSexEnum.values()) {
            if (userSexEnum.getContent().equals(content) ) {
                return userSexEnum.getCode();
            }
        }

        return -1;
    }
}
