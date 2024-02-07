package utils;


import constant.NumberConstant;
import enums.EnvEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import java.util.List;
import java.util.Properties;

/**
 * @projectName: yuanyaotech
 * @package: com.yuanyaotech.common.utils
 * @description: 发送邮件
 * @author: zhangdizhong
 * @create: 2019/10/08 16:41
 * @version: 1.0
 */
public class EmailUtil {

    private static final Logger log = LoggerFactory.getLogger(EmailUtil.class);

    public static boolean sendMail(String content, String fileName, String reUserMail) {
        log.info("[ 开始发送邮件... ]");
        Transport transport = null;
        try {
            //处理文件名乱码问题
            System.setProperty("mail.mime.splitlongparameters", "false");

            Properties props = new Properties();
            //协议
            props.setProperty("mail.transport.protocol", "ssl");
            // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
            props.put("mail.smtp.host", "smtp.exmail.qq.com");
            // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.connectiontimeout", "10000");
            props.put("mail.smtp.timeout", "10000");
            props.put("mail.smtp.writetimeout", "10000");
            props.setProperty("mail.smtp.port", "465");

            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            // 用刚刚设置好的props对象构建一个session
            Session session = Session.getDefaultInstance(props);
            log.info("props对象构建一个session成功");

            // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
            // 用（你可以在控制台（console)上看到发送邮件的过程）
            session.setDebug(false);
            // 用session为参数定义消息对象
            MimeMessage message = new MimeMessage(session);
            // 加载发件人地址
            if ((!StringUtils.isEmpty(content) && content.contains(EnvEnum.getEnvNameByFlag(NumberConstant.FORE.toString()))) ||
                    (!StringUtils.isEmpty(fileName) && fileName.contains(EnvEnum.getEnvNameByFlag(NumberConstant.FORE.toString())))) {
                message.setFrom(new InternetAddress("monitor@liangbaba.com"));
            }else {
                message.setFrom(new InternetAddress("monitor-dev@liangbaba.com"));
            }

            // 加载收件人地址
            String[] sendTos = reUserMail.split(",");
            //String[] sendTos = {"wanghengheng@liangbaba.com"};
            InternetAddress[] sendTo = new InternetAddress[sendTos.length];
            for (int i = 0; i < sendTos.length; i++) {
                //System.out.println("发送到:" + sendTos[i]);
                sendTo[i] = new InternetAddress(sendTos[i]);
            }
            message.setRecipients(Message.RecipientType.TO, sendTo);
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress(reUserMail));
            // 加载标题
            message.setSubject(fileName);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText(content);
            multipart.addBodyPart(contentPart);

            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();
            log.info("构建邮件主题成功------------");
            // 发送邮件
            transport = session.getTransport("smtp");
            log.info("session.getTransport成功");
            // 连接服务器的邮箱itgvdpupxayfbbac

            /**
             * 发送邮件的类型
             * 发件人邮箱
             * 发件人邮箱授权码
             * itgvdpupxayfbbac
             *
             */
            if ((!StringUtils.isEmpty(content) && content.contains(EnvEnum.getEnvNameByFlag(NumberConstant.FORE.toString()))) ||
                    (!StringUtils.isEmpty(fileName) && fileName.contains(EnvEnum.getEnvNameByFlag(NumberConstant.FORE.toString())))) {
                transport.connect("smtp.exmail.qq.com", "monitor@liangbaba.com", "m123456LBB");
            }else {
                transport.connect("smtp.exmail.qq.com", "monitor-dev@liangbaba.com", "Liangbb888");
            }

            log.info("连接到邮件服务器成功");
            // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            log.info("从邮件服务器发送邮件成功");
            return true;
        } catch (Exception e) {
            log.info("邮件服务器异常，原因：{}", ExceptionUtil.getStackTrace(e));
            e.printStackTrace();
            return false;
        } finally {
            try {
                transport.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean sendMail(String content, String fileName, String reUserMail, String environment) {
        log.info("[ 开始发送邮件... ]");
        Transport transport = null;
        try {
            //处理文件名乱码问题
            System.setProperty("mail.mime.splitlongparameters", "false");

            Properties props = new Properties();
            //协议
            props.setProperty("mail.transport.protocol", "ssl");
            // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
            props.put("mail.smtp.host", "smtp.exmail.qq.com");
            // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.connectiontimeout", "10000");
            props.put("mail.smtp.timeout", "10000");
            props.put("mail.smtp.writetimeout", "10000");
            props.setProperty("mail.smtp.port", "465");

            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            // 用刚刚设置好的props对象构建一个session
            Session session = Session.getDefaultInstance(props);
            log.info("props对象构建一个session成功");

            // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
            // 用（你可以在控制台（console)上看到发送邮件的过程）
            session.setDebug(false);
            // 用session为参数定义消息对象
            MimeMessage message = new MimeMessage(session);
            if (StringUtils.isBlank(environment)){
                // 加载发件人地址
                if ((!StringUtils.isEmpty(content) && content.contains(EnvEnum.getEnvNameByFlag(NumberConstant.FORE.toString()))) ||
                        (!StringUtils.isEmpty(fileName) && fileName.contains(EnvEnum.getEnvNameByFlag(NumberConstant.FORE.toString())))) {
                    message.setFrom(new InternetAddress("monitor@liangbaba.com"));
                }else {
                    message.setFrom(new InternetAddress("monitor-dev@liangbaba.com"));
                }
            }else {
                if ("prod".equals(environment)) {
                    message.setFrom(new InternetAddress("monitor@liangbaba.com"));
                }else {
                    message.setFrom(new InternetAddress("monitor-dev@liangbaba.com"));
                }
            }


            // 加载收件人地址
            String[] sendTos = reUserMail.split(",");
            //String[] sendTos = {"wanghengheng@liangbaba.com"};
            InternetAddress[] sendTo = new InternetAddress[sendTos.length];
            for (int i = 0; i < sendTos.length; i++) {
                //System.out.println("发送到:" + sendTos[i]);
                sendTo[i] = new InternetAddress(sendTos[i]);
            }
            message.setRecipients(Message.RecipientType.TO, sendTo);
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress(reUserMail));
            // 加载标题
            message.setSubject(fileName);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText(content);
            multipart.addBodyPart(contentPart);

            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();
            log.info("构建邮件主题成功------------");
            // 发送邮件
            transport = session.getTransport("smtp");
            log.info("session.getTransport成功");
            // 连接服务器的邮箱itgvdpupxayfbbac

            if (StringUtils.isBlank(environment)){
                /**
                 * 发送邮件的类型
                 * 发件人邮箱
                 * 发件人邮箱授权码
                 * itgvdpupxayfbbac
                 *
                 */
                if ((!StringUtils.isEmpty(content) && content.contains(EnvEnum.getEnvNameByFlag(NumberConstant.FORE.toString()))) ||
                        (!StringUtils.isEmpty(fileName) && fileName.contains(EnvEnum.getEnvNameByFlag(NumberConstant.FORE.toString())))) {
                    transport.connect("smtp.exmail.qq.com", "monitor@liangbaba.com", "m123456LBB");
                }else {
                    transport.connect("smtp.exmail.qq.com", "monitor-dev@liangbaba.com", "Liangbb888");
                }
            }else {
                if ("prod".equals(environment)) {
                    transport.connect("smtp.exmail.qq.com", "monitor@liangbaba.com", "m123456LBB");
                }else {
                    transport.connect("smtp.exmail.qq.com", "monitor-dev@liangbaba.com", "Liangbb888");
                }
            }

            log.info("连接到邮件服务器成功");
            // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            log.info("从邮件服务器发送邮件成功");
            return true;
        } catch (Exception e) {
            log.info("邮件服务器异常，原因：{}", ExceptionUtil.getStackTrace(e));
            e.printStackTrace();
            return false;
        } finally {
            try {
                transport.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean sendMailAppendix(String content, String fileName, String reUserMail, String environment
            , List<String> fileMap,String email,String password) {
        log.info("[ 开始发送邮件... ]");
        Transport transport = null;
        try {
            //处理文件名乱码问题
            System.setProperty("mail.mime.splitlongparameters", "false");

            Properties props = new Properties();
            //协议
            props.setProperty("mail.transport.protocol", "ssl");
            // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
            props.put("mail.smtp.host", "smtp.exmail.qq.com");
            // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.connectiontimeout", "10000");
            props.put("mail.smtp.timeout", "10000");
            props.put("mail.smtp.writetimeout", "10000");
            props.setProperty("mail.smtp.port", "465");

            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            // 用刚刚设置好的props对象构建一个session
            Session session = Session.getDefaultInstance(props);
            log.info("props对象构建一个session成功");

            // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
            // 用（你可以在控制台（console)上看到发送邮件的过程）
            session.setDebug(false);
            // 用session为参数定义消息对象
            MimeMessage message = new MimeMessage(session);
           /* message.setFrom(new InternetAddress(email));*/
            if (StringUtils.isBlank(environment)){
                // 加载发件人地址
                if ((!StringUtils.isEmpty(content) && content.contains(EnvEnum.getEnvNameByFlag(NumberConstant.FORE.toString()))) ||
                        (!StringUtils.isEmpty(fileName) && fileName.contains(EnvEnum.getEnvNameByFlag(NumberConstant.FORE.toString())))) {
                    message.setFrom(new InternetAddress(email));
                }else {
                    message.setFrom(new InternetAddress(email));
                }
            }else {
                if ("prod".equals(environment)) {
                    message.setFrom(new InternetAddress(email));
                }else {
                    message.setFrom(new InternetAddress(email));
                }
            }


            // 加载收件人地址
            String[] sendTos = reUserMail.split(",");
            //String[] sendTos = {"wanghengheng@liangbaba.com"};
            InternetAddress[] sendTo = new InternetAddress[sendTos.length];
            for (int i = 0; i < sendTos.length; i++) {
                //System.out.println("发送到:" + sendTos[i]);
                sendTo[i] = new InternetAddress(sendTos[i]);
            }
            message.setRecipients(Message.RecipientType.TO, sendTo);
            //message.addRecipient(Message.RecipientType.TO, new InternetAddress(reUserMail));
            // 加载标题
            message.setSubject(fileName);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText(content);
            multipart.addBodyPart(contentPart);

            //添加附件
            for(String item:fileMap){
                MimeBodyPart mbp=new MimeBodyPart();
                DataHandler dataHandler=new DataHandler(new FileDataSource(item));
                mbp.setDataHandler(dataHandler);
                String fileNameEncode= MimeUtility.encodeWord(dataHandler.getName(),"utf-8","B");
                mbp.setFileName(fileNameEncode);
                multipart.addBodyPart(mbp);
            }

            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();
            log.info("构建邮件主题成功------------");
            // 发送邮件
            transport = session.getTransport("smtp");
            log.info("session.getTransport成功");
            // 连接服务器的邮箱itgvdpupxayfbbac

            if (StringUtils.isBlank(environment)){
                /**
                 * 发送邮件的类型
                 * 发件人邮箱
                 * 发件人邮箱授权码
                 * itgvdpupxayfbbac
                 *
                 */
           // transport.connect("smtp.exmail.qq.com", "monitor-dev@liangbaba.com", "Liangbb888");
           // transport.connect("smtp.exmail.qq.com", "monitor@liangbaba.com", "m123456LBB");
                if ((!StringUtils.isEmpty(content) && content.contains(EnvEnum.getEnvNameByFlag(NumberConstant.FORE.toString()))) ||
                        (!StringUtils.isEmpty(fileName) && fileName.contains(EnvEnum.getEnvNameByFlag(NumberConstant.FORE.toString())))) {
                    transport.connect("smtp.exmail.qq.com", email, password);
                }else {
                    transport.connect("smtp.exmail.qq.com", email, password);
                }
            }else {
                if ("prod".equals(environment)) {
                    transport.connect("smtp.exmail.qq.com", email, password);
                }else {
                    transport.connect("smtp.exmail.qq.com", email, password);
                }
            }

            log.info("连接到邮件服务器成功");
            // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            log.info("从邮件服务器发送邮件成功");
            return true;
        } catch (Exception e) {
            log.info("邮件服务器异常，原因：{}", ExceptionUtil.getStackTrace(e));
            e.printStackTrace();
            return false;
        } finally {
            try {
                transport.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
