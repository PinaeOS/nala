package org.pinae.nala.xb.marshal.parser;

import java.util.List;
import java.util.Map;

import org.pinae.nala.xb.annotation.Root;
import org.pinae.nala.xb.exception.MarshalException;
import org.pinae.nala.xb.node.AttributeConfig;
import org.pinae.nala.xb.node.NodeConfig;
import org.pinae.nala.xb.util.TypeConver;
import org.pinae.nala.xb.xml.CdataText;
import org.pinae.nala.xb.xml.XmlText;

/**
 * 将目标解析为<code>NodeConfig</code>格式
 * 
 * 目标包括
 * <ul>
 * <li>解析的对象包括基本的对象(包括支持继承<code>XmlObject</code>的对象)</li>
 * <li>含有注释信息的对象</li>
 * <li><code>List</code></li>
 * <li><code>Map</code></li>
 * </ul>
 * 
 * @author Huiyugeng
 * 
 */
public class ObjectParser {
	/**
	 * 将对象解析为<code>NodeConfig</code>格式
	 * 
	 * @param nodeName 节点名称
	 * @param rootObject 需要解析的对象
	 * 
	 * @return 解析后的<code>NodeConfig</code>格式
	 * 
	 * @throws MarshalException 解析异常
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NodeConfig parse(String nodeName, Object rootObject) throws MarshalException {
		Class rootClass = rootObject.getClass();

		if (TypeConver.isBesicType(rootClass)) {
			return new NodeConfig(nodeName, rootObject.toString());
		} else if (rootObject instanceof List) {
			return new ListParser().parse(nodeName, (List) rootObject);
		} else if (rootObject instanceof Map) {
			return new MapParser().parse(nodeName, (Map) rootObject);
		} else if (rootObject instanceof XmlText) {
			return new NodeConfig(nodeName, rootObject);
		} else if (rootObject instanceof CdataText) {
			return new NodeConfig(nodeName, rootObject);
		} else {
			if (rootClass.isAnnotationPresent(Root.class)) {
				return new AnnotationObjectParser().parse(nodeName, rootObject);
			} else {
				return new DefaultObjectParser().parse(rootObject);
			}
		}
	}

	/**
	 * 根据字段类型解析对象值并构造节点
	 * 
	 * @param fieldType 字段类型
	 * @param name 节点配置名称
	 * @param value 节点配置值
	 * 
	 * @return 节点配置信息
	 */
	protected NodeConfig parseNodeValue(String fieldType, String name, Object value) {
		value = parseValue(fieldType, value);
		if (value != null) {
			NodeConfig nodeConfig = new NodeConfig();
			nodeConfig.setName(name);
			nodeConfig.setValue(value.toString());
			return nodeConfig;
		}
		return null;
	}

	/**
	 * 根据字段类型解析对象之并构建属性
	 * 
	 * @param fieldType 字段类型
	 * @param name 属性配置名称
	 * @param value 属性配置值
	 * 
	 * @return 属性配置信息
	 */
	protected AttributeConfig parseAttributeValue(String fieldType, String name, Object value) {
		value = parseValue(fieldType, value);
		if (value != null) {
			AttributeConfig attributeConfig = new AttributeConfig();
			attributeConfig.setName(name);
			attributeConfig.setValue(value.toString());
			return attributeConfig;
		}
		return null;
	}

	/*
	 * 根据字段类型获取对象值并转化为字符串
	 * 
	 * @param fieldType 字段类型
	 * 
	 * @param value 对象值
	 * 
	 * @return 对象后的字符串
	 */
	private String parseValue(String fieldType, Object value) {
		if (TypeConver.isBasicType(fieldType)) {
			if (value != null) {
				return value.toString();
			} else {
				return null;
			}
		}
		return null;
	}
}
