

package com.xupp.teststorage.storage.fileconvertor;


public interface IFileConvertor {
    /**
     *
     * @param downPath 源文件实际地址
     * @param localserverpath 文件本机服务地址
     * @param remoteserverpath 远程服务端地址
     * @return
     */
    Object convert(String downPath, String localserverpath, String remoteserverpath);

}
