package org.pinae.nala.xb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.pinae.nala.xb.XML;
import org.pinae.nala.xb.marshaller.XMLMarshallerTest;
import org.pinae.nala.xb.resource.bean.People;
import org.pinae.nala.xb.resource.bean.Person;
import org.pinae.nala.xb.resource.bean.PersonOne;
import org.pinae.nala.xb.resource.bean.PersonThree;
import org.pinae.nala.xb.resource.bean.PersonTwo;

public class XMLTest {
	
	private static final Logger log = Logger.getLogger(XMLTest.class);
	
	@Test
	public void testParseFileToMap() {
		Map map = XML.parseFileToMap(NalaTestConstant.TEST_XMLFILE3, "utf8");
		assertEquals(map.size(), 5);
	}
	
	@Test
	public void testParseXMLToMap() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(NalaTestConstant.TEST_XMLFILE3));
			StringBuffer xml = new StringBuffer();
			String line = null;
            while((line = reader.readLine()) != null){
                xml.append(line);
            }
			reader.close();
			Map map = XML.parseXMLToMap(xml.toString(), "utf8");
			assertEquals(map.size(), 5);
		} catch (IOException e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void testParseFile() {
		Map map = (Map)XML.parseFile(NalaTestConstant.TEST_XMLFILE3, "utf8", Map.class);
		assertEquals(map.size(), 5);
	}
	
	@Test
	public void testParseXML() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(NalaTestConstant.TEST_XMLFILE3));
			StringBuffer xml = new StringBuffer();
			String line = null;
            while((line = reader.readLine()) != null){
                xml.append(line);
            }
			reader.close();
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
