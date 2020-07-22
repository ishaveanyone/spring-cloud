

package com.xupp.teststorage.facator;

import com.xupp.teststorage.storage.fileconvertor.FileConvertorBuilder;
import com.xupp.teststorage.storage.fileconvertor.FileConvertorEnum;
import com.xupp.teststorage.storage.fileconvertor.IFileConvertor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class FileConvertorFactory {

    @Value("${storage.convertor.type}")
    private String convertorType;

    public IFileConvertor getFileConvertor(String type){
        return  getFileConvertor(FileConvertorEnum.getFileConvertorEnum(type));
    }
    //获取默认的预览 功能
    public IFileConvertor getFileConvertor(){
        return  getFileConvertor(FileConvertorEnum.getFileConvertorEnum(convertorType));
    }

    public IFileConvertor getFileConvertor(FileConvertorEnum fileServerEnum){
        return new FileConvertorBuilder().build(fileServerEnum);
    }
}
