package org.pinae.nala.xb.resource;

/**
 * XML结点属性配置类
 * 
 * @author Huiyugeng
 *
 */
public class AttributeConfig {
	private String name;
	private String value;
	
	/**
	 * 返回XML结点的属性名称
	 * 
	 * @return 返回属性名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置XML结点的属性名称
	 * 
	 * @param name 设置的属性名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 返回XML结点的属性值
	 * 
	 * @return 返回XML结点的属性值
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置XML结点的属性值
	 * 
	 * @param value 设置XML结点的属性值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * 返回表示 AttributeConfig 值的 String 对象, 值为AttributeConfig的name的值和value的值
	 * 
	 * @return AttributeConfig对象的字符串表示形式
	 */
	public String toString(){
		return "name:" + this.name + " value:" + this.value;
	}
	
	/**
	 * 将此对象与指定对象进行比较。当且仅当该参数不是 null, 且 AttributeConfig 对象与此对象包含相同的name,value, 子结点, 属性值时, 结果才为 true
	 * 
	 * @return 要与之进行比较的对象
	 */
	public boolean equals(Object obj){
		if(!(obj instanceof AttributeConfig)) return false;
		final AttributeConfig attr = (AttributeConfig)obj;
		if(attr.getName().equals(name) && attr.getValue().equals(value)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 返回 AttributeConfig 的哈希码, 结果是37乘以name的哈希码和value的哈希码的和
	 * 
	 * @return 此对象的哈希码值
	 */
	public int hashCode(){
		return 37 * ((name==null?0:name.hashCode()) + (value==null?0:value.hashCode()));
	}
}
