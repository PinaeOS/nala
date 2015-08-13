package org.pinae.nala.xb.xml;

/**
 * XML对象
 * Nala绑定时, 如果指定变量类型为XML, 则不会对相应的XML进行解析和映射
 * 
 * 例如
 * 定义private Xml data;
 * 则XML描述
 * <data>
 * 	<text>Hello</text>
 * </data>
 * 在data的映射值<text>Hello</text>
 * 
 * @author Huiyugeng
 *
 */
public class XmlObject {
	private String xml; //XML值
	/**
	 * 构造函数
	 * 
	 * @param xml XML值
	 */
	public XmlObject(String xml){
		this.xml = xml;
	}
	
	/**
	 * 构造函数
	 */
	public XmlObject(){
		
	}
	
	/**
	 * 返回XML值
	 * @return XML值
	 */
	public String getXml() {
		return xml;
	}

	/**
	 * 设置XML值
	 * @param xml XML值
	 */
	public void setXml(String xml) {
		this.xml = xml;
	}
	
}
