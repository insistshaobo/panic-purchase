package enums;


import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName EnvEnum
 * @Description: 根据ip获取服务名
 * @Author zhangdizhong
 * @Date 2019/11/2 16:15
 * @Version V1.0
 **/
public enum EnvEnum {

    DEV("1", "开发环境", "dev"),
    TEST("2", "测试环境", "test"),
    PRE("3", "预发布环境", "pre"),
    PROD("4", "生产环境", "prod");

    private String flag;//flag
    private String envName;//环境名
    private String env;//环境名

    EnvEnum(String flag, String envName, String env) {
        this.flag = flag;
        this.envName = envName;
        this.env = env;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public static String getEnvNameByFlag(String flag) {
        if (StringUtils.isEmpty(flag)) {
            return null;
        }
        if (flag.equals(EnvEnum.DEV.getFlag())) {
            return EnvEnum.DEV.getEnvName();
        }
        if (flag.equals(EnvEnum.TEST.getFlag())) {
            return EnvEnum.TEST.getEnvName();
        }
        if (flag.equals(EnvEnum.PRE.getFlag())) {
            return EnvEnum.PRE.getEnvName();
        }
        if (flag.equals(EnvEnum.PROD.getFlag())) {
            return EnvEnum.PROD.getEnvName();
        }
        return null;
    }

    public static String getEnvNameByEnv(String env) {
        if (StringUtils.isEmpty(env)) {
            return null;
        }
        if (env.equals(EnvEnum.DEV.getEnv())) {
            return EnvEnum.DEV.getEnvName();
        }
        if (env.equals(EnvEnum.TEST.getEnv())) {
            return EnvEnum.TEST.getEnvName();
        }
        if (env.equals(EnvEnum.PRE.getEnv())) {
            return EnvEnum.PRE.getEnvName();
        }
        if (env.equals(EnvEnum.PROD.getEnv())) {
            return EnvEnum.PROD.getEnvName();
        }
        return null;
    }

    public static String getEnvByFlag(String flag){
        if (StringUtils.isEmpty(flag)) {
            return null;
        }
        if (flag.equals(EnvEnum.DEV.getFlag())) {
            return EnvEnum.DEV.getEnv();
        }
        if (flag.equals(EnvEnum.TEST.getFlag())) {
            return EnvEnum.TEST.getEnv();
        }
        if (flag.equals(EnvEnum.PRE.getFlag())) {
            return EnvEnum.PRE.getEnv();
        }
        if (flag.equals(EnvEnum.PROD.getFlag())) {
            return EnvEnum.PROD.getEnv();
        }
        return null;
    }

    public static Boolean getPRODEnv(String env) {
        if (PROD.getEnv().equals(env)) return Boolean.TRUE;
        return Boolean.FALSE;
    }

}
