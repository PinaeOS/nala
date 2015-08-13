package org.pinae.nala.xb.marshal.parser;

import java.util.List;
import java.util.Map;

import org.pinae.nala.xb.annotation.Root;
import org.pinae.nala.xb.exception.MarshalException;
import org.pinae.nala.xb.resource.AttributeConfig;
import org.pinae.nala.xb.resource.NodeConfig;
import org.pinae.nala.xb.util.TypeConver;
import org.pinae.nala.xb.xml.CdataObject;
import org.pinae.nala.xb.xml.XmlObject;

/**
 * 对象解析
 * 
 * @author Huiyugeng
 * 
 */
public class ObjectParser {
	/**
	 * 将对象解析为NodeConfig中间格式
	 * 
	 * @param rootObject 需要解析的对象
	 * 
	 * @return 解析后的中间格式
	 * 
	 * @throws MarshalException 解析异常
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NodeConfig parse(String key, Object rootObject)
			throws MarshalException {
		Class rootClass = rootObject.getClass();

		if (TypeConver.isBesicType(rootClass)) {
			return new NodeConfig(key, rootObject.toString());
		} else if (rootObject instanceof List) {
			return new ListParser().parse(key, (List) rootObject);
		} else if (rootObject instanceof Map) {
			return new MapParser().parse(key, (Map) rootObject);
		} else if (rootObject instanceof XmlObject) {
			return new NodeConfig(key, rootObject);
		} else if (rootObject instanceof CdataObject){
			return new NodeConfig(key, rootObject);
		}else {
			if (rootClass.isAnnotationPresent(Root.class)) {
				return new AnnotationObjectParser().parse(key, rootObject);
			} else {
				return new DefaultObjectParser().parse(rootObject);
			}
		}
	}

	/**
	 * 根据字段类型解析对象值并构造结点
	 * 
	 * @param fieldType 字段类型
	 * @param name 结点配置名称
	 * @param value 结点配置值
	 * 
	 * @return 结点配置信息
	 */
	public NodeConfig parseNodeConfigValue(String fieldType, String name,
			Object value) {
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
	public AttributeConfig parseAttributeConfigValue(String fieldType,
			String name, Object value) {
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
	 * 根据字段类型获取对象值并转化为String
	 * 
	 * @param fieldType 字段类型
	 * 
	 * @param value 对象值
	 * 
	 * @return 对象之转化为String
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
