package org.pinae.nala.xb.marshal;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.pinae.nala.xb.annotation.Root;
import org.pinae.nala.xb.exception.MarshalException;
import org.pinae.nala.xb.marshal.parser.ObjectParser;
import org.pinae.nala.xb.node.AttributeConfig;
import org.pinae.nala.xb.node.Namespace;
import org.pinae.nala.xb.node.NodeConfig;
import org.pinae.nala.xb.util.Constant;
import org.pinae.nala.xb.xml.CdataText;
import org.pinae.nala.xb.xml.XmlElementUtils;
import org.pinae.nala.xb.xml.XmlText;

/**
 * <code>XMLMarshaller</code>提供实现<code>Marshaller</code>接口中的方法 主要用于将中间格式
 * <code>NodeConfig</code>转换为XML文本
 * 
 * @author Huiyugeng
 * 
 */
public class XmlMarshaller extends AbstractMarshal implements Marshaller {
	/* 需要解析的XML对象 */
	private Object targetObj;

	private NodeConfig config;
	
	private ObjectParser parser = new ObjectParser();

	/**
	 * 构造函数
	 * 
	 * @param targetObj 需要解析的Java对象
	 * 
	 */
	public XmlMarshaller(Object targetObj) {
		this.targetObj = targetObj;
	}

	/**
	 * 构造函数
	 * 
	 * @param config 节点配置
	 */
	protected XmlMarshaller(NodeConfig config) {
		this.config = config;
	}

	/**
	 * 按照Java对象的内容输出XML格式的描述
	 * 
	 * @return 输出的XML数据
	 */
	public StringBuffer marshal() throws MarshalException {
		
		if (this.config == null && this.targetObj != null) {
			
			// 初始化解析器
			this.parser.init();
			
			Class<?> rootClass = targetObj.getClass();
			String tag = nodeTag;

			if (StringUtils.isEmpty(tag)) {
				if (targetObj instanceof Map) {
					tag = (String) ((Map<?, ?>) targetObj).get(Constant.NODE_TAG);
					tag = tag == null ? Constant.ROOT_TAG : tag;
				} else if (targetObj instanceof List) {
					tag = "list";
				} else if (rootClass.isAnnotationPresent(Root.class)) {
					Root root = (Root) rootClass.getAnnotation(Root.class);
					tag = root.name();
				}
			}

			if (StringUtils.isEmpty(tag)) {
				tag = Constant.ROOT_TAG;
			}
			this.config = this.parser.parse(tag, targetObj);
		}
		
		if (this.config == null) {
			throw new MarshalException("Object is null");
		}

		if (domMode) {
			return marshalByJDom();
		} else {
			return marshalByString();
		}
	}


	private StringBuffer marshalByJDom() {

		Format format = Format.getCompactFormat();
		if (prettyPrint) {
			format.setIndent(indent);
		}
		format.setEncoding(encoding);

		StringBuffer xmlBuffer = new StringBuffer();

		Document doc = new Document(buildXMLNodeByElement(null, this.config, 0));
		xmlBuffer.append(new XMLOutputter(format).outputString(doc));

		if (documentEnd != null && !documentEnd.equals("")) {
			xmlBuffer.append(documentEnd + endOfLine);
		}

		return xmlBuffer;
	}

	private Element buildXMLNodeByElement(Element parent, NodeConfig config, int deep) {
		String tagName = config.getName();
		Element element = new Element(tagName);

		List<Namespace> namespaces = config.getNamespace();
		for (Namespace namespace : namespaces) {
			String prefix = namespace.getPrefix();
			String uri = namespace.getUri();
			if (StringUtils.isNotBlank(prefix) && StringUtils.isNotBlank(uri)) {
				element.addNamespaceDeclaration(org.jdom.Namespace.getNamespace(prefix, uri));
			}
		}

		List<AttributeConfig> attributeList = config.getAttribute();
		if (attributeList != null) {
			for (AttributeConfig attribute : attributeList) {
				if (nodeMode) {
					Element childElement = new Element(attribute.getName());
					childElement.setText(attribute.getValue());
					element.addContent(childElement);
				} else {
					element.setAttribute(attribute.getName(), attribute.getValue());
				}
			}
		}
		
		/* 是否处理本层节点, 当本层节点与下一次层节点名称相同时, 则不对本层节点进行处理 */
		boolean layerFlag = true;
		
		List<NodeConfig> childrenNodeList = config.getChildrenNodes();
		if (childrenNodeList != null && childrenNodeList.size() > 0) {
			for (NodeConfig childNode : childrenNodeList) {
				if (parent != null && childNode.getName().equals(tagName)) {
					layerFlag = false;
					Element childElement = buildXMLNodeByElement(parent, childNode, deep);
					parent.addContent(childElement);
				} else {
					Element childElement = buildXMLNodeByElement(element, childNode, deep + 1);
					if (childElement != null) {
						element.addContent(childElement);
					}
				}
			}
		} else {
			Object value = config.getValue();
			if (value != null) {
				if (value instanceof CdataText) {
					element.addContent(new CDATA(((CdataText) value).getData()));
				} else if (value instanceof XmlText) {
					element.setText(((XmlText) value).getXml());
				} else {
					element.setText(value.toString());
				}
			}
		}
		return layerFlag ? element : null;
	}

	private StringBuffer marshalByString() {
		StringBuffer xmlBuffer = new StringBuffer();

		if (documentStart != null && !documentStart.equals("")) {
			xmlBuffer.append(documentStart + endOfLine);
		} else {
			xmlBuffer.append(String.format("<?xml version='%s' encoding='%s'?>", version, encoding));
		}

		Map<String, String> namespaceMap = this.parser.getNamespaces();
		super.namespaces = "";
		for (String prefix : namespaceMap.keySet()) {
			if (prefix != null) {
				String uri = namespaceMap.get(prefix);
				if (prefix.equals("")) {
					super.namespaces += String.format(" xmlns=\"%s\"", uri);
				} else {
					super.namespaces += String.format(" xmlns:%s=\"%s\"", prefix, uri);
				}
			}
		}
		xmlBuffer.append(buildXMLNodeByString(this.config, 0));

		if (documentEnd != null && !documentEnd.equals("")) {
			xmlBuffer.append(documentEnd + endOfLine);
		}

		return xmlBuffer;
	}

	/*
	 * 根据结构体, 构建XML内容
	 */
	private StringBuffer buildXMLNodeByString(NodeConfig config, int deep) {
		StringBuffer xmlBuffer = new StringBuffer();
		String tagName = config.getName();

		if (isLowCase == true) {
			tagName = StringUtils.lowerCase(config.getName());
		}

		List<Namespace> namespaces = config.getNamespace();
		if (namespaces != null && namespaces.size() > 0) {
			Namespace namespace = namespaces.get(0);
			if (!namespace.getPrefix().equals("")) {
				tagName = namespace.getPrefix() + ":" + tagName;
			}
		}

		Object tagValue = config.getValue();
		if (tagValue == null) {
			tagValue = "";
		}
		if (tagValue instanceof CdataText) {
			tagValue = String.format("<![CDATA[%s]]>", ((CdataText) tagValue).getData());
		} else if (tagValue instanceof XmlText) {
			tagValue = ((XmlText) tagValue).getXml();
		} else if (cdata == true) {
			if (XmlElementUtils.containXMLEscapeChar(tagValue.toString())) {
				tagValue = String.format("<![CDATA[%s]]>", tagValue.toString());
			}
		}
		
		StringBuffer attributeBuffer = new StringBuffer();
		StringBuffer nodeBuffer = new StringBuffer();

		List<AttributeConfig> attributeList = config.getAttribute();
		if (attributeList != null && attributeList.size() > 0) {
			for (AttributeConfig attribute : attributeList) {
				if (attribute.getValue() != null) {
					if (nodeMode == false) {
						if (isLowCase == true) {
							attributeBuffer.append(String.format("%s=\"%s\" ", StringUtils.lowerCase(attribute.getName()), attribute.getValue()));
						} else {
							attributeBuffer.append(String.format("%s=\"%s\" ", attribute.getName(), attribute.getValue()));
						}
					} else {
						NodeConfig node = new NodeConfig();
						node.setName(attribute.getName());
						node.setValue(attribute.getValue());
						nodeBuffer.append(buildXMLNodeByString(node, deep + 1));
					}
				}
			}
		}

		boolean isDeep = true;

		List<NodeConfig> childrenNodeList = config.getChildrenNodes();
		if (childrenNodeList != null && childrenNodeList.size() > 0) {
			for (NodeConfig childNode : childrenNodeList) {
				StringBuffer nodeXML = null;
				if (childNode.getName().equals(tagName)) {
					nodeXML = buildXMLNodeByString(childNode, deep);
					isDeep = false;
				} else {
					nodeXML = buildXMLNodeByString(childNode, deep + 1);
				}
				nodeBuffer.append(nodeXML);
			}
		}

		if (isDeep == false) {
			return nodeBuffer;
		}

		String tab = "";
		if (prettyPrint) {
			for (int i = 0; i < deep; i++) {
				tab += indent;
			}
		}
		if (!tagName.equals(Constant.XML_TAG)) {
			if (deep == 0) {
				xmlBuffer.append(tab + "<" + tagName + super.namespaces);
			} else {
				xmlBuffer.append(tab + "<" + tagName);
			}
		}

		if (attributeBuffer != null && attributeBuffer.length() > 0) {
			xmlBuffer.append(" " + attributeBuffer.toString().trim());
		}

		if (!tagName.equals(Constant.XML_TAG)) {
			if (nodeBuffer.length() > 0) {
				xmlBuffer.append(">");
			} else {
				if (tagValue != null) {
					xmlBuffer.append(">");
				} else {
					xmlBuffer.append("/>" + endOfLine);
				}
			}
		}

		if (nodeBuffer.length() > 0) {
			xmlBuffer.append(endOfLine + nodeBuffer);
			xmlBuffer.append(tab + "</" + tagName + ">" + endOfLine);
		} else {
			if (tagValue != null) {
				if (!tagName.equals(Constant.XML_TAG)) {
					xmlBuffer.append(tagValue.toString());
					xmlBuffer.append("</" + tagName + ">" + endOfLine);
				} else {
					xmlBuffer.append(tab + tagValue + endOfLine);
				}
			}
		}

		return xmlBuffer;
	}

}
