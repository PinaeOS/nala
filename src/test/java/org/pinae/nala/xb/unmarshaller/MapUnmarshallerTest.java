package org.pinae.nala.xb.unmarshaller;

import java.util.HashMap;
import java.util.Map;

import org.pinae.nala.xb.TestConstant;
import org.pinae.nala.xb.exception.NoSuchPathException;
import org.pinae.nala.xb.exception.UnmarshalException;
import org.pinae.nala.xb.unmarshal.Unmarshaller;
import org.pinae.nala.xb.unmarshal.XmlUnmarshaller;
import org.pinae.nala.xb.util.ResourceReader;

import junit.framework.TestCase;

/**
 * MapUnmarshaller单元测试
 * 
 * @author Huiyugeng
 * 
 */
public class MapUnmarshallerTest extends TestCase {

	/**
	 * 将XML绑定为Map
	 */
	@SuppressWarnings("rawtypes")
	public void testBindMap1(){
		Unmarshaller bind = null;
		try {
			bind = new XmlUnmarshaller(new ResourceReader().getFileStream(TestConstant.TEST_XMLFILE3));
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
					.getFileStream(TestConstant.TEST_XMLFILE2));
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
					.getFileStream(TestConstant.TEST_ENCODING, "GBK"));
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
					.getFileStream(TestConstant.TEST_CDATA, "GBK"));
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
