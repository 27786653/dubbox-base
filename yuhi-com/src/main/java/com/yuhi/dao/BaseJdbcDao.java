package com.yuhi.dao;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
/**
 * 抽象数据操作层次
 * @author 李森林
 *
 */
public interface BaseJdbcDao {
	/**
     * 
     * @return Date 启动时间
     */
	public Date getStartTime();
	 /**
     * 获取数据库当前时间
     * @return Date 当前时间
     */
	public Date getCurrentTime();
    /**
     * 查询并返回JsonObject
     * @param sql SQL语句
     * @param args 参数
     * @return List<JSONObject> JSON列表
     */
	public List<JSONObject> queryForJsonList(String sql, Object... args);
	/**
     * 分页查询并返回JsonObject
     * @param sql SQL语句
     * @param args 参数
     * @return List<JSONObject> JSON列表
     */
	public List<JSONObject> queryForJsonListPage(String sql,int start, int limit,Object... args);
	/**
     * 查询并返回单个JsonObject
     * @param sql SQL语句
     * @param args 参数
     * @return JSONObject JSON数据
     */
	public JSONObject queryForJsonObject(String sql, Object... args);
	/**
	 * 统计行数
     * @param sql SQL语句
     * @param args 参数
     * @return String 文本
     */
	public Long queryForCountNum(String sql, Object... args);
	/**
	 * 返回单个字符串值
     * @param sql SQL语句
     * @param args 参数
     * @return String 文本
     */
	public String queryForString(String sql, Object... args);
	/**
     * 数据库id生成器
     * @return String 唯一键值
     */
	public String generateKey();
	/**
     * 插入
     * @param tableName 表名
     * @param data JSONObject对象
     */
	public int insert(JSONObject data);
     /**
      * 插入并返回id
      * 注意：该方法只适合于自增id的返回
      * @param tableName
      * @param data
      * @return
      */
     public int insertOrReturnId(JSONObject data);
     /**
      * 批量插入数据
      * @param tableName 数据库表名称
      * @param list 插入数据集合
      */
     public void insertBatch(final List<LinkedHashMap<String, Object>> list);
      /**
       * 删除数据多条件
       * @param tableName 表名
       * @param data JSONObject对象
       *and id=? 
       */
      public int delete( String[] array,String[] param);
      /**
       * 根据id删除数据
       * @param tableName
       * @param id
       * @return
       */
      public int delete(String id);
      /**
       * 修改
       * @param tableName 表名
       * @param map 
       * @param data JSONObject对象
       *and id=? 
       */
      public int update(Map<String,Object> paramMap,String id);
}
