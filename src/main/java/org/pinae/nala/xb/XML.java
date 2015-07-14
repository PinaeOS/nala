package org.pinae.nala.xb;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import org.pinae.nala.xb.marshal.Marshaller;
import org.pinae.nala.xb.marshal.XMLMarshaller;
import org.pinae.nala.xb.unmarshal.Unmarshaller;
import org.pinae.nala.xb.unmarshal.XMLUnmarshaller;
import org.pinae.nala.xb.util.ResourceReader;

/**
 * XML工具集
 * 
 * @author Huiyugeng
 *
 */
public class XML {
	
	/**
	 * 将XML文件绑定为Map对象
	 * 
	 * @param filename XML文件路径
	 * @param encoding XML文件编码
	 * 
	 * @return Map对象
	 */
	public static Map<?, ?> parseFileToMap(String filename, String encoding) {
		return (Map<?, ?>)parseFile(filename, encoding, Map.class);
	}

	/**
	 * 将XML字符串绑定为Map对象
	 * 
	 * @param xml XML字符串
	 * @param encoding XML字符串编码
	 * 
	 * @return Map对象
	 */
	public static Map<?, ?> parseXMLToMap(String xml, String encoding) {
		Map<?, ?> map = new HashMap();
		try {
			Unmarshaller bind = new XMLUnmarshaller(new ByteArrayInputStream(xml.getBytes(encoding)));
			bind.setRootClass(Map.class);
			map = (Map<?, ?>)bind.unmarshal();
		} catch (Exception e) {
			
		}
		
		return map;
	}
	
	/**
	 * 将XML文件绑定为对象
	 * 
	 * @param filename XML文件路径
	 * @param encoding XML文件编码
	 * @param cls 对象类型
	 * 
	 * @return 绑定后的对象
	 */
	public static Object parseFile(String filename, String encoding, Class cls) {
		Object object = null;
		try {
			Unmarshaller bind = new XMLUnmarshaller(new ResourceReader().getFileStream(filename, encoding));
			bind.setRootClass(cls);
			object = bind.unmarshal();
		} catch (Exception e) {

		}

		return object;
	}
	
	/**
	 * 将XML文件绑定为对象
	 * 
	 * @param xml XML字符串
	 * @param encoding XML字符串编码
	 * @param cls 对象类型
	 * 
	 * @return 绑定后的对象
	 */
	public static Object parseXML(String xml, String encoding, Class cls) {
		Object object = null;
		try {
			Unmarshaller bind = new XMLUnmarshaller(new ByteArrayInputStream(xml.getBytes(encoding)));
			bind.setRootClass(cls);
			object = bind.unmarshal();
		} catch (Exception e) {
			
		}
		
		return object;
	}
	
	/**
	 * 将对象生成XML文本
	 * 
	 * @param object 对象
	 * @param encoding XML编码
	 * @param nodeMode 是否采用节点模式, 如果采用节点模式将不生成XML属性
	 * 
	 * @return XML文本
	 */
	public static String toXMLString(Object object, String encoding, boolean nodeMode) {
		try {
			Marshaller marshaller = new XMLMarshaller(object);
			
			marshaller.setDocumentStart(String.format("<?xml version='1.0' encoding='%s'?>", encoding));
			marshaller.enableLowCase(true);
			marshaller.enablePrettyPrint(true);
			marshaller.enableCDATA(true);
			marshaller.enableNodeMode(nodeMode);
			
			return marshaller.marshal().toString();
		} catch (Exception e) {
			return null;
		}
	}
}
