
package com.xupp.teststorage.define;

import java.util.*;

public class Constants {

    //文件配置
    public static final class Material {

        public static final Integer MATERIAL_ROOT_DIR_PID=-1; // 标识 父级id 的标识
        // 文档的三种状态
        public static final Integer NORMAL=0; // 上传完毕 是一个正常文件
        public static final Integer UPLOADING=1; //上传未完成文件
        //后台需要的一些属性，这里配置和前端交互好的一些配置
        public static enum ATTRS{
            FILESIZE("filesize");
            public String alias;
            ATTRS(String alias){
                this.alias=alias;
            }
        }
        public  final static List<String> NO_NEED_TRANSFORM_FILTER = new ArrayList<String>(){{
            add("pdf"); add("jpg");add("png"); add("jpeg");
            add("gif");add("tif");add("mp4");
            add("avi");
            add("txt");
        }};
        public final static List<String> NEED_TRANSFORM_CHARSET_FILTER = new ArrayList<String>(){{
            add("xml");add("ppt");add("xlsx");add("doc");add("xls");
            add("docx");
            add("pptx");
        }};
        public final static List<String> NEED_DOWNLOAD_FILTER = new ArrayList<String>(){{
            add("dwg");add("mdb");add("zip");
        }};
        public final static List<String> NEED_TRANSCODING = new ArrayList<String>(){{
            add("txt");add("ini");
        }};

        //参数的说明
        public static final class ParamDefine {
            public static final String refId="主体的id，比如项目id或者工程id";
            public static final String name="文件名称";
            public static final String parentGuid="父级id";
            public static final String space="存储的空间域（模块对应中文名）";
            public static final String tags="文件标签";
            public static final String fileSize="文件大小";
            public static final String guids="文件id列表";
            public static final String guid="文件id";
            public static final String chunkNum="分片下标";
            public static final String tagPostionKey="前端存储路径的key值 全域是postion";
        }
    }


    /**
     * web socket 配置
     */
    public static final class WebSocket {
        public static final String[] ORIGINS = {"*"};
        public static final String QUEUE = "/queue";
        public static final String TOPIC = "/topic";
        public static final String WEBSOCKET_CONN = "/websocket";
        public static final String[] DESTINATIONPREFIXES = {QUEUE,TOPIC};// 这个主要是是和一些mq框架的使用术语 队列或者 主题 但是现在使用本地   /topic 代表发布广播，即群发   /queue 代表点对点，即发指定用户  这句话表示在topic和queue这两个域上可以向客户端发消息。
        /*
         *  "/app" 为配置应用服务器的地址前缀，表示所有以/app 开头的客户端消息或请求
         *  都会路由到带有@MessageMapping 注解的方法中
         */
        public static final String APPLICATIONDESTINATIONPREFIXE="/app";
        /*
         *  1. 配置一对一消息前缀， 客户端接收一对一消息需要配置的前缀 如“'/user/'+userid + '/message'”，
         *     是客户端订阅一对一消息的地址 stompClient.subscribe js方法调用的地址
         *  2. 使用@SendToUser发送私信的规则不是这个参数设定，在框架内部是用UserDestinationMessageHandler处理，
         *     而不是而不是 AnnotationMethodMessageHandler 或  SimpleBrokerMessageHandler
         *     or StompBrokerRelayMessageHandler，是在@SendToUser的URL前加“user+sessionId"组成
         */
        public static final String USERDESTINATIONPREFIX="/user"; //点对点 订阅发布前缀
    }

}
