package utils;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.FileWriterWithEncoding;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

/**
 * @Author zhouda
 * @Date 2023-06-30
 * @Version V1.0
 */
@Slf4j
public class ImageUtils {
    /**
     * 获取图片大小KB
     * @param bytes
     * @return
     */
    public static float bytes2kb(long bytes){
        BigDecimal filesize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
        if(returnValue > 1){
            return returnValue;
        }
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP).floatValue();
        return returnValue;
    }

    /**
     * 获取图片字节
     * @param urlPath
     * @return
     */
    public static byte[] getImageFromURL(String urlPath) {
        byte[] data = null;
        InputStream is = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            // conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(6000);
            is = conn.getInputStream();
            if (conn.getResponseCode() == 200) {
                data = readInputStream(is);
            } else {
                data = null;
            }
        } catch (MalformedURLException e) {
            log.error("MalformedURLException", e);
        } catch (IOException e) {
            log.error("IOException", e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                log.error("IOException", e);
            }
            conn.disconnect();
        }
        return data;
    }
    public static byte[] readInputStream(InputStream is) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = -1;
        try {
            while ((length = is.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            baos.flush();
        } catch (IOException e) {
            log.error("IOException", e);
        }
        byte[] data = baos.toByteArray();
        try {
            is.close();
            baos.close();
        } catch (IOException e) {
            log.error("IOException", e);
        }
        return data;
    }

    /**
     * 通过BufferedImage图片流调整图片大小
     * 指定压缩后长宽
     */
    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_AREA_AVERAGING);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    /**
     * 通过BufferedImage图片流调整图片大小
     * @param originalImage
     * @param reduceMultiple 缩小倍数
     * @return
     * @throws IOException
     */
    public static BufferedImage resizeImage(BufferedImage originalImage, float reduceMultiple) throws IOException {
        int width = (int) (originalImage.getWidth() * reduceMultiple);
        int height = (int) (originalImage.getHeight() * reduceMultiple);
        Image resultingImage = originalImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    /**
     * 压缩图片到指定大小
     * @param srcImgData
     * @param reduceMultiple 每次压缩比率
     * @return
     * @throws IOException
     */
    public static byte[] resizeImage(byte[] srcImgData, float reduceMultiple) {
        BufferedImage bi = null;
        ByteArrayOutputStream bOut;
        try {
            bi = ImageIO.read(new ByteArrayInputStream(srcImgData));
            int width = (int) (bi.getWidth() * reduceMultiple); // 源图宽度
            int height = (int) (bi.getHeight() * reduceMultiple); // 源图高度
            Image image = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.setColor(Color.RED);
            g.drawImage(image, 0, 0, null); // 绘制处理后的图
            g.dispose();
            bOut = new ByteArrayOutputStream();
            ImageIO.write(tag, "JPEG", bOut);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bOut.toByteArray();
    }


    /**
     * BufferedImage图片流转byte[]数组
     */
    public static byte[] imageToBytes(BufferedImage bImage) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }


    /**
     * byte[]数组转BufferedImage图片流
     */
    private static BufferedImage bytesToBufferedImage(byte[] ImageByte) {
        ByteArrayInputStream in = new ByteArrayInputStream(ImageByte);
        BufferedImage image = null;
        try {
            image = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static InputStream getInputStreamByUrl(String urlPath) throws IOException {
        URL url = new URL(urlPath);
        URLConnection urlConnection = url.openConnection();
        urlConnection.setAllowUserInteraction(false);
        return url.openStream();
    }

    public static void fileAppendContent(File file, String content) {
        try {
            FileWriterWithEncoding fw = new FileWriterWithEncoding(file,"GB2312",true);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
