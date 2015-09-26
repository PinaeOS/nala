package org.pinae.nala.xb.marshal;

import org.pinae.nala.xb.exception.MarshalException;

/**
 * 将经过ObjectParser解析的NodeConfig输出为XML流
 * 
 * @author Huiyugeng
 *
 */
public interface Marshaller {
	/**
	 * 将NodeConfig编写为XML输出
	 * 
	 * @return XML输出
	 * 
	 * @throws MarshalException 编组异常
	 */
	public StringBuffer marshal() throws MarshalException;
	
	/**
	 * 设置默认节点描述
	 * 
	 * @param nodeTag 默认节点描述
	 */
	public void setNodeTag(String nodeTag);
	
	
	/**
	 * 是否启用缩进格式
	 * 
	 * @param prettyPrint 启用缩进格式 true 启用 false 关闭
	 */
	public void enablePrettyPrint(boolean prettyPrint);
	
	/**
	 * 是否启用结点模式
	 * 
	 * @param nodeMode 启用结点模式 true 启用 false 关闭
	 */
	public void enableNodeMode(boolean nodeMode);
	
	/**
	 * 是否启用首字母小写
	 * 
	 * @param isLowCase 启用首字母小写 true 启用 false 关闭
	 */
	public void enableLowCase(boolean isLowCase);
	
	/**
	 * 设置XML文档首部
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
	 * 是否使用自动CDATA适配
	 * 
	 * @param cdata 启用CDATA自动识别模式
	 */
	public void enableCDATA(boolean cdata);
	
}
