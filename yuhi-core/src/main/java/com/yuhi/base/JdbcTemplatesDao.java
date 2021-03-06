package com.yuhi.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.alibaba.fastjson.JSONObject;
import com.yuhi.constant.Const;
import com.yuhi.dao.BaseJdbcDao;
import com.yuhi.exception.jdbcException;


public abstract class JdbcTemplatesDao implements BaseJdbcDao {

	private Logger logger=Logger.getLogger(getClass());
    /** JSON数据行映射器 */
    private static final JsonRowMapper JSON_ROW_MAPPER = new JsonRowMapper();
    /** JDBC调用模板 */
    private JdbcTemplate jdbcTemplate;
    /** 启动时间 */
    private static Date startTime;
    /** 当前表名**/
    private String tableName;
    
    public JdbcTemplatesDao(){
				try {
					tableName=setControllerTable();
					if(StringUtils.isEmpty(tableName))throw new Exception();
				} catch (Exception e) {
					logger.error("No Set TableName");
				}
	}
    
    /** 当前处理的table名**/
    protected abstract String setControllerTable();
    /**
     * @param dataSource 数据源
     */
    @Lazy
    @Resource
    @Qualifier("dataSource")
    public void initJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        if (startTime == null) {
            startTime = getCurrentTime();
        }
    }

    /**
     * @return JdbcTemplate JDBC调用模板
     */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Override
    public Date getStartTime() {
        return startTime;
    }

    @Override
    public Date getCurrentTime() {
        return this.getJdbcTemplate().queryForObject("SELECT now()", Date.class);
    }

    @Override
    public List<JSONObject> queryForJsonList(String sql, Object... args) {
        return this.jdbcTemplate.query(sql, JSON_ROW_MAPPER, args);
    }
    @Override
    public List<JSONObject> queryForJsonListPage(String sql,int pageNum, int pagesize,Object... args){
    	StringBuffer sb=new StringBuffer(sql);
    	appendPageSql(sb,pageNum,pagesize);
    	return this.jdbcTemplate.query(sb.toString(), JSON_ROW_MAPPER, args);
    }
    @Override
    public JSONObject queryForJsonObject(String sql, Object... args) {
        List<JSONObject> jsonList = queryForJsonList(sql, args);
        if (jsonList == null || jsonList.size() < 1) {
            return null;
        }
        return jsonList.get(0);
    }
    @Override
    public Long queryForCountNum(String sql, Object... args) {
    	return queryforObjByType(Long.class,sql,args);
    }
    @Override
    public String queryForString(String sql, Object... args) {
    	return queryforObjByType(String.class,sql,args);
    }
    @Override
    public <T> List<T> queryforListByType(Class type,String sql, Object... args){
    	List<T> dataList = this.jdbcTemplate.queryForList(sql, args, type);
        if (dataList == null || dataList.size() < 1) {
            return null;
        }
        return dataList;
    }
    @Override
    public <T> List<Map<String, Object>> queryforMap(String sql, Object... args){
    	List<Map<String,Object>> dataList = this.jdbcTemplate.queryForList(sql, args);
        if (dataList == null || dataList.size() < 1) {
            return null;
        }
        return dataList;
    }
	private <T> T queryforObjByType(Class type,String sql, Object... args){
		
		return (T) this.jdbcTemplate.queryForObject(sql, type, args);
//    	List<T> dataList = this.jdbcTemplate.queryForList(sql, args, type);
//        if (dataList == null || dataList.size() < 1) {
//            return null;
//        }
//        return dataList.get(0);
    }
	
    private void appendPageSql(StringBuffer sql, int pageNum, int pagesize) {
//    	oracle
//        sql.insert(0, "SELECT * FROM (SELECT PAGE_VIEW.*, ROWNUM AS ROW_SEQ_NO FROM (");
//        sql.append(") PAGE_VIEW WHERE ROWNUM <= " + (start + limit));
//        sql.append(") WHERE ROW_SEQ_NO > " + start);
//    	mysql
    	if(pageNum<0||pagesize<0)throw new jdbcException("error Page Or Limit Number!");
    	sql.append(" LIMIT "+((pageNum-1)*pagesize)+","+pagesize);
    }


    @Override
    public String generateKey() {
        String sql = "SELECT   date_format(NOW(),'%Y%m%d')  FROM DUAL ";//%Y%m%d%h%i%s'
        String pre = this.getJdbcTemplate().queryForObject(sql, String.class);
        String uid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        return pre + uid.substring(12);
    }

    /**
     * @param sql sql
     * @param sqlArgs 参数容器
     * @param params 参数的个数
     */
    public void appendSqlIn(StringBuffer sql, List<Object> sqlArgs, String[] params) {
        if (params != null && params.length > 0) {
            sql.append(" (");
            for (int i = 0; i < params.length; i++) {
                if (i == 0) {
                    sql.append("?");
                }
                else {
                    sql.append(",?");
                }
                sqlArgs.add(params[i]);
            }
            sql.append(") ");
        }
    }

    /**
     * @param c 原列名
     * @return String 调整后列名
     */
    public static String c(String c) {
        if (StringUtils.isBlank(c)) {
            return null;
        }
        return c.trim().toUpperCase();
    }

    /**
     * @param v 参数
     * @return String 调整后参数
     */
    public static String v(String v) {
        if (StringUtils.isBlank(v)) {
            return null;
        }
        return v.trim().replaceAll("'", "''");
    }

    /**
     * 获得数据库时间戳
     * @param rs 结果集
     * @param column 列名
     * @return String 文本值
     * @throws SQLException SQL异常错误
     */
    protected String getDate(ResultSet rs, String column) throws SQLException {
        Date date = rs.getDate(column);
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, Const.FORMAT_DATE);
    }

    /**
     * 获得数据库时间
     * @param rs 结果集
     * @param column 列名
     * @return String 文本值
     * @throws SQLException SQL异常错误
     */
    protected String getDateTime(ResultSet rs, String column) throws SQLException {
        Date date = rs.getDate(column);
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, Const.FORMAT_DATETIME);
    }

    /**
     * 获得数据库时间戳
     * @param rs 结果集
     * @param column 列名
     * @return String 文本值
     * @throws SQLException SQL异常错误
     */
    protected String getTimestamp(ResultSet rs, String column) throws SQLException {
        Date date = rs.getDate(column);
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, Const.FORMAT_TIMESTAMP);
    }
    @Override
    public int insert(JSONObject data) {
    	List<Object> sqlArgs = new ArrayList<Object>();
    	final String sql=getInsertSql(data,sqlArgs);
        return this.getJdbcTemplate().update(sql, sqlArgs.toArray()); 
    }
    @Override
    public int insertOrReturnId(JSONObject data) { 
    	final List<Object> sqlArgs = new ArrayList<Object>();
    	final String sql=getInsertSql(data,sqlArgs);
    	KeyHolder keyHolder = new GeneratedKeyHolder();  
        int autoIncId = 0;  
    	jdbcTemplate.update(new PreparedStatementCreator() {  
	        public PreparedStatement createPreparedStatement(Connection con)  
	                  throws SQLException {  
	              PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);  
	              for (int i = 0; i < sqlArgs.size(); i++) {
	            	  ps.setObject((i+1), sqlArgs.get(i));
	              }
	              return ps;  
	          }  
        }, keyHolder);  
        autoIncId = keyHolder.getKey().intValue();
        return autoIncId;  
    }  
   
    @Override
    public void insertBatch(final List<LinkedHashMap<String, Object>> list) {
        
        if (list.size() <= 0) {
            return;
        }
        
        LinkedHashMap<String, Object> linkedHashMap = list.get(0);
        
        StringBuffer sql = new StringBuffer();
        sql.append(" INSERT INTO ");
        sql.append(tableName + " ( ");
        
        final String[] keyset =  (String[]) linkedHashMap.keySet().toArray(new String[linkedHashMap.size()]);
        
        for (int i = 0; i < linkedHashMap.size(); i++) {
            sql.append(keyset[i] + ",");
        }
        
        sql.delete(sql.length() - 1, sql.length());
       
        sql.append(" ) VALUES ( ");
        for (int i = 0; i < linkedHashMap.size(); i++) {
            sql.append("?,");
        }
        
        sql.delete(sql.length() - 1, sql.length());
        sql.append(" ) ");
        
        this.getJdbcTemplate().batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                LinkedHashMap<String, Object>  map = list.get(i);
                Object[] valueset = map.values().toArray(new Object[map.size()]);
                int len = map.keySet().size();
                for (int j = 0; j < len; j++) {
                    ps.setObject(j + 1, valueset[j]);
                }
            }
            public int getBatchSize() {
                return list.size();
            }
      });
    } 
    @Override
    public int delete( String[] array,String[] param) {
    	if (array.length<=0) {
            return 0;
        }
        StringBuffer sql = new StringBuffer();
        sql.append(" Delete From ");
        sql.append(tableName + " where 1=1 ");
    	for (String str : array) {
    		sql.append(" and "+str+" ");
		}
        return this.getJdbcTemplate().update(sql.toString(), param); 
    }
    @Override
    public int delete(String id) {
    	String sql=" Delete From "+tableName + " where id=? ";
    	return this.getJdbcTemplate().update(sql, id); 
    }
    @Override
    public int updateBySql(String sql,String... args) {
        return this.getJdbcTemplate().update(sql,args); 
    }
    @Override
    public int update(Map<String,Object> paramMap,String id) {
    	List<Object> array=new ArrayList<Object>();
        StringBuffer sql = new StringBuffer();
        sql.append("UPDATE "+tableName+" set ");
//    	 for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
//    		   sql.append(entry.getKey()+"=?,");
//    		   array.add(entry.getValue());
//    	 }
    	 for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
  		   sql.append(entry.getKey()+"='"+entry.getValue()+"',");
    	 }
    	 sql.deleteCharAt(sql.length()-1);
    	 sql.append(" where id=?");
    	 array.add(id);
    	 System.out.println(array);
        return this.getJdbcTemplate().update(sql.toString(),id); 
    }
    
    /**
     * 组装插入语句
     * @param tableName
     * @param data
     * @param sqlArgs
     * @return
     */
    private String getInsertSql(JSONObject data, List<Object> sqlArgs){
    	if (data.size() <= 0) {
            return "";
        }
        final StringBuffer sql = new StringBuffer();
        sql.append(" INSERT INTO ");
        sql.append(tableName + " ( ");
    	Set<Entry<String, Object>> set = data.entrySet();
    	for (Iterator<Entry<String, Object>> iterator = set.iterator(); iterator.hasNext();) {
			Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
			sql.append(entry.getKey() + ",");
			sqlArgs.add(entry.getValue());
		}

        sql.delete(sql.length() - 1, sql.length());
        sql.append(" ) VALUES ( ");
        for (int i = 0; i < set.size(); i++) {
            sql.append("?,");
        }
        
        sql.delete(sql.length() - 1, sql.length());
        sql.append(" ) ");  
        return sql.toString();
    }
}
