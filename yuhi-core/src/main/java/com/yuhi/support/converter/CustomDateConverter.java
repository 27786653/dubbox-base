package com.yuhi.support.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName CustomDateConverter.
 * @coment: 自定义日期转换
 * @user: 李森林
 * @category @author www.justintoForest@Gamil.com
 * @since: 2017-05-10 下午 02:49
 */
public class CustomDateConverter implements Converter<String,Date>{

	private String dateFormat="yyyy-MM-dd HH:mm:ss";

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
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
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

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
}
