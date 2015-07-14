package org.pinae.nala.xb.marshaller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.pinae.nala.xb.NalaTestConstant;
import org.pinae.nala.xb.exception.MarshalException;
import org.pinae.nala.xb.exception.NoSuchPathException;
import org.pinae.nala.xb.marshal.Marshaller;
import org.pinae.nala.xb.marshal.XMLMarshaller;
import org.pinae.nala.xb.util.ResourceWriter;

public class ListXMLMarshallerTest extends TestCase{
	private static final Logger log = Logger.getLogger(ListXMLMarshallerTest.class);
	
	/**
	 * 测试从List对象生成XML文件(编组)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testMarshal1() {
		List people = new ArrayList();
		people.add(org.pinae.nala.xb.resource.map.PersonOne.getObject());
		people.add(org.pinae.nala.xb.resource.map.PersonTwo.getObject());
		
		Marshaller marshaller = new XMLMarshaller(people);
		marshaller.setNodeTag("people");
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
	
	/**
	 * 测试从List对象生成XML文件(编组)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testMarshal2() {
		List people = new ArrayList();
		people.add(org.pinae.nala.xb.resource.bean.PersonOne.getObject());
		people.add(org.pinae.nala.xb.resource.bean.PersonTwo.getObject());
		
		Marshaller marshaller = new XMLMarshaller(people);
		marshaller.setNodeTag("people");
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
	
	/**
	 * 测试从List对象生成XML文件(编组)
	 */
	@SuppressWarnings("rawtypes")
	public void testMarshal3() {
		List people = new ArrayList();
		
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
	
	/**
	 * 测试从List对象生成XML文件(编组)
	 */
	public void testMarshal4() {
		List<String> list = new ArrayList<String>();
		
		list.add("Item1");
		list.add("Item2");
		list.add("Item3");
		
		Marshaller marshaller = new XMLMarshaller(list);
		marshaller.setNodeTag("list");
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
