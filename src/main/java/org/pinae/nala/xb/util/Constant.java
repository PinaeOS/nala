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
	
	/**
	 * List的类型名称
	 */
	public static final String LIST_CLASS = List.class.getName();
	
	/**
	 * Map的类型名称
	 */
	public static final String MAP_CLASS = Map.class.getName(); //
	
	/**
	 * XML文本的类型名称
	 */
	public static final String XML_CLASS = XmlText.class.getName();//
	
	/**
	 * get方法前缀
	 */
	public static final String GET_PREFIX = "get";
	
	/**
	 * set方法前缀
	 */
	public static final String SET_PREFIX = "set";
	
	/**
	 * 获取XML命名空间方法名
	 */
	public static final String GET_NAMESPACE = "getNamespaces";
	
	/**
	 * 获取XML命名空间方法名
	 */
	public static final String SET_NAMESPACE = "setNamespaces";
	
	/**
	 * getValue方法名
	 */
	public static final String GET_VALUE = "getValue";
	
	/**
	 * setValue方法名
	 */
	public static final String SET_VALUE = "setValue";
	
	/**
	 * 根节点默认名称
	 */
	public static final String ROOT_TAG = "root";
	
	/**
	 * XML节点(绑定值XmlText)节点名称
	 */
	public static final String XML_TAG = "xml";
	
	/**
	 * 节点名称 (用于Map解析)
	 */
	public static final String NODE_TAG = "nodeTag";
	
	/**
	 * 节点值 (用于Map解析)
	 */
	public static final String NODE_VALUE = "nodeValue";
	
}
