package org.pinae.nala.xb.node;

/**
 * XML命名空间配置
 * 
 * @author Huiyugeng
 *
 */
public class Namespace {
	
	private String prefix;
	private String uri;
	
	/**
	 * 构造函数
	 * 
	 * @param prefix 命名空间前缀
	 * @param uri 命名空间URI地址
	 */
	public Namespace(String prefix, String uri) {
		super();
		this.prefix = prefix;
		this.uri = uri;
	}
	
	/**
	 * 返回命名空间前缀
	 * 
	 * @return 命名空间前缀
	 */
	public String getPrefix() {
		return prefix;
	}
	/**
	 * 设置命名空间前缀
	 * 
	 * @param prefix 命名空间前缀
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	/**
	 * 返回命名空间URI地址 
	 * 
	 * @return 命名空间URI地址
	 */
	public String getUri() {
		return uri;
	}
	/**
	 * 设置命名空间URI地址
	 * 
	 * @param uri 命名空间URI地址
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
}
