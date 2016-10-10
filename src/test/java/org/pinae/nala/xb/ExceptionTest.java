package org.pinae.nala.xb;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.junit.Test;
import org.pinae.nala.xb.exception.UnmarshalException;
import org.pinae.nala.xb.unmarshal.Unmarshaller;
import org.pinae.nala.xb.unmarshal.XmlUnmarshaller;

public class ExceptionTest {
	
	@Test
	public void testBlankXml() {
		String xml = "";
		
		try {
			Unmarshaller bind = new XmlUnmarshaller(new ByteArrayInputStream(xml.getBytes()));
			bind.setRootClass(Map.class);
			bind.unmarshal();
			fail("Can't cache exception");
		} catch (UnmarshalException e) {
			assertTrue(e.getMessage() != null);
		}
	}
}
