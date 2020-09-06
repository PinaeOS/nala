package org.pinae.nala.xb;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

import org.pinae.nala.xb.exception.MarshalException;
import org.pinae.nala.xb.exception.NoSuchPathException;
import org.pinae.nala.xb.exception.UnmarshalException;
import org.pinae.nala.xb.marshal.Marshaller;
import org.pinae.nala.xb.marshal.XmlMarshaller;
import org.pinae.nala.xb.unmarshal.Unmarshaller;
import org.pinae.nala.xb.unmarshal.XmlUnmarshaller;
import org.pinae.nala.xb.util.ResourceReader;

/**
 * XML文本编组/解组工具
 * 
 * @author Huiyugeng
 *
 */
public class Xml {

	/**
	 * 将XML字符串绑定为Map对象
	 * @param <K> Map对象键类型
	 * @param <V> Map对象值类型
	 * 
	 * @param xml XML字符串
	 * @param encoding XML字符串编码, 例如UTF-8, GBK
	 * 
	 * @return Map对象
	 * 
	 * @throws UnmarshalException 解组异常
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> toMap(String xml, String encoding) throws UnmarshalException {
		Map<K, V> map = (Map<K, V>)toObject(xml, encoding, Map.class);
		return map;
	}
	
	
	/**
	 * 将XML文件绑定为对象
	 * @param <T> 需要绑定对象的类型
	 * 
	 * @param xml XML字符串
	 * @param encoding XML文件编码, 例如UTF-8, GBK
	 * @param cls 绑定目标类
	 * 
	 * @return 绑定后的对象
	 * 
	 * @throws UnmarshalException 解组异常
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toObject(String xml, String encoding, Class<T> cls) throws UnmarshalException {
		
		if (xml == null || xml.trim().equals("")) {
			throw new UnmarshalException("XML String is Empty");
		}
		
		Object object = null;
		
		Unmarshaller bind = null;
		
		try {
			bind = new XmlUnmarshaller(new ByteArrayInputStream(xml.getBytes(encoding)));
		} catch (UnsupportedEncodingException e) {
			throw new UnmarshalException(e);
		}
		
		if (bind != null) {
			bind.setRootClass(cls);
			object = bind.unmarshal();
		}
		
		return (T)object;
	}
	
	/**
	 * 将对象生成XML文本
	 * 
	 * @param object 需要生成XML的对象
	 * @param encoding XML文本编码, 例如UTF-8, GBK
	 * @param nodeMode 是否采用节点模式, 如果采用节点模式将不生成XML属性
	 * 
	 * @return XML文本
	 * 
	 * @throws MarshalException 编组异常
	 */
	public static String toXML(Object object, String encoding, boolean nodeMode) throws MarshalException {

		Properties properties = new Properties();
		properties.put("node", Boolean.toString(nodeMode));
		properties.put("lowcase", "true");
		properties.put("pretty", "true");
		properties.put("cdata", "true");
		properties.put("indent", "\t");
		properties.put("endofline", "\n");
		properties.put("document-start", String.format("<?xml version='1.0' encoding='%s'?>", encoding));
			
		String xml = toXML(object, encoding, properties);
		return xml;
		
	}
	
	/**
	 * <p>将对象生成XML文本</p>
	 * 
	 * 配置属性主要包括:
	 * <ul>
	 * <li>node: 是否使用节点模式 (true:false)  </li>
	 * <li>lowcase: 是否使用全小写 (true:false) </li>
	 * <li>pretty: 是否格式化输出 (true:false) </li>
	 * <li>cdata 是否支持CDATA节点 (true:false) </li>
	 * <li>indent 文档缩进 (默认 \t) </li>
	 * <li>endofline 结尾换行符 (默认 \n) </li>
	 * <li>document-start 插入文档开始部分 </li>
	 * <li>document-end 插入文档结束部分 </li>
	 * </ul>
	 * 
	 * @param object 需要生成XML的对象
	 * @param encoding XML文本编码, 例如UTF-8, GBK
	 * @param properties 配置属性 

	 * @return XML文本
	 * 
	 * @throws MarshalException 编组异常
	 * 
	 */
	public static String toXML(Object object, String encoding, Properties properties) throws MarshalException {
		Marshaller marshaller = new XmlMarshaller(object);
		
		marshaller.enableLowCase(properties.contains("lowcase") ? Boolean.parseBoolean(properties.get("lowcase").toString()) : true);
		marshaller.enablePrettyPrint(properties.contains("pretty") ? Boolean.parseBoolean(properties.get("pretty").toString()) : true);
		marshaller.enableCDATA(properties.contains("cdata") ? Boolean.parseBoolean(properties.get("cdata").toString()) : true);
		marshaller.enableNodeMode(properties.contains("node") ? Boolean.parseBoolean(properties.get("node").toString()) : true);
		
		marshaller.setIndent(properties.contains("indent") ? (String)properties.get("indent") : "\t");
		marshaller.setEndOfLine(properties.contains("endofline") ? (String)properties.get("endofline") : "\n");
		
		marshaller.setDocumentStart(properties.contains("document-start") ? 
				(String)properties.get("document-start") : 
				String.format("<?xml version='1.0' encoding='%s'?>", encoding));
		marshaller.setDocumentEnd(properties.contains("document-end") ? 
				(String)properties.get("document-end") : "");
		
		return marshaller.marshal().toString();
	}
	
	/**
	 * 将XML文件绑定为<code>Map</code>对象
	 * @param <K> Map对象键类型
	 * @param <V> Map对象值类型
	 * 
	 * @param file XML文件
	 * @param encoding XML文件编码, 例如UTF-8, GBK
	 * 
	 * @return Map对象
	 * 
	 * @throws NoSuchPathException 路径异常
	 * @throws UnmarshalException 解组异常
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> toMap(File file, String encoding) throws UnmarshalException, NoSuchPathException {
		return (Map<K, V>) toObject(file, encoding, Map.class);
	}
	
	/**
	 * 将XML文件绑定为对象
	 * @param <T> 需要绑定对象的类型
	 * 
	 * @param file XML文件
	 * @param encoding XML文件编码, 例如UTF-8, GBK
	 * @param cls 绑定目标类
	 * 
	 * @return 绑定后的对象
	 * 
	 * @throws NoSuchPathException 路径异常
	 * @throws UnmarshalException 解组异常
	 */
	@SuppressWarnings({ "unchecked" })
	public static <T> T toObject(File file, String encoding, Class<T> cls) throws UnmarshalException, NoSuchPathException {
		
		if (file == null || !file.exists()) {
			throw new NoSuchPathException("Could not find file");
		} 
		if (file.isDirectory()) {
			throw new NoSuchPathException(String.format("%s is Directory", file.getAbsolutePath()));
		}
		
		Object object = null;
		
		Unmarshaller bind = new XmlUnmarshaller(new ResourceReader().getFileStream(file, encoding));
		bind.setRootClass(cls);
		object = bind.unmarshal();


		return (T)object;
	}
}
