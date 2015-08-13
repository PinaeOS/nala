package org.pinae.nala.xb.xml;

/**
 * CDATA对象
 * 
 * @author Huiyugeng
 *
 */
public class CdataObject {
	private String data; //XML值
	/**
	 * 构造函数
	 * 
	 * @param xml XML值
	 */
	public CdataObject(String data){
		this.data = data;
	}
	
	/**
	 * 构造函数
	 */
	public CdataObject(){
		
	}
	
	/**
	 * 返回XML值
	 * @return XML值
	 */
	public String getData() {
		return data;
	}

	/**
	 * 设置XML值
	 * @param xml XML值
	 */
	public void setData(String data) {
		this.data = data;
	}
	
}
