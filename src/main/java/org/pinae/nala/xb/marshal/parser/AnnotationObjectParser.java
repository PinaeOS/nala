package org.pinae.nala.xb.marshal.parser;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.pinae.nala.xb.XmlObject;
import org.pinae.nala.xb.annotation.Attribute;
import org.pinae.nala.xb.annotation.Element;
import org.pinae.nala.xb.annotation.ElementValue;
import org.pinae.nala.xb.exception.MarshalException;
import org.pinae.nala.xb.node.AttributeConfig;
import org.pinae.nala.xb.node.Namespace;
import org.pinae.nala.xb.node.NodeConfig;
import org.pinae.nala.xb.util.Constant;

/**
 * 将带注释的Object解析为<code>NodeConfig</code>格式
 * 
 * @author Huiyugeng
 *
 */
public class AnnotationObjectParser extends ObjectParser {

	/**
	 * 将带注释的Object解析为<code>NodeConfig</code>中间格式
	 * 
	 * @param nodeName 根节点名称
	 * @param rootObject 需要解析的Object
	 * 
	 * @return Object解析后的<code>NodeConfig</code>中间格式
	 * 
	 * @throws MarshalException 编组异常
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NodeConfig parse(String nodeName, Object rootObject) throws MarshalException {
		boolean isXmlObject = rootObject instanceof XmlObject ? true : false; // 判读该类是否继承XmlObject

		NodeConfig rootNode = new NodeConfig();
		List<AttributeConfig> attributeConfigList = new ArrayList<AttributeConfig>();
		List<NodeConfig> nodeConfigList = new ArrayList<NodeConfig>();
		try {
			Class rootClass = rootObject.getClass();
			Field fields[] = rootClass.getDeclaredFields();
			for (Field field : fields) {

				field.setAccessible(true);

				String fieldType = field.getType().getName();

				if (field.isAnnotationPresent(Element.class)) {
					Element element = (Element) field.getAnnotation(Element.class);

					NodeConfig nodeConfig = parseNodeValue(fieldType, element.name(), field.get(rootObject));

					if (nodeConfig != null) {
						nodeConfigList.add(nodeConfig);
					}

					// 如果返回为List类型, 则循环获取每一项XML结构体
					else if (fieldType.equals(Constant.LIST_CLASS)) {
						List lstValue = (List) field.get(rootObject);
						for (Object value : lstValue) {
							String listTypeName = field.getGenericType().toString();
							String fieldGenericType = StringUtils.substringAfter(listTypeName, Constant.LIST_CLASS);

							nodeConfig = parseNodeValue(fieldGenericType, element.name(), value);
							if (nodeConfig == null) {
								nodeConfig = super.parse(element.name(), value);
							}
							nodeConfigList.add(nodeConfig);
						}
					}
					// 如果为其他类型, 则递归进入该类进行类解析
					else {
						Object value = field.get(rootObject);
						if (value != null) {
							nodeConfigList.add(super.parse(element.name(), value));
						}
					}
				}

				// 属性解析
				if (field.isAnnotationPresent(Attribute.class)) {
					Attribute attribute = (Attribute) field.getAnnotation(Attribute.class);
					AttributeConfig attributeConfig = parseAttributeValue(fieldType, attribute.name(), field.get(rootObject));
					if (attributeConfig != null) {
						attributeConfigList.add(attributeConfig);
					}
				}

				// 节点值解析
				if (field.isAnnotationPresent(ElementValue.class)) {
					rootNode.setValue(rootObject.toString());
				}

			}

			// 如果是XmlObject的派生类, 则设置命名空间
			if (isXmlObject) {
				Method methodNamespace = rootClass.getMethod(Constant.GET_NAMESPACE, null);
				List namespaces = (List) methodNamespace.invoke(rootObject, null);
				if (namespaces != null && namespaces.size() > 0) {
					for (int i = 0; i < namespaces.size(); i++) {
						Namespace namespace = (Namespace) namespaces.get(i);
						rootNode.setNamespace(namespace.getPrefix(), namespace.getUri());
					}
				}
			}
			rootNode.setAttribute(attributeConfigList);
			rootNode.setChildrenNodes(nodeConfigList);
			rootNode.setName(nodeName);
		} catch (IllegalAccessException e) {
			throw new MarshalException(e);
		} catch (IllegalArgumentException e) {
			throw new MarshalException(e);
		} catch (InvocationTargetException e) {
			throw new MarshalException(e);
		} catch (SecurityException e) {
			throw new MarshalException(e);
		} catch (NoSuchMethodException e) {
			throw new MarshalException(e);
		}
		return rootNode;
	}
}
