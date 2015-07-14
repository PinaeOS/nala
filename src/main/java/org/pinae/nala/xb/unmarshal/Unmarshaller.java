package org.pinae.nala.xb.unmarshal;

import org.pinae.nala.xb.exception.UnmarshalException;

/**
 * 对象绑定类
 * 
 * 通过解析XML/JSON实现了XML/JSON元素与Java类之间的值映射
 * 
 * @author Huiyugeng
 *
 */
public interface Unmarshaller {
	
	/**
	 * 返回一个与XML/JSON绑定后的对象
	 * 
	 * @return 绑定的对象
	 * @throws UnmarshalException 解组异常
	 */
	public Object unmarshal() throws UnmarshalException;
	
	
	/**
	 * 设置XML/JSON根标签所对应的类
	 * 例如：org.pinae.nala.Root
	 * 
	 * @param clazz 需要绑定的类
	 */
	@SuppressWarnings("rawtypes")
	public void setRootClass(Class clazz);
	
	/**
	 * 是否采用对XML-Object/JSON-Object进行验证
	 * 当使用XML-Object/JSON-Object验证时, 当Object中没有XML/JSON对应字段时会抛出异常
	 * 默认情况为false
	 * 
	 * @param validation 是否采用XML-Object/JSON-Object验证
	 */
	public void validate(boolean validation);
}
