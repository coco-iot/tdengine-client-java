```shell
jdbc:[TAOS|TAOS-RS]://[host_name]:[port]/[database_name]?[user={user}|&password={password}|&charset={charset}|&cfgdir={config_dir}|&locale={locale}|&timezone={timezone}]

mvn package -Dmaven.test.skip=true
```

```shell
user：登录 TDengine 用户名，默认值 'root'。
password：用户登录密码，默认值 'taosdata'。
cfgdir：客户端配置文件目录路径，Linux OS 上默认值 /etc/taos，Windows OS 上默认值 C:/TDengine/cfg。
charset：客户端使用的字符集，默认值为系统字符集。
locale：客户端语言环境，默认值系统当前 locale。
timezone：客户端使用的时区，默认值为系统当前时区。
batchfetch: true：在执行查询时批量拉取结果集；false：逐行拉取结果集。默认值为：false。开启批量拉取同时获取一批数据在查询数据量较大时批量拉取可以有效的提升查询性能。
batchErrorIgnore：true：在执行 Statement 的 executeBatch 时，如果中间有一条 SQL 执行失败将继续执行下面的 SQL。false：不再执行失败 SQL 后的任何语句。默认值为：false。
```