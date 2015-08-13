package org.pinae.nala.xb.unmarshal.creator;

import org.pinae.nala.xb.exception.MarshalException;
import org.pinae.nala.xb.marshal.XmlMarshaller;
import org.pinae.nala.xb.resource.NodeConfig;
import org.pinae.nala.xb.util.Constant;
import org.pinae.nala.xb.util.TypeConver;
import org.pinae.nala.xb.xml.XmlObject;

/**
 * 构建对象值
 * 
 * @author Huiyugeng
 *
 */
public class ObjectValueCreator {
	/**
	 * 构建对象
	 * 
	 * @param fieldType 字段类型
	 * @param nodeConfig 结点配置
	 * 
	 * @return 构建的对象值
	 */
	public Object getValue(String fieldType, NodeConfig nodeConfig){
		Object value = null;
		if(fieldType.equals(Constant.XML_CLASS)){ 
			value = new XmlObject(createXML(nodeConfig));
		}else{
			if(TypeConver.isBasicType(fieldType)){
				value = TypeConver.converValue(fieldType, nodeConfig.getValue().toString());
			}
		}
		return value;
	}

	/*
	 * 根据构造体在对象中填入XML
	 */
	private String createXML(NodeConfig node){
		class XMLCreator extends XmlMarshaller{
			public XMLCreator(NodeConfig node){
				super(node);
			}	
			public String print(){
				try {
					return super.marshal().toString();
				} catch (MarshalException e) {
					return "";
				}
			}
		}
		XMLCreator creator = new XMLCreator(node);
		return creator.print();
	}
}
