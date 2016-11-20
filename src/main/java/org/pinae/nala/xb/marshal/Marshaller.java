package org.pinae.nala.xb.marshal;

import org.pinae.nala.xb.exception.MarshalException;

/**
 * <p>将经过解析对象输出为 XML文本</p>
 * 
 * <p>
 * 支持解析的对象包括基本的对象(包括支持继承<code>XmlObject</code>的对象), 含有注释信息的对象, 
 * <code>List</code>, <code>Map</code>, 对象解析后生成使用<code>NodeConfig</code>描述的中间格式,
 * 然后使用中间格式输出为XML文本
 * </p>
 * 
 * @author Huiyugeng
 *
 */
public interface Marshaller {
	/**
	 * 输出为XML文本
	 * 
	 * @return XML文本
	 * 
	 * @throws MarshalException XML编组异常
	 */
	public StringBuffer marshal() throws MarshalException;
	
	/**
	 * 设置是否使用JDOM生成XML
	 * 
	 * @param domMode 生成模式 true JDOM生成XML false 拼接字符串生成XML
	 */
	public void enableDomMode(boolean domMode);
	
	/**
	 * XML编码版本
	 * 
	 * @param version XML编码版本
	 */
	public void setVersion(String version);
	
	/**
	 * 设置XML默认编码
	 * 
	 * @param encoding 默认编码
	 */
	public void setEncoding(String encoding);

	/**
	 * 设置根节点标签名称
	 * 
	 * @param nodeTag 根节点标签名称
	 */
	public void setNodeTag(String nodeTag);

	/**
	 * 是否启用缩进格式
	 * 
	 * @param prettyPrint 启用缩进格式 true 启用 false 关闭
	 */
	public void enablePrettyPrint(boolean prettyPrint);

	/**
	 * 是否启用节点模式
	 * 
	 * @param nodeMode 启用节点模式 true 启用 false 关闭
	 */
	public void enableNodeMode(boolean nodeMode);

	/**
	 * 是否启用字母小写
	 * 
	 * @param isLowCase 启用字母小写 true 启用 false 关闭
	 */
	public void enableLowCase(boolean isLowCase);

	/**
	 * 设置XML文档首部
	 * 通常用于设定XML文档编码集
	 * 
	 * @param start XML首部字符
	 */
	public void setDocumentStart(String start);

	/**
	 * 设置XML文档尾部
	 * 
	 * @param end XML尾部字符
	 */
	public void setDocumentEnd(String end);

	/**
	 * 设置每行结尾符
	 * 
	 * @param endOfLine 结尾符, 例如\n
	 */
	public void setEndOfLine(String endOfLine);

	/**
	 * 设置缩进格式
	 * 
	 * @param indent 缩进格式, 例如\t
	 */
	public void setIndent(String indent);

	/**
	 * 是否使用CDATA适配
	 * 
	 * @param cdata 启用CDATA识别模式
	 */
	public void enableCDATA(boolean cdata);

}
