package org.pinae.nala.xb.marshal;

/**
 * <code>AbstractMarshal</code>为<code>Marshaller</code>接口中的抽象类
 * 主要用于存放预置的设置参数
 * 
 * @author Huiyugeng
 *
 */
public abstract class AbstractMarshal {
	protected boolean domMode = true; //使用JDOM生成XML
	protected String endOfLine = "\n"; // 行结束符
	protected String indent = "\t"; // 缩进
	protected String documentStart = ""; // XML文档头
	protected String documentEnd = ""; // XML文档尾
	protected boolean prettyPrint = true; // 是否启用缩进格式
	protected boolean nodeMode = false; // XML注入是否启用节点模式, 如果启用节点模式, 则将属性写为节点
	protected String nodeTag = ""; // XML默认节点描述
	protected boolean isLowCase = true; // 是否采用首字母小写
	protected String namespaces = "";
	protected boolean cdata = false; // 是否使用CDATA识别模式
	
	/**
	 * 是否使用JDOM生成XML
	 * 
	 * @return 是否使用JDOM生成XML
	 */
	public boolean isDomMode() {
		return domMode;
	}

	/**
	 * 设置是否使用JDOM生成XML
	 * 
	 * @param domMode 生成模式 true JDOM生成XML false 拼接字符串生成XML
	 */
	public void setDomMode(boolean domMode) {
		this.domMode = domMode;
	}

	/**
	 * 设置默认节点描述
	 * 
	 * @param nodeTag 默认节点描述
	 */
	public void setNodeTag(String nodeTag) {
		this.nodeTag = nodeTag;
	}

	/**
	 * 是否启用节点模式
	 * 
	 * @param nodeMode 启用节点模式 true 启用 false 关闭
	 */
	public void enableNodeMode(boolean nodeMode) {
		this.nodeMode = nodeMode;
	}

	/**
	 * 是否启用缩进格式
	 * 
	 * @param prettyPrint 启用缩进格式 true 启用 false 关闭
	 */
	public void enablePrettyPrint(boolean prettyPrint) {
		this.prettyPrint = prettyPrint;
	}

	/**
	 * 是否启用首字母小写
	 * 
	 * @param isLowCase 启用首字母小写 true 启用 false 关闭
	 */
	public void enableLowCase(boolean isLowCase) {
		this.isLowCase = isLowCase;
	}

	/**
	 * 设置XML文档首部
	 * 
	 * @param start XML首部字符
	 */
	public void setDocumentStart(String start) {
		this.documentStart = start;
	}

	/**
	 * 设置XML文档尾部
	 * 
	 * @param end XML尾部字符
	 */
	public void setDocumentEnd(String end) {
		this.documentEnd = end;
	}

	/**
	 * 设置每行结尾符
	 * 
	 * @param endOfLine 结尾符, 例如\n
	 */
	public void setEndOfLine(String endOfLine) {
		this.endOfLine = endOfLine;
	}

	/**
	 * 设置缩进格式
	 * 
	 * @param indent 缩进格式, 例如\t
	 */
	public void setIndent(String indent) {
		this.indent = indent;
	}

	/**
	 * 是否使用自动CDATA适配
	 * 
	 * @param cdata 启用CDATA自动识别模式
	 */
	public void enableCDATA(boolean cdata) {
		this.cdata = cdata;
	}

}
