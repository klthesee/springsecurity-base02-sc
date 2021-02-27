

+---common
|   +---service_base 工具类写在这个模块
|   \---spring_security springSecurity的相关配置
+---infastructuce
|   \---api_gateway  网关的内容
\---service
    \---service_acl  权限管理的功能
    

![avatar](./README.assets/image-20210226004942643.png)

数据表
![avatar](./README.assets/image-20210226011730659.png)



授权认证流程
1)从token中获取用户名，
2)根据用户名从redis中获取权限，
3)将权限加入到sec中管理,
4)sec管理用户的授权


- 未授权统一处理类


网关也注册到了注册中心
网关原理：请求发到网关，网关从注册中心上获取所有服务，找到请求需要的服务进行转发。

![image-20210227173345527](README.assets/image-20210227173345527.png)

- 该项目安全架构：
所有增删查改服务都依赖了spring_security模块，然后每个服务都可以拿到spring_security的bean，认证和授权使用的是spring_security写的逻辑。
网关的作用只做了路由转发和允许跨域