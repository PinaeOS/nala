package org.pinae.nala.xb.marshaller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.pinae.nala.xb.NalaTestConstant;
import org.pinae.nala.xb.exception.MarshalException;
import org.pinae.nala.xb.exception.NoSuchPathException;
import org.pinae.nala.xb.marshal.Marshaller;
import org.pinae.nala.xb.marshal.XMLMarshaller;
import org.pinae.nala.xb.resource.map.PersonOne;
import org.pinae.nala.xb.util.ResourceWriter;
/**
 * org.pinae.nala.xb.marshal.XMLMarshaller测试
 * 
 * @author Huiyugeng
 */

public class MapXMLMarshallerTest  extends TestCase {
	private static final Logger log = Logger.getLogger(MapXMLMarshallerTest.class);
	@SuppressWarnings("rawtypes")
	Map people = new HashMap();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setUp(){
		List peopleList = new ArrayList();
		peopleList.add(PersonOne.getObject());
		people.put("person", peopleList);
		people.put("nodeTag", "people");
	}
	
	/**
	 * 测试从Map对象生成XML文件(编组)
	 */
	public void testMarshal() {
		Marshaller marshaller = new XMLMarshaller(people);

		marshaller.setDocumentStart("<?xml version='1.0' encoding='gb2312'?>");
		try {
			new ResourceWriter().writeToFile(marshaller.marshal(), NalaTestConstant.OUTPUT_XMLFILE);
			log.debug(marshaller.marshal());
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (MarshalException e) {
			fail(e.getMessage());
		}
		
	}
}
