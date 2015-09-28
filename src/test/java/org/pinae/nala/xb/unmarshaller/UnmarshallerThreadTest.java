package org.pinae.nala.xb.unmarshaller;

import org.apache.log4j.Logger;
import org.pinae.nala.xb.NalaTestConstant;
import org.pinae.nala.xb.data.bean.People;
import org.pinae.nala.xb.exception.NoSuchPathException;
import org.pinae.nala.xb.exception.UnmarshalException;
import org.pinae.nala.xb.unmarshal.Unmarshaller;
import org.pinae.nala.xb.unmarshal.XmlUnmarshaller;
import org.pinae.nala.xb.util.ResourceReader;


/**
 * Unmarshaller多线程测试
 * 
 * @author Huiyugeng
 *
 */
public class UnmarshallerThreadTest implements Runnable {
	
	private static final Logger log = Logger.getLogger(UnmarshallerThreadTest.class);
	private String thread_id;
	
	/**
	 * 设置线程编号
	 * 
	 * @param thread_id
	 */
	public UnmarshallerThreadTest(String thread_id){
		this.thread_id = thread_id;
	}
	
	/**
	 * 测试将XML解析程Java对象(多线程)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run(){
		while(true){
			Unmarshaller bind = null;
			try {
				bind = new XmlUnmarshaller(new ResourceReader().getFileStream(NalaTestConstant.TEST_XMLFILE1));
			} catch (NoSuchPathException e) {
				log.debug(e.getMessage());
			} catch (UnmarshalException e) {
				log.debug(e.getMessage());
			}
			bind.setRootClass(People.class);
			People people = new People();
			try {
				people = (People) bind.unmarshal();
			} catch (UnmarshalException e) {
				log.debug(e.getMessage());
			}
			log.debug(thread_id + ":" + people.getPerson().size());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				log.debug(e.getMessage());
			}
		}
	}
	
	/**
	 * 启动多线程测试
	 * 
	 * @param arg 启动参数
	 */
	public static void main(String[] arg){
		Thread t1= new Thread(new UnmarshallerThreadTest("1"));
		t1.start();
		Thread t2= new Thread(new UnmarshallerThreadTest("2"));
		t2.start();
	}
}
