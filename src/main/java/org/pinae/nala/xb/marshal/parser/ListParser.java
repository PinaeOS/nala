package org.pinae.nala.xb.marshal.parser;

import java.util.ArrayList;
import java.util.List;

import org.pinae.nala.xb.exception.MarshalException;
import org.pinae.nala.xb.node.NodeConfig;

/**
 * 将List解析为NodeConfig格式
 * 
 * @author Huiyugeng
 *
 */
public class ListParser extends ObjectParser {
	
	/**
	 * 采用循环的方式, 通过List解析生成NodeConfig格式
	 * 
	 * @param nodeName 根节点名称
	 * @param list 需要解析的List对象
	 * 
	 * @return 解析后的NodeConfig格式
	 * 
	 * @throws MarshalException 编组异常
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public NodeConfig parse(String nodeName, List list) throws MarshalException {
		NodeConfig nodeConfig = new NodeConfig();
		List<NodeConfig> nodeConfigList = new ArrayList<NodeConfig>();

		if (list != null) {
			for (Object item : list) {
				nodeConfigList.add(super.parse(nodeName, item));
			}
		}
		nodeConfig.setName(nodeName);
		nodeConfig.setChildrenNodes(nodeConfigList);
		return nodeConfig;
	}
}
