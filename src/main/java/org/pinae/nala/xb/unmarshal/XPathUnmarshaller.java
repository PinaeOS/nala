package org.pinae.nala.xb.unmarshal;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.pinae.nala.xb.exception.UnmarshalException;
import org.pinae.nala.xb.resource.NodeConfig;
import org.pinae.nala.xb.unmarshal.parser.XmlParser;


/**
 * 通过指定的XPath, 绑定指定的对象
 * 支持标准XPath路径描述
 * 
 * @author Huiyugeng
 *
 */
public class XPathUnmarshaller extends BasicUnmarshaller {
	private XmlParser xmlParser = new XmlParser();
	private List<?> lstNodeConfig = null;
	
	/**
	 * 构造函数
	 * 
	 * @param xml 输入的XML流
	 * @param xpath 指定的XPath路径
	 * 
	 * @throws UnmarshalException 解组的异常
	 */
	public XPathUnmarshaller(InputStreamReader xml, String xpath) throws UnmarshalException {
		
		if(xpath == null){
			throw new UnmarshalException("Please use constructor XPathUnmarshaller(InputStreamReader xml, String xpath) to set xpath value");
		}
		
		this.lstNodeConfig = xmlParser.parserByXpath(xml, xpath);
	}
	
	public XPathUnmarshaller(InputStream xml, String xpath) throws UnmarshalException {
		if(xpath == null){
			throw new UnmarshalException("Please use constructor XPathUnmarshaller(InputStream xml, String xpath) to set xpath value");
		}
		
		this.lstNodeConfig = xmlParser.parserByXpath(new InputStreamReader(xml), xpath);
	}

	@SuppressWarnings("rawtypes")
	public List unmarshal() throws UnmarshalException {
		List<Object> lstObject = new ArrayList<Object>();
		if(lstNodeConfig !=null && lstNodeConfig.size()>0){
			for(Iterator iterNodeConfig = lstNodeConfig.iterator();iterNodeConfig.hasNext();){
				NodeConfig config = (NodeConfig)iterNodeConfig.next();
				if(config!=null){
					Object targetObject = super.creteObject(config);
					lstObject.add(targetObject);
				}else{
					throw new UnmarshalException("Couldn't get XML information");
				}
			}
			return lstObject;
		}else{
			throw new UnmarshalException("Couldn't get XML information by XPath");
		}
		
	}
	

}
