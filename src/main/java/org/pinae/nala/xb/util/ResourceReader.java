package org.pinae.nala.xb.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import org.pinae.nala.xb.exception.NoSuchPathException;
import org.pinae.nala.xb.exception.UnmarshalException;


/**
 * 读取资源内容
 * 
 * @author Huiyugeng
 * 
 */
public class ResourceReader {
	/**
	 * 将文件读出为输出流
	 * 
	 * @param filename 需要读取的文件名称
	 * 
	 * @return 文件内容输出流
	 * @throws NoSuchPathException 搜索文件时, 无法发现路径引发的异常
	 * @throws FileNotFoundException 搜索文件时, 无法发现文件的异常
	 */
	public InputStreamReader getFileStream(String filename)
			throws NoSuchPathException{
		try {
			return new InputStreamReader(new FileInputStream(filename));
		} catch (FileNotFoundException e) {
			throw new NoSuchPathException(e);
		}
	}
	
	/**
	 * 将文件读出为输出流
	 * 
	 * @param filename 需要读取的文件名称
	 * @param encoding 文件编码
	 * 
	 * @return 文件内容输出流
	 * @throws NoSuchPathException 搜索文件时, 无法发现路径引发的异常
	 * @throws FileNotFoundException 搜索文件时, 无法发现文件的异常
	 */
	public InputStreamReader getFileStream(String filename, String encoding)
			throws NoSuchPathException, UnmarshalException{
		try {
			return new InputStreamReader(new FileInputStream(filename), encoding);
		} catch (FileNotFoundException e) {
			throw new NoSuchPathException(e);
		} catch (UnsupportedEncodingException e){
			throw new UnmarshalException(e);
		}
	}

	/**
	 * 将URL资源读出为输出流
	 * 
	 * @param path URL资源
	 * @return 资源内容输出流
	 * @throws NoSuchPathException 搜索URL等资源时, 无法发现路径引发的异常
	 * @throws IOException 读写URL资源引发的异常
	 */
	public InputStream getURLStream(String path) throws NoSuchPathException,
			IOException {
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			throw new NoSuchPathException(e);
		}
		if (url != null) {
			return url.openStream();
		}
		return null;
	}
}
