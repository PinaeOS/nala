package org.pinae.nala.xb.data.bean;

import java.util.ArrayList;
import java.util.List;

import org.pinae.nala.xb.xml.XmlText;

public class PeopleInXml {
	
	private List<XmlText> person = new ArrayList<XmlText>();
	
	public void setPerson(XmlText person){
		this.person.add(person);
	}
	public List<XmlText> getPerson(){
		return person;
	}
}
