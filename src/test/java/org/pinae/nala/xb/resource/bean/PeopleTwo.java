package org.pinae.nala.xb.resource.bean;

import org.pinae.nala.xb.xml.XmlObject;

public class PeopleTwo {
	
	public static Object getPeople(){
		PeopleInXml people = new PeopleInXml();
		people.setPerson(new XmlObject("<firstname>inter</firstname><lastname>hyg</lastname>"));
		people.setPerson(new XmlObject("<firstname>hyg</firstname><lastname>genggeng</lastname>"));
		return people;
	}
}
