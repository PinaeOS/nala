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
 * 将XML解析为NodeConfig
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
		NodeConfig nodeConfig = new NodeConfig();

		nodeConfig.setName(XmlElementUtils.mapXMLToObject(element.getName()));
		nodeConfig.setNamespace(element.getNamespacePrefix(), element.getNamespaceURI());

		List childrenNodes = element.getChildren();
		if (childrenNodes != null && childrenNodes.size() > 0) {
			List<NodeConfig> subNodeConfigList = new ArrayList<NodeConfig>();
			
			for (Object childrenNode : childrenNodes) {
				if (childrenNode instanceof Element) {
					NodeConfig subNodeConfig = parserNode((Element)childrenNode);
					subNodeConfigList.add(subNodeConfig);
				}
			}
			
			nodeConfig.setChildrenNodes(subNodeConfigList);
		}

		String value = element.getText();
		if (value != null && !value.trim().equals("")) {
			nodeConfig.setValue(value.trim());
		}
		nodeConfig.setAttribute(parserAttribute(element));
		
		return nodeConfig;
	}

	/*
	 * 解析XML属性
	 */
	@SuppressWarnings("rawtypes")
	private List<AttributeConfig> parserAttribute(Element element) {
		
		List attributes = element.getAttributes();
		List<AttributeConfig> attributeConfigList = null;
		
		if (attributes != null && attributes.size() > 0) {
			attributeConfigList = new ArrayList<AttributeConfig>();
			
			for (Iterator iter = attributes.iterator(); iter.hasNext();) {
				AttributeConfig attributeConfig = new AttributeConfig();
				Attribute attribute = (Attribute) iter.next();
				attributeConfig.setName(XmlElementUtils.mapXMLToObject(attribute.getName()));
				attributeConfig.setValue(attribute.getValue());
				attributeConfigList.add(attributeConfig);
			}
		}
		
		return attributeConfigList;
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
	 * @param xpath XPath路径, 例如root/parent/child
	 * 
	 * @return 将输入XML解析的NodeConfig (XPath路径支持)
	 * 
	 * @throws UnmarshalException 解组异常
	 */
	@SuppressWarnings("rawtypes")
	public List<NodeConfig> parserByXpath(InputStreamReader xml, String xpath) throws UnmarshalException {
		
		List<NodeConfig> nodeConfigList = new ArrayList<NodeConfig>();
		SAXBuilder builder = new SAXBuilder(false);
		Document doc = null;
		
		try {
			doc = builder.build(xml);
			Element root = doc.getRootElement();
			
			if (xpath != null && !xpath.equals("")) {
				List selectNodes = XPath.selectNodes(root, xpath);
				for (Iterator iterNode = selectNodes.iterator(); iterNode.hasNext();) {
					NodeConfig nodeConfig = parserNode((Element) iterNode.next());
					nodeConfigList.add(nodeConfig);
				}
			}
			
			return nodeConfigList;
		} catch (JDOMException e) {
			throw new UnmarshalException(e);
		} catch (IOException e) {
			throw new UnmarshalException(e);
		}

	}
}
