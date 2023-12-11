package job;


public enum EnvAccountTokenEnum {
    DEV("dev","4*Hte85plZDmFZ%xkw0EGlvf^nlC2jp2G2aF"),
    TEST("test","o2xZlK^bpKgcjE2OwYdYC4LkhvrQq&51eeJi"),
    PRE("pre","bTvIU5sdNlywGbAkXRIDkdpshouddDrHYD1H"),
    PROD("prod","moGxQxNKBQyVqMv2y8jB5Jntl8ncEDHdoqdd");

    EnvAccountTokenEnum(String env, String accountToken) {
        this.env = env;
        this.accountToken = accountToken;
    }

    public String env;

    public String accountToken;

    public String getEnv() {
        return env;
    }

    public String getAccountToken() {
        return accountToken;
    }

    public static String valAccountToken(String env){
        for (EnvAccountTokenEnum e : values()){
            if (e.getEnv().equals(env)){
                return e.getAccountToken();
            }
        }
        return null;
    }
}
