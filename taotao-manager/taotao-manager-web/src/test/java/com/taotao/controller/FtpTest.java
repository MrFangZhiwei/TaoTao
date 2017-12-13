package com.taotao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import com.taotao.common.util.FtpUtil;

public class FtpTest
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

		FileInputStream inputStream = new FileInputStream(new File("D:/1.png"));
		FtpUtil.uploadFile("192.168.1.101", 21, "ftpuser", "ftpuser", "/home/ftpuser/www/images", "/2016/11/27","hello.png", inputStream);
	}

}
