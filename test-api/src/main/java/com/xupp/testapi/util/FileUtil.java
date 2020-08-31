package com.xupp.testapi.util;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * 文件工具
 * @author xupp
 * @date 2018/12/21
 */
public class FileUtil {

    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil(){
        super();
    }

    /**
     * txt文件 转 utf-8 格式
     * @param file
     * @return
     */
    public static File TXTHandler(File file) {
        //原本这里的是gb2312，为了兼容更多，选择了gbk
        String code = "gbk";
        byte[] head = new byte[3];
        try {
//            InputStream inputStream = new FileInputStream(file);
//            inputStream.read(head);
            String encoding=getFileCharsetByICU4J(file);
            if("UTF-16".equals(encoding)){
                code = "UTF-16";
            }else if ("Unicode".equals(encoding)) {
                code = "Unicode";
            }else if ("UTF-8".equals(encoding)) {
                code = "UTF-8";
            }
            LOG.info("源文件编码>>" + code);
            if (code.equals("UTF-8")) {
                return file;
            }
            String str = FileUtils.readFileToString(file, code);
            FileUtils.writeStringToFile(file, str, "UTF-8");
            LOG.info("转码结束>>");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    public static String getFileCharsetByICU4J(File file) {
        String encoding = null;

        try {
            Path path = Paths.get(file.getPath());
            byte[] data = Files.readAllBytes(path);
            CharsetDetector detector = new CharsetDetector();
            detector.setText(data);
            CharsetMatch match = detector.detect();
            if (match == null) {
                return encoding;
            }

            encoding = match.getName();
        } catch (IOException var6) {
            var6.printStackTrace();
        }
        return encoding;
    }
    public static void main(String[] args) {
        System.out.println(getFileCharsetByICU4J(new File("H://utf8测试.txt")));
    }
}
