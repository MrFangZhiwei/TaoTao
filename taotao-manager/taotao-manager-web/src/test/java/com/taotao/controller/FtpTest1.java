package com.taotao.controller;

import com.taotao.common.util.FtpUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

public class FtpTest1
{
	/**
	 * 测试程序
	 * <p>Title: testFtpUtil</p>
	 * <p>Description: </p>
	 * @throws Exception
	 */
	@Test
	public void testFtpUtil() throws Exception
	{

		FileInputStream inputStream = new FileInputStream(new File("E:/2/1.jpg"));

		boolean b = FtpUtil.uploadFile("10.76.76.193", 21, "guest", "guest$123", "D:/出租电路开通计划/舒烨测试", "", "2221322.jpg", inputStream);
		System.out.println(b);
		//FTPClient ftp = new FTPClient();
		//ftp.setControlEncoding("GBK");
		//ftp.connect("10.76.76.193");
		//ftp.login("guest", "guest$123");// 登录
		//String path = "/D:/出租电路开通计划/舒烨测试";
		//boolean flag = ftp.changeWorkingDirectory(path);
		//System.out.println("path=" + flag);
		////指定上传文件的类型  二进制文件
		//ftp.setFileType(FTP.BINARY_FILE_TYPE);
		////读取本地文件
		//File file = new File("E:/2/1.txt");
		//InputStream local = new FileInputStream(file);
		////第一个参数是文件名
		//ftp.storeFile("11111.txt", local);
		//local.close();
		////退出
		//ftp.logout();
		////断开连接
		//ftp.disconnect();
		//FtpUtil.downloadFile("10.76.76.193",21,"guest","guest$123","/D:/出租电路开通计划/舒烨测试/","小方同学.jpg","E:/2/");
	}

}
