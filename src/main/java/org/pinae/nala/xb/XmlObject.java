package org.pinae.nala.xb;

import java.util.ArrayList;
import java.util.List;

import org.pinae.nala.xb.node.Namespace;


/**
 * <p>当对象继承<code>XmlObject</code>时
 * 可以提供XML命名空间和节点值的支持 </p>
 * 
 * <p>节点值是指 <code> <key>value</key> </code>中的value</p>
 * 
 * @author Huiyugeng
 *
 */
public abstract class XmlObject {
	private List<Namespace> namespaces = new ArrayList<Namespace>();
	private String value;
	
	/**
	 * 构造函数
	 */
	public XmlObject(){
		
	}
	
	/**
	 * 构造函数
	 * 
	 * @param value XML节点值
	 */
	public XmlObject(String value){
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
	 * 获取XML节点值
	 * 
	 * @return XML节点值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置XML节点值
	 * 
	 * @param value XML节点值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString(){
		return value;
	}

}
