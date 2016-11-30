package com.yuhi.support;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class CustomDateConverter implements Converter<String,Date>{

	@Override
	public Date convert(String source) {
		Long time=0l;
		//实现 将日期串转成日期类型(格式是yyyy-MM-dd HH:mm:ss)
		try {
			time=Long.parseLong(source);
			return new Date(time);
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			System.out.println("转换错误");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				//转成直接返回
				return simpleDateFormat.parse(source);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//如果参数绑定失败返回null
		return null;
	}

}
