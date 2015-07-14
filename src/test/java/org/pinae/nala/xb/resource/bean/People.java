package org.pinae.nala.xb.resource.bean;
import java.util.ArrayList;
import java.util.List;
public class People extends org.pinae.nala.xb.NalaObject {
	private List<Person> person = new ArrayList<Person>();
	public void setPerson(Person person){
		this.person.add(person);
	}
	public List<Person> getPerson(){
		return person;
	}
}