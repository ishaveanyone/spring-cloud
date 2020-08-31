/**
 * Date: 2020-06-10 15:28
 * Author: xupp
 */

package com.xupp.testapi.replite;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageReptile {
    // 地址
    // 获取img标签正则
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
    // 获取src路径的正则
    private static final String IMGSRC_REG = "[a-zA-z]+://[^\\s]*";

    // 获取html内容
    public static String getHTML(String srcUrl) throws Exception {
        URL url = new URL(srcUrl);
        URLConnection conn = url.openConnection();
        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        StringBuffer buffer = new StringBuffer();
        while ((line = br.readLine()) != null) {
            buffer.append(line);
            buffer.append("\n");
        }
        br.close();
        isr.close();
        is.close();
        return buffer.toString();
    }

    // 获取image url地址
    public static List<String> getImageURL(String html) {
        Matcher matcher = Pattern.compile(IMGURL_REG).matcher(html);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

    // 获取image src地址
    public static List<String> getImageSrc(List<String> listUrl) {
        List<String> listSrc = new ArrayList<String>();
        for (String img : listUrl) {
            Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(img);
            while (matcher.find()) {
                listSrc.add(matcher.group().substring(0,
                        matcher.group().length() - 1));
            }
        }
        return listSrc;
    }







    public static List<InputStream> getAllFileStream(List<String> listImgSrc) throws Exception {
        try {
            List<InputStream> streams=new ArrayList();
            for (String url : listImgSrc) {
                URL uri = new URL(url);
                InputStream in = uri.openStream();
                streams.add(in);
            }
            return streams;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("下载失败");
        }
        return null;
    }








    // 下载图片
    public static void download(List<String> listImgSrc) {
        try {
            String dir=String.valueOf(System.currentTimeMillis());
            // 开始时间
            Date begindate = new Date();
            int i=0;
            for (String url : listImgSrc) {
                // 开始时间
                Date begindate2 = new Date();
                String imageName = url.substring(url.lastIndexOf("/") + 1,
                        url.length());
                URL uri = new URL(url);
                InputStream in = uri.openStream();
                File file =new File("H:\\自创\\感人漫画\\"+dir+"\\"+i+"_"+imageName);
                if(!new File("H:\\自创\\感人漫画\\"+dir+"\\").exists()){
                    new File("H:\\自创\\感人漫画\\"+dir+"\\").mkdirs();
                }
                System.out.println(file.getAbsolutePath());
                FileOutputStream fo = new FileOutputStream(file);// 文件输出流
                byte[] buf = new byte[1024];
                int length = 0;
                System.out.println("开始下载:" + url);
                while ((length = in.read(buf, 0, buf.length)) != -1) {
                    fo.write(buf, 0, length);
                }
                // 关闭流
                in.close();
                fo.close();
                System.out.println(imageName + "下载完成");
                // 结束时间
                Date overdate2 = new Date();
                double time = overdate2.getTime() - begindate2.getTime();
                System.out.println("耗时：" + time / 1000 + "s");
                i++;
            }
            Date overdate = new Date();
            double time = overdate.getTime() - begindate.getTime();
            System.out.println("总耗时：" + time / 1000 + "s");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("下载失败");
        }
    }



    public static void main(String[] args) throws Exception {
//        getAllFileStream("https://www.sohu.com/a/292031263_349043");
    }

}
