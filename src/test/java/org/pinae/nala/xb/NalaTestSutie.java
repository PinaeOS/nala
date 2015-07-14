package org.pinae.nala.xb;

import java.util.Enumeration;

import junit.framework.Test;
import junit.framework.TestFailure;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;
import org.pinae.nala.xb.marshaller.AnnotationXMLMarshallerTest;
import org.pinae.nala.xb.marshaller.ListXMLMarshallerTest;
import org.pinae.nala.xb.marshaller.MapXMLMarshallerTest;
import org.pinae.nala.xb.marshaller.XMLMarshallerTest;
import org.pinae.nala.xb.unmarshaller.AnnotationXMLUnmarshaller;
import org.pinae.nala.xb.unmarshaller.MapUnmarshallerTest;
import org.pinae.nala.xb.unmarshaller.XMLUnmarshallerTest;
import org.pinae.nala.xb.unmarshaller.XPathUnmarshallerTest;


/**
 * Nala测试集合
 * 
 * @author Huiyugeng
 *
 */
public class NalaTestSutie{
	private static final Logger logger = Logger.getLogger(NalaTestSutie.class);
	
	/**
	 * 测试所有用例
	 *
	 */
	public static Test suite(){
		TestSuite suite = new TestSuite("Test Nala");
		//测试注释形式的对象生成XML
		suite.addTestSuite(AnnotationXMLMarshallerTest.class);
		//测试Map生成XML
		suite.addTestSuite(MapXMLMarshallerTest.class);
		//测试List生成XML
		suite.addTestSuite(ListXMLMarshallerTest.class);
		//测试对象生成XML
		suite.addTestSuite(XMLMarshallerTest.class);
		
		//测试XML映射注释形式的对象
		suite.addTestSuite(AnnotationXMLUnmarshaller.class);
		//测试XML映射Map
		suite.addTestSuite(MapUnmarshallerTest.class);
		//测试XML映射对象
		suite.addTestSuite(XMLUnmarshallerTest.class);
		//测试采用XPath映射对象
		suite.addTestSuite(XPathUnmarshallerTest.class);
		
		return suite;
	}
	
	public static void main(String arg[]){
		
		TestResult result = new TestResult();
		suite().run(result);
		
		logger.info(result.runCount() + " tests execute");
		
		if(result.wasSuccessful()){
			logger.info("All Test Pass!");
		}else{
			logger.error("Test No Pass");
			
			Enumeration<TestFailure> failures = result.failures();
			if(failures.hasMoreElements()){
				TestFailure failure = failures.nextElement();
				logger.error("Failure:" + failure.exceptionMessage());
			}
			
			Enumeration<TestFailure> errors = result.errors();
			if(errors.hasMoreElements()){
				TestFailure error = errors.nextElement();
				logger.error("Error: " + error.exceptionMessage());
			}
		}
	}
}
