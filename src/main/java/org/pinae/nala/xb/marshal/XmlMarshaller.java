package org.pinae.nala.xb.marshal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.pinae.nala.xb.annotation.Root;
import org.pinae.nala.xb.exception.MarshalException;
import org.pinae.nala.xb.marshal.parser.DefaultObjectParser;
import org.pinae.nala.xb.marshal.parser.ObjectParser;
import org.pinae.nala.xb.resource.AttributeConfig;
import org.pinae.nala.xb.resource.Namespace;
import org.pinae.nala.xb.resource.NodeConfig;
import org.pinae.nala.xb.xml.CdataObject;
import org.pinae.nala.xb.xml.XmlObject;
import org.pinae.nala.xb.xml.XmlElementUtils;

/**
 * XMLMarshaller提供实现Marshaller接口中的方法
 * 
 * @author Huiyugeng
 * 
 */
public class XmlMarshaller extends BasicMarshal implements Marshaller {
	private Object rootObject;

	private NodeConfig config;

	/**
	 * 构造函数
	 * 
	 * @param rootObject 需要解析的Java对象
	 * 
	 */
	public XmlMarshaller(Object rootObject) {
		this.rootObject = rootObject;
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
	 * 解析对象成为中间格式
	 * 
	 * @param rootObject 需要解析的对象
	 * 
	 * @return 解析的中间格式
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private NodeConfig parseObject(Object rootObject) throws MarshalException {
		Class rootClass = rootObject.getClass();

		String tag = nodeTag;

		if (StringUtils.isEmpty(tag)) {
			if (rootObject instanceof Map) {
				tag = (String) ((Map) rootObject).get("nodeTag");
				tag = tag == null ? "root" : tag;
			} else if (rootObject instanceof List) {
				tag = "list";
			} else if (rootClass.isAnnotationPresent(Root.class)) {
				Root root = (Root) rootClass.getAnnotation(Root.class);
				tag = root.name();
			}
		}

		if (StringUtils.isEmpty(tag)) {
			tag = "root";
		}
		return new ObjectParser().parse(tag, rootObject);
	}

	/**
	 * 按照Java对象的内容输出XML格式的描述
	 * 
	 * @return 输出的XML数据
	 */
	@SuppressWarnings("rawtypes")
	public StringBuffer marshal() throws MarshalException {
		if (this.config == null && this.rootObject != null) {
			this.config = parseObject(rootObject);
		}

		StringBuffer xmlBuffer = new StringBuffer();

		if (documentStart != null && !documentStart.equals(""))
			xmlBuffer.append(documentStart + "\n");

		Map mapNamespaces = DefaultObjectParser.getNamespaces();
		for (Iterator iterPrefix = mapNamespaces.keySet().iterator(); iterPrefix.hasNext();) {
			String prefix = (String) iterPrefix.next();
			if (prefix != null) {
				String uri = (String) mapNamespaces.get(prefix);
				if (prefix.equals("")) {
					namespaces += String.format(" xmlns=\"%s\"", uri);
				} else {
					namespaces += String.format(" xmlns:%s=\"%s\"", prefix, uri);
				}
			}
		}
		xmlBuffer.append(getNodeXML(this.config, 0));

		if (documentEnd != null && !documentEnd.equals(""))
			xmlBuffer.append(documentEnd + "\n");
		return xmlBuffer;
	}

	/*
	 * 根据结构体, 构建XML内容
	 */
	@SuppressWarnings("rawtypes")
	private StringBuffer getNodeXML(NodeConfig config, int deep) {
		StringBuffer tempBuffer = new StringBuffer();
		String tagName = config.getName();
		
		if (isLowCase == true) {
			tagName = StringUtils.uncapitalize(config.getName());
		}
		
		if (config.getNamespace() != null && config.getNamespace().size() > 0) {
			Namespace namespace = (Namespace) config.getNamespace().get(0);
			if (!namespace.getPrefix().equals("")) {
				tagName = namespace.getPrefix() + ":" + tagName;
			}
		}

		Object tagValue = config.getValue();
		if (tagValue == null){
			tagValue = "";
		}
		if (tagValue instanceof CdataObject) {
			tagValue = String.format("<![CDATA[%s]]>", ((CdataObject) tagValue).getData());
		} else if (tagValue instanceof XmlObject) {
			tagValue = ((XmlObject) tagValue).getXml();
		} else if (cdata == true){
			if (XmlElementUtils.containXMLEscapeChar(tagValue.toString())){
				tagValue = String.format("<![CDATA[%s]]>", tagValue.toString());
			}
		}
		StringBuffer attributeBuffer = new StringBuffer();
		StringBuffer nodeBuffer = new StringBuffer();

		if (config.getAttribute() != null && config.getAttribute().size() > 0) {
			for (Iterator iterAttribute = config.getAttribute().iterator(); iterAttribute.hasNext();) {
				AttributeConfig attribute = (AttributeConfig) iterAttribute.next();
				if (attribute.getValue() != null) {
					if (nodeMode == false) {
						if (isLowCase == true) {
							attributeBuffer.append(String.format("%s=\"%s\" ", StringUtils.uncapitalize(attribute.getName()), attribute.getValue()));
						} else {
							attributeBuffer.append(String.format("%s=\"%s\" ", attribute.getName(), attribute.getValue()));
						}
					} else {
						NodeConfig node = new NodeConfig();
						node.setName(attribute.getName());
						node.setValue(attribute.getValue());
						nodeBuffer.append(getNodeXML(node, deep + 1));
					}
				}
			}
		}
		
		boolean isDeep = true;
		
		if (config.getChildrenNodes() != null && config.getChildrenNodes().size() > 0) {
			for (Iterator iterNode = config.getChildrenNodes().iterator(); iterNode.hasNext();) {
				NodeConfig node = (NodeConfig) iterNode.next();
				
				StringBuffer nodeXML = null;
				if (node.getName().equals(tagName)) {
					nodeXML = getNodeXML(node, deep);
					isDeep = false;
				} else {
					nodeXML = getNodeXML(node, deep + 1);
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
		if (!tagName.equals("xml")) {
			if (deep == 0) {
				tempBuffer.append(tab + "<" + tagName + namespaces);
			} else {
				tempBuffer.append(tab + "<" + tagName);
			}
		}

		if (attributeBuffer != null && attributeBuffer.length() > 0) {
			tempBuffer.append(" " + attributeBuffer.toString().trim());
			
		}
		
		if (!tagName.equals("xml")) {
			if (nodeBuffer.length() > 0) {
				tempBuffer.append(">");
			} else {
				if (tagValue != null) {
					tempBuffer.append(">");
				} else {
					tempBuffer.append("/>" + endOfLine);
				}
			}
		}


		if (nodeBuffer.length() > 0) {
			tempBuffer.append("\n" + nodeBuffer);
			tempBuffer.append(tab + "</" + tagName + ">" + endOfLine);
		} else {
			if (tagValue != null) {
				if (!tagName.equals("xml")) {
					tempBuffer.append(tagValue.toString());
					tempBuffer.append("</" + tagName + ">" + endOfLine);
				} else {
					tempBuffer.append(tab + tagValue + endOfLine);
				}
			}
		}

		return tempBuffer;
	}

}
