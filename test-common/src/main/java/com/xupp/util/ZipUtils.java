/**

 * FileName:     ZipUtil.java
 * @Description: TODO(用一句话描述该文件做什么)  
 *  All rights Reserved, Designed By DIST 
 * Copyright:    Copyright(C) 2010-2014 
 * Company     DIST
 * @author:    weifj
 * @version    V1.0  
 * Createdate:    2015-11-24 上午9:41:11  
 *
 * Modification  History:
 * Date         Author        Version        Discription
 * -----------------------------------------------------------------------------------
 *  2015-11-24       weifj          1.0             创建
 */
package com.xupp.util;

/**
 * 压缩工具类
 * @author Administrator
 *
 */


import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import java.io.File;
import java.io.IOException;

/**
 * @author 压缩指定的目录以及解压指定的压缩文件(仅限ZIP格式).
 */
public class ZipUtils {
	public final static String encoding = "GBK";

	/**
	 * 1.可以压缩目录(支持多级)<br>
	 * 2.可以压缩文件<br>
	 * 3.如果压缩文件的路径或父路径不存在, 将会自动创建<br>
	 * 
	 * @param src
	 *            将要进行压缩的目录
	 * @param zip
	 *            最终生成的压缩文件的路径
	 */
	public static void zip(File src, File dest) throws IOException {
		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setEncoding(encoding);
		zip.setDestFile(dest);
		FileSet fileSet = new FileSet();
		fileSet.setProject(prj);
		if (src.isFile()) {
			fileSet.setFile(src);
		} else {
			fileSet.setDir(src);
		}
		zip.addFileset(fileSet);
		zip.execute();
	}

	/**
	 * 1.可以压缩目录(支持多级)<br>
	 * 2.可以压缩文件<br>
	 * 3.如果压缩文件的路径或父路径不存在, 将会自动创建<br>
	 * 
	 * @param src
	 *            将要进行压缩的目录
	 * @param zip
	 *            最终生成的压缩文件的路径
	 */
	public static void zip(String src, String dest) throws IOException {
		Project prj = new Project();
		Zip zip = new Zip();
		zip.setProject(prj);
		zip.setEncoding(encoding);
		zip.setDestFile(new File(dest));
		FileSet fileSet = new FileSet();
		fileSet.setProject(prj);
		if (new File(src).isFile()) {
			fileSet.setFile(new File(src));
		} else {
			fileSet.setDir(new File(src));
		}
		zip.addFileset(fileSet);
		zip.execute();
	}

	/**
	 * 将指定的压缩文件解压到指定的目标目录下. 如果指定的目标目录不存在或其父路径不存在, 将会自动创建.
	 * 
	 * @param zip
	 *            将会解压的压缩文件
	 * @param dest
	 *            解压操作的目录目录
	 */
	public static void unzip(File src, File dest) throws IOException {
		Project proj = new Project();
		Expand expand = new Expand();
		expand.setProject(proj);
		expand.setTaskType("unzip");
		expand.setTaskName("unzip");
		expand.setSrc(src);
		expand.setDest(dest);
		expand.setEncoding(encoding);// 设置编码不能少，少了文件名会有乱码
		expand.execute();
	}

	public static void main(String[] args) {

		try {
			ZipUtils.zip(new File("D:\\test\\684"), new File("D:\\test\\684.zip"));

			// ZipUtils.unzip(new File("D:\\测试.zip"), new File("D:\\test\\"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
