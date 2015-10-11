package org.pinae.nala.xb.unmarshal;

import org.pinae.nala.xb.exception.UnmarshalException;

/**
 * 对象绑定类, 通过解析XML实现了XML元素与Java类之间的值映射
 * 
 * @author Huiyugeng
 *
 */
public interface Unmarshaller {
	
	/**
	 * 返回一个与XML绑定后的对象
	 * 
	 * @return 绑定的对象
	 * @throws UnmarshalException 解组异常
	 */
	public Object unmarshal() throws UnmarshalException;
	
	
	/**
	 * 设置XML根标签所对应的类
	 * 例如：<code>org.pinae.nala.Root</code>
	 * 
	 * @param clazz 需要绑定的类
	 */
	public void setRootClass(Class<?> clazz);
	
	/**
	 * 是否采用对XML-Object进行验证
	 * 当使用XML-Object验证时, 当Object中没有XML对应字段时会抛出异常
	 * 默认情况为false
	 * 
	 * @param validation 是否采用XML-Object验证
	 */
	public void validate(boolean validation);
}
