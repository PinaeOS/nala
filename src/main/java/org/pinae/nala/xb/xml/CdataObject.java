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
	 * @param data CDATA值
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
	 * 返回CDATA值
	 * 
	 * @return CDATA值
	 */
	public String getData() {
		return data;
	}

	/**
	 * 设置CDATA值
	 * 
	 * @param data CDATA值
	 */
	public void setData(String data) {
		this.data = data;
	}
	
}
