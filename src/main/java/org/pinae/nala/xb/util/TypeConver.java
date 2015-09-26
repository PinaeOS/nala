package org.pinae.nala.xb.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;


/**
 * 基础类型转换
 * 
 * @author Huiyugeng
 *
 */
public class TypeConver {
	private static Logger log = Logger.getLogger(TypeConver.class);
	
	/*
	 * 构建基本数据类型
	 */
	private static final String[] basicTypeArray = {
		"int", "boolean", "float", "double", "short", "char", "long", "byte",
		"java.lang.String", "java.lang.Integer", "java.lang.Boolean",
		"java.lang.Double", "java.lang.Short", "java.lang.Character",
		"java.lang.Long", "java.lang.Byte", "java.math.BigDecimal", "java.math.BigInteger",
		"java.util.Date"
	};
	
	/**
	 * 判断类是否为基本数据类型
	 * 
	 * @param cls 类信息
	 * 
	 * @return 是否基本数据类型
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isBesicType(Class cls){
		if (cls == null) return false;
		String type = cls.getName();
		return isBasicType(type);
		
	}
	
	/**
	 * 判断字段类型是否为基本数据类型
	 * 
	 * @param type 字段类型名称
	 * 
	 * @return 是否基本类型
	 */
	public static boolean isBasicType(String type){
		for(String basicType : basicTypeArray){
			if(basicType.equals(type)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 根据类型进行值转换
	 * 
	 * @param type 字段类型
	 * @param value 需要转换值
	 * 
	 * @return 转换后的值
	 */
	public static Object converValue(String type, String value){
		if(type.equals("java.lang.String")){
			return value;
		} else if(type.equals("int") || type.equals("java.lang.Integer")){
			return (int)Integer.parseInt(value);
		} else if(type.equals("boolean") || type.equals("java.lang.Boolean")){
			return (boolean)Boolean.parseBoolean(value);
		} else if(type.equals("float") || type.equals("java.lang.Double")){
			return (float)Float.parseFloat(value);
		} else if(type.equals("double") || type.equals("java.lang.Double")){
			return (double)Double.parseDouble(value);
		} else if(type.equals("short") || type.equals("java.lang.Short")){
			return (short)Short.parseShort(value);
		} else if(type.equals("char") || type.equals("java.lang.Character")){
			if(value.length()>0){
				return value.charAt(0);
			}else{
				return null;
			}
		} else if(type.equals("long") || type.equals("java.lang.Long")){
			return (long)Long.parseLong(value);
		} else if(type.equals("byte") || type.equals("java.lang.Byte")){
			return (byte)Byte.parseByte(value);
		} else if (type.equals("java.util.Date")){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (value.contains("/")){
				String items[] = value.split("/");
				dateFormat = new SimpleDateFormat(items[0]);
				value = items[1];
			}
			try {
				return dateFormat.parseObject(value);
			} catch (ParseException e) {
				log.error(String.format("Parse Date Exception: exception=%s", e.getMessage()));
				return null;
			}
		} else if (type.equals("java.math.BigDecimal")){
			return new BigDecimal(value);
		} else if (type.equals("java.math.BigInteger")){
			return new BigInteger(value);
		} else{
			return null;
		}
	}
	
	/**
	 * 根据类型描述获取对应的Class
	 * 
	 * @param type 类型描述
	 * 
	 * @return 对应的Class
	 * 
	 * @throws ClassNotFoundException 无法找到对应的类
	 */
	@SuppressWarnings("rawtypes")
	public static Class getTypeClass(String type) throws ClassNotFoundException{
		Class clazz = null;
		if(type.equals("int")){
			clazz = int.class;
		} else if(type.equals("boolean")){
			clazz = boolean.class;
		} else if(type.equals("float")){
			clazz = float.class;
		} else if(type.equals("double")){
			clazz = double.class;
		} else if(type.equals("short")){
			clazz = short.class;
		} else if(type.equals("char")){
			clazz = char.class;
		} else if(type.equals("long")){
			clazz = long.class;
		} else if(type.equals("byte")){
			clazz = byte.class;
		} else {
			clazz = Class.forName(type);
		}
		return clazz;
	}
}
