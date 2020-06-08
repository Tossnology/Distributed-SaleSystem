# Distributed-SaleSystem
 A distributed sale system
 

修改顺序：
首先修改01主机nginx配置，不要重启，将前端文件放入nginx，此时再重启，同时启动03，04主机的
spring后台

说明：
- **Java**：util包中为工具类，用于实现Redis各种操作，在Service包中加入xxxCacheService，该类为对Service再次进行缓存封装，如果有某些Servi
ce没有对应的CacheService，表示该Service不需要缓存封装。
- **MySQL**配置：MySQL中配置三个数据库db1，db2，db3，sql文件位于resources/config/sql中。Mycat配置文件位于resources/config/mycat
- 启动项目需要同时启动redis，mycat
- **Redis**配置；redis相关ip以及port绑定配置位于resources文件夹中的JedisPoolConfig.xml中。
- **Nginx**配置；resources/nginx/salesysconfig01为01主机的nginx配置文件，将虚拟机中/etc/nginx/sites-avaliable中对应文件内容修改为
该配置文件内容，然后shell输入sudo nginx -s reload重启nginx服务。
- **Jar包部署**：项目目录out/artifacts中的Sale..._jar文件夹为项目启动jar和依赖包的总目录，将其移动到03，04主机中/opt位置（其他位置也可，
主要方便管理），进入Sale..._jar，在文件夹内的shell输入java -jar SaleSystem.jar启动SpringBoot后台服务。（注：不使用jar部署的话，可以直接在
03，04主机上使用IDEA运行该项目）
- **Nginx前台部署**：该项目的webapp文件夹移动到虚拟机01的/var/www/html中，重命名为sale，然后重启nginx。
- **进入方法**：在启动**MyCat**，重启**Nginx**后，进入浏览器，输入localhost:3000/sale/login.html进入进销存系统。

日志:
- 2020-06-02 修改mysql数据表结构，取消创建多表，各个商店物价以及店员统一归到一张表中。修改Mapper文件以及相关service。
- 2020-06-06 修改mycat的schema配置，加入Redis工具类，增加缓存层CacheService
- 2020-06-08 修改后台Controllerapi，所有api前统一加上前缀 /api/，同时也修改了前端请求api