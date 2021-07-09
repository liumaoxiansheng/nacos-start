1、nacos安装
https://nacos.io/zh-cn/docs/quick-start.html
启动命令：
Linux/Unix/Mac
启动命令(standalone代表着单机模式运行，非集群模式):
sh startup.sh -m standalone
如果您使用的是ubuntu系统，或者运行脚本报错提示[[符号找不到，可尝试如下运行：
bash startup.sh -m standalone

Windows
启动命令(standalone代表着单机模式运行，非集群模式):
startup.cmd -m standalone

关闭服务器：
Linux/Unix/Mac
sh shutdown.sh
Windows
shutdown.cmd
或者双击shutdown.cmd运行文件

2、持久化配置(可不配)：
初始化配置：执行config/nacos-sql.sql
修改config/application.properties
  spring.datasource.platform=mysql
  ### Count of DB:
  db.num=1
  ### Connect URL of DB:
  db.url.0=jdbc:mysql://127.0.0.1:3306/nacos_info?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
  db.user.0=root
  db.password.0=123456

  高版本MySQL8+：注意url连接参数：userUnicode=true&characterEncoding=utf-8&serverTimezone=Hongkong&allowMultiQueries=true