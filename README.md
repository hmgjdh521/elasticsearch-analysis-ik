IK Analysis for Elasticsearch
=============================

扩展ik分词器 支持除远程热加载词库之外 可以从数据库里动态加载 目前支持MySQL

Versions
--------

目前是6.5.3版本改造

配置使用
--------
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
	<comment>IK Analyzer 扩展配置</comment>
	<!--用户可以在这里配置自己的扩展字典 -->
	<entry key="ext_dict">custom/mydict.dic;custom/single_word_low_freq.dic</entry>
	 <!--用户可以在这里配置自己的扩展停止词字典-->
	<entry key="ext_stopwords">custom/ext_stopword.dic</entry>
 	<!--用户可以在这里配置远程扩展字典 -->
	<entry key="remote_ext_dict">location</entry>
 	<!--用户可以在这里配置远程扩展停止词字典-->
	<entry key="remote_ext_stopwords">http://xxx.com/xxx.dic</entry>
    
    <!--mysql  扩展词典-->
    <!--jdbc url-->
	<entry key="mysql_ext_dict_url">jdbc:mysql://localhost:3306/ik</entry>
	<!--数据库用户名-->
	<entry key="mysql_ext_dict_username">root</entry>
	<!--数据库密码-->
	<entry key="mysql_ext_dict_password">123456</entry>
	<!--所在表-->
	<entry key="mysql_ext_dict_table">keyWord</entry>
	<!--词的列名 默认keyword-->
	<entry key="mysql_ext_dict_keywordField">keyword</entry>
	<!--创建时间列名 默认create_at-->
	<entry key="mysql_ext_dict_createAtField">create_at</entry>
	<!--更新时间列名 默认update_at-->
	<entry key="mysql_ext_dict_updateAtField">update_at</entry>

	<!--mysql  停用词-->
	<entry key="mysql_ext_stopwords_url"></entry>
	<entry key="mysql_ext_stopwords_username"></entry>
	<entry key="mysql_ext_stopwords_password"></entry>
	<entry key="mysql_ext_stopwords_table"></entry>
	<entry key="mysql_ext_stopwords_keywordField"></entry>
	<entry key="mysql_ext_stopwords_createAtField"></entry>
	<entry key="mysql_ext_stopwords_updateAtField"></entry>



</properties>
```

### 基础使用方法
https://github.com/zhuzhiqiang18/elasticsearch-analysis-ik/blob/master/README.md

