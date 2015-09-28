package org.pinae.nala.xb.unmarshal.creator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.pinae.nala.xb.XmlObject;
import org.pinae.nala.xb.annotation.Attribute;
import org.pinae.nala.xb.annotation.Element;
import org.pinae.nala.xb.annotation.ElementValue;
import org.pinae.nala.xb.exception.UnmarshalException;
import org.pinae.nala.xb.node.AttributeConfig;
import org.pinae.nala.xb.node.NodeConfig;
import org.pinae.nala.xb.util.Constant;
import org.pinae.nala.xb.util.TypeConver;

/**
 * 根据注释信息构建Java对象
 * 
 * @author Huiyugeng
 *
 */
public class AnnotationObjectCreator extends DefaultObjectCreator{
	
	/**
	 * 构造函数
	 * 
	 * @param validation 是否启动属性和方法验证
	 */
	public AnnotationObjectCreator(boolean validation){
		super(validation);
	}
	
	/**
	 * 根据NodeConfig结构和带有注释的类生成对象
	 * 
	 * @param rootNode NodeConfig结构
	 * @param rootClass 需要生成对象的类
	 * @param rootObject 如果类为内部类, 则需要对象来生成内部类
	 * 
	 * @return NodeConfig生成的对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getObject(NodeConfig rootNode, Class rootClass, Object rootObject) 
			throws UnmarshalException, SecurityException{
		
		Field[] fields = rootClass.getDeclaredFields();
		Map<String, Field> elementMap = new HashMap<String, Field>();
		Map<String, Field> attributeMap = new HashMap<String, Field>();
		Field elementValue = null;
		
		for(Field field : fields){
			if(field.isAnnotationPresent(Element.class)){
				Element element = (Element)field.getAnnotation(Element.class);
				elementMap.put(element.name(), field);
				continue;
			}
			if(field.isAnnotationPresent(Attribute.class)){
				Attribute attribute = (Attribute)field.getAnnotation(Attribute.class);
				attributeMap.put(attribute.name(), field);
				continue;
			}
			if(field.isAnnotationPresent(ElementValue.class)){
				elementValue = field;
				continue;
			}
		}
		
		Object targetObject = null;
		try {
			if(rootObject!=null){
				Constructor constructor = rootClass.getConstructor(new Class[]{rootObject.getClass()});
                targetObject = constructor.newInstance(rootObject);
			}else{
				targetObject = rootClass.newInstance();
			}
		} catch (Exception e) {
			throw new UnmarshalException(e);
		}
		
		boolean isNalaObject = targetObject instanceof XmlObject ? true : false;
		
		if(rootNode.hasChildren()){
			List childrenNode = rootNode.getChildrenNodes();
			for(Iterator iterNode = childrenNode.iterator(); iterNode.hasNext();){
				NodeConfig nodeConfig = (NodeConfig)iterNode.next();
				
				Field field = elementMap.get(nodeConfig.getName());
				if(field!=null){
					String fieldType = field.getType().getName();
					Object fieldValue = null;
					List<Object> listField = null;
					
					field.setAccessible(true);
					
					if(fieldType.equals(Constant.LIST_CLASS)){
						String listTypeName = field.getGenericType().toString();
						fieldType = listTypeName.substring(Constant.LIST_CLASS.length() + 1, listTypeName.length() -1 );
						try {
							listField = (List<Object>)field.get(targetObject);
						} catch (IllegalArgumentException e) {
							throw new UnmarshalException(e);
						} catch (IllegalAccessException e) {
							throw new UnmarshalException(e);
						}
					}
					
					ObjectValueCreator objectValueCreator = new ObjectValueCreator();
					
					fieldValue = objectValueCreator.getValue(fieldType, nodeConfig);
					
					if(fieldValue==null){
						//如果字段类别是带有$(表示内部类)则采用先构造外部类, 然后通过外部类生成内部类实例
						if(fieldType.indexOf('$')>0){
							try{
								fieldValue = getObject(nodeConfig, Class.forName(fieldType), targetObject);
							}catch (ClassNotFoundException e) {
								throw new UnmarshalException(e);
							}
						}else{
							try{
								fieldValue = getObject(nodeConfig, Class.forName(fieldType), null);
							}catch (ClassNotFoundException e) {
								throw new UnmarshalException(e);
							}
						}
					}
					
					if(listField!=null){
						listField.add(fieldValue);
						fieldValue = listField;
					}
					
					setField(field, targetObject, fieldValue);
				}

			}

		}
		
		if(rootNode.getValue()!=null && elementValue!=null){
			setField(elementValue, targetObject, rootNode.getValue());
		}
		
		if(rootNode.hasAttributes()){
			List attributes = rootNode.getAttribute();
			if(attributes!=null && attributes.size()>0){
				for(Iterator iterAttribute = attributes.iterator(); iterAttribute.hasNext();){
					AttributeConfig attributeConfig = (AttributeConfig)iterAttribute.next();

					Field field = attributeMap.get(attributeConfig.getName());
					if(field!=null){
						String fieldType = field.getType().getName();
						Object attributeValue = TypeConver.converValue(fieldType, attributeConfig.getValue());
						setField(field, targetObject, attributeValue);
					}
				}
			}
		}
		
		if(isNalaObject){
			setNameSpaces(rootNode, targetObject);
		}
		
		return targetObject;
	}
	
	/*
	 * 执行字段, 将Node或Attribute的内容写入对象属性中
	 */
	private void setField(Field field, Object targetObject, Object value) throws UnmarshalException{
		field.setAccessible(true);
		try {
			field.set(targetObject, value);
		} catch (IllegalArgumentException e) {
			throw new UnmarshalException(e);
		} catch (IllegalAccessException e) {
			throw new UnmarshalException(e);
		}
	}
	
}
