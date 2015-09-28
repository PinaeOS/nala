package org.pinae.nala.xb.node;

import java.util.ArrayList;
import java.util.List;

/**
 * XML节点配置类
 * 
 * @author Huiyugeng
 *
 */
public class NodeConfig {
	
	private String name;
	private Object value;
	private List<AttributeConfig> attribute;
	private List<NodeConfig> childrenNodes;
	private List<Namespace> namespace = new ArrayList<Namespace>();
	
	/**
	 * 构造函数
	 */
	public NodeConfig(){
		
	}
	
	/**
	 * 构造函数
	 * 
	 * @param name XML节点名称
	 * @param value XML节点值
	 */
	public NodeConfig(String name, Object value){
		this.name = name;
		this.value = value;
	}
	
	/**
	 * 返回节点命名空间配置
	 * 
	 * @return 节点命名空间配置
	 */
	@SuppressWarnings("rawtypes")
	public List getNamespace() {
		return namespace;
	}
	
	/**
	 * 设置节点命名空间配置
	 * 
	 * @param prefix 节点命名空间名称
	 * @param uri 节点命名空间URI
	 */
	public void setNamespace(String prefix, String uri) {
		Namespace namespace = new Namespace(prefix, uri);
		this.namespace.add(namespace);
	}
	/**
	 * 返回该节点的子节点队列
	 * 
	 * @return 子节点队列
	 */
	@SuppressWarnings("rawtypes")
	public List getChildrenNodes() {
		return childrenNodes;
	}
	/**
	 * 设置该节点的子节点队列
	 * 
	 * @param childrenNodes 子节点队列
	 */
	public void setChildrenNodes(List<NodeConfig> childrenNodes) {
		this.childrenNodes = childrenNodes;
	}
	/**
	 * 返回该节点的属性队列
	 * 
	 * @return 属性队列
	 */
	@SuppressWarnings("rawtypes")
	public List getAttribute() {
		return attribute;
	}
	/**
	 * 设置该节点的属性队列
	 * 
	 * @param attribute 属性队列
	 */
	public void setAttribute(List<AttributeConfig> attribute) {
		this.attribute = attribute;
	}
	/**
	 * 返回XML的节点值
	 * 
	 * @return 节点值
	 */
	public Object getValue() {
		return value;
	}
	/**
	 * 设置XML的节点值
	 * 
	 * @param value 节点值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 返回XML的节点名称
	 * 
	 * @return 节点名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 返回XML的节点名称
	 * 
	 * @param name 节点名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 根据子节点是否为空且子节点队列长度为0, 判断该节点是否还有子节点
	 * 
	 * @return 是否存在子节点
	 */
	public boolean hasChildren() {
		return (childrenNodes!=null && childrenNodes.size()>0);
	}
	
	/**
	 * 根据节点的属性列表是否为空且子节点队列长度为0, 判断该节点是否有属性
	 * 
	 * @return 是否存在子节点
	 */
	public boolean hasAttributes() {
		return (attribute!=null && attribute.size()>0);
	}
	
	/**
	 * 返回表示 NodeConfig 值的 String 对象, 值为NodeConfig的name的值和value的值
	 * 
	 * @return NodeConfig对象的字符串表示形式
	 */
	public String toString(){
		return "name:" + this.name + " value:" + this.value;
	}
	
	/**
	 * 将此对象与指定对象进行比较。当且仅当该参数不是 null, 且 NodeConfig 对象与此对象包含相同的name,value, 子节点, 属性值时, 结果才为 true
	 * 
	 * @return 要与之进行比较的对象
	 */
	public boolean equals(Object obj){
		if(!(obj instanceof NodeConfig)) return false;
		final NodeConfig attr = (NodeConfig)obj;
		if(attr.getName().equals(name) && attr.getValue().equals(value)){
			if(attr.getAttribute().equals(attribute) && attr.getChildrenNodes().equals(childrenNodes)){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	/**
	 * 返回 NodeConfig 的哈希码, 结果是37乘以name的哈希码和value的哈希码的和
	 * 
	 * @return 此对象的哈希码值
	 */
	public int hashCode(){
		int result = 37 * (name==null ? 0 : name.hashCode() + value.toString() == null ? 0 : value.hashCode());
		result += 37 * ((attribute==null?0:attribute.hashCode()) + (childrenNodes==null?0:childrenNodes.hashCode()));
		return result;
	}
	
}
