package cn.javayuan.system.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 上传工具
 * Created by 李明元 on 2017/3/16.
 */
public class FileUtil {
    /**
     * 保存图片
     * @param newFileName
     * @param filedata
     * @param saveFilePath
     */

    public static void saveFile(String newFileName, MultipartFile filedata, String saveFilePath) {

        /* 构建文件目录 */
        File fileDir = new File(saveFilePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        try {
            FileOutputStream out = new FileOutputStream(saveFilePath + "\\"
                    + newFileName);
            // 写入文件
            out.write(filedata.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除图片
     * @param oldPicName
     */
    public static void deleteFile(String oldPicName, String saveFilePath) {
        /* 构建文件目录 */
        File fileDir = new File(saveFilePath+"/"+oldPicName);
        if (fileDir.exists()) {
            //把修改之前的图片删除 以免太多没用的图片占据空间
            fileDir.delete();
        }

    }
}
