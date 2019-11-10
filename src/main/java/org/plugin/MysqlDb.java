package org.plugin;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import org.apache.logging.log4j.Logger;
import org.wltea.analyzer.dic.Monitor;
import org.wltea.analyzer.help.ESPluginLoggerFactory;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author zzq
 * @date 2019-11-10 10:52
 */
public class MysqlDb {
    private static final Logger logger = ESPluginLoggerFactory.getLogger(Monitor.class.getName());

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC";

    public Connection conn = null;
    public Statement stmt = null;
    public MySqlConf mySqlConf=null;

    public MysqlDb(MySqlConf mySqlConf) {
        this.mySqlConf=mySqlConf;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
            // 打开链接
            logger.info("连接数据库...");
            String url =mySqlConf.getUrl()+"?characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull";
            conn = (Connection) DriverManager.getConnection(url,mySqlConf.getUserName(),mySqlConf.getPassWord());
            // 执行查询
            logger.info(" 实例化Statement对象...");
            stmt = (Statement) conn.createStatement();
        }catch(SQLException se){
            // 处理 JDBC 错误
            logger.error(se.getMessage());
        }catch(Exception e){
            // 处理 Class.forName 错误
            logger.error(e.getMessage());
        }
    }

    public Long getMaxDateTime() {
        ResultSet resultSet = null;
        try {
            String sql = "SELECT MAX(max_date) FROM(  " +
                    "(  select MAX(" + mySqlConf.getCreateAtField() + ") max_date FROM " + mySqlConf.getTable() + ")" +
                    "   UNION  " +
                    "(  select MAX(" + mySqlConf.getUpdateAtField() + ") max_date FROM " + mySqlConf.getTable() + ")" +
                    ")   s";
            resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                Date date = resultSet.getDate(1);
                return date.getTime();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return 0L;
    }

    public Long getCount() {
        ResultSet resultSet = null;
        try {
            String sql = "select count(*) from "+mySqlConf.getTable();
            resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                return resultSet.getLong(1);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return 0L;
    }

    public ResultSet getKeyWordResultSet() {
        ResultSet resultSet = null;
        try {
            String sql = "select "+mySqlConf.getKeyWordField()+" from "+mySqlConf.getTable();
            resultSet = stmt.executeQuery(sql);
            return resultSet;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public void close(){
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException se2) {
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        MySqlConf mySqlConf = new MySqlConf();
        mySqlConf.setUpdateAtField("update_at");
        mySqlConf.setCreateAtField("create_at");
        mySqlConf.setKeyWordField("keyword");
        mySqlConf.setUrl("jdbc:mysql://localhost:3306/ik");
        mySqlConf.setUserName("root");
        mySqlConf.setPassWord("123456");
        mySqlConf.setTable("keyWord");
        MysqlDb mysqlDb = new MysqlDb(mySqlConf);
        System.out.println(mysqlDb.getMaxDateTime());
        ResultSet rs= mysqlDb.getKeyWordResultSet();
        while (rs.next()){
            System.out.println(rs.getString(1));
        }
        System.out.println(mysqlDb.getCount());
    }
}
