package org.pinae.nala.xb.util;

import java.util.List;
import java.util.Map;

import org.pinae.nala.xb.xml.XmlText;

/**
 * 常量定义
 * 
 * @author Huiyugeng
 *
 */
public final class Constant {
	
	public static final String LIST_CLASS = List.class.getName(); //定义List的类型
	
	public static final String MAP_CLASS = Map.class.getName(); //定义Map的类型
	
	public static final String XML_CLASS = XmlText.class.getName();//定义XML的类型
	
	public static final String GET_PREFIX = "get"; //定义get方法前缀
	
	public static final String SET_PREFIX = "set"; //定义set方法前缀
	
	public static final String GET_NAMESPACE = "getNamespaces"; //定义获取XML命名空间方法名
	
	public static final String SET_NAMESPACE = "setNamespaces"; //定义获取XML命名空间方法名
	
	public static final String GET_VALUE = "getValue"; //定义getValue方法名
	
	public static final String SET_VALUE = "setValue"; //定义setValue方法名
	
	public static final String ROOT_TAG = "root"; //根节点默认名称
	
	public static final String XML_TAG = "xml"; //XML节点(绑定值XmlText)节点名称
}
