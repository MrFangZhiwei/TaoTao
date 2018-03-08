package com.taotao.controller;

import com.taotao.common.util.FtpUtil;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;


public class FTPTestDemo {
	@Test
	public void testFtpClient() throws SocketException, IOException {
		FTPClient ftpClient = new FTPClient();
		
		try {
			// 连接FTP服务器
			ftpClient.connect("103.202.99.238", 21);
			// 登录FTP服务器
			ftpClient.login("test", "KEYONE123cn");
			// 将文件转换为IO
			FileInputStream fileInputStream = new FileInputStream(new File("F:\\1.png"));
			// 指定上传远程目录
			ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");// 绝对路径
			// ftpClient.changeWorkingDirectory("www/images");//相对路径
			// 设置上传文件类型
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			// 上传文件并指定远程文件名
			// ftpClient.storeFile("中文名.jpg", fileInputStream);//中文会出现乱码
			ftpClient.storeFile("a.png", fileInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 退出登录
			ftpClient.logout();
			// 断开连接
			ftpClient.disconnect();
		}

	}

	@Test
	public void testFTPUtil() {
		try {
			FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\a\\Pictures\\16-040836_50.jpg"));
			String filename=new String("中文名2.jpg".getBytes("utf-8"),"iso-8859-1");//解决中文文件名乱码
			FtpUtil.uploadFile("192.168.1.110", 21, "ftpuser", "123", "/home/ftpuser/www/images", "/2016/09/26",filename ,
					fileInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}