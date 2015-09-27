package org.pinae.nala.xb.marshal.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.pinae.nala.xb.exception.MarshalException;
import org.pinae.nala.xb.resource.AttributeConfig;
import org.pinae.nala.xb.resource.NodeConfig;
import org.pinae.nala.xb.util.TypeConver;

/**
 * 将Map解析为NodeConfig格式
 * 
 * @author Huiyugeng
 * 
 */
public class MapParser extends ObjectParser {
	
	/**
	 * 采用递归的方式, 通过Map解析生成XML结构配置
	 * 
	 * @param nodeName 根节点名称
	 * @param map 需要解析的Map对象
	 * 
	 * @return 解析后的NodeConfig格式
	 * 
	 * @throws MarshalException 编组异常
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public NodeConfig parse(String nodeName, Map map) throws MarshalException {

		NodeConfig nodeConfig = new NodeConfig();
		List<AttributeConfig> attributeConfigList = new ArrayList<AttributeConfig>();
		List<NodeConfig> nodeConfigList = new ArrayList<NodeConfig>();

		if (map != null) {
			Set<Map.Entry<String, Object>> set = map.entrySet();

			for (Map.Entry<String, Object> entry : set) {
				String key = entry.getKey();
				Object value = entry.getValue();

				if (value == null) {
					value = "";
				}
				String valueType = value.getClass().getName();
				if (key.equals("nodeTag")) {
					continue;
				}
				if (!key.equals("nodeValue")) {
					if (TypeConver.isBasicType(valueType)) {
						AttributeConfig attributeConfig = new AttributeConfig();
						attributeConfig.setName(key);
						attributeConfig.setValue(value.toString());
						attributeConfigList.add(attributeConfig);
					} else {
						nodeConfigList.add(super.parse(key, value));
					}
				} else {
					if (TypeConver.isBasicType(value.getClass().toString())) {
						nodeConfig.setValue(value.toString());
					}
				}
			}
		}
		nodeConfig.setName(nodeName);
		nodeConfig.setChildrenNodes(nodeConfigList);
		nodeConfig.setAttribute(attributeConfigList);
		return nodeConfig;
	}
}
