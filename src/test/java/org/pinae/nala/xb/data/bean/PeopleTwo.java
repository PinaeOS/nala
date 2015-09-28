package org.pinae.nala.xb.data.bean;

import org.pinae.nala.xb.xml.XmlText;

public class PeopleTwo {
	
	public static Object getPeople(){
		PeopleInXml people = new PeopleInXml();
		people.setPerson(new XmlText("<firstname>inter</firstname><lastname>hyg</lastname>"));
		people.setPerson(new XmlText("<firstname>hyg</firstname><lastname>genggeng</lastname>"));
		return people;
	}
}
