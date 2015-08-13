package org.pinae.nala.xb.unmarshaller;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.pinae.nala.xb.NalaTestConstant;
import org.pinae.nala.xb.exception.NoSuchPathException;
import org.pinae.nala.xb.exception.UnmarshalException;
import org.pinae.nala.xb.unmarshal.Unmarshaller;
import org.pinae.nala.xb.unmarshal.XmlUnmarshaller;
import org.pinae.nala.xb.util.ResourceReader;


/**
 * org.pinae.nala.xb.unmarshaller测试
 * 
 * @author Huiyugeng
 * 
 */
public class MapUnmarshallerTest extends TestCase {
	private static final Logger log = Logger.getLogger(MapUnmarshallerTest.class);

	/**
	 * 将XML绑定为Map
	 */
	@SuppressWarnings("rawtypes")
	public void testBindMap1(){
		Unmarshaller bind = null;
		try {
			bind = new XmlUnmarshaller(new ResourceReader().getFileStream(NalaTestConstant.TEST_XMLFILE3));
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}
		bind.setRootClass(Map.class);
		Map map = new HashMap();
		try {
			map = (Map)bind.unmarshal();
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}
		assertEquals(map.size(), 5);
	}
	
	/**
	 * 将XML绑定为Map
	 */
	@SuppressWarnings("rawtypes")
	public void testBindMap2(){
		Unmarshaller bind = null;
		try {
			bind = new XmlUnmarshaller(new ResourceReader()
					.getFileStream(NalaTestConstant.TEST_XMLFILE2));
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}
		bind.setRootClass(Map.class);
		Map map = new HashMap();
		try {
			map = (Map)bind.unmarshal();
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}
		assertEquals(map.size(), 1);
	}
	
	/**
	 * 将XML绑定为Map(中文实例)
	 */
	@SuppressWarnings("rawtypes")
	public void testBindMap3(){
		Unmarshaller bind = null;
		try {
			bind = new XmlUnmarshaller(new ResourceReader()
					.getFileStream(NalaTestConstant.TEST_ENCODING, "GBK"));
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}
		bind.setRootClass(Map.class);
		Map map = new HashMap();
		try {
			map = (Map)bind.unmarshal();
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}
		assertEquals(map.size(), 1);
	}
	
	/**
	 * 将XML绑定为Map(CDATA)
	 */
	@SuppressWarnings("rawtypes")
	public void testBindMap4(){
		Unmarshaller bind = null;
		try {
			bind = new XmlUnmarshaller(new ResourceReader()
					.getFileStream(NalaTestConstant.TEST_CDATA, "GBK"));
		} catch (NoSuchPathException e) {
			fail(e.getMessage());
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}
		bind.setRootClass(Map.class);
		Map map = new HashMap();
		try {
			map = (Map)bind.unmarshal();
		} catch (UnmarshalException e) {
			fail(e.getMessage());
		}
		assertEquals(map.size(), 1);
	}
}
