package org.pinae.nala.xb.resource.bean;

import java.util.Date;

import org.pinae.nala.xb.resource.Namespace;
import org.pinae.nala.xb.xml.CdataObject;

public class PersonOne {

	public static Person getObject(){
		Person person = new Person();
		person.setNamespaces(new Namespace("tns", "http://www.pinae.com"));
		person.setNamespaces(new Namespace("tns2", "http://www.pinae.org"));
		person.setId(1);
		
		Person.Sex sex= person.new Sex();
		sex.setValue("1");
		
		Website website1 = new Website();
		website1.setValue("http://www.google.com");
		Website website2 = new Website();
		website2.setValue("http://www.baidu.com");
		Website website3 = new Website();
		website3.setValue("http://www.163.com");
		Website website4 = new Website();
		website4.setValue("http://www.21cn.com");

		person.setWebsite(website1);
		person.setWebsite(website2);
		person.setWebsite(website3);
		person.setWebsite(website4);
		
		Name name = new Name();
		Name.Firstname firstname = name.new Firstname();
		Name.Lastname lastname = name.new Lastname();
		firstname.setValue("Tom");
		lastname.setValue("Jim");
		name.setFirstname(firstname);
		name.setLastname(lastname);
		
		Telephone telephone = new Telephone();
		Telephone.Mobile mobile = telephone.new Mobile();
		mobile.setNet("gsm");
		mobile.setValue("13343351822");
		Telephone.Office office = telephone.new Office();
		office.setValue("010-8556697");
		Telephone.Home home = telephone.new Home();
		home.setValue("0757-82988679");
		telephone.setHome(home);
		telephone.setOffice(office);
		telephone.setMobile(mobile);
		
		person.setBirthday(new Date());
		person.setName(name);
		person.setAge(23);
		person.setSex(sex);
		person.setTelephone(telephone);
		
		person.setOther(new CdataObject("<XML>Tearcher</XML>"));
		person.setPc("<Model>Acer 4750G</Model>");
		
		return person;
	}

}
