package com.yuhi.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类<br>
 * 1.数据库主键值或UUID字段值的生成<br>
 * 
 * @version 2015年5月28日 下午1:15:19
 */
public class IDCreate {
	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String numberChar = "0123456789";

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无"-"分割.<br>
	 * 例如：57d7058dbc79444db7e57a5d0b955cc8<br>
	 */
	public static String uuidNotSplit() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 封装JDK自带的UUID<br>
	 * 例如：57d7058d-bc79-444d-b7e5-7a5d0b955cc8<br>
	 * */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 返回一个定长的随机字符串(只包含大小写字母、数字)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateString() {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i <= 2; i++) {
			sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
		}
		return sb.toString();
	}

	public synchronized static String getDateString() {
		@SuppressWarnings("unused")
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String reTime = format.format(date);
		return reTime;
	}

	public synchronized static String getFileDateString() {
		@SuppressWarnings("unused")
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String reTime = format.format(date);
		return reTime;
	}

	/**
	 * 返回一个定长的随机纯字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateMixString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(letterChar.length())));
		}
		return sb.toString();
	}

	/**
	 * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateLowerString(int length) {
		return generateMixString(length).toLowerCase();
	}

	/**
	 * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
	 * 
	 * @param length
	 *            随机字符串长度
	 * @return 随机字符串
	 */
	public static String generateUpperString(int length) {
		return generateMixString(length).toUpperCase();
	}

	/**
	 * 生成一个定长的纯0字符串
	 * 
	 * @param length
	 *            字符串长度
	 * @return 纯0字符串
	 */
	public static String generateZeroString(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append('0');
		}
		return sb.toString();
	}

	/**
	 * 根据数字生成一个定长的字符串，长度不够前面补0
	 * 
	 * @param num
	 *            数字
	 * @param fixdlenth
	 *            字符串长度
	 * @return 定长的字符串
	 */
	public static String toFixdLengthString(long num, int fixdlenth) {
		StringBuffer sb = new StringBuffer();
		String strNum = String.valueOf(num);
		if (fixdlenth - strNum.length() >= 0) {
			sb.append(generateZeroString(fixdlenth - strNum.length()));
		} else {
			throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth
					+ "的字符串发生异常！");
		}
		sb.append(strNum);
		return sb.toString();
	}

	/**
	 * 根据数字生成一个定长的字符串，长度不够前面补0
	 * 
	 * @param num
	 *            数字
	 * @param fixdlenth
	 *            字符串长度
	 * @return 定长的字符串
	 */
	public static String toFixdLengthString(int num, int fixdlenth) {
		StringBuffer sb = new StringBuffer();
		String strNum = String.valueOf(num);
		if (fixdlenth - strNum.length() >= 0) {
			sb.append(generateZeroString(fixdlenth - strNum.length()));
		} else {
			throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth
					+ "的字符串发生异常！");
		}
		sb.append(strNum);
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(getDateString());
		// System.out.println(generateString());
		// // System.out.println(getBicker());
		// System.out.println(generateMixString(15));
		// System.out.println(generateLowerString(15));
		// System.out.println(generateUpperString(15));
		// System.out.println(generateZeroString(15));
		// System.out.println(toFixdLengthString(123, 15));
		// System.out.println(toFixdLengthString(123L, 15));
	}

}
