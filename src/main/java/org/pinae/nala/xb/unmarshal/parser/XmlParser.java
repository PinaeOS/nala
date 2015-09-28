package org.pinae.nala.xb.unmarshal.parser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;
import org.pinae.nala.xb.exception.UnmarshalException;
import org.pinae.nala.xb.node.AttributeConfig;
import org.pinae.nala.xb.node.NodeConfig;
import org.pinae.nala.xb.xml.XmlElementUtils;

/**
 * 将XML解析为结构体
 * 
 * @author Huiyugeng
 *
 */
public class XmlParser {
	protected static final Logger log = Logger.getLogger("XMLParser");

	/*
	 * 解析XML节点
	 */
	@SuppressWarnings("rawtypes")
	private NodeConfig parserNode(Element element) {
		NodeConfig config = new NodeConfig();
		config.setName(XmlElementUtils.mapXMLToObject(element.getName()));
		config.setNamespace(element.getNamespacePrefix(), element.getNamespaceURI());
		List childrenNode = element.getChildren();
		if (childrenNode != null && childrenNode.size() > 0) {
			List<NodeConfig> nodeItems = new ArrayList<NodeConfig>();
			for (Iterator iter = childrenNode.iterator(); iter.hasNext();) {
				nodeItems.add(parserNode((Element) iter.next()));
			}
			config.setChildrenNodes(nodeItems);
		}

		String value = element.getText();
		if (value != null && !value.trim().equals("")) {
			config.setValue(value.trim());
		}
		config.setAttribute(parserAttribute(element));
		return config;
	}

	/*
	 * 解析XML属性
	 */
	@SuppressWarnings("rawtypes")
	private List<AttributeConfig> parserAttribute(Element element) {
		List attributes = element.getAttributes();
		List<AttributeConfig> attributeItems = null;
		if (attributes != null && attributes.size() > 0) {
			attributeItems = new ArrayList<AttributeConfig>();
			for (Iterator iter = attributes.iterator(); iter.hasNext();) {
				AttributeConfig attributeConfig = new AttributeConfig();
				Attribute attribute = (Attribute) iter.next();
				attributeConfig.setName(XmlElementUtils.mapXMLToObject(attribute.getName()));
				attributeConfig.setValue(attribute.getValue());
				attributeItems.add(attributeConfig);
			}
		}
		return attributeItems;
	}

	/**
	 * 将XML流进行解析
	 * 
	 * @param xml 需要解析的XML
	 * 
	 * @return 将输入XML流解析的NodeConfig
	 * 
	 * @throws UnmarshalException 解组异常
	 */
	public NodeConfig parser(InputStreamReader xml) throws UnmarshalException {
		NodeConfig config = new NodeConfig();
		SAXBuilder builder = new SAXBuilder(false);
		Document doc = null;
		try {
			doc = builder.build(xml);
			config = parserNode(doc.getRootElement());

			xml.close();
			return config;
		} catch (JDOMException e) {
			throw new UnmarshalException(e);
		} catch (IOException e) {
			throw new UnmarshalException(e);
		}
	}

	/**
	 * 根据XPath对XML流进行解析
	 * 
	 * @param xml 需要解析的XML
	 * @param xpath XPath路径
	 * 
	 * @return 将输入XML解析的NodeConfig (XPath路径支持)
	 * 
	 * @throws UnmarshalException 解组异常
	 */
	@SuppressWarnings("rawtypes")
	public List<NodeConfig> parserByXpath(InputStreamReader xml, String xpath) throws UnmarshalException {
		List<NodeConfig> lstNodeConfig = new ArrayList<NodeConfig>();
		SAXBuilder builder = new SAXBuilder(false);
		Document doc = null;
		try {
			doc = builder.build(xml);
			Element root = doc.getRootElement();
			if (xpath != null && !xpath.equals("")) {
				List node = XPath.selectNodes(root, xpath);
				for (Iterator iterNode = node.iterator(); iterNode.hasNext();) {
					lstNodeConfig.add(parserNode((Element) iterNode.next()));
				}
			}
			return lstNodeConfig;
		} catch (JDOMException e) {
			throw new UnmarshalException(e);
		} catch (IOException e) {
			throw new UnmarshalException(e);
		}

	}
}
