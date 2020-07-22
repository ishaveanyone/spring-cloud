
package com.xupp.teststorage.storage.fileconvertor;

import com.xupp.teststorage.define.Constants;
import com.xupp.teststorage.util.HttpUrlUtil;
import com.xupp.teststorage.util.StringUtil;
import org.springframework.stereotype.Component;

/**
 * 使用  kkfileview 进行文件 转换的 业务对接
 * todo officeonline 同理
 */
@Component
public class KkfileviewFileConvertor implements IFileConvertor{

    private final String onlinePreviewRequestMapping="onlinePreview";
    private final String onlinePreviewRequestParam="url";
    @Override
    public Object convert(String downPath, String localserverpath, String remoteserverpath) {
        //文件下载地址
        String suffix= StringUtil.catLastStrByChar(downPath, '.').toLowerCase();
        //如果是需要下载的文件
        if (Constants.Material.NEED_DOWNLOAD_FILTER.contains(suffix.toLowerCase())) {
//            return new MaterialViewResultVO(localserverpath,true);
        }
        //如果是可以直接预览的文件
        if (Constants.Material.NO_NEED_TRANSFORM_FILTER.contains(suffix.toLowerCase())) {
//            return new MaterialViewResultVO(localserverpath,false);
        }
        return null;
//        return new MaterialViewResultVO(remoteserverpath+onlinePreviewRequestMapping+"?"+onlinePreviewRequestParam+
//                "="+ HttpUrlUtil.getURLEncoderString(localserverpath),false);
    }

}
