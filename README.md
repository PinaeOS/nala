#nala#

Nala is a xml binding library for Java

- Easy to integrated to any Java application
- Fastest XML binding to Object

## Installation ##

Direct Download (2015/7/10)

The lastest stable is nala-1.6.tar.gz (release notes)

Maven

	<dependency>
	    <groupId>org.pinae</groupId>
	    <artifactId>nala</artifactId>
	    <version>1.6</version>
	</dependency>


## Getting Start ##

demo for nala:

	public class DemoTestManager {
		public static void main(String arg[]) throws Exception {
			try {
				StringBuffer xml = new ResourceReader().readFile("test.xml");

				//Parse XML to Object
				Map map = Xml.toMap(xml.toString(), "utf8");
				//convert Object to XML 
				String xml = Xml.toXML(map, "utf8", true);
			} catch (IOException e) {
				
			}
		}
	}
	
## Documentation ##

Full documentation is hosted on [HERE](). 
Sources are available in the `docs/` directory.

## License ##

nala is licensed under the Apache License, Version 2.0 See LICENSE for full license text