package cn.sxfei.app.modules.sapress.comm.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import cn.sxfei.app.plugins.sqlplugin.PathKit;

public class FileUtils {
    private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

    public static String[] saveFile(MultipartFile file) {
        String[] results = new String[3];
        String originalFileName = file.getOriginalFilename();
        UUID uuid = UUID.randomUUID();// 生成UUID作为文件名
        String fjguid = uuid.toString().replaceAll("-", "");
        //
        String fileNames[] = originalFileName.split("\\.");
        String kzm = "." + fileNames[fileNames.length - 1];
        String fileName = fjguid + kzm;
        String filePath = PathKit.getWebRootPath() + "/";
        String filePathName = filePath + fileName;
        log.info("文件保存路径：" + filePathName);
        try {
            InputStream inStream = file.getInputStream();
            byte[] data = readInputStream(inStream);
            File imageFile = new File(filePathName);
            // 创建输出流
            FileOutputStream outStream = new FileOutputStream(imageFile);
            // 写入数据
            outStream.write(data);
            // 关闭输出流
            outStream.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        File imageFile = new File(filePathName);
        boolean flag = new QiNiuTools().uploadFile(imageFile, fileName);
        results[0] = fileName;
        results[1] = filePathName;
        results[2] = String.valueOf(flag);
        return results;
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();
    }
}
