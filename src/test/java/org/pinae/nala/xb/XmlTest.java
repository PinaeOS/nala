package org.pinae.nala.xb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.junit.Test;
import org.pinae.nala.xb.data.bean.People;
import org.pinae.nala.xb.data.bean.Person;
import org.pinae.nala.xb.data.bean.PersonOne;
import org.pinae.nala.xb.data.bean.PersonThree;
import org.pinae.nala.xb.data.bean.PersonTwo;
import org.pinae.nala.xb.exception.MarshalException;
import org.pinae.nala.xb.exception.NoSuchPathException;
import org.pinae.nala.xb.exception.UnmarshalException;
import org.pinae.nala.xb.util.ResourceReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlTest {

	private static final Logger logger = LoggerFactory.getLogger(XmlTest.class);

	@SuppressWarnings("rawtypes")
	@Test
	public void testFileToMap() {
		try {
			Map map = Xml.toMap(new File(TestConstant.TEST_XMLFILE3), "utf8");
			assertEquals(map.size(), 5);
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		}

	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testToMap() {
		try {
			StringBuffer xml = new ResourceReader().readFile(TestConstant.TEST_XMLFILE3);
			Map map = Xml.toMap(xml.toString(), "utf8");
			assertEquals(map.size(), 5);
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void testToMap2() {
		try {
			StringBuffer xml = new ResourceReader().readFile(TestConstant.TEST_XMLFILE6);
			Map<String, Object> map = Xml.toMap(xml.toString(), "utf8");
			assertEquals(map.size(), 1);
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}

	}
	
	@Test
	public void testFileToObject() {
		try {
			Map<String, Object> map =  Xml.toObject(new File(TestConstant.TEST_XMLFILE3), "utf8", Map.class);
			assertEquals(map.size(), 5);
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void testToObject() {
		try {
			StringBuffer xml = new ResourceReader().readFile(TestConstant.TEST_XMLFILE3);
			Map<String, Object> map = Xml.toObject(xml.toString(), "utf8", Map.class);
			assertEquals(map.size(), 5);
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testToXML() {
		People people = new People();

		people.setPerson((Person) PersonOne.getObject());
		people.setPerson((Person) PersonTwo.getObject());
		people.setPerson((Person) PersonThree.getObject());

		try {
			String xml = Xml.toXML(people, "utf8", true);
			logger.debug(xml);
		} catch (MarshalException e) {
			fail(e.getMessage());
		}

	}
}
