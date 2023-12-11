package utils;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangshaobo
 * @version 1.0
 * @Description: 脱敏工具类
 * @date 2023/5/6 14:12
 */
public class DesensitizationUtil {

    // 手机号码前三后四脱敏
    public static String mobileEncrypt(String mobile) {
        if (StringUtils.isEmpty(mobile) || (mobile.length() != 11)) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 【中文姓名】只显示第一个汉字，其他隐藏为2个星号，比如：李**
     *
     * @param fullName 姓名
     * @return 脱敏后的姓名
     */
    public static String chineseName(String fullName) {
        if (StrUtil.isBlank(fullName)) {
            return StrUtil.EMPTY;
        }
        return StrUtil.hide(fullName, 1, fullName.length());
    }

    /**
     * 【身份证号】前1位 和后2位
     *
     * @param idCardNum 身份证
     * @param front     保留：前面的front位数；从1开始
     * @param end       保留：后面的end位数；从1开始
     * @return 脱敏后的身份证
     */
    public static String idCardNum(String idCardNum, int front, int end) {
        //身份证不能为空
        if (StrUtil.isBlank(idCardNum)) {
            return StrUtil.EMPTY;
        }
        //需要截取的长度不能大于身份证号长度
        if ((front + end) > idCardNum.length()) {
            return StrUtil.EMPTY;
        }
        //需要截取的不能小于0
        if (front < 0 || end < 0) {
            return StrUtil.EMPTY;
        }
        return StrUtil.hide(idCardNum, front, idCardNum.length() - end);
    }

    /**
     * 【固定电话 前四位，后两位
     *
     * @param num 固定电话
     * @return 脱敏后的固定电话；
     */
    public static String fixedPhone(String num) {
        if (StrUtil.isBlank(num)) {
            return StrUtil.EMPTY;
        }
        return StrUtil.hide(num, 4, num.length() - 2);
    }

    /**
     * 【手机号码】前三位，后4位，其他隐藏，比如135****2210
     *
     * @param num 移动电话；
     * @return 脱敏后的移动电话；
     */
    public static String mobilePhone(String num) {
        if (StrUtil.isBlank(num)) {
            return StrUtil.EMPTY;
        }
        return StrUtil.hide(num, 3, num.length() - 4);
    }

    /**
     * 【地址】只显示到地区，不显示详细地址，比如：北京市海淀区****
     *
     * @param address       家庭住址
     * @param sensitiveSize 敏感信息长度
     * @return 脱敏后的家庭地址
     */
    public static String address(String address, int sensitiveSize) {
        if (StrUtil.isBlank(address)) {
            return StrUtil.EMPTY;
        }
        int length = address.length();
        return StrUtil.hide(address, length - sensitiveSize, length);
    }

    /**
     * 【电子邮箱】邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示，比如：d**@126.com
     *
     * @param email 邮箱
     * @return 脱敏后的邮箱
     */
    public static String email(String email) {
        if (StrUtil.isBlank(email)) {
            return StrUtil.EMPTY;
        }
        int index = StrUtil.indexOf(email, '@');
        if (index <= 1) {
            return email;
        }
        return StrUtil.hide(email, 1, index);
    }

    /**
     * 银行卡号脱敏
     * eg: 1101 **** **** **** 3256
     *
     * @param bankCardNo 银行卡号
     * @return 脱敏之后的银行卡号
     * @since 5.6.3
     */
    public static String bankCard(String bankCardNo) {
        if (StrUtil.isBlank(bankCardNo)) {
            return bankCardNo;
        }
        bankCardNo = StrUtil.trim(bankCardNo);
        if (bankCardNo.length() < 9) {
            return bankCardNo;
        }

        final int length = bankCardNo.length();
        final int midLength = length - 8;
        final StringBuilder buf = new StringBuilder();

        buf.append(bankCardNo, 0, 4);
        for (int i = 0; i < midLength; ++i) {
            if (i % 4 == 0) {
                buf.append(CharUtil.SPACE);
            }
            buf.append('*');
        }
        buf.append(CharUtil.SPACE).append(bankCardNo, length - 4, length);
        return buf.toString();
    }

    /**
     * 中文姓名脱敏
     * @param fullName 姓名
     * @return 脱敏结果
     */
    public static String maskName(String fullName) {
        if (fullName == null || fullName.trim().length() == 0) {
            return "";
        }
        fullName = fullName.trim();
        int length = fullName.length();
        if (length <= 2) {
            return fullName.replaceAll("(?<=\\S)(\\S+)$", "*");
        } else {
            String firstChar = fullName.substring(0, 1);
            String otherChars = fullName.substring(1, length - 1); // 不脱敏首尾汉字
            StringBuilder sb = new StringBuilder();
            sb.append(firstChar);
            for (int i = 0; i < otherChars.length(); i++) {
                sb.append("*");
            }
            sb.append(fullName.charAt(length - 1));
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        String name1 = "张三";
        String name1Expected = "张*三";
        System.out.println(maskName(name1));

        String name2 = "李四丰";
        String name2Expected = "李*丰";
        System.out.println(maskName(name2));

        String name3 = "李三四丰";
        System.out.println(maskName(name3));


    }
}

