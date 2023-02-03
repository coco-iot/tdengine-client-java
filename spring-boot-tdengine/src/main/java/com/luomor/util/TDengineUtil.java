package com.luomor.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.taosdata.jdbc.TSDBDriver;

public class TDengineUtil {
    public void test() throws ClassNotFoundException, SQLException {
        Class.forName("com.taosdata.jdbc.TSDBDriver");
        String jdbcUrl = "jdbc:TAOS://localhost:6030/test?user=root&password=taosdata";
        Connection conn = DriverManager.getConnection(jdbcUrl);

        Class.forName("com.taosdata.jdbc.rs.RestfulDriver");
        jdbcUrl = "jdbc:TAOS-RS://localhost:6041/test?user=root&password=taosdata";
        conn = DriverManager.getConnection(jdbcUrl);
        // INSERT INTO test.t1 USING test.weather (ts, temperature) TAGS('beijing') VALUES (now, 24.6);

        Statement stmt = conn.createStatement();

        // create database
        stmt.executeUpdate("create database if not exists db");

        // use database
        stmt.executeUpdate("use db");

        // create table
        stmt.executeUpdate("create table if not exists tb (ts timestamp, temperature int, humidity float)");
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

    public Connection getConn1() throws Exception{
        Class.forName("com.taosdata.jdbc.TSDBDriver");
        String jdbcUrl = "jdbc:TAOS://taosdemo.com:6030/test?user=root&password=taosdata";
        Properties connProps = new Properties();
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_CHARSET, "UTF-8");
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_LOCALE, "en_US.UTF-8");
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_TIME_ZONE, "UTC-8");
        connProps.setProperty("debugFlag", "135");
        connProps.setProperty("maxSQLLength", "1048576");
        Connection conn = DriverManager.getConnection(jdbcUrl, connProps);
        return conn;
    }
      
    public Connection getRestConn() throws Exception{
        Class.forName("com.taosdata.jdbc.rs.RestfulDriver");
        String jdbcUrl = "jdbc:TAOS-RS://taosdemo.com:6041/test?user=root&password=taosdata";
        Properties connProps = new Properties();
        connProps.setProperty(TSDBDriver.PROPERTY_KEY_BATCH_LOAD, "true");
        Connection conn = DriverManager.getConnection(jdbcUrl, connProps);
        return conn;
    }
}
