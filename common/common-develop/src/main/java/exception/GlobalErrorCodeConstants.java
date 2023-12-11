package exception;

/**
 * @ClassName GlobalErrorCodeConstants
 * @Description
 * @Author wangjm
 * @Date 2023/4/24
 * @Version 1.0
 */

/**
 * 全局错误码枚举
 * 0-999 系统异常编码保留
 * <p>
 * 一般情况下，使用 HTTP 响应状态码 https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Status
 * 虽然说，HTTP 响应状态码作为业务使用表达能力偏弱，但是使用在系统层面还是非常不错的
 */
public interface GlobalErrorCodeConstants {
    ErrorCode SUCCESS = new ErrorCode(200, "成功");
    ErrorCode SHSUCCESS = new ErrorCode(000000, "成功");

    // ========== 三方请求错误段 ==========
    ErrorCode OCR_IMG_ERROR = new ErrorCode(301,"无法识别，请重新上传");

    // ========== 客户端错误段 ==========

    ErrorCode BAD_REQUEST = new ErrorCode(400, "请求参数不正确");
    ErrorCode UNAUTHORIZED = new ErrorCode(401, "账号未登录");
    ErrorCode FORBIDDEN = new ErrorCode(403, "没有该操作权限");
    ErrorCode NOT_FOUND = new ErrorCode(404, "请求未找到");
    ErrorCode METHOD_NOT_ALLOWED = new ErrorCode(405, "请求方法不正确");
    ErrorCode LOCKED = new ErrorCode(423, "请求失败，请稍后重试"); // 并发请求，不允许
    static ErrorCode LOCKED_REDIS(Object... args){
        return new ErrorCode(423, "{0}", args);
    }

    ErrorCode TOO_MANY_REQUESTS = new ErrorCode(429, "请求过于频繁，请稍后重试");

    // ========== 服务端错误段 ==========

    ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode(500, "系统异常");
    ErrorCode REQUEST_TO_FINANCENOTE_ERROR = new ErrorCode(501, "请求白条服务异常");

    // ========== 自定义错误段 ==========
    ErrorCode REPEATED_REQUESTS = new ErrorCode(900, "重复请求，请稍后重试");
    ErrorCode DEMO_DENY = new ErrorCode(901, "演示模式，禁止写操作");
    ErrorCode NOT_QUERY_DATA = new ErrorCode(902, "数据不存在");
    ErrorCode NOT_CERTIFICATION = new ErrorCode(903, "用户未认证");
    ErrorCode NOT_SIGN = new ErrorCode(904, "用户未签约");
    ErrorCode NOT_OPEN_VIRACCTNO = new ErrorCode(905, "电子账户未开通");
    ErrorCode NOT_ACTIVATE_VIRACCTNO = new ErrorCode(906, "电子账户未激活");
    ErrorCode USER_INFO = new ErrorCode(907, "用户信息异常");

    ErrorCode REPEAT_CODE = new ErrorCode(940, "重复签约");
    ErrorCode CHANNEL_NOT_OPEN = new ErrorCode(941, "签约渠道未打开");
    ErrorCode UN_IN_SIGN_WHITE_LIST = new ErrorCode(942, "不在签约白名单");
    ErrorCode IDENTITY_INFO_INCONSISTENT = new ErrorCode(943, "银行签约信息与认证信息不一致");
    ErrorCode PING_AN_AVAILABLE_MONEY_NOT_ALLOW = new ErrorCode(944, "平安银行账户有余额未提现");
    ErrorCode PING_AN_BLOCK_MONEY_NOT_ALLOW = new ErrorCode(945, "平安银行账户有冻结金额");
    ErrorCode HAVE_UN_OVER_CONTRACT = new ErrorCode(946, "用户有未完结的合同");
    ErrorCode NO_RECEIPT_ERROR = new ErrorCode(947,"单个用户账户间转账无回单");
    ErrorCode OPEN_AUDIT_INP_ERROR = new ErrorCode(948,"开户审核中");
    ErrorCode OPEN_AUDIT_SUCCESS_ERROR = new ErrorCode(947,"开户审核成功");



    ErrorCode SEND_VRFY_FAIL = new ErrorCode(950, "短信发送失败");
    /**
     * 个人预注册验证码验证失败
     */
    ErrorCode PERSONAL_SUBMIT_VERIFY= new ErrorCode(951, "验证码错误或超时，请重新获取");
    ErrorCode NO_VIRACCTNO= new ErrorCode(952, "未查询到记账簿账号");
    /**
     * 个人电子账本注册异常
     */
    ErrorCode PERSONAL_ELEC_REGISTER= new ErrorCode(953, "您填写的信息不准确，请检查后重新填写");
    /**
     * 电子账本信息查询异常
     */
    ErrorCode ELEC_INFO_QUERY= new ErrorCode(954, "账户信息查询异常");
    /**
     * 电子账本信息查询异常
     */
    ErrorCode VIRTUAL_ACCOUNT_INFO_QUERY= new ErrorCode(954, "账户不存在或未激活");
    /**
     * 个人预注册验证码重发失败
     */
    ErrorCode PERSONAL_REGISTER_CODE= new ErrorCode(955, "验证码发送失败");

    /**
     * 参数异常
     * @param args
     * @return
     */
    static ErrorCode PERSONAL_REGISTER_PARAM_ERROR(Object... args){
        return new ErrorCode(956, "个人预注册参数{0}异常", args);
    }

    ErrorCode BIND_CARD_FAIL= new ErrorCode(960, "您填写的信息不准确，请检查后重新填写");
    ErrorCode BIND_REPEAT= new ErrorCode(961, "银行卡已绑定");
    ErrorCode ACCOUNT_INCONSISTENT= new ErrorCode(962, "银行卡账户名和开户姓名不一致");
    ErrorCode ACCOUNT_INCONSISTENT_LEGALNAME= new ErrorCode(963, "银行卡账户名和法人姓名不一致");
    ErrorCode BIND_CARD_GREATER_FIVE= new ErrorCode(964, "绑定银行卡不能大于5张");
    ErrorCode REMOVE_CARD_FAIL= new ErrorCode(965, "解绑银行卡失败");
    ErrorCode REMOVE_CARD_LAST= new ErrorCode(966, "解绑失败，请至少保留一张银行卡");
    ErrorCode REMOVE_CARD_FAIL_HAVE_DRAW= new ErrorCode(967, "银行卡正在提现，暂不能解绑");
    ErrorCode REMOVE_CARD_FAIL_FOR_PROXY= new ErrorCode(968, "银行卡做为代收账户，不能解绑");
    ErrorCode REMOVE_CARD_FAIL_FOR_AMOUNT= new ErrorCode(969, "银行卡有未提现金额，不能解绑");

    ErrorCode DRAW_MONEY_ERROR= new ErrorCode(970, "提现金额大于可提现余额");
    ErrorCode DRAW_CARD_ERROR= new ErrorCode(971, "选择的银行卡不能用作提现，请更换银行卡");
    ErrorCode DRAW_MONEY_SERVICE_FEE_ERROR= new ErrorCode(972, "提现金额不能小于服务费金额");


    ErrorCode OCR_EXCEPTION = new ErrorCode(998, "未知错误");
    ErrorCode UNKNOWN = new ErrorCode(999, "未知错误");


    static ErrorCode PARAM_NOT_EMPTY(Object... args){
        return new ErrorCode(1001, "{0}参数不能为空", args);
    }

    static ErrorCode DATA_IS_NOT_EXIST(Object... args){
        return new ErrorCode(1002, "{0}数据不存在", args);
    }

    static ErrorCode BANK_TRADE_ERROR(Object... args){
        return new ErrorCode(1003, "银行交易失败：{0}", args);
    }


    static ErrorCode PAY_PARAMETER_ERROR(Object... args){
        return new ErrorCode(3001, "参数校验失败：{0}", args);
    }
    static ErrorCode PAY_IDEMPOTENT_ERROR(Object... args){
        return new ErrorCode(3002, "重复提交：{0}", args);
    }
    static ErrorCode PAY_AMOUNT_ERROR(Object... args){
        return new ErrorCode(3003, "金额校验失败：{0}", args);
    }
    static ErrorCode PAY_USER_ERROR(Object... args){
        return new ErrorCode(3004, "用户校验失败：{0}", args);
    }
    static ErrorCode PAY_PLATFORM_ACCOUNT_ERROR(Object... args){
        return new ErrorCode(3005, "平台账户校验失败：{0}", args);
    }
    static ErrorCode PAY_WIND_ERROR(Object... args){
        return new ErrorCode(3006, "风控校验失败：{0}", args);
    }
    static ErrorCode PAY_PAY_ORDER_ERROR(Object... args){
        return new ErrorCode(3007, "{0}", args);
    }


    /**
     * 是否为服务端错误，参考 HTTP 5XX 错误码段
     *
     * @param code 错误码
     * @return 是否
     */
    static boolean isServerErrorCode(Integer code) {
        return code != null
                && code >= INTERNAL_SERVER_ERROR.getCode() && code <= INTERNAL_SERVER_ERROR.getCode() + 99;
    }
}
