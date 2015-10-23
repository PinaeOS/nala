package org.pinae.nala.xb.unmarshal;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.pinae.nala.xb.exception.UnmarshalException;
import org.pinae.nala.xb.node.NodeConfig;
import org.pinae.nala.xb.unmarshal.parser.XmlParser;

/**
 * 通过指定的XPath绑定指定的对象, 支持标准XPath路径描述, 
 * 例如: <code>/root/node[1]/subnode</code>
 * 
 * @author Huiyugeng
 *
 */
public class XPathUnmarshaller extends AbstractUnmarshaller {

	private XmlParser xmlParser = new XmlParser();
	private List<?> nodeConfigList = null;

	/**
	 * 构造函数
	 * 
	 * @param xml 输入的XML流
	 * @param xpath 指定的XPath路径
	 * 
	 * @throws UnmarshalException 解组的异常
	 */
	public XPathUnmarshaller(InputStreamReader xml, String xpath) throws UnmarshalException {

		if (xpath == null) {
			throw new UnmarshalException("Please use constructor XPathUnmarshaller(InputStreamReader xml, String xpath) to set XPath");
		}

		this.nodeConfigList = xmlParser.parserByXpath(xml, xpath);
	}

	public XPathUnmarshaller(InputStream xml, String xpath) throws UnmarshalException {
		if (xpath == null) {
			throw new UnmarshalException("Please use constructor XPathUnmarshaller(InputStream xml, String xpath) to set XPath");
		}

		this.nodeConfigList = xmlParser.parserByXpath(new InputStreamReader(xml), xpath);
	}

	@SuppressWarnings("rawtypes")
	public List unmarshal() throws UnmarshalException {
		List<Object> lstObject = new ArrayList<Object>();
		if (nodeConfigList != null) {
			for (Iterator iterNodeConfig = nodeConfigList.iterator(); iterNodeConfig.hasNext();) {
				NodeConfig nodeConfig = (NodeConfig) iterNodeConfig.next();
				if (nodeConfig != null) {
					Object targetObject = super.creteObject(nodeConfig);
					if (targetObject != null) {
						lstObject.add(targetObject);
					}
				} else {
					throw new UnmarshalException("NodeConfig is null and Parse XML Error");
				}
			}
			return lstObject;
		} else {
			throw new UnmarshalException("NodeConfig is null and Parse XML Error");
		}

	}

}
