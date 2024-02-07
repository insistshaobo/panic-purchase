package utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @projectName: yuanyaotech
 * @package: com.yuanyaotech.common.utils
 * @description: 异常收集工具类
 * @author: zhangdizhong
 * @create: 2019/10/08 15:03
 * @version: 1.0
 */
public class ExceptionUtil {

    /**
     * 获取异常中的堆栈信息
     *
     * @param throwable
     * @return
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try {
            throwable.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }

    /**
     * 获取异常中的堆栈信息
     *
     * @param throwable
     * @return 堆栈信息
     */
    public static String getFirstStackTrace(Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        try {
            //输入异常信息
            sb.append(throwable.getMessage());
            StackTraceElement[] stackTrace = throwable.getStackTrace();
            if (stackTrace != null) {
                //只获取堆栈信息的前五行的异常消息
                for (int i = 0; i < Math.min(stackTrace.length, 5); i++) {
                    sb.append("\n").append(stackTrace[i].toString());
                }
            }
        }catch (Exception e){
            sb.append(e.getMessage());
        }
        return sb.toString();
    }
}
