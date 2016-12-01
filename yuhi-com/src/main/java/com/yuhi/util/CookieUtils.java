package com.yuhi.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie工具类
 * @version 2015年5月27日 下午3:51:27
 */
public class CookieUtils
{
	/**
	 * 设置 Cookie（生存时间：1天）
	 * 
	 * @param name 名称
	 * @param value 值
	 */
	public static void setCookie(HttpServletResponse response, String name, String value)
	{
		setCookie(response, name, value, 60 * 60 * 24);
	}

	/**
	 * 设置 Cookie，设置Cookie的Path为"/"
	 * 
	 * @param name  名称
	 * @param value 值
	 * @param maxAge 生存时间（单位：秒）
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, int maxAge)
	{
		Cookie cookie = new Cookie(name, null);
		cookie.setPath("/");//设置path为根目录，其所有子目录都能访问
		cookie.setMaxAge(maxAge);
		try
		{
			cookie.setValue(URLEncoder.encode(value, "utf-8"));
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		response.addCookie(cookie);
	}

	/**
	 * 获得指定Cookie的值
	 * 
	 * @param name 名称
	 * @return Cookie值
	 */
	public static String getCookie(HttpServletRequest request, String name)
	{
		return getCookie(request, null, name, false);
	}

	/**
	 * 获得指定Cookie的值，并删除。
	 * 
	 * @param name 名称
	 * @return Cookie值
	 */
	public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name)
	{
		return getCookie(request, response, name, true);
	}

	/**
	 * 获得指定Cookie的值
	 * 
	 * @param request  请求对象
	 * @param response 响应对象
	 * @param name 名字
	 * @param isRemove 是否移除
	 * @return Cookie值
	 */
	public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name, boolean isRemove)
	{
		String value = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
		{
			for (Cookie cookie : cookies)
			{
				if (cookie.getName().equals(name))
				{
					try
					{
						value = URLDecoder.decode(cookie.getValue(), "utf-8");
					}
					catch (UnsupportedEncodingException e)
					{
						e.printStackTrace();
					}
					if (isRemove)
					{
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
		}
		return value;
	}
}
