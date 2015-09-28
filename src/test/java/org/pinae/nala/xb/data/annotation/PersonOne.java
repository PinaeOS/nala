package org.pinae.nala.xb.data.annotation;

import java.util.ArrayList;
import java.util.List;

import org.pinae.nala.xb.data.annotation.Telephone.Mobile;

public class PersonOne {
	
	public static Person getObject(){
		
		Person person = new Person();
		
		Name name = new Name();
		name.setFirstname("Zhizhi");
		name.setLastname("Wang");
		person.setName(name);
		
		person.setAge(30);
		person.setId(1);
		
		Telephone telephone = new Telephone();
		
		telephone.setHome("010-8899331");
		Mobile mobile = telephone.new Mobile();
		mobile.setNet("gsm");
		mobile.setValue("1339988776");
		telephone.setMobile(mobile);
		telephone.setOffice("010-8899332");
		person.setTelephone(telephone);
		
		List<String> website = new ArrayList<String>();
		website.add("http://www.163.com");
		website.add("http://www.126.com");
		website.add("http://www.263.com");
		
		person.setWebsite(website);
		
		return person;
	}
}
