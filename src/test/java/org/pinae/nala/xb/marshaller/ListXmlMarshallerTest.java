package org.pinae.nala.xb.marshaller;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.pinae.nala.xb.TestConstant;
import org.pinae.nala.xb.exception.MarshalException;
import org.pinae.nala.xb.exception.NoSuchPathException;
import org.pinae.nala.xb.marshal.Marshaller;
import org.pinae.nala.xb.marshal.XmlMarshaller;
import org.pinae.nala.xb.util.ResourceWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ListMarshaller单元测试
 * 
 * @author Huiyugeng
 */
public class ListXmlMarshallerTest {

	private static final Logger logger = LoggerFactory.getLogger(ListXmlMarshallerTest.class);

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
		marshaller.setDocumentStart("<!-- List to XML -->");

		try {
			new ResourceWriter().writeToFile(marshaller.marshal(), TestConstant.OUTPUT_XMLFILE);
			logger.debug(marshaller.marshal().toString());
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
		marshaller.setDocumentStart("<!-- List to XML -->");

		try {
			new ResourceWriter().writeToFile(marshaller.marshal(), TestConstant.OUTPUT_XMLFILE);
			logger.debug(marshaller.marshal().toString());
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
		List list = new ArrayList();

		Marshaller marshaller = new XmlMarshaller(list);
		marshaller.setDocumentStart("<!-- List to XML -->");

		try {
			new ResourceWriter().writeToFile(marshaller.marshal(), TestConstant.OUTPUT_XMLFILE);
			logger.debug(marshaller.marshal().toString());
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

		list.add("item1");
		list.add("item2");
		list.add("item3");

		Marshaller marshaller = new XmlMarshaller(list);
		marshaller.setNodeTag("list");
		marshaller.setDocumentStart("<!-- List to XML -->");

		try {
			new ResourceWriter().writeToFile(marshaller.marshal(), TestConstant.OUTPUT_XMLFILE);
			logger.debug(marshaller.marshal().toString());
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (MarshalException e) {
			fail(e.getMessage());
		}
	}
}
