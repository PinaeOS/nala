package org.pinae.nala.xb;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.pinae.nala.xb.marshal.Marshaller;
import org.pinae.nala.xb.marshal.XmlMarshaller;
import org.pinae.nala.xb.unmarshal.Unmarshaller;
import org.pinae.nala.xb.unmarshal.XmlUnmarshaller;

/**
 * XML文本编组/解组工具
 * 
 * @author Huiyugeng
 *
 */
public class Xml {
	


	/**
	 * 将XML字符串绑定为Map对象
	 * 
	 * @param xml XML字符串
	 * @param encoding XML字符串编码, 例如UTF-8, GBK
	 * 
	 * @return Map对象
	 */
	public static Map<?, ?> toMap(String xml, String encoding) {
		Map<?, ?> map = new HashMap();
		try {
			Unmarshaller bind = new XmlUnmarshaller(new ByteArrayInputStream(xml.getBytes(encoding)));
			bind.setRootClass(Map.class);
			map = (Map<?, ?>)bind.unmarshal();
		} catch (Exception e) {
			
		}
		
		return map;
	}
	
	
	/**
	 * 将XML文件绑定为对象
	 * 
	 * @param xml XML字符串
	 * @param encoding XML文件编码, 例如UTF-8, GBK
	 * @param cls 绑定目标类
	 * 
	 * @return 绑定后的对象
	 */
	public static Object toObject(String xml, String encoding, Class<Map> cls) {
		Object object = null;
		try {
			Unmarshaller bind = new XmlUnmarshaller(new ByteArrayInputStream(xml.getBytes(encoding)));
			bind.setRootClass(cls);
			object = bind.unmarshal();
		} catch (Exception e) {
			
		}
		
		return object;
	}
	
	/**
	 * 将对象生成XML文本
	 * 
	 * @param object 需要生成XML的对象
	 * @param encoding XML文本编码, 例如UTF-8, GBK
	 * @param nodeMode 是否采用节点模式, 如果采用节点模式将不生成XML属性
	 * 
	 * @return XML文本
	 */
	public static String toXML(Object object, String encoding, boolean nodeMode) {
		try {
			Marshaller marshaller = new XmlMarshaller(object);
			
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
	
	/**
	 * 将对象生成XML文本
	 * 
	 * <p>
	 * 	配置属性主要包括:
	 * 	<ul>
	 * 	<li>node: 是否使用节点模式 (true:false)  </li>
	 *  <li>lowcase: 是否使用全小写 (true:false) </li>
	 *  <li>pretty: 是否格式化输出 (true:false) </li>
	 *  <li>cdata 是否支持CDATA节点 (true:false) </li>
	 *  <li>indent 文档缩进 (默认 \t) </li>
	 *  <li>endofline 结尾换行符 (默认 \n) </li>
	 *  <li>documentstart 插入文档开始部分 </li>
	 *  <li>documentend 插入文档结束部分 </li>
	 * 	</ul>
	 * </p>
	 * 
	 * @param object 需要生成XML的对象
	 * @param encoding XML文本编码, 例如UTF-8, GBK
	 * @param properties 配置属性 

	 * @return XML文本
	 */
	public static String toXML(Object object, String encoding, Properties properties) {
		try {
			Marshaller marshaller = new XmlMarshaller(object);
			
			marshaller.setDocumentStart(String.format("<?xml version='1.0' encoding='%s'?>", encoding));
			
			marshaller.enableLowCase(properties.contains("lowcase") ? Boolean.parseBoolean(properties.get("lowcase").toString()) : true);
			marshaller.enablePrettyPrint(properties.contains("pretty") ? Boolean.parseBoolean(properties.get("pretty").toString()) : true);
			marshaller.enableCDATA(properties.contains("cdata") ? Boolean.parseBoolean(properties.get("cdata").toString()) : true);
			marshaller.enableNodeMode(properties.contains("node") ? Boolean.parseBoolean(properties.get("node").toString()) : true);
			
			return marshaller.marshal().toString();
		} catch (Exception e) {
			return null;
		}
	}
}
