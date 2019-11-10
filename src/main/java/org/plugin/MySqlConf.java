package org.plugin;

/**
 * MySQL配置类
 */
public class MySqlConf {
    private String url;

    private String userName;

    private String passWord;

    private String table;

    private String keyWordField;

    private String createAtField;

    private String updateAtField;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getKeyWordField() {
        return keyWordField;
    }

    public void setKeyWordField(String keyWordField) {
        this.keyWordField = keyWordField;
    }

    public String getCreateAtField() {
        return createAtField;
    }

    public void setCreateAtField(String createAtField) {
        this.createAtField = createAtField;
    }

    public String getUpdateAtField() {
        return updateAtField;
    }

    public void setUpdateAtField(String updateAtField) {
        this.updateAtField = updateAtField;
    }


    @Override
    public String toString() {
        return "MySqlConf{" +
                "url='" + url + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", table='" + table + '\'' +
                ", keyWordField='" + keyWordField + '\'' +
                ", createAtField='" + createAtField + '\'' +
                ", updateAtField='" + updateAtField + '\'' +
                '}';
    }
}
