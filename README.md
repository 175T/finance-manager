# 财务管理系统正式环境安装说明

### 1. 安装位置
C:\Deployments
- Deployments 文件目录结构
```$xslt
C:\Deployments> dir
Mode                LastWriteTime     Length Name
----                -------------     ------ ----
d----         2020/7/29     21:47            finance-manager
d----         2020/7/11      9:50            Java
d----         2020/7/29     21:42            supports
-a---         2020/7/29     22:21        122 启动财务管理系统.bat
```

- finance-manager 文件目录结构
```$xslt
C:\Deployments\finance-manager> dir
Mode                LastWriteTime     Length Name
----                -------------     ------ ----
d----         2020/7/29     21:41            db
d----         2020/7/29     21:41            k8s
d----         2020/7/29     21:41            libs
d----         2020/7/29     21:47            logs
d----         2020/7/29     21:41            src
d----         2020/7/29     21:41            target
-----          2020/7/9     20:49      12832 finance-manager.iml
-----         2019/9/21      9:39      11357 LICENSE
-----          2020/7/9     20:48      11759 pom.xml
-----         2020/7/28     23:04        146 README.md
```

### 2. 启动脚本
- 脚本位置
C:\Deployments\启动财务管理系统.bat
- 脚本内容
```$xslt
cd finance-manager
C:\Deployments\Java\Java1.8.251\bin\java -jar target/finance-manager.jar --spring.profiles.active=real
```

### 3. Apache 配置
C:\BtSoft\apache\conf\vhost\caiwu.myrealhost.com.conf
```$xslt
<VirtualHost *:80>
        # ServerAdmin webmaster@example.com
        # DocumentRoot "C:/wwwroot/caiwu.myrealhost.com"
        # ServerAlias caiwu.myrealhost.com
        ProxyPreserveHost On
        ServerName caiwu.myrealhost.com
        ProxyPass / http://localhost:9001/
        ErrorLog "C:/BtSoft/wwwlogs/caiwu.myrealhost.com-error.log"
        CustomLog "C:/BtSoft/wwwlogs/caiwu.myrealhost.com-access.log" combined
</VirtualHost>
```

### 4. MySQL 数据库
- 数据库位置: 
C:\BtSoft\mysql\MySQL5.5\data\financedb
- 数据库链接参数:
```$xslt
datasource.url=jdbc:mysql://localhost:3306/financedb?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8&useSSL=false&verifyServerCertificate=false&autoReconnct=true&autoReconnectForPools=true&allowMultiQueries=true
datasource.username=finance
datasource.password=finance20231016
datasource.driverClassName=com.mysql.cj.jdbc.Driver
```

### 5. 程序包下载 (阿里云)
http://47.100.238.23/download-supports

### 6. Nginx (阿里云)
```$xslt
user  root;
worker_processes  2;
events {
    worker_connections  1024;
}
http {
    include       mime.types;
    default_type  application/octet-stream;
    sendfile      on;
    keepalive_timeout  65;
    gzip  on;
    server {
        listen       80;
        server_name  localhost 47.100.238.23 finance.myrealhost.com;
        location  /download-supports/ {
            alias /root/deployments/download-supports/;
        }
        location  / {
            #upload file max size
            client_max_body_size 10m;
            sendfile       on;
            tcp_nopush     on;
            proxy_pass     http://127.0.0.1:9001;
            proxy_redirect     off;
            proxy_set_header   Host              $host:$server_port;
            proxy_set_header   X-Real-IP         $remote_addr;
            proxy_set_header   X-Forwarded-For   $proxy_add_x_forwarded_for;
            proxy_set_header   X-Forwarded-Proto $scheme;
        }
    }
}
```