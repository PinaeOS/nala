package org.pinae.nala.xb.resource.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonTwo {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getObject(){
		
		Map name = new HashMap();
		name.put("firstname", "Tu");
		name.put("lastname", "Tu");
		
		Map telephone = new HashMap();
		telephone.put("home", "010-1234567");
		telephone.put("mobile", "13391562775");
		
		Map person = new HashMap();
		person.put("name", name);
		person.put("telephone", telephone);
		
		person.put("age", 26);
		person.put("sex", "female");
		
		List website = new ArrayList();
		website.add("http://www.taobao.com");
		website.add("http://www.tmall.com");
		
		person.put("website", website);
		
		return person;
	}
}
