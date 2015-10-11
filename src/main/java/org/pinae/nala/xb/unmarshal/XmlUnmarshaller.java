package org.pinae.nala.xb.unmarshal;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.pinae.nala.xb.exception.UnmarshalException;
import org.pinae.nala.xb.node.NodeConfig;
import org.pinae.nala.xb.unmarshal.parser.XmlParser;

/**
 * 实现XML与对象间的绑定
 * 
 * @author Huiyugeng
 *
 */
public class XmlUnmarshaller extends AbstractUnmarshaller {
	
	private XmlParser xmlParser = new XmlParser();
	private NodeConfig config;

	/**
	 * 构造函数
	 * 
	 * @param xml 需要绑定的XML输入流
	 * 
	 * @throws UnmarshalException 解组异常
	 */
	public XmlUnmarshaller(InputStreamReader xml) throws UnmarshalException {
		if (xml != null) {
			config = xmlParser.parser(xml);
		} else {
			throw new UnmarshalException("Unmarshal Fail: Input XML is NULL");
		}
	}

	/**
	 * 构造函数
	 * 
	 * @param xml 需要绑定的XML输入流
	 * 
	 * @throws UnmarshalException 解组异常
	 */
	public XmlUnmarshaller(InputStream xml) throws UnmarshalException {
		if (xml != null) {
			config = xmlParser.parser(new InputStreamReader(xml));
		} else {
			throw new UnmarshalException("Unmarshal Fail: Input XML is NULL");
		}
	}

	public Object unmarshal() throws UnmarshalException {
		if (config != null) {
			return super.creteObject(config);
		} else {
			throw new UnmarshalException("Unmarshal Fail: Parse XML Error");
		}
	}

}
