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
    /**
     * 重设密码
     */
    public static final String RESET_PASSWORD_URL = DEV_HOST + "ibid/user/resetPwd";
    /**
     * 获取主页内容
     */
    public static final String MAIN_URL = DEV_HOST + "private/userAgent/home/info";
    /**
     * 获取凭证列表
     */
    public static final String VOUCHER_LIST_URL = DEV_HOST + "ibid/issue/list";
    /**
     * 申请凭证
     */
    public static final String REQUEST_VOUCHER_URL = DEV_HOST + "ibid/issue/application";
    /**
     * 获取凭证类型列表
     */
    public static final String QUERY_VOUCHER_TYPES_URL = DEV_HOST + "ibid/issue/selissuer";
    /**
     * 获取凭证详情
     */
    public static final String VOUCHER_DETAIL_URL = DEV_HOST + "ibid/issue/show";
    /**
     * 主动出示凭证生成url
     */
    public static final String GENERATE_URL_BY_DID = DEV_HOST + "private/userAgent/show/card";
    /**
     * 验证者获取凭证的url
     */
    public static final String GET_DID_URL_BY_VERIFIER = DEV_HOST + "ibid/userAgent/vp/get";
    /**
     * 扫码识别访问获取数据
     */
    public static final String SCAN_CODE_GET_DATA = DEV_HOST + "private/verifier/valid/card";

}
