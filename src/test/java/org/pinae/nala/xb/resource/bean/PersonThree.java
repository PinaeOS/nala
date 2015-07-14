package org.pinae.nala.xb.resource.bean;

import java.util.Date;



public class PersonThree {

	public static Person getObject(){
		Person person = new Person();
		person.setId(3);
		
		Person.Sex sex= person.new Sex();
		sex.setValue("0");
		
		Website website1 = new Website();
		website1.setValue("http://www.csdn.net");
		Website website2 = new Website();
		website2.setValue("http://www.ibm.com");
		Website website3 = new Website();
		website3.setValue("http://www.sun.com");

		person.setWebsite(website1);
		person.setWebsite(website2);
		person.setWebsite(website3);
		
		Name name = new Name();
		Name.Firstname firstname = name.new Firstname();
		Name.Lastname lastname = name.new Lastname();
		firstname.setValue("Babo");
		lastname.setValue("Wang");
		name.setFirstname(firstname);
		name.setLastname(lastname);
		
		Telephone telephone = new Telephone();
		Telephone.Mobile mobile = telephone.new Mobile();
		mobile.setValue("124049917");
		Telephone.Office office = telephone.new Office();
		office.setValue("020-1234567");
		Telephone.Home home = telephone.new Home();
		home.setValue("020-2345678");
		telephone.setHome(home);
		telephone.setOffice(office);
		telephone.setMobile(mobile);
		
		person.setBirthday(new Date());
		person.setName(name);
		person.setAge(26);
		person.setName(name);
		person.setSex(sex);
		person.setTelephone(telephone);
		
		return person;
	}

}
