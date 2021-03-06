/**
 * Date: 2020-06-10 15:55
 * Author: xupp
 */

package com.xupp.teststorage.domain;

import com.xupp.constant.Constants;
import com.xupp.teststorage.facator.FileConvertorFactory;
import com.xupp.teststorage.facator.StorageServerFactor;
import com.xupp.teststorage.replite.ImageReptile;
import com.xupp.teststorage.storage.IStorage;
import com.xupp.util.ZipUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = Constants.SOURCEFROM.ZIP)
public class ZipMaterialDomain implements IMaterialUD{
    @Autowired
    private FileConvertorFactory fileConvertorFactory;
    @Autowired
    private StorageServerFactor storageServerFactor;
    @Override
    public Object upload(Map<String,Object> maps)  {
        MultipartFile multipartFile=(MultipartFile) maps.get("file");
        String refId=(String) maps.get("refId");
        String space ="fs";
        try(IStorage iStorage = storageServerFactor.getStorageServer();) {
            File parentFileDir= Files.createTempDirectory("comic").toFile();
            File targetFile = new File(parentFileDir, 0+"_"+multipartFile.getOriginalFilename());
            multipartFile.transferTo(targetFile);
            Map<String,String> options=new HashMap();
            options.put("filename",targetFile.getName());
            iStorage.uploadFileToSpace(space,refId,targetFile,options);
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
            FileUtils.copyInputStreamToFile(streamMap.values().iterator().next(),
                    zip_file);
        }catch (Exception e){
            e.printStackTrace();
        }
        return zip_file;
    }
}
