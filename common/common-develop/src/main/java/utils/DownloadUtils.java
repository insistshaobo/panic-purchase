package utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Description: 下载工具类
 * @Author zhanglianhai
 * @Date 2023/3/21
 * @Version V8.5
 **/
@Slf4j
public class DownloadUtils {

    /***
     * @Description: 根据地址下载文件
     * @Param: name 新文件名
     * @Param: url 文件链接地址
     * @Author: zhanglianhai
     * @Date: 2023/3/21
     * @Version V8.5
     **/
    public static String download(String name,String path,String localPath) {
        if(name.length()>20){
            name = name.replaceAll("\\/", "-");
        }
        String extense = path.substring(path.lastIndexOf("."));
        InputStream is = null;
        FileOutputStream os = null;
        try {
            URL url = new URL(path);
            URLConnection con = url.openConnection();
            is = con.getInputStream();
            byte[] bs = new byte[1024];
            int len;
            String filename = localPath+name+extense;
            File file = FileOperateUtils.getFile(filename);
            os = new FileOutputStream(file, true);
            // 开读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            return filename;
        } catch (Exception e) {
            log.error("合同文件上传sftp先根据pdf链接下载到本地异常：{}",e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


//    public static void main(String[] args) {
//        try {
//			/*// 如果需要通过URL获取资源的加上以下的代码，不需要的省略就行
//			URL url = new URL(strURL);
//			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//			// 设置超时间为3秒
//			conn.setConnectTimeout(3*1000);
//			// 防止屏蔽程序抓取而返回403错误
//			conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//			// 获取输入流
//			InputStream inputStream = conn.getInputStream();
//			Workbook workbook  = Workbook.getWorkbook(inputStream);
//			......*/
//
//            String fileUrl = "https://prewap.liangbaba.com:8090/storage/M00/04/F5/rBO3o2QZlmKALl6yAAapD3WSPT4656.pdf";
//            String fileName = "zhang测试";
//            download(fileName,fileUrl,"E:\\home\\");
//            System.out.println("end");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 压缩文件
     * @param datumName 压缩包名称
     * @param filePathList 附件路径
     * @return File
     * @throws Exception Exception
     */
    public static File compressedFileToZip(String datumName, List<String> filePathList,String path) throws Exception {
        //压缩包具体名称（拼接时间戳防止重名）
        String zipFileName = datumName + "-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".zip";
        //生成压缩包存储地址（最后会删掉）
        String fileZip = path + zipFileName;
        OutputStream os=null;
        ZipOutputStream zos = null ;
        File file = new File(fileZip);
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            os=new FileOutputStream(file);
            //压缩文件
            zos = new ZipOutputStream(os);
            byte[] buf = new byte[1024];
            for (String filePath : filePathList) {
                File tempFile = new File(filePath);
                //在压缩包中添加文件夹
                //zos.putNextEntry(new ZipEntry("测试/"+tempFile.getName()));
                //直接在压缩包中添加文件
                zos.putNextEntry(new ZipEntry(tempFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(tempFile);
                while ((len = in.read(buf)) != -1){
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("文件打包异常:"+e.getMessage());
            throw new Exception("文件打包:"+e.getMessage());
        }finally {
            //关闭流
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //关闭流
            if(os!= null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     * 输出文件流到response
     * @param fileName fileName
     * @param file file
     * @throws IOException IOException
     */
    public static void writeFileToRes( String fileName, File file) throws IOException {

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        FileInputStream inputStream = new FileInputStream(file);
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("application/zip;charset=utf-8");
        //2.设置文件头：最后一个参数是设置下载文件名
        response.setHeader("Content-disposition",
                String.format("attachment; filename=\"%s\"", URLEncoder.encode(fileName,"UTF-8")));
        response.addHeader("Content-Length", "" + file.length());
        response.setCharacterEncoding("UTF-8");
        //3.通过response获取ServletOutputStream对象(out)
        ServletOutputStream out = response.getOutputStream();

        int b = 0;
        byte[] buffer = new byte[1024];
        while (b != -1) {
            b = inputStream.read(buffer);
            //4.写到输出流(out)中
            out.write(buffer, 0, b);
        }
        out.flush();
        out.close();
        inputStream.close();

    }

    /**
     * 文件下载
     *
     * @param fileName  请求
     * @param file
     */
    public static void downloadFile(String fileName, File file) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            inputStream =  new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment;filename=" + EncodingUtils.convertToFileName(request, fileName));
            // 获取输出流
            outputStream = response.getOutputStream();
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            log.error("文件下载出错", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
