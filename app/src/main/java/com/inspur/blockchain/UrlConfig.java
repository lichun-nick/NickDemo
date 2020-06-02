package com.inspur.blockchain;

/**
 * @author lichun
 */
public final class UrlConfig {

    private static final String DEV_HOST = "https://dev.qualink.com/";
    /**
     * 注册
     */
    public static final String REGISTER_URL = DEV_HOST+"ibid/user/register";
    /**
     * 请求验证码
     */
    public static final String REQUEST_VERIFICATION_CODE_URL = DEV_HOST+"ibid/notice/sms/";
    /**
     * 密码/验证码登录
     */
    public static final String PASSWORD_LOGIN_URL = DEV_HOST + "ibid/user/login";
    /**
     * 检查token有效性
     */
    public static final String CHECK_TOKEN = DEV_HOST + "ibid/user/checkToken";

}
