package constant;

public class CommonConstant {

    /**
     * volatile 变量只能保证可见性
     */
    private volatile static CommonConstant instance = null;

    /**
     * @Title:<p>LogClassName</p>
     * @Description:<p>默认构造方法，单例实例初始化</p>
     * @date: <p>2019年5月6日 上午8:46:59</p>
     */
    private CommonConstant() {
        getInstance();
    }

    /**
     * @return
     * @Title: <p>getInstance</p>
     * @Description: <p>单例获取当前类对象实例</p>
     * @date: <p>2019年5月6日 上午8:47:10</p>
     */
    private static CommonConstant getInstance() {
        if (instance == null) {
            synchronized (CommonConstant.class) {
                if (instance == null) {
                    instance = new CommonConstant();
                }
            }
        }
        return instance;
    }

    /**
     * 执行成功
     */
    public static final String CODE_200 = "200";
    /**
     * 未登录/TOKEN失效
     */
    public static final String CODE_401 = "401";

    /**
     * 返回信息
     */
    public static final String MSG = "msg";

    /**
     * 关闭
     */
    public static final String CLOSE = "0";

    /**
     * 开启
     */
    public static final String START = "1";


    /**
     * 主数据系统标识
     */
    public static final String KEY_PREFIX = "liangbaba:";

    /**
     * redis保存基础数据前缀
     */
    public static final String SYS_CONFIG_PREFIX = KEY_PREFIX + "sys:config:";

    //H5域名
    public static final String USER_WAP_DOMAIN = KEY_PREFIX + "user:wap:domain";
    //front域名
    public static final String USER_FRONT_DOMAIN = KEY_PREFIX + "user:front:domain";
    //admin域名
    public static final String USER_ADMIN_DOMAIN = KEY_PREFIX + "user:admin:domain";

    /**
     * redis保存H5端登录用户信息数据前缀
     */
    public static final String USER_WAP_PREFIX = KEY_PREFIX + "user:wap:";
    /**
     * 买家APP
     */
    public static final String USER_WAP_APP_PREFIX = KEY_PREFIX + "user:wap:app:";
    /**
     * 商家App
     */
    public static final String USER_FRONT_APP_PREFIX = KEY_PREFIX + "user:front:app:";
    /**
     * redis保存front端登录用户信息数据前缀
     */
    public static final String USER_FRONT_PREFIX = KEY_PREFIX + "user:front:";
    /**
     * redis保存小程序端登录用户信息数据前缀
     */
    public static final String USER_APPLET_PREFIX = KEY_PREFIX + "user:applet:";

    /**
     * redis保存admin端登录用户信息数据前缀
     */
    public static final String USER_ADMIN_PREFIX = KEY_PREFIX + "user:admin:";

    public static final String USER_WAP_USER_ACCOUNT_PREFIX = KEY_PREFIX + "user:account:wap:";

    public static final String USER_FRONT_USER_ACCOUNT_PREFIX = KEY_PREFIX + "user:account:front:";

    /**
     * redis保存front端登录用户权限信息数据前缀
     */
    public static final String USER_FRONT_PERMISSION_PREFIX = KEY_PREFIX + "user:front:permission:";

    /**
     * redis保存H5端登录用户主体信息数据至白条模块前缀
     */
    public static final String USER_WAP_FINANCINGNOTE_PREFIX = KEY_PREFIX + "user:wap:financingNote:";
    /**
     * redis保存H5端登录用户主体信息数据至白条模块前缀
     */
    public static final String IS_SH_BANK_CHANNEL_OPEN = "IS_SH_BANK_CHANNEL_OPEN";
    /**
     * 上海银行准入标签
     */
    public static final String SHYHZR = "SHYHZR";



}

