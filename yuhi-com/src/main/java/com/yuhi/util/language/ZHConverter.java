package com.yuhi.util.language;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class ZHConverter {
	private Properties charMap = new Properties();
	private Set<String> conflictingSets  = new HashSet<String>();

	public static final int TRADITIONAL = 0;
	public static final int SIMPLIFIED = 1;
	private static final int NUM_OF_CONVERTERS = 2;
	private static final ZHConverter[] converters = new ZHConverter[NUM_OF_CONVERTERS];
	private static final String[]  propertyFiles = new String[2];

	static {
		propertyFiles[TRADITIONAL] = "zh2Hant.properties";
		propertyFiles[SIMPLIFIED] = "zh2Hans.properties";
	}



	/**
	 *
	 * @param converterType 0 for traditional and 1 for simplified
	 * @return
	 */
	public static ZHConverter getInstance(int converterType) {

		if (converterType >= 0 && converterType < NUM_OF_CONVERTERS) {

			if (converters[converterType] == null) {
				synchronized(ZHConverter.class) {
					if (converters[converterType] == null) {
						converters[converterType] = new ZHConverter(propertyFiles[converterType]);
					}
				}
			}
			return converters[converterType];

		} else {
			return null;
		}
	}

	public static String convert(String text, int converterType) {
		ZHConverter instance = getInstance(converterType);
		return instance.convert(text);
	}


	private ZHConverter(String propertyFile) {

	    InputStream is = null;


	    is = getClass().getResourceAsStream(propertyFile);

		//File propertyFile = new File("C:/Temp/testMDB/TestTranslator/abc.txt");
		if (is != null) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(is));
				charMap.load(reader);
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (reader != null)
						reader.close();
					if (is != null)
						is.close();
				} catch (IOException e) {
				}
			}
		}
		initializeHelper();
	}

	@SuppressWarnings("rawtypes")
	private void initializeHelper() {
		Map<String,Integer> stringPossibilities = new HashMap<String,Integer>();
		Iterator iter = charMap.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			if (key.length() >= 1) {

				for (int i = 0; i < (key.length()); i++) {
					String keySubstring = key.substring(0, i + 1);
					if (stringPossibilities.containsKey(keySubstring)) {
						Integer integer = (Integer)(stringPossibilities.get(keySubstring));
						stringPossibilities.put(keySubstring, new Integer(
								integer.intValue() + 1));

					} else {
						stringPossibilities.put(keySubstring, new Integer(1));
					}

				}
			}
		}

		iter = stringPossibilities.keySet().iterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			if (((Integer)(stringPossibilities.get(key))).intValue() > 1) {
				conflictingSets.add(key);
			}
		}
	}

	public String convert(String in) {
		StringBuilder outString = new StringBuilder();
		StringBuilder stackString = new StringBuilder();

		for (int i = 0; i < in.length(); i++) {

			char c = in.charAt(i);
			String key = "" + c;
			stackString.append(key);

			if (conflictingSets.contains(stackString.toString())) {
			} else if (charMap.containsKey(stackString.toString())) {
				outString.append(charMap.get(stackString.toString()));
				stackString.setLength(0);
			} else {
				CharSequence sequence = stackString.subSequence(0, stackString.length()-1);
				stackString.delete(0, stackString.length()-1);
				flushStack(outString, new StringBuilder(sequence));
			}
		}

		flushStack(outString, stackString);

		return outString.toString();
	}


	private void flushStack(StringBuilder outString, StringBuilder stackString) {
		while (stackString.length() > 0){
			if (charMap.containsKey(stackString.toString())) {
					outString.append(charMap.get(stackString.toString()));
					stackString.setLength(0);

				} else {
					outString.append("" + stackString.charAt(0));
					stackString.delete(0, 1);
			}

		}
	}


	String parseOneChar(String c) {

		if (charMap.containsKey(c)) {
			return (String) charMap.get(c);
		}
		return c;
	}
	
	public static void main(String[] args) {
		//繁体转换简体
		String traditionalSrc = "核心提示：澳大利亞FAXTS新聞3月5日刊登評論認為，美國在全球一系列被解釋成用來防禦來自伊朗和朝鮮導彈襲擊的彈道導彈防禦系統的部署行為，以及最近將先進具有反導能力愛國者導彈出售給台灣的動作，其根本目的是針對兩個主要的核大國--中國和俄羅斯。";
		String simplified = ZHConverter.convert(traditionalSrc,ZHConverter.SIMPLIFIED);
		System.out.println(simplified);
		//简体转换繁体	
		String traditional = ZHConverter.convert(simplified, ZHConverter.TRADITIONAL);
		System.out.println(traditional);    
	}


}