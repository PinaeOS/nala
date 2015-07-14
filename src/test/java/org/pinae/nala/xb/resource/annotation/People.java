package org.pinae.nala.xb.resource.annotation;
import java.util.ArrayList;
import java.util.List;

import org.pinae.nala.xb.annotation.Element;
import org.pinae.nala.xb.annotation.Root;

@Root(name="people")
public class People extends org.pinae.nala.xb.NalaObject {
	
	@Element(name="person")
	private List<Person> person = new ArrayList<Person>();
	
	public void setPerson(Person person){
		this.person.add(person);
	}
	public List<Person> getPerson(){
		return person;
	}
}