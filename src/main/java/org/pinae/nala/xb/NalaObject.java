package org.pinae.nala.xb;

import java.util.ArrayList;
import java.util.List;

import org.pinae.nala.xb.resource.Namespace;


/**
 * 每个绑定对象都可选继承的抽象类, 提供了XML命名空间的支持
 * 
 * @author Huiyugeng
 *
 */
public abstract class NalaObject {
	private List<Namespace> namespaces = new ArrayList<Namespace>();
	private String value;
	
	public NalaObject(){
		
	}
	
	public NalaObject(String value){
		this.value = value;
	}
	
	/**
	 * 返回该绑定的XML命名空间
	 * 
	 * @return 命名空间
	 */
	public List<Namespace> getNamespaces() {
		return namespaces;
	}

	/**
	 * 设置该绑定对象的XML命名空间
	 * 
	 * @param namespaces 命名空间
	 */
	public void setNamespaces(Namespace namespaces) {
		this.namespaces.add(namespaces);
	}

	/**
	 * 获取XML结点值
	 * 
	 * @return XML结点值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置XML结点值
	 * 
	 * @param value XML结点值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString(){
		return value;
	}

}
