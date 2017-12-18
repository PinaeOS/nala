package org.pinae.nala.xb.unmarshal.creator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pinae.nala.xb.node.AttributeConfig;
import org.pinae.nala.xb.node.NodeConfig;

/**
 * 根据NodeConfig构建Map对象
 * 
 * @author Huiyugeng
 *
 */
public class MapCreator {

	/**
	 * 根据结构体生成Map
	 * 
	 * @param nodeConfig NodeConfig结构
	 * 
	 * @return NodeConfig生成的Map对象
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getMap(NodeConfig nodeConfig) {
		Map map = new HashMap();
		List<AttributeConfig> attributes = nodeConfig.getAttribute();
		map.putAll(getAttribute(attributes));

		List<NodeConfig> childrenNodes = nodeConfig.getChildrenNodes();
		if (childrenNodes != null && childrenNodes.size() > 0) {
			for (NodeConfig childNode : childrenNodes) {
				String name = childNode.getName();
				Object value = childNode.getValue();

				if (childNode.hasChildren()) {
					Map childNodeMap = getMap(childNode);
					map.put(name, addToList(map.get(name), childNodeMap));
				} else {
					if (childNode.hasAttributes()) {
						Map attributeMap = new HashMap();
						attributeMap = getAttribute(childNode.getAttribute());
						if (attributeMap.size() > 0) {
							if (value != null && value instanceof String) {
								attributeMap.put("nodeValue", value);
							}
							value = attributeMap;
						}
					}
				}

				if (value != null) {
					map.put(name, addToList(map.get(name), value));
				}
			}
		}
		if (nodeConfig.getName() != null && nodeConfig.getValue() != null) {
			map.put(nodeConfig.getName(), nodeConfig.getValue());
		}
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map getAttribute(List<AttributeConfig> attributes) {
		Map map = new HashMap();
		if (attributes != null && attributes.size() > 0) {
			for (AttributeConfig attribute : attributes) {
				String name = attribute.getName();
				String value = attribute.getValue();
				if (value != null && !value.equals("")) {
					map.put(name, value);
				}
			}
		}
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object addToList(Object previousValue, Object newValue) {
		if (previousValue != null) {
			List list = new ArrayList();
			if (previousValue instanceof List) {
				list = (List) previousValue;
				list.add(newValue);
			} else {
				list.add(previousValue);
				list.add(newValue);
			}
			return list;
		} else {
			return newValue;
		}
	}
}
