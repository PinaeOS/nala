package org.pinae.nala.xb;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.pinae.nala.xb.resource.bean.People;
import org.pinae.nala.xb.resource.bean.Person;
import org.pinae.nala.xb.resource.bean.PersonOne;
import org.pinae.nala.xb.resource.bean.PersonThree;
import org.pinae.nala.xb.resource.bean.PersonTwo;
import org.pinae.nala.xb.util.ResourceReader;

import junit.framework.TestCase;

public class XMLTest extends TestCase{
	
	private static final Logger log = Logger.getLogger(XMLTest.class);
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testParseFileToMap() {
		Map map = XML.parseFileToMap(NalaTestConstant.TEST_XMLFILE3, "utf8");
		assertEquals(map.size(), 5);
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testParseXMLToMap() {
		try {
			StringBuffer xml = new ResourceReader().read(NalaTestConstant.TEST_XMLFILE3);
			Map map = XML.parseXMLToMap(xml.toString(), "utf8");
			assertEquals(map.size(), 5);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testParseFile() {
		Map map = (Map)XML.parseFile(NalaTestConstant.TEST_XMLFILE3, "utf8", Map.class);
		assertEquals(map.size(), 5);
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void testParseXML() {
		try {
			StringBuffer xml = new ResourceReader().read(NalaTestConstant.TEST_XMLFILE3);
			Map map = (Map)XML.parseXML(xml.toString(), "utf8", Map.class);
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
		
		String xml = XML.toXMLString(people, "utf8", true);
		
		log.debug(xml);
	}
}
