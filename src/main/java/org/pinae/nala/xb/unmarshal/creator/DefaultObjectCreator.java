package org.pinae.nala.xb.unmarshal.creator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.pinae.nala.xb.NalaObject;
import org.pinae.nala.xb.exception.UnmarshalException;
import org.pinae.nala.xb.resource.AttributeConfig;
import org.pinae.nala.xb.resource.Namespace;
import org.pinae.nala.xb.resource.NodeConfig;
import org.pinae.nala.xb.util.Constant;
import org.pinae.nala.xb.util.TypeConver;

/**
 * 根据结构体和默认规则构建Java对象
 * 
 * @author Huiyugeng
 *
 */
public class DefaultObjectCreator {
	// 是否启动属性和方法验证, 如果启动验证, 当出现NoSuchField或NoSuchMethod时, 抛出UnmarshalException
	private boolean validation = false;

	/**
	 * 构造函数
	 * 
	 * @param validation 是否启动属性和方法验证
	 */
	public DefaultObjectCreator(boolean validation) {
		this.validation = validation;
	}

	/**
	 * 根据NodeConfig结构生成对象
	 * 
	 * @param rootNode NodeConfig结构
	 * @param rootClass 需要生成对象的类
	 * @param rootObject 如果类为内部类, 则需要对象来生成内部类
	 * 
	 * @return NodeConfig生成的对象
	 * 
	 * @throws UnmarshalException 解组异常
	 * @throws SecurityException 安全检查异常
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getObject(NodeConfig rootNode, Class rootClass, Object rootObject) throws UnmarshalException, SecurityException {
		Object targetObject = null;
		try {
			if (rootObject != null) {
				Constructor constructor = rootClass.getConstructor(new Class[] { rootObject.getClass() });
				targetObject = constructor.newInstance(rootObject);
			} else {
				targetObject = rootClass.newInstance();
			}
		} catch (Exception e) {
			throw new UnmarshalException(e);
		}

		boolean isNalaObject = targetObject instanceof NalaObject ? true : false;

		if (rootNode.hasChildren()) {
			List childrenNode = rootNode.getChildrenNodes();
			for (Iterator iterNode = childrenNode.iterator(); iterNode.hasNext();) {
				NodeConfig nodeConfig = (NodeConfig) iterNode.next();
				Field field = null;
				try {
					String fieldName = nodeConfig.getName();
					field = rootClass.getDeclaredField(fieldName);
					if (field == null) {
						throw new UnmarshalException(new NoSuchFieldException());
					}
				} catch (SecurityException e) {
					throw new UnmarshalException(e);
				} catch (NoSuchFieldException e) {
					throw new UnmarshalException(e);
				}
				String fieldType = field.getType().getName();
				Object methodValue = null;
				String methodName = Constant.SET_PREFIX + StringUtils.capitalize(nodeConfig.getName());

				// 从如果字段类别为List<E>则从其参数化引用中取得E的类型
				if (fieldType.equals(Constant.LIST_CLASS)) {
					String listTypeName = field.getGenericType().toString();
					fieldType = listTypeName.substring(Constant.LIST_CLASS.length() + 1, listTypeName.length() - 1);
				}

				ObjectValueCreator objectValueCreator = new ObjectValueCreator();

				methodValue = objectValueCreator.getValue(fieldType, nodeConfig);

				if (methodValue == null) {
					// 如果字段类别是带有$(表示内部类)则采用先构造外部类, 然后通过外部类生成内部类实例
					if (fieldType.indexOf('$') > 0) {
						try {
							methodValue = getObject(nodeConfig, Class.forName(fieldType), targetObject);
						} catch (ClassNotFoundException e) {
							throw new UnmarshalException(e);
						}
					} else {
						try {
							methodValue = getObject(nodeConfig, Class.forName(fieldType), null);
						} catch (ClassNotFoundException e) {
							throw new UnmarshalException(e);
						}
					}
				}

				try {
					Method method = rootClass.getMethod(methodName, TypeConver.getTypeClass(fieldType));
					if (method != null)
						setMethod(method, targetObject, methodValue);
				} catch (NoSuchMethodException e) {
					if (validation) {
						throw new UnmarshalException(e);
					}
				} catch (ClassNotFoundException e) {
					if (validation) {
						throw new UnmarshalException(e);
					}
				}
			}

		} else {
			try {
				Method method = rootClass.getMethod(Constant.SET_VALUE, String.class);
				if (method != null)
					setMethod(method, targetObject, rootNode.getValue());
			} catch (NoSuchMethodException e) {
				if (validation) {
					throw new UnmarshalException(e);
				}
			}
		}
		if (rootNode.hasAttributes()) {
			setAttribute(rootNode, targetObject);
		}

		if (isNalaObject) {
			setNameSpaces(rootNode, targetObject);
		}

		return targetObject;
	}

	/*
	 * 根据XML构造体在对象中填入属性
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setAttribute(NodeConfig nodeConfig, Object targetObject) throws UnmarshalException {
		Class targetClass = targetObject.getClass();
		List attributes = nodeConfig.getAttribute();
		if (attributes != null && attributes.size() > 0) {
			for (Iterator iterAttr = attributes.iterator(); iterAttr.hasNext();) {
				AttributeConfig attribute = (AttributeConfig) iterAttr.next();
				String methodName = Constant.SET_PREFIX + StringUtils.capitalize(attribute.getName());
				try {
					Method method = targetClass.getMethod(methodName, String.class);
					if (method != null) {
						setMethod(method, targetObject, attribute.getValue());
					}
				} catch (NoSuchMethodException e) {
					if (validation) {
						throw new UnmarshalException(e);
					}
				}
			}
		}
	}

	/*
	 * 执行方法, 将Node或Attribute的内容写入对象属性中
	 */
	private void setMethod(Method method, Object targetObject, Object value) throws UnmarshalException {
		try {
			method.invoke(targetObject, value);
		} catch (IllegalArgumentException e) {
			throw new UnmarshalException(e);
		} catch (IllegalAccessException e) {
			throw new UnmarshalException(e);
		} catch (InvocationTargetException e) {
			throw new UnmarshalException(e);
		}
	}

	/*
	 * 将命名空间内容写入 NalaObject派生对象的属性中
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void setNameSpaces(NodeConfig nodeConfig, Object targetObject) throws UnmarshalException {
		Class targetClass = targetObject.getClass();

		List namespaces = nodeConfig.getNamespace();

		if (namespaces != null && namespaces.size() > 0) {
			for (int i = 0; i < namespaces.size(); i++) {
				Method method;
				try {
					method = targetClass.getMethod(Constant.SET_NAMESPACE, Namespace.class);
					if (method != null)
						setMethod(method, targetObject, namespaces.get(i));
				} catch (NoSuchMethodException e) {
					// ignore exception
				}

			}
		}
	}

}
