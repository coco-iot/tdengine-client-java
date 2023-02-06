```shell
jdbc:[TAOS|TAOS-RS]://[host_name]:[port]/[database_name]?[user={user}|&password={password}|&charset={charset}|&cfgdir={config_dir}|&locale={locale}|&timezone={timezone}]

mvn package -Dmaven.test.skip=true

cd springbootdemo
mvn clean package

http://49.232.6.131:8012/weather/init
http://49.232.6.131:8012/weather/10/0
http://49.232.6.131:8012/weather/lastOne
```

```shell
https://github.com/taosdata/taos-connector-jdbc/blob/main/README-CN.md

user：登录 TDengine 用户名，默认值 'root'。
password：用户登录密码，默认值 'taosdata'。
cfgdir：客户端配置文件目录路径，Linux OS 上默认值 /etc/taos，Windows OS 上默认值 C:/TDengine/cfg。
charset：客户端使用的字符集，默认值为系统字符集。
locale：客户端语言环境，默认值系统当前 locale。
timezone：客户端使用的时区，默认值为系统当前时区。
batchfetch: true：在执行查询时批量拉取结果集；false：逐行拉取结果集。默认值为：false。开启批量拉取同时获取一批数据在查询数据量较大时批量拉取可以有效的提升查询性能。
batchErrorIgnore：true：在执行 Statement 的 executeBatch 时，如果中间有一条 SQL 执行失败将继续执行下面的 SQL。false：不再执行失败 SQL 后的任何语句。默认值为：false。

使用 JDBC REST 连接，不需要依赖客户端驱动。与 JDBC 原生连接相比，仅需要：

driverClass 指定为“com.taosdata.jdbc.rs.RestfulDriver”；
jdbcUrl 以“jdbc:TAOS-RS://”开头；
使用 6041 作为连接端口。
url 中的配置参数如下：

user：登录 TDengine 用户名，默认值 'root'。
password：用户登录密码，默认值 'taosdata'。
batchfetch: true：在执行查询时批量拉取结果集；false：逐行拉取结果集。默认值为：true。逐行拉取结果集使用 HTTP 方式进行数据传输。JDBC REST 连接支持批量拉取数据功能。taos-jdbcdriver 与 TDengine 之间通过 WebSocket 连接进行数据传输。相较于 HTTP，WebSocket 可以使 JDBC REST 连接支持大数据量查询，并提升查询性能。
charset: 当开启批量拉取数据时，指定解析字符串数据的字符集。默认为系统字符集。
batchErrorIgnore：true：在执行 Statement 的 executeBatch 时，如果中间有一条 SQL 执行失败，继续执行下面的 SQL 了。false：不再执行失败 SQL 后的任何语句。默认值为：false。
httpConnectTimeout: 连接超时时间，单位 ms， 默认值为 5000。
httpSocketTimeout: socket 超时时间，单位 ms，默认值为 5000。仅在 batchfetch 设置为 false 时生效。
messageWaitTimeout: 消息超时时间, 单位 ms， 默认值为 3000。 仅在 batchfetch 设置为 true 时生效。
useSSL: 连接中是否使用 SSL。
```