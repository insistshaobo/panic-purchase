package utils;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.nacos.common.utils.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fanyudong
 * @date 2022/5/7
 */
@Slf4j
public class DingTalkUtil {

    /**
     * 发送钉钉消息
     * @param webhook 发送消息地址（示例：https://oapi.dingtalk.com/robot/send?access_token=xxx）
     * @param msg     发送消息类型及内容（详情见钉钉文档：https://open.dingtalk.com/document/group/custom-robot-access）
     * @return
     * @author fanyudong
     * @date 2022/5/7
     */
    public static void sendDingTalk(String webhook, Map<String, Object> msg){
        //设置请求头
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity request = new HttpEntity<>(msg, httpHeader);
        RestTemplate httpsRestTemplate = new RestTemplate();
        httpsRestTemplate.postForObject(webhook, request, JSONObject.class);
    }

    /**
     * @Description: 发送钉钉消息 @ 所有人
     * @Param: [webHook：钉钉群地址, content：消息内容, ifEnv：是否需要环境信息]
     * @Author: whh
     * @Date: 2022/10/19 19:24
     * @Version V8.2.9.2
     **/
    public static void sendDingTalk(String webHook, String content, Boolean ifEnv) {

        //消息内容Map
        Map<String, Object> textMap = new HashMap();
        if (ifEnv){
            String env = StringUtils.isEmpty(SpringContextUtils.getActiveProfile()) ? "" : SpringContextUtils.getActiveProfile();
            textMap.put("content", "【" + env.toUpperCase() + "】" + content);
        } else {
            textMap.put("content", content);
        }

        //消息封装
        Map<String, Object> msg = new HashMap();
        msg.put("msgtype", "text");
        // msg.put("at", atUsers);
        msg.put("text", textMap);
        //发送钉钉消息
        sendDingTalk(webHook, msg);
    }

    /**
     * @Description: 发送钉钉消息 @ 所有人
     * @Param: [webHook：钉钉群地址, content：消息内容, ifEnv：是否需要环境信息]
     * @Author: whh
     * @Date: 2022/10/19 19:24
     * @Version V8.2.9.2
     **/
    public static void sendDingTalkAtAll(String webHook, String content, Boolean ifEnv) {

        //消息内容Map
        Map<String, Object> textMap = new HashMap();
        if (ifEnv){
            String env = StringUtils.isEmpty(SpringContextUtils.getActiveProfile()) ? "" : SpringContextUtils.getActiveProfile();
            textMap.put("content", "【" + env.toUpperCase() + "】" + content);
        } else {
            textMap.put("content", content);
        }

        //需要 @ 所有人
        Map<String, Object> atUsers = new HashMap();
        atUsers.put("isAtAll", true);

        //消息封装
        Map<String, Object> msg = new HashMap();
        msg.put("msgtype", "text");
        msg.put("at", atUsers);
        msg.put("text", textMap);
        //发送钉钉消息
        sendDingTalk(webHook, msg);
    }

    /**
     * @Description: 发送钉钉消息 @ 所有人
     * @Param: [webHook：钉钉群地址, content：消息内容, ifEnv：是否需要环境信息,
     *          secret：钉钉群聊secret，添加钉钉机器人的对应的【安全设置】选择加签，生成的秘钥, atAll：是否需要at所有人，
     *          senderPhoneList：at指定人的手机号]
     * @Author: whh
     * @Date: 2022/10/19 19:24
     * @Version V8.2.9.2.3
     **/
    public static void sendDingTalkAll(String webHook, String content, Boolean ifEnv, String secret, Boolean atAll, List<String> senderPhoneList) {

        try {
            log.info("发送的消息内容：" + content);
            if (!StringUtils.isEmpty(secret)){
                Long timestamp = System.currentTimeMillis();
                String stringToSign = timestamp + "\n" + secret;
                Mac mac = Mac.getInstance("HmacSHA256");
                
                mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
                byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
                String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");

                webHook = webHook + "&timestamp=" + timestamp + "&sign=" + sign;
            }
            if (ObjectUtil.isEmpty(atAll)){
                atAll = Boolean.FALSE;
            }
            //消息内容Map
            Map<String, Object> textMap = new HashMap();
            if (ifEnv){
                String env = StringUtils.isEmpty(SpringContextUtils.getActiveProfile()) ? "" : SpringContextUtils.getActiveProfile();
                textMap.put("content", "【" + env.toUpperCase() + "】" + content);
            } else {
                textMap.put("content", content);
            }
            //消息封装
            Map<String, Object> msg = new HashMap();
            //需要 @ 所有人
            Map<String, Object> atUsers = new HashMap();
            msg.put("msgtype", "text");
            //atAll为true，at所有人
            if (atAll){
                atUsers.put("isAtAll", true);
                msg.put("at", atUsers);
            }
            //atAll为false，发送人手机号不为空，at指定人
            if (!atAll && !CollectionUtils.isEmpty(senderPhoneList)){
                atUsers.put("isAtAll", false);
                atUsers.put("atMobiles", senderPhoneList);
                msg.put("at", atUsers);
            }
            msg.put("text", textMap);
            //发送钉钉消息
            sendDingTalk(webHook, msg);

        } catch (Exception e) {
            log.error("推送钉钉消息异常：{}", ExceptionUtil.getStackTrace(e));
        }
    }

    /*public static void main(String[] args) {
        *//*List<String> senderPhoneList = new ArrayList<>();
        senderPhoneList.add("17601280363");
        String ccc = "流程 【合同未确认提醒】小白白专卖店 孙风星 邦基三维 豆粕 42% 吨 1000元 1吨 2021-04-30";
        sendDingTalkAll("https://oapi.dingtalk.com/robot/send?access_token=0a9ac529829c72103fd8c9e6e453c1d6e86159bdc40e6f1f8c38014b8934b586", ccc, Boolean.FALSE, null, Boolean.FALSE, senderPhoneList);
    *//*

        List<String> senderPhoneList = new ArrayList<>();
        senderPhoneList.add("17601280363");
        String ccc = "流程 【合同未确认提醒】小白白专卖店 孙风星 邦基三维 豆粕 42% 吨 1000元 1吨 2021-04-30";
        sendDingTalkAtByPhone("https://oapi.dingtalk.com/robot/send?access_token=0a9ac529829c72103fd8c9e6e453c1d6e86159bdc40e6f1f8c38014b8934b586", ccc,
                Boolean.FALSE, senderPhoneList);
    }*/

    /**
     * @Description: 发送钉钉消息 @ 指定人，根据手机号
     * @Param: [webHook：钉钉群地址, content：消息内容, ifEnv：是否需要环境信息，senderPhoneList：@ 人对应的手机号]
     * @Author: whh
     * @Date: 2022/10/19 19:24
     * @Version V8.2.9.2
     **/
    public static void sendDingTalkAtByPhone(String webHook, String content, Boolean ifEnv, List<String> senderPhoneList) {

        //消息内容Map
        Map<String, Object> textMap = new HashMap();
        if (ifEnv){
            String env = StringUtils.isEmpty(SpringContextUtils.getActiveProfile()) ? "" : SpringContextUtils.getActiveProfile();
            textMap.put("content", "【" + env.toUpperCase() + "】" + content);
        } else {
            textMap.put("content", content);
        }

        //需要 @ 所有人
        Map<String, Object> atUsers = new HashMap();
        atUsers.put("isAtAll", false);
        atUsers.put("atMobiles", senderPhoneList);

        //消息封装
        Map<String, Object> msg = new HashMap();
        msg.put("msgtype", "text");
        msg.put("at", atUsers);
        msg.put("text", textMap);
        //发送钉钉消息
        sendDingTalk(webHook, msg);
    }

    /**
     * @Description: 发送钉钉消息 @ 所有人
     * @Param: [webHook：钉钉群地址, content：消息内容, ifEnv：是否需要环境信息,
     *          secret：钉钉群聊secret，添加钉钉机器人的对应的【安全设置】选择加签，生成的秘钥, atAll：是否需要at所有人，
     *          senderPhoneList：at指定人的手机号]
     * @Author: whh
     * @Date: 2022/10/19 19:24
     * @Version V8.2.9.2.3
     **/
    public static void sendDingTalkAtUserId(String webHook, String content, Boolean ifEnv, String secret, List<String> senderDingUserIdList) {

        try {
            log.info("发送的消息内容：" + content);
            if (!StringUtils.isEmpty(secret)){
                Long timestamp = System.currentTimeMillis();
                String stringToSign = timestamp + "\n" + secret;
                Mac mac = Mac.getInstance("HmacSHA256");

                mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
                byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
                String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");

                webHook = webHook + "&timestamp=" + timestamp + "&sign=" + sign;
            }
            //消息内容Map
            Map<String, Object> textMap = new HashMap();
            if (ifEnv){
                String env = StringUtils.isEmpty(SpringContextUtils.getActiveProfile()) ? "" : SpringContextUtils.getActiveProfile();
                textMap.put("content", "【" + env.toUpperCase() + "】" + content);
            } else {
                textMap.put("content", content);
            }
            //消息封装
            Map<String, Object> msg = new HashMap();
            //需要 @ 所有人
            Map<String, Object> atUsers = new HashMap();
            msg.put("msgtype", "text");
            if (!CollectionUtils.isEmpty(senderDingUserIdList)){
                atUsers.put("isAtAll", false);
                atUsers.put("atUserIds", senderDingUserIdList);
                msg.put("at", atUsers);
            }
            msg.put("text", textMap);
            //发送钉钉消息
            sendDingTalk(webHook, msg);

        } catch (Exception e) {
            log.error("推送钉钉消息异常：{}",ExceptionUtil.getStackTrace(e));
        }
    }

    public static void main(String[] args) {
        sendDingTalkAll("https://oapi.dingtalk.com/robot/send?access_token=cca0db0776d04f9d129509896b5e4449a61eb6db64ce0f9237dba7b8beced8e5", "辅助下单成功，申请业务员：上海天坎离股份有限公司，客户：钱梅雨，操作时间：2023-05-23 18:00:30，玉米信息：null 玉米 null null，下单吨数：1，单价：1111.0，保证金：111.1，总金额：1111.0，下单时间：2023-05-23 18:00:30，提货期：2023-05-06-05.31，合同号：XYEDD20230523YYH03501，",
                false, "SEC3d9ce48b2521b039485f7c496a8fc95413cbf0aa91935038e6cd7e2ecaa25d37", null, null);
    }
}
