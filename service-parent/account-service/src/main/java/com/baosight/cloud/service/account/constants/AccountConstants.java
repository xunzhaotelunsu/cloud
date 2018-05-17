package com.baosight.cloud.service.account.constants;

/**
 * Created by yang on 2018/3/21.
 */
public class AccountConstants {

    private AccountConstants(){

    }

    /**
     * 登录名类型
     */
    //id
    public static final String LOGIN_NAME_TYPE_ID = "id";
    //用户名
    public static final String LOGIN_NAME_TYPE_NAME = "name";
    //手机
    public static final String LOGIN_NAME_TYPE_MOBILE = "mobile";
    //邮箱
    public static final String LOGIN_NAME_TYPE_EMAIL = "email";
    /**
     * 用户状态
     */
    //启用
    public static final String ACCOUNT_STATUS_ACTIVE = "1";
    //停用
    public static final String ACCOUNT_STATUS_INACTIVE = "0";

    //patterns
    public static final String USER_PATTERN = "^[a-zA-Z0-9_]{3,16}$";

    public static final String PASSWORD_PATTERN = "^[@A-Za-z0-9!#$%^&*.~_]{6,22}$";

    public static final String MOBILE_PATTERN = "^(((13[0-9]{1})|(14[0-9]{1})|(17[0-9]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\\d{8})$";

}
