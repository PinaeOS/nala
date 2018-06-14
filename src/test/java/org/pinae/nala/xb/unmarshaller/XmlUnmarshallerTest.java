package org.pinae.nala.xb.unmarshaller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.pinae.nala.xb.TestConstant;
import org.pinae.nala.xb.data.bean.OrgList;
import org.pinae.nala.xb.data.bean.People;
import org.pinae.nala.xb.data.bean.PeopleInXml;
import org.pinae.nala.xb.data.bean.PersonList;
import org.pinae.nala.xb.exception.NoSuchPathException;
import org.pinae.nala.xb.exception.UnmarshalException;
import org.pinae.nala.xb.unmarshal.Unmarshaller;
import org.pinae.nala.xb.unmarshal.XmlUnmarshaller;
import org.pinae.nala.xb.util.ResourceReader;

/**
 * XmlUnmarshaller单元测试
 * 
 * @author Huiyugeng
 * 
 */
public class XmlUnmarshallerTest {

	/**
	 * 测试绑定整个XML
	 */
	@Test
	public void testBindObject() {
		People people = new People();

		try {
			Unmarshaller bind = new XmlUnmarshaller(new ResourceReader().getFileStream(TestConstant.TEST_XMLFILE1));

			bind.setRootClass(People.class);

			people = (People) bind.unmarshal();
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}

		assertEquals(people.getPerson().size(), 2);
	}

	/**
	 * 测试绑定整个XML
	 */
	@Test
	public void testBindObject2() {
		PersonList personList = new PersonList();

		try {
			Unmarshaller bind = new XmlUnmarshaller(new ResourceReader().getFileStream(TestConstant.TEST_XMLFILE4));

			bind.setRootClass(PersonList.class);

			personList = (PersonList) bind.unmarshal();
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}

		assertEquals(personList.getPerson().size(), 4);
	}

	/**
	 * 测试绑定整个XML
	 */
	@Test
	public void testBindObject3() {
		OrgList orgList = new OrgList();

		try {
			Unmarshaller bind = new XmlUnmarshaller(new ResourceReader().getFileStream(TestConstant.TEST_XMLFILE5, "GBK"));

			bind.setRootClass(OrgList.class);

			orgList = (OrgList) bind.unmarshal();
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}

		assertEquals(orgList.getOrgSize(), 10);
	}
	
	/**
	 * 测试在对象中绑定XML文本
	 */
	@Test
	public void testBindObjectInXml() {
		PeopleInXml people = new PeopleInXml();

		try {
			Unmarshaller bind = new XmlUnmarshaller(new ResourceReader().getFileStream(TestConstant.TEST_XMLFILE1));

			bind.setRootClass(PeopleInXml.class);

			people = (PeopleInXml) bind.unmarshal();
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}

		assertEquals(people.getPerson().size(), 2);
	}

}
