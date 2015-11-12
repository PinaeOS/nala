package org.pinae.nala.xb.marshaller;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.pinae.nala.xb.TestConstant;
import org.pinae.nala.xb.data.map.PersonOne;
import org.pinae.nala.xb.exception.MarshalException;
import org.pinae.nala.xb.exception.NoSuchPathException;
import org.pinae.nala.xb.marshal.Marshaller;
import org.pinae.nala.xb.marshal.XmlMarshaller;
import org.pinae.nala.xb.util.ResourceWriter;

/**
 * MapMarshaller单元测试
 * 
 * @author Huiyugeng
 */
public class MapXmlMarshallerTest {
	
	private static final Logger logger = Logger.getLogger(MapXmlMarshallerTest.class);
	
	@SuppressWarnings("rawtypes")
	Map people = new HashMap();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Before
	public void setUp() {
		List peopleList = new ArrayList();
		peopleList.add(PersonOne.getObject());
		people.put("person", peopleList);
		people.put("nodeTag", "people");
	}

	/**
	 * 测试从Map对象生成XML文件(编组)
	 */
	@Test
	public void testMarshal() {
		Marshaller marshaller = new XmlMarshaller(people);

		marshaller.setDocumentStart("<?xml version='1.0' encoding='gb2312'?>");
		try {
			new ResourceWriter().writeToFile(marshaller.marshal(), TestConstant.OUTPUT_XMLFILE);
			logger.debug(marshaller.marshal());
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (MarshalException e) {
			fail(e.getMessage());
		}

	}
}
