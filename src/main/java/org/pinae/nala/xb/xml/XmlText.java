package org.pinae.nala.xb.xml;

/**
 * XML对象
 * 
 * 绑定时, 如果指定变量类型为XML, 则不会对相应的XML进行解析和映射
 * 在XML对象中将保持原有的XML文本信息
 * 
 * @author Huiyugeng
 *
 */
public class XmlText {
	private String xml; //XML值
	/**
	 * 构造函数
	 * 
	 * @param xml XML值
	 */
	public XmlText(String xml){
		this.xml = xml;
	}
	
	/**
	 * 构造函数
	 */
	public XmlText(){
		
	}
	
	/**
	 * 返回Xml文本
	 * 
	 * @return Xml文本
	 */
	public String getXml() {
		return xml;
	}

	/**
	 * 设置Xml文本
	 * 
	 * @param xml Xml文本
	 */
	public void setXml(String xml) {
		this.xml = xml;
	}
	
}
