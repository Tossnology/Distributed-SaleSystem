# Distributed-SaleSystem
 A distributed sale system

说明：
- util包中为工具类，用于实现Redis各种操作，在Service包中加入xxxCacheService，该类为对Service再次进行缓存封装，如果有某些Servi
ce没有对应的CacheService，表示该Service不需要缓存封装。
- MySQL中配置三个数据库db1，db2，db3，sql文件位于resources/config/sql中。Mycat配置文件位于resources/config/mycat
- 启动项目需要同时启动redis，mycat
- redis相关ip以及port绑定配置位于resources文件夹中的JedisPoolConfig.xml中。

日志:
- 2020-06-02 修改mysql数据表结构，取消创建多表，各个商店物价以及店员统一归到一张表中。修改Mapper文件以及相关service。
- 2020-06-06 修改mycat的schema配置，加入Redis工具类，增加缓存层CacheService