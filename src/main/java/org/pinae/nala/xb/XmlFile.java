package org.pinae.nala.xb;

import java.util.Map;

import org.apache.log4j.Logger;
import org.pinae.nala.xb.unmarshal.Unmarshaller;
import org.pinae.nala.xb.unmarshal.XmlUnmarshaller;
import org.pinae.nala.xb.util.ResourceReader;

/**
 * XML文件编组/解组工具
 * 
 * @author Huiyugeng
 *
 */
public class XmlFile {
	
	private static Logger log = Logger.getLogger(XmlFile.class);
	
	/**
	 * 将XML文件绑定为<code>Map</code>对象
	 * 
	 * @param filename XML文件路径
	 * @param encoding XML文件编码, 例如UTF-8, GBK
	 * 
	 * @return Map对象
	 */
	public static Map<?, ?> toMap(String filename, String encoding) {
		return (Map<?, ?>) toObject(filename, encoding, Map.class);
	}
	
	/**
	 * 将XML文件绑定为对象
	 * 
	 * @param filename XML文件路径
	 * @param encoding XML文件编码, 例如UTF-8, GBK
	 * @param cls 绑定目标类
	 * 
	 * @return 绑定后的对象
	 */
	public static Object toObject(String filename, String encoding, Class<Map> cls) {
		Object object = null;
		try {
			Unmarshaller bind = new XmlUnmarshaller(new ResourceReader().getFileStream(filename, encoding));
			bind.setRootClass(cls);
			object = bind.unmarshal();
		} catch (Exception e) {
			log.error(String.format("toObject Exception : exception=%s", e.getMessage()));
		}

		return object;
	}
}
