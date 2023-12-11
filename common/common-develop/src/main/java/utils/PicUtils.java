package utils;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;

/**
 * 图片压缩Utils
 *
 * @author worstEzreal
 * @version V1.1.0
 * @date 2018/3/12
 */
public class PicUtils {

    private static Logger logger = LoggerFactory.getLogger(PicUtils.class);

//    public static void main(String[] args) throws IOException {
//        byte[] bytes = FileUtils.readFileToByteArray(new File("D:\\1.jpg"));
//        long l = System.currentTimeMillis();
//        bytes = PicUtils.compressPicForScale(bytes, 300, "x");// 图片小于300kb
//        System.out.println(System.currentTimeMillis() - l);
//        FileUtils.writeByteArrayToFile(new File("D:\\dd1.jpg"), bytes);
//    }

    /**
     * 根据指定大小压缩图片
     *
     * @param imageBytes  源图片字节数组
     * @param desFileSize 指定图片大小，单位kb
     * @param   remark   备注
     * @return 压缩质量后的图片字节数组
     */
    public static byte[] compressPicForScale(byte[] imageBytes, long desFileSize, String remark) {
        if (imageBytes == null || imageBytes.length <= 0 || imageBytes.length < desFileSize * 1024) {
            return imageBytes;
        }
        long srcSize = imageBytes.length;
        double accuracy = getAccuracy(srcSize / 1024);
        try {
            while (imageBytes.length > desFileSize * 1024) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageBytes.length);
                Thumbnails.of(inputStream)
                        .scale(accuracy)
                        .outputQuality(accuracy)
                        .toOutputStream(outputStream);
                imageBytes = outputStream.toByteArray();
            }
            logger.info("【图片压缩】备注信息：{} | 图片原大小：{}kb | 压缩后大小：{}kb",
                    remark, srcSize / 1024, imageBytes.length / 1024);
        } catch (Exception e) {
            logger.error("【图片压缩】msg=图片压缩失败!", e);
        }
        return imageBytes;
    }

    /**
     * 自动调节精度(经验数值)
     *
     * @param size 源图片大小
     * @return 图片压缩质量比
     */
    private static double getAccuracy(long size) {
        double accuracy;
        if (size < 900) {
            accuracy = 0.85;
        } else if (size < 2047) {
            accuracy = 0.6;
        } else if (size < 3275) {
            accuracy = 0.44;
        } else {
            accuracy = 0.4;
        }
        return accuracy;
    }


    public static void main(String[] args) throws BiffException, IOException {
        String localFile = "E:\\补充影像资料文件夹\\" + DateUtils.format(new Date(), DateUtils.DATE_PATTERN)+"\\" ;
        File createFile = new File(localFile);
        if (!createFile.exists()) {
            if (!createFile.mkdirs()) {
                System.out.println("无文件路径，且生成文件路径失败！");
            }
        }
        String errorFile = localFile + "errorUrl.txt";
        String sqlFile = localFile + "sql.txt";
        String filePath = "C:\\Users\\PC\\Desktop\\用户影像资料.xls";
        StringBuilder sqlSb = new StringBuilder();
        StringBuilder errorSb = new StringBuilder();
        Workbook workbook = Workbook.getWorkbook(new File(filePath));
        Sheet sheet = workbook.getSheet(0);
        // 循环获取每一行数据 因为默认第一行为标题行，我们可以从 1 开始循环，如果需要读取标题行，从 0 开始
        for (int i = 0; i < sheet.getRows(); i++) {
            System.out.println(String.format("第%s行", i));
            // 获取第一列的第 i 行信息 sheet.getCell(列，行)，下标从0开始
            String userId = sheet.getCell(0, i).getContents();
            // 获取第二列的第 i 行信息
            String userName = sheet.getCell(1, i).getContents();
            // 获取第三列的第 i 行信息
            String frontCardUrl = sheet.getCell(2, i).getContents();
            if (StringUtils.isNotEmpty(frontCardUrl)) {
                String imageName = userId + "-" + userName + "-" + "身份证正面.jpg";
                File file = new File(localFile + imageName);
                try {
                    BufferedImage image = ImageIO.read(new URL(frontCardUrl));
                    ImageIO.write(image, "jpg", file);
                    System.out.println("身份证正面读取图片正常");
                } catch (IOException e) {
                    String replaceUrl = frontCardUrl.replaceAll("https://wap.liangbaba.com:8090/", "https://lbbbusinessnode.oss-cn-shanghai.aliyuncs.com/");
                    try {
                        BufferedImage image = ImageIO.read(new URL(replaceUrl));
                        sqlSb.append("UPDATE u_certification SET front_card_url ='").append(replaceUrl).append("' WHERE user_id = ").append(userId).append(";\n");
                        ImageIO.write(image, "jpg", file);
                        System.out.println("主体id：" + userId + "主体名称：" + userName + "fastdfs不能访问，OSS可以访问\n");
                    } catch (IOException ex) {
                        errorSb.append("主体id：").append(userId).append("主体名称").append(userName).append("身份证正面读取图片异常，fastdfs不能访问，OSS也不能访问\n");
                        System.out.println("主体id：" + userId + "主体名称：" + userName + "身份证正面读取图片异常:" + replaceUrl + "\n");
                    }
                }
            }
            // 获取第四列的第 i 行信息
            String reverseCardUrl = sheet.getCell(3, i).getContents();
            if (StringUtils.isNotEmpty(reverseCardUrl)) {
                String imageName = userId + "-" + userName + "-" + "身份证反面.jpg";
                File file = new File(localFile + imageName);
                try {
                    BufferedImage image = ImageIO.read(new URL(reverseCardUrl));
                    ImageIO.write(image, "jpg", file);
                    System.out.println("身份证反面读取图片正常");
                } catch (IOException e) {
                    String replaceUrl = reverseCardUrl.replaceAll("https://wap.liangbaba.com:8090/", "https://lbbbusinessnode.oss-cn-shanghai.aliyuncs.com/");
                    try {
                        BufferedImage image = ImageIO.read(new URL(replaceUrl));
                        sqlSb.append("UPDATE u_certification SET reverse_card_url ='").append(replaceUrl).append("' WHERE user_id = ").append(userId).append(";\n");
                        ImageIO.write(image, "jpg", file);
                        System.out.println("主体id：" + userId + "主体名称：" + userName + "fastdfs不能访问，OSS可以访问\n");
                    } catch (IOException ex) {
                        errorSb.append("主体id：").append(userId).append("主体名称").append(userName).append("身份证反面读取图片异常,fastdfs不能访问，OSS也不能访问\n");
                        System.out.println("主体id：" + userId + "主体名称：" + userName + "身份证反面读取图片异常:" + replaceUrl + "\n");
                    }

                }
            }
            // 获取第五列的第 i 行信息
            String businessLicenseUrl = sheet.getCell(4, i).getContents();
            if (StringUtils.isNotEmpty(businessLicenseUrl)) {
                String imageName = userId + "-" + userName + "-" + "营业执照.jpg";
                File file = new File(localFile + imageName);
                try {
                    BufferedImage image = ImageIO.read(new URL(businessLicenseUrl));
                    ImageIO.write(image, "jpg", file);
                    System.out.println("营业执照读取图片正常");
                } catch (IOException e) {
                    String replaceUrl = businessLicenseUrl.replaceAll("https://wap.liangbaba.com:8090/", "https://lbbbusinessnode.oss-cn-shanghai.aliyuncs.com/");
                    try {
                        BufferedImage image = ImageIO.read(new URL(replaceUrl));
                        sqlSb.append("UPDATE u_certification SET business_license_url ='").append(replaceUrl).append("' WHERE user_id = ").append(userId).append(";\n");
                        ImageIO.write(image, "jpg", file);
                        System.out.println("主体id：" + userId + "主体名称：" + userName + "fastdfs不能访问，OSS可以访问\n");
                    } catch (IOException ex) {
                        errorSb.append("主体id：").append(userId).append("主体名称").append(userName).append("营业执照读取图片异常，fastdfs不能访问，OSS也不能访问\n");
                        System.out.println("主体id：" + userId + "主体名称：" + userName + "营业执照读取图片异常:" + replaceUrl + "\n");
                    }
                }
            }
            // 获取第六列的第 i 行信息
            String legalFrontCardUrl = sheet.getCell(5, i).getContents();
            if (StringUtils.isNotEmpty(legalFrontCardUrl)) {
                String imageName = userId + "-" + userName + "-" + "法人身份证正面.jpg";
                File file = new File(localFile + imageName);
                try {
                    BufferedImage image = ImageIO.read(new URL(legalFrontCardUrl));
                    ImageIO.write(image, "jpg", file);
                    System.out.println("法人身份证正面读取图片正常");
                } catch (IOException e) {
                    String replaceUrl = legalFrontCardUrl.replaceAll("https://wap.liangbaba.com:8090/", "https://lbbbusinessnode.oss-cn-shanghai.aliyuncs.com/");
                    try {
                        BufferedImage image = ImageIO.read(new URL(replaceUrl));
                        sqlSb.append("UPDATE u_certification SET legal_front_card_url ='").append(replaceUrl).append("' WHERE user_id = ").append(userId).append(";\n");
                        ImageIO.write(image, "jpg", file);
                        System.out.println("主体id：" + userId + "主体名称：" + userName + "fastdfs不能访问，OSS可以访问\n");
                    } catch (IOException ex) {
                        errorSb.append("主体id：").append(userId).append("主体名称").append(userName).append("法人身份证正面图片异常，fastdfs不能访问，OSS也不能访问\n");
                        System.out.println("主体id：" + userId + "主体名称：" + userName + "法人身份证正面图片异常:" + replaceUrl + "\n");
                    }
                }
            }
            // 获取第七列的第 i 行信息
            String legalReverseCardUrl = sheet.getCell(6, i).getContents();
            if (StringUtils.isNotEmpty(legalReverseCardUrl)) {
                String imageName = userId + "-" + userName + "-" + "法人身份证反面.jpg";
                File file = new File(localFile + imageName);
                try {
                    BufferedImage image = ImageIO.read(new URL(legalReverseCardUrl));
                    ImageIO.write(image, "jpg", file);
                    System.out.println("法人身份证反面读取图片正常");
                } catch (IOException e) {
                    String replaceUrl = legalReverseCardUrl.replaceAll("https://wap.liangbaba.com:8090/", "https://lbbbusinessnode.oss-cn-shanghai.aliyuncs.com/");
                    try {
                        BufferedImage image = ImageIO.read(new URL(replaceUrl));
                        sqlSb.append("UPDATE u_certification SET legal_reverse_card_url ='").append(replaceUrl).append("' WHERE user_id = ").append(userId).append(";\n");
                        ImageIO.write(image, "jpg", file);
                        System.out.println("主体id：" + userId + "主体名称：" + userName + "fastdfs不能访问，OSS可以访问\n");
                    } catch (IOException ex) {
                        errorSb.append("主体id：").append(userId).append("主体名称").append(userName).append("法人身份证反面图片异常，fastdfs不能访问，OSS也不能访问\n");
                        System.out.println("主体id：" + userId + "主体名称：" + userName + "法人身份证反面图片异常:" + replaceUrl + "\n");
                    }

                }
            }

        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(sqlFile))) {
            writer.println(sqlSb.toString());
            System.out.println("内容成功写入sql文件！");
        } catch (IOException e) {
            System.out.println("写入文件时出现错误：" + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(errorFile))) {
            writer.println(errorSb.toString());
            System.out.println("内容成功写入errorFile文件！");
        } catch (IOException e) {
            System.out.println("写入文件时出现错误：" + e.getMessage());
        }
    }
}