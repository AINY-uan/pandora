# Pandora

## 打包目录结构

共三个文件夹：bin、shbin、stdoutLog

### bin
+ 包含依赖jar包
+ 运行配置文件
+ 日志配置文件
+ 运行包

### shbin
启动停止脚本文件位置

+ start 脚本
```shell script
#!/bin/sh
sh ./stop
LOGFILE=/opt/AINY_uan/Test/stdoutLog/starter.log
cd /opt/AINY_uan/Test/bin
nohup java -jar -Xmx200m -Xms200m Test-1.0-SNAPSHOT.jar >$LOGFILE 2>&1 &
echo "service start..."
```

+ stop 脚本
```shell script
#!/bin/sh
id=`ps -ef | grep Test-1.0-SNAPSHOT.jar| grep -v grep | awk '{print $2}'` 
echo 
echo "Stop service...... kill" $id 
kill -9  $id 
```

需授予执行权限（chmod +x start）

### stdoutLog
服务启动日志文件夹