/**
 * Date: 2020-06-10 15:55
 * Author: xupp
 */

package com.xupp.testapi.domain;

import com.xupp.constant.Constants;
import com.xupp.testapi.facator.FileConvertorFactory;
import com.xupp.testapi.facator.StorageServerFactor;
import com.xupp.testapi.replite.ImageReptile;
import com.xupp.testapi.storage.IStorage;
import com.xupp.util.ZipUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = Constants.SOURCEFROM.URL)
public class NetURLMaterialDomain implements IMaterialUD{
    @Autowired
    private FileConvertorFactory fileConvertorFactory;
    @Autowired
    private StorageServerFactor storageServerFactor;
    @Override
    public Object upload(Map<String,Object> maps)  {
        String url=(String) maps.get("url");
        String refId=(String) maps.get("refId");
        String space ="fs";
        try(IStorage iStorage = storageServerFactor.getStorageServer();) {
            String html= ImageReptile.getHTML(url);
            List<String> imageUrls= ImageReptile.getImageURL(html);
            List<String> imageSrcs= ImageReptile.getImageSrc(imageUrls);
            List<InputStream> streams=ImageReptile.getAllFileStream(imageSrcs);
            File parentFileDir= Files.createTempDirectory("comic").toFile();
            for(int i=0;i<imageSrcs.size();i++){
                String imageName = imageSrcs.get(i).substring(imageSrcs.get(i).lastIndexOf("/") + 1);
                imageName+=".jpeg";
                File targetFile = new File(parentFileDir, i+"_"+imageName);
                FileUtils.copyInputStreamToFile(streams.get(i), targetFile);
                Map<String,String> options=new HashMap();
                options.put("filename",targetFile.getName());
                iStorage.uploadFileToSpace(space,refId,targetFile,options);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 下载挂接的所有附件
     * @param refId
     * @return
     */
    @Override
    public File download(String refId) throws Exception {
        String space ="fs";
        File zip_root_file=Files.createTempDirectory("temZip").toFile();
        File zip_file=new File(zip_root_file.getParentFile().getAbsolutePath()+"/temZip"+refId+".zip");
        try (IStorage iStorage = storageServerFactor.getStorageServer();) {
            Map<String,InputStream> streamMap=
                    iStorage.zipDownload(space,refId);
            //准备一个文件夹 存放所有的
            streamMap.forEach((k,v)->{
                String targetPath=zip_root_file.getAbsolutePath()+"/"+k;
                try {
                    FileUtils.copyInputStreamToFile(v,new File(targetPath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            ZipUtils.zip(zip_root_file,zip_file);
        }catch (Exception e){
            e.printStackTrace();
        }
        return zip_file;
    }

}
