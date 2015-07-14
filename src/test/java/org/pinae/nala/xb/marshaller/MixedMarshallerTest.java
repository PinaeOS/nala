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
import org.pinae.nala.xb.util.ResourceWriter;


/**
 * org.pinae.nala.xb.marshal.XMLMarshaller测试
 * 
 * @author Huiyugeng
 *
 */
public class MixedMarshallerTest extends TestCase {
	
	private static final Logger log = Logger.getLogger(MixedMarshallerTest.class);
	@SuppressWarnings("rawtypes")
	private Map people = new HashMap();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setUp(){
		List mapGroup = new ArrayList();
		mapGroup.add(org.pinae.nala.xb.resource.map.PersonOne.getObject());
		mapGroup.add(org.pinae.nala.xb.resource.map.PersonTwo.getObject());
		
		List beanGroup = new ArrayList();
		beanGroup.add(org.pinae.nala.xb.resource.bean.PersonOne.getObject());
		beanGroup.add(org.pinae.nala.xb.resource.bean.PersonTwo.getObject());
		
		List annotationGroup = new ArrayList();
		annotationGroup.add(org.pinae.nala.xb.resource.annotation.PersonOne.getObject());
		
		people.put("mapGroup", mapGroup);
		people.put("beanGroup", beanGroup);
		people.put("annotationGroup", annotationGroup);
		
		people.put("country", "china");
		people.put("count", 5);
		people.put("kia", false);
		people.put("nodeTag", "people");
	}
	
	/**
	 * 测试从对象生成XML文件(编组)
	 */
	public void testMarshal() {
		Marshaller marshaller = new XMLMarshaller(people);

		marshaller.setDocumentStart("<?xml version='1.0' encoding='gb2312'?>");
		marshaller.enableNodeMode(true);
		marshaller.enableLowCase(true);
		marshaller.enableCDATA(true);
		
		try {
			new ResourceWriter().writeToFile(marshaller.marshal(), NalaTestConstant.OUTPUT_XMLFILE);
			log.debug(marshaller.marshal().toString());
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (MarshalException e) {
			fail(e.getMessage());
		}

	}
	
	

}
