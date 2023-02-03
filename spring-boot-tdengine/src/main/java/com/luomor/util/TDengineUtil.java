package com.luomor.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.taosdata.jdbc.TSDBDriver;

public class TDengineUtil {
    public void test() throws ClassNotFoundException, SQLException {
        Class.forName("com.taosdata.jdbc.TSDBDriver");
        String jdbcUrl = "jdbc:TAOS://localhost:6030/test?user=root&password=taosdata";
        Connection conn = DriverManager.getConnection(jdbcUrl);

        Class.forName("com.taosdata.jdbc.rs.RestfulDriver");
        jdbcUrl = "jdbc:TAOS-RS://localhost:6030/test?user=root&password=taosdata";
        conn = DriverManager.getConnection(jdbcUrl);
    }
    
    /**
     * # first fully qualified domain name (FQDN) for TDengine system
        firstEp               cluster_node1:6030

        # second fully qualified domain name (FQDN) for TDengine system, for cluster only
        secondEp              cluster_node2:6030

        # default system charset
        # charset               UTF-8

        # system locale
        # locale                en_US.UTF-8
     * @return
     * @throws Exception
     */
    public Connection getConn() throws Exception{
        Class.forName("com.taosdata.jdbc.TSDBDriver");
        String jdbcUrl = "jdbc:TAOS://:/test?user=root&password=taosdata";
        Properties connProps = new Properties();
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_CHARSET, "UTF-8");
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_LOCALE, "en_US.UTF-8");
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_TIME_ZONE, "UTC-8");
        Connection conn = DriverManager.getConnection(jdbcUrl, connProps);
        return conn;
    }
}
