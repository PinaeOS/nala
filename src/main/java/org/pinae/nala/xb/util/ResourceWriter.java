package org.pinae.nala.xb.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.pinae.nala.xb.exception.NoSuchPathException;


/**
 * XML内容写入工具
 * 
 * @author Huiyugeng
 *
 */
public class ResourceWriter {
	
	/**
	 * 将XML内容写入文件
	 * 
	 * @param content XML内容
	 * @param filename 需要写入的文件
	 * 
	 * @throws NoSuchPathException  写入文件时, 无法发现路径引发的异常
	 * @throws IOException 文件写入异常
	 */
	public void writeToFile(StringBuffer content, String filename) throws NoSuchPathException, IOException{
		try {
			FileWriter input = new FileWriter(filename);
			input.write(content.toString(), 0, content.length());
			input.flush();
			input.close();
		} catch (FileNotFoundException e) {
			throw new NoSuchPathException(e);
		}
	}
	
	/**
	 * 将XML内容写入文件
	 * 
	 * @param content XML内容
	 * @param filename 需要写入的文件
	 * @param encoding 文件编码
	 * 
	 * @throws NoSuchPathException  写入文件时, 无法发现路径引发的异常
	 * @throws IOException 文件写入异常
	 */
	public void writeToFile(StringBuffer content, String filename, String encoding) throws NoSuchPathException, IOException{
		try {
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filename), encoding);
			writer.write(content.toString(), 0, content.length());
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			throw new NoSuchPathException(e);
		}
	}
}
