package org.pinae.nala.xb.data.bean;

import java.util.Date;


public class PersonTwo {

	public static Person getObject(){
		Person person = new Person();
		person.setId(2);
		person.setKwClass("Business");
		
		Person.Sex sex= person.new Sex();
		sex.setKey("no");
		sex.setValue("0");

		Website website1 = new Website();
		website1.setValue("http://www.qq.com");
		Website website2 = new Website();
		website2.setValue("http://www.venustech.com.cn");

		person.setWebsite(website1);
		person.setWebsite(website2);

		Name name = new Name();
		Name.Firstname firstname = name.new Firstname();
		Name.Lastname lastname = name.new Lastname();
		firstname.setValue("Ken");
		lastname.setValue("Li");
		name.setFirstname(firstname);
		name.setLastname(lastname);
		
		Telephone telephone = new Telephone();
		Telephone.Mobile mobile = telephone.new Mobile();
		mobile.setValue("1391562775");
		Telephone.Office office = telephone.new Office();
		office.setValue("010-66778899");
		Telephone.Home home = telephone.new Home();
		home.setValue("0757-88990011");
		telephone.setHome(home);
		telephone.setOffice(office);
		telephone.setMobile(mobile);
		
		person.setBirthday(new Date());
		person.setName(name);
		person.setAge(22);
		person.setName(name);
		person.setSex(sex);
		person.setTelephone(telephone);
		
		return person;
	}

}
