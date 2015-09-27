package org.pinae.nala.xb.marshal.parser;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.pinae.nala.xb.NalaObject;
import org.pinae.nala.xb.annotation.Attribute;
import org.pinae.nala.xb.annotation.Element;
import org.pinae.nala.xb.annotation.ElementValue;
import org.pinae.nala.xb.exception.MarshalException;
import org.pinae.nala.xb.resource.AttributeConfig;
import org.pinae.nala.xb.resource.Namespace;
import org.pinae.nala.xb.resource.NodeConfig;
import org.pinae.nala.xb.util.Constant;


/**
 * 将带注释的Object解析为NodeConfig格式
 * 
 * @author Huiyugeng
 *
 */
public class AnnotationObjectParser extends ObjectParser{
	private static Map<String, String> mapNamespaces = new HashMap<String, String>();

	/**
	 * 获得Object中记录的命名空间信息
	 * 
	 * @return 将命名空间列表
	 */
	@SuppressWarnings("rawtypes")
	public static Map getNamespaces(){
		return mapNamespaces;
	}
	
	/**
	 * 将带注释的Object解析为NodeConfig格式
	 * 
	 * @param nodeName 根节点名称
	 * @param rootObject 需要解析的Object
	 * 
	 * @return Object解析后的NodeConfig格式
	 * 
	 * @throws MarshalException 编组异常
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NodeConfig parse(String nodeName, Object rootObject) throws MarshalException{
		boolean isNalaObject = rootObject instanceof NalaObject ? true : false; //判读该类是否继承NalaObject
		
		NodeConfig rootNode = new NodeConfig();
		List<AttributeConfig> attributeConfigList = new ArrayList<AttributeConfig>();
		List<NodeConfig> nodeConfigList = new ArrayList<NodeConfig>();
		try {
			Class rootClass = rootObject.getClass();
			Field fields[] = rootClass.getDeclaredFields();
			for (Field field : fields){
				
				field.setAccessible(true);
				
				String fieldType = field.getType().getName();
				
				if(field.isAnnotationPresent(Element.class)){
					Element element = (Element)field.getAnnotation(Element.class);
					
					NodeConfig nodeConfig = parseNodeConfigValue(fieldType, 
							element.name(), 
							field.get(rootObject));
					
					if(nodeConfig!=null){
						nodeConfigList.add(nodeConfig);
					}
					
					//如果返回为List类型, 则循环获取每一项XML结构体
					else if (fieldType.equals(Constant.LIST_CLASS)){
						List lstValue = (List)field.get(rootObject);
						for(Object value : lstValue){
							String listTypeName = field.getGenericType().toString();
							String fieldGenericType = StringUtils.substringAfter(listTypeName, Constant.LIST_CLASS);
							
							nodeConfig = parseNodeConfigValue(fieldGenericType, element.name(), value);
							if(nodeConfig==null){
								nodeConfig = super.parse(element.name(), value);
							}
							nodeConfigList.add(nodeConfig);
						}
					}
					//如果为其他类型, 则递归进入该类进行类解析
					else {
						Object value = field.get(rootObject);
						if(value!=null){
							nodeConfigList.add(super.parse(element.name(), value));
						}
					}
				}
				
				if(field.isAnnotationPresent(Attribute.class)){
					Attribute attribute = (Attribute)field.getAnnotation(Attribute.class);
					AttributeConfig attributeConfig = parseAttributeConfigValue(fieldType, 
							attribute.name(), 
							field.get(rootObject));
					if(attributeConfig!=null){
						attributeConfigList.add(attributeConfig);
					}
				}
				
				if(field.isAnnotationPresent(ElementValue.class)){
					rootNode.setValue(rootObject.toString());
				}

			}

			
			//如果是NalaObject的派生类, 则设置命名空间
			if(isNalaObject){
				Method methodNamespace = rootClass.getMethod(Constant.GET_NAMESPACE, null);
				List namespaces = (List)methodNamespace.invoke(rootObject, null);
				if(namespaces != null && namespaces.size()>0){
					for(int i=0; i<namespaces.size() ; i++){
						Namespace namespace = (Namespace)namespaces.get(i);
						rootNode.setNamespace(namespace.getPrefix(),namespace.getUri());
						mapNamespaces.put(namespace.getPrefix(),namespace.getUri());
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
