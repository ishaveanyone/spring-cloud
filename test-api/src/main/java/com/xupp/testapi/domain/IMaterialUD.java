/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-12-19 11:25
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
package com.xupp.testapi.domain;

import java.io.File;
import java.util.Map;

//定义一组文件对应的操作接口 通过实现不同文件的上传和下载
public interface IMaterialUD {

     Object upload(Map<String, Object> maps);

     File download(String refId) throws Exception;
}
