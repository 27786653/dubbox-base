package com.yuhi.support.properties;

import com.yuhi.util.language.AESUtil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * ClassName EncryptPropertyPlaceholderConfigurer.
 * @coment: 加密配置文件
 * @user: 李森林
 * @category @author www.justintoForest@Gamil.com
 * @since: 2017-05-10 下午 02:14
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	/**
	 * @category 加密的属性名.
	 * @author www.justintoForest@Gamil.com
	 * @since 2017-05-10 下午 02:54
	 */
	private String[] propertyNames;

	/**
	 * 解密指定propertyName的加密属性值
	 * @param propertyName
	 * @param propertyValue
	 * @return
	 */
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		for (String p : propertyNames) {
			if (p.equalsIgnoreCase(propertyName)) {
				return AESUtil.AESDecode(propertyValue);
			}
		}
		return super.convertProperty(propertyName, propertyValue);
	}


	public String[] getPropertyNames() {
		return propertyNames;
	}

	public void setPropertyNames(String[] propertyNames) {
		this.propertyNames = propertyNames;
	}
}
