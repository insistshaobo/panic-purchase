package constant;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author lengfj
 * @version 1.0
 * @date 2023/5/8
 **/
public interface RequestHeadersConst {
    /**
     * token
     */
    String token = "token";
    /**
     * 账号token
     */
    String accountToken = "accountToken";
    /**
     * 来源
     */
    String source = "source";
    /**
     * 客户端
     */
    String clientSource = "clientSource";
    /**
     * 渠道
     */
    String channel = "channel";
    /**
     * 版本号
     */
    String version = "version";
    String deviceCode = "deviceCode";
    String phoneModel = "phoneModel";
    String XForwardedFor = "X-Forwarded-For";
    String XRealIP = "X-Real-IP";

    List<String> HEADERS = Lists.newArrayList(
            token,accountToken,source,clientSource,channel,version,deviceCode,phoneModel);
}
