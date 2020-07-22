# 使用properties 配置默认注册域找不到
# 注册服务端 不要配置 contenxt 路径不然 客户端找不到
    最好不要设置 上下文 ，不然通过服务名称去找 api 还要加上上下文
# 注册暴漏的路由不要出现 - 
Property	        Description
region	            配置region 名称 一个服务只能有一个 A String containing a name for the region where the application will be deployed
com.xupp.teststorage.service-url	        配置多个zone 并且提供url A Map containing the list of available zones for the given region
availability-zones	配置多个region A Map containing a comma-separated list of zones for the given region
可以这么用 
eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
    region: region-1
    com.xupp.teststorage.service-url:
      zone1: http://localhost:8761/eureka/
      zone2: http://127.0.0.1:8762/eureka/
    availability-zones:
      region-1: zone1,zone2
      
      
      
# 关于设计模式 
## 原则 
开闭  对扩展开 对修改关闭
迪米特 只和自己有耦合的对象打交道 （）
里氏替换 就是子类必须实现父类全部方法 保证父类出现的完全可以替换成子类
单一职责 接口职责单一 
依赖倒置 保证各模块之间依赖 各自的接口 不依赖具体的实现
接口隔离 要求接口方法尽量少 

