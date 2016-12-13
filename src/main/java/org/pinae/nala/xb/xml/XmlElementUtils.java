package org.pinae.nala.xb.xml;

/**
 * XML元素处理函数库
 * 
 * @author Huiyugeng
 *
 */
public class XmlElementUtils {
	/*
	 * 为防止XML标签/属性与Java关键字冲突, 构建XML标签与Java关键字之间的映射, 
	 * 例如XML描述class映射到Java属性为kwClass
	 */
	private static String[][] keyWordMapping = {
			{"class","kwClass"},
			{"interface","kwInterface"},
			{"package","kwPackage"},
			{"int", "kwInt"},
			{"long", "kwLong"},
			{"double", "kwDouble"},
			{"float", "kwFloat"},
			{"public", "kwPublic"},
			{"private", "kwPrivate"},
			{"protected","keProtected"},
			{"static", "kwStatic"},
			{"final", "kwFinal"}
		};
	
	/**
	 * XML中Element Name/Attribute Name与Class Name/Method Name中名称映射
	 * 
	 * @param xml XML Element Name/Attribute Name
	 * 
	 * @return 映射后的名称, 例如class映射后为kwClass
	 */
	public static String mapXMLToObject(String xml) {
		for (int i = 0; i < keyWordMapping.length; i++) {
			if (keyWordMapping[i][0].equalsIgnoreCase(xml)) {
				return keyWordMapping[i][1];
			}
		}
		return xml;
	}

	/**
	 * XML中Element Name/Attribute Name与Class中Class Name/Method Name中名称映射
	 * 
	 * @param clazz Class Name/Method Name
	 * 
	 * @return 映射后的名称, 例如class映射后为kwClass
	 */
	public static String mapObjectToXML(String clazz) {
		for (int i = 0; i < keyWordMapping.length; i++) {
			if (keyWordMapping[i][1].equalsIgnoreCase(clazz)) {
				return keyWordMapping[i][0];
			}
		}
		return clazz;
	}
	
	/**
	 * 判断字符串中是否含有XML的转义符
	 * 
	 * @param xml 需要判断的字符串
	 * 
	 * @return 转义符
	 */
	public static boolean containXMLEscapeChar(String xml) {
		String escapes[] = { "<", ">", "&", "\'", "\"" };

		for (String escape : escapes) {
			if (xml.contains(escape)) {
				return true;
			}
		}
		return false;
	}
}
