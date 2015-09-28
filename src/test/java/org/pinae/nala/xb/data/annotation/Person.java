package org.pinae.nala.xb.data.annotation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.pinae.nala.xb.annotation.Attribute;
import org.pinae.nala.xb.annotation.Element;

public class Person extends org.pinae.nala.xb.XmlObject {
	
	@Element(name="sex")
	private Sex sex;
	
	@Element(name="age")
	private int age;
	
	@Element(name="telephone")
	private Telephone telephone;
	
	@Element(name="name")
	private Name name;
	
	@Element(name="website")
	private List<String> website = new ArrayList<String>();
	
	@Attribute(name="id")
	private int id;
	
	@Element(name="class")
	private String kwClass;
	
	@Element(name="birthday")
	private Date birthday;
	
	@Element(name="cost")
	private BigDecimal cost;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKwClass() {
		return kwClass;
	}
	public void setKwClass(String kwClass) {
		this.kwClass = kwClass;
	}
	public void setSex(Sex sex){
		this.sex = sex;
	}
	public Sex getSex(){
		return sex;
	}
	public void setAge(int age){
		this.age = age;
	}
	public int getAge(){
		return age;
	}
	public void setTelephone(Telephone telephone){
		this.telephone = telephone;
	}
	public Telephone getTelephone(){
		return telephone;
	}
	public void setName(Name name){
		this.name = name;
	}
	public Name getName(){
		return name;
	}
	public void setWebsite(List<String> website){
		this.website = website;
	}
	public List<String> getWebsite(){
		return website;
	}
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}
	public Date getBirthday(){
		return birthday;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public class Sex extends org.pinae.nala.xb.XmlObject {
		@Attribute(name="key")
		private String key;
		
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
	}
}