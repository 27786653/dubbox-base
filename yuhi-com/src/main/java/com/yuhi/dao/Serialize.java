package com.yuhi.dao;
/**
 * 抽象序列化接口
 * @author 李森林
 *
 */
public interface Serialize {
	/**
	 * 序列化
	 * @param object
	 * @return
	 */
	public  byte[] serialize(Object object);
	/**
	 * 反序列化
	 * @param bytes
	 * @return
	 */
	public  Object unserialize(byte[] bytes);
}