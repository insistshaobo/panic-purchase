package utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * @version V8.4.7
 * @ClassName: DesEncryptUtil
 * @Description: 字符串加解密
 * @date: 2023 2023/2/24  1:49 PM
 */
@Slf4j
public class DesEncryptUtil {

    public static final String key = "wadq12dssas64353fds";

    /**
     * @return 加密后的字符串的base64格式
     * @Description: 加密
     * @Param sourceStr 原始未加密明文 key 密钥，长度必须大于等于8位
     * @Date: 2021/4/8 13:24
     **/
    public static String encrypt(String sourceStr, String key) {
        try {
            if (sourceStr == null || sourceStr.isEmpty()) {
                throw new Exception("原文不能为空！");
            }
            if (key == null || key.isEmpty() || key.length() < 8) {
                throw new Exception("密钥不能为空，且密钥必须大于等于8位！");
            }
            byte[] datasource = sourceStr.getBytes("utf-8");
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes("utf-8"));

            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);

            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");

            //用密匙初始化Cipher对象,ENCRYPT_MODE用于将 Cipher 初始化为加密模式的常量
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);

            //现在，获取数据并加密 正式执行加密操作
            //按单部分操作加密或解密数据，或者结束一个多部分操作
            return Base64.encodeBase64String(cipher.doFinal(datasource));

        } catch (Throwable e) {
            log.error("DES 加密异常，详情：" + e.toString());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return 解密后的字符串
     * @Description: 解密
     * @Param encodedStrBase64 加密过的密文base64形式（由加密方法返回的）  key 密钥，长度必须大于等于8位
     * @Date: 2021/4/8 13:27
     **/
    public static String decrypt(String encodedStrBase64, String key) throws Exception {
        if (encodedStrBase64 == null || encodedStrBase64.isEmpty()) {
            throw new Exception("密文不能为空！");
        }

        if (key == null || key.isEmpty() || key.length() < 8) {
            throw new Exception("密钥不能为空，且密钥必须大于等于8位！");
        }

        byte[] src = Base64.decodeBase64(encodedStrBase64);

        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(key.getBytes("utf-8"));
        // 创建一个密匙工厂
        // 返回实现指定转换的 Cipher 对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return new String(cipher.doFinal(src));
    }

    public static Long getDecryptUserId(String userIdCode) {
        Long userId = null;
        try{
            userId = Long.valueOf(DesEncryptUtil.decrypt(userIdCode, DesEncryptUtil.key));
        }catch (Exception e){
            log.error("userId解密出现异常:{}",e);
        }
        return userId;
    }

}