package com.example.demo.Utils;

import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class FileUtil {
    //文件上传工具类服务方法

    //输入：文件流、保存位置、文件名
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception{
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(targetFile.getAbsolutePath()+"/"+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
    //将保存的文件的目标路径：M
    public static String getUpLoadFilePath()
    {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(!path.exists()) path = new File("");
        File filePath = new File(path.getAbsolutePath(),"static/images/upload/");
        return filePath.getAbsolutePath();
    }
    //保存到和邓一样的target的路径下面去
    public static String getUpLoadFilePath2()
    {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(!path.exists()) path = new File("");
        File filePath = new File(path.getAbsolutePath(),"static/hr_file");
        return filePath.getAbsolutePath();
    }



    /**
     * 上传多张
     * @param files file数组
     * @param path  保存路径
     * @return
     */
    public static List<String> uploadMore(MultipartFile[] files, String path) throws IOException {
        List<String> imagePathList = new ArrayList<>();
        //判断file数组不能为空并且长度大于0
        if (files != null && files.length > 0){
            String date = new SimpleDateFormat("yyyy/MM").format(new Date()).toString();
            // 处理路径并判断目录是否存在
            File dir = new File(path + "/" + date);
            if (!dir.exists()) {
                // 设置写权限
                dir.setWritable(true);
                dir.mkdirs();
            }
            for (MultipartFile file : files){
                if (!file.isEmpty()){
                    // 原文件名称
                    String fileName = file.getOriginalFilename();
                    String newFileName = date + "/" + UUID.randomUUID().toString().replace("-","") + fileName.substring(fileName.lastIndexOf("."));
                    file.transferTo(new File(path + "/" + newFileName));
                    imagePathList.add(newFileName);
                }
            }
        }
        return imagePathList;
    }

    /**
     * 删除图片
     * @param filePath 文件路径
     * @return
     */
    public static boolean delete(String filePath){
        File file = new File(filePath);
        if (file.exists() && file.isFile()){
            System.gc(); // 强制删除
            return file.delete();
        }
        return false;
    }
}

