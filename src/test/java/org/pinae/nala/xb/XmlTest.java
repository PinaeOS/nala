package org.pinae.nala.xb;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.pinae.nala.xb.data.bean.People;
import org.pinae.nala.xb.data.bean.Person;
import org.pinae.nala.xb.data.bean.PersonOne;
import org.pinae.nala.xb.data.bean.PersonThree;
import org.pinae.nala.xb.data.bean.PersonTwo;
import org.pinae.nala.xb.util.ResourceReader;

import junit.framework.TestCase;

public class XmlTest extends TestCase{
	
	private static final Logger log = Logger.getLogger(XmlTest.class);
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testParseFileToMap() {
		Map map = Xml.toMap(NalaTestConstant.TEST_XMLFILE3, "utf8");
		assertEquals(map.size(), 5);
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testParseXMLToMap() {
		try {
			StringBuffer xml = new ResourceReader().readFile(NalaTestConstant.TEST_XMLFILE3);
			Map map = Xml.toMap(xml.toString(), "utf8");
			assertEquals(map.size(), 5);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testParseFile() {
		Map map = (Map)Xml.toObject(NalaTestConstant.TEST_XMLFILE3, "utf8", Map.class);
		assertEquals(map.size(), 5);
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testParseXML() {
		try {
			StringBuffer xml = new ResourceReader().readFile(NalaTestConstant.TEST_XMLFILE3);
			Map map = (Map)Xml.toObject(xml.toString(), "utf8", Map.class);
			assertEquals(map.size(), 5);
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testToXML() {
		People people = new People();
		
		people.setPerson((Person)PersonOne.getObject());
		people.setPerson((Person)PersonTwo.getObject());
		people.setPerson((Person)PersonThree.getObject());
		
		String xml = Xml.toXML(people, "utf8", true);
		
		log.debug(xml);
	}
}
