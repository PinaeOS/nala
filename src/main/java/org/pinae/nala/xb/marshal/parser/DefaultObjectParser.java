package org.pinae.nala.xb.marshal.parser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pinae.nala.xb.NalaObject;
import org.pinae.nala.xb.exception.MarshalException;
import org.pinae.nala.xb.resource.AttributeConfig;
import org.pinae.nala.xb.resource.Namespace;
import org.pinae.nala.xb.resource.NodeConfig;
import org.pinae.nala.xb.util.Constant;
import org.pinae.nala.xb.util.TypeConver;
import org.pinae.nala.xb.xml.XmlElementUtils;

/**
 * 将Object解析为中间格式
 * 
 * @author Huiyugeng
 *
 */
public class DefaultObjectParser extends ObjectParser {
	private static Map<String, String> mapNamespaces = new HashMap<String, String>();

	/**
	 * 获得Object中记录的命名空间信息
	 * 
	 * @return 将命名空间列表
	 */
	@SuppressWarnings("rawtypes")
	public static Map getNamespaces() {
		return mapNamespaces;
	}

	/**
	 * 将Object进行解析
	 * 
	 * @param rootObject 需要解析的Object
	 * 
	 * @return 将Object解析的NodeConfig
	 * 
	 * @throws MarshalException 编组异常
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public NodeConfig parse(Object rootObject) throws MarshalException {

		NodeConfig node = new NodeConfig();
		List<AttributeConfig> attributeList = new ArrayList<AttributeConfig>();
		List<NodeConfig> nodeList = new ArrayList<NodeConfig>();

		Class cls = rootObject.getClass();
		String nodeName = XmlElementUtils.mapObjectToXML(cls.getSimpleName());

		try {

			Method methods[] = cls.getDeclaredMethods();

			for (Method method : methods) {
				if (method.getName().startsWith(Constant.GET_PREFIX)) {
					String methodReturnType = method.getReturnType().getName();
					String methodName = method.getName();

					methodName = XmlElementUtils.mapObjectToXML(methodName.substring(3));

					// 如果返回值为字符串类型
					if (TypeConver.isBasicType(methodReturnType)) {

						// 全部加入到XML结构体属性中
						Object attributeValue = method.invoke(rootObject, null);

						if (attributeValue != null) {

							AttributeConfig attributeConfig = new AttributeConfig();
							attributeConfig.setName(methodName);
							attributeConfig.setValue(attributeValue.toString());
							attributeList.add(attributeConfig);
						}

					}
					// 如果返回为List类型, 则循环获取每一项XML结构体
					else if (methodReturnType.equals(Constant.LIST_CLASS)) {
						List list = (List) method.invoke(rootObject, null);
						for (Object item : list) {
							nodeList.add(super.parse(methodName, item));
						}
					}
					// 如果为其他类型, 则递归进入该类进行类解析
					else {
						Object object = method.invoke(rootObject, null);
						if (object != null) {
							nodeList.add(super.parse(methodName, object));
						}
					}
				}
			}

			// 如果是getValue方法则获取Value值并附到XML结构体中
			try {
				Method methodValue = cls.getMethod(Constant.GET_VALUE, null);
				if (methodValue != null) {
					String value = (String) methodValue.invoke(rootObject, null);
					node.setValue(value);
				}
			} catch (NoSuchMethodException e) {

			}

			// 如果是NalaObject的派生类, 则设置命名空间
			boolean isNalaObject = rootObject instanceof NalaObject ? true : false; // 判读该类是否继承NalaObject
			if (isNalaObject) {
				Method methodNamespace = cls.getMethod(Constant.GET_NAMESPACE, null);
				List namespaces = (List) methodNamespace.invoke(rootObject, null);
				if (namespaces != null && namespaces.size() > 0) {
					for (int i = 0; i < namespaces.size(); i++) {
						Namespace namespace = (Namespace) namespaces.get(i);
						node.setNamespace(namespace.getPrefix(), namespace.getUri());
						mapNamespaces.put(namespace.getPrefix(), namespace.getUri());
					}
				}

			}

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

		node.setName(nodeName);
		node.setAttribute(attributeList);
		node.setChildrenNodes(nodeList);

		return node;
	}
}
