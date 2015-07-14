package org.pinae.nala.xb.resource.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.pinae.nala.xb.xml.CDATAObject;

public class PersonOne {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map getObject(){
		
		Map name = new HashMap();
		name.put("firstname", "yugeng");
		name.put("lastname", "Hui");
		
		Map telephone = new HashMap();
		telephone.put("home", "010-1234567");
		telephone.put("mobile", "13343351822");
		
		Map person = new HashMap();
		person.put("name", name);
		person.put("telephone", telephone);
		
		person.put("age", 21);
		person.put("sex", "male");
		
		List website = new ArrayList();
		website.add("http://www.baidu.com");
		website.add("http://www.pinae.org");
		
		person.put("website", website);
		
		person.put("other", new CDATAObject("<Job>Teacher</Job>"));
		
		return person;
	}
}
