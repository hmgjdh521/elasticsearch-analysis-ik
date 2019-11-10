package org.wltea.analyzer.dic;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.SpecialPermission;
import org.plugin.MySqlConf;
import org.plugin.MysqlDb;
import org.wltea.analyzer.help.ESPluginLoggerFactory;

import java.security.AccessController;
import java.security.PrivilegedAction;

public class MySqlMonitor implements Runnable {
    private static final Logger logger = ESPluginLoggerFactory.getLogger(MySqlMonitor.class.getName());

    /*
     * 上次更改时间
     */
    private Long last_modified=0L;

    /**
     * 上次条数
     */
    private Long last_count=0L;

    /*
     * mysql配置
     */
    private MySqlConf mysqlExt;

    public MySqlMonitor(MySqlConf mysqlExt) {
        this.mysqlExt = mysqlExt;
    }
    /**
     * 监控流程：
     */

    public void run() {
        SpecialPermission.check();
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            this.runUnprivileged();
            return null;
        });
    }

    private void runUnprivileged() {
        if(mysqlExt==null){
            return;
        }
        MysqlDb mysqlDb = new MysqlDb(mysqlExt);
        long maxDate = mysqlDb.getMaxDateTime();
        long dataCount = mysqlDb.getCount();
        mysqlDb.close();
        //有更新
        if(maxDate!=last_modified || dataCount!=last_count){
            // 远程词库有更新,需要重新加载词典，并修改last_modified,eTags
            Dictionary.getSingleton().reLoadMainDict();
            last_count = dataCount;
            last_modified = maxDate;
        }else{

        }
    }
}
