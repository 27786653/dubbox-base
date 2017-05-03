package com.yuhi.base.web.support;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LayuiDateConverter implements Converter<String,Date>{

	@Override
	public Date convert(String source) {
		//实现 将日期串转成日期类型(格式是yyyy-MM-dd HH:mm:ss)
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				//转成直接返回
				return simpleDateFormat.parse(source);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			System.out.println("转换错误");
		}
		//如果参数绑定失败返回null
		return null;
	}

}