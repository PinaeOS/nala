package org.pinae.nala.xb.data.bean;

import java.util.ArrayList;
import java.util.List;

public class PersonList {
	
	private List<Person> person = new ArrayList<Person>();

	public List<Person> getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person.add(person);
	}

	public class Person {
		private String name;
		private String age;
		private String sex;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		
		
	}
}
