package org.pinae.nala.xb.marshal;

/**
 * <code>AbstractMarshal</code>为<code>Marshaller</code>接口中的抽象类
 * 主要用于存放预置的设置参数
 * 
 * @author Huiyugeng
 *
 */
public abstract class AbstractMarshal {
	/* true:使用JDOM生成XML, false:使用字符串拼接方式 */
	protected boolean domMode = true;
	/* XML编码版本, 默认值 1.0 */
	protected String version = "1.0";
	/* XML默认编码, 默认值 UTF-8 */
	protected String encoding = "UTF-8";
	/* 行结束符, 默认为\n */
	protected String endOfLine = "\n";
	/* 缩进符, 默认为 \t */
	protected String indent = "\t";
	/* XML文档头, 在JDOM模式失效 */
	protected String documentStart = "";
	/* XML文档尾, 在JDOM模式失效 */
	protected String documentEnd = "";
	/* 是否启用缩进格式 */
	protected boolean prettyPrint = true;
	/* XML注入是否启用节点模式, 如果启用节点模式, 则将属性写为节点 */
	protected boolean nodeMode = false;
	/* XML默认根节点名称  */
	protected String nodeTag = "";
	/* 是否采用字母小写 */
	protected boolean isLowCase = true;
	/* 命名空间 */
	protected String namespaces = "";
	/* 是否使用CDATA识别模式 */
	protected boolean cdata = false; 

	/**
	 * 设置是否使用JDOM生成XML
	 * 
	 * @param domMode 生成模式 true JDOM生成XML false 拼接字符串生成XML
	 */
	public void enableDomMode(boolean domMode) {
		this.domMode = domMode;
	}
	
	/**
	 * XML编码版本, 默认值 1.0
	 * 
	 * @param version XML编码版本
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * 设置XML默认编码 (UTF-8/GB2312/GBK)
	 * 
	 * @param encoding XML默认编码
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * 设置默认节点名称
	 * 
	 * @param nodeTag 默认节点名称
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
	 * 是否启用字母小写
	 * 
	 * @param isLowCase 启用字母小写 true 启用 false 关闭
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
