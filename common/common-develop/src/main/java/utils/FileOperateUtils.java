package utils;

import exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

/**
 * @Description: 文件操作工具类
 * @Author zhanglianhai
 * @Date 2023/3/9
 * @Version V8.4.5.9
 **/
@Slf4j
public class FileOperateUtils {

    // 生成文件夹
    public static void createDir(String path) {
        File folder = new File(path);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.setWritable(true, false);
            folder.mkdirs();
            log.info("创建文件夹");
        } else {
            log.info("文件夹已存在");
        }
    }

    /***
     * @Description: 创建文件路径
     * @Author: zhanglianhai
     * @Date: 2023/3/28
     * @Version V8.5
     **/
    public static void createFile(String path) {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                log.info("无文件路径，且生成文件路径失败！");
            }
        }
        if(!file.exists()) {
            try {
                if(!file.createNewFile()){
                    log.error("创建文件失败");
                    throw new ServiceException("创建文件失败");
                }
            } catch (IOException e) {
                log.error("创建文件失败:{}",e);
                throw new ServiceException("创建文件失败");
            }
        }

    }

    /***
     * @Description: 创建文件路径，返回file
     * @Author: zhanglianhai
     * @Date: 2023/3/28
     * @Version V8.5
     **/
    public static File getFile(String path) {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                log.info("无文件路径，且生成文件路径失败！");
            }
        }
        if(!file.exists()) {
            try {
                if(!file.createNewFile()){
                    log.error("创建文件失败");
                    throw new ServiceException("创建文件失败");
                }
            } catch (IOException e) {
                log.error("创建文件失败:{}",e);
                throw new ServiceException("创建文件失败");
            }
        }
        return file;

    }


    /**
     * @Descriptin 删除文件
     * @param fileName 文件路径
     * @return
     * @author daikeyi
     * @date 2022/12/14 20:09
     * @vesion V8.4.5.1
     */
    public static void deleteFile(String fileName){
        File deleteFile = new File(fileName);
        if (deleteFile.exists()) {
            //缓存的file文件删除
            boolean delete = deleteFile.delete();
        }
    }
}
