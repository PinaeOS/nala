package org.pinae.nala.xb.marshaller;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.pinae.nala.xb.TestConstant;
import org.pinae.nala.xb.exception.MarshalException;
import org.pinae.nala.xb.exception.NoSuchPathException;
import org.pinae.nala.xb.marshal.Marshaller;
import org.pinae.nala.xb.marshal.XmlMarshaller;
import org.pinae.nala.xb.util.ResourceWriter;

/**
 * ListMarshaller单元测试
 * 
 * @author Huiyugeng
 */
public class ListXmlMarshallerTest {

	private static final Logger logger = Logger.getLogger(ListXmlMarshallerTest.class);

	/**
	 * 测试从List对象生成XML文件(编组)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testMarshal1() {
		List people = new ArrayList();
		people.add(org.pinae.nala.xb.data.map.PersonOne.getObject());
		people.add(org.pinae.nala.xb.data.map.PersonTwo.getObject());

		Marshaller marshaller = new XmlMarshaller(people);
		marshaller.setNodeTag("people");
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

	/**
	 * 测试从List对象生成XML文件(编组)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testMarshal2() {
		List people = new ArrayList();
		people.add(org.pinae.nala.xb.data.bean.PersonOne.getObject());
		people.add(org.pinae.nala.xb.data.bean.PersonTwo.getObject());

		Marshaller marshaller = new XmlMarshaller(people);
		marshaller.setNodeTag("people");
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

	/**
	 * 测试从List对象生成XML文件(编组)
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testMarshal3() {
		List people = new ArrayList();

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

	/**
	 * 测试从List对象生成XML文件(编组)
	 */
	@Test
	public void testMarshal4() {
		List<String> list = new ArrayList<String>();

		list.add("Item1");
		list.add("Item2");
		list.add("Item3");

		Marshaller marshaller = new XmlMarshaller(list);
		marshaller.setNodeTag("list");
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
