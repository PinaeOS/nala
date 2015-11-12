package org.pinae.nala.xb.data.bean;

import java.util.ArrayList;
import java.util.List;

import org.pinae.nala.xb.XmlObject;

/**
 * 组织结构列表
 * 
 * @author Huiyugeng
 *
 */
public class OrgList extends XmlObject {
	private List<Org> org = new ArrayList<Org>();

	public List<Org> getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org.add(org);
	}
	
	/**
	 * 根据组织编号获取组织信息
	 * 
	 * @param id 组织编号
	 * @return 组织信息
	 */
	public Org getOrgByID(String id){
		if(id==null || id.equals(""))
			return null;
		for(Org org : this.org){
			if(id.equals(org.getId())){
				return org;
			}
		}
		return null;
	}
	
	/**
	 *  获取组织列表长度
	 *  
	 *  @return 组织列表长度
	 */
	public int getOrgSize(){
		if(org==null){
			return 0;
		}
		return org.size();
	}
	
	/**
	 * 获取组织结构列表
	 * 
	 * @return 组织结构列表
	 */
	public List<String> getOrgNameList(){
		List<String> orgNameList = new ArrayList<String>();
		for(Org org : this.org){
			orgNameList.add(org.getName());
		}
		return orgNameList;
	}

	public class Org extends XmlObject {
		private String id; //组织编号
		private String name; //组织名称
		private String desc; //组织描述
		private String status; //组织状态
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
	}
}
