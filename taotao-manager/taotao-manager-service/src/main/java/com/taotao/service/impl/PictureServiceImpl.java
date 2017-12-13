package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.util.FtpUtil;
import com.taotao.common.util.IDUtils;
import com.taotao.service.PictureService;

@Service
public class PictureServiceImpl implements PictureService
{
	/**
	 * 图片上传服务
	 * <p>Title: uploadPicture</p>
	 * <p>Description: </p>
	 * @param uploadFile
	 * @return
	 */

	//加载Resourse.properties文件
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;

	@Override
	public Map uploadPicture(MultipartFile uploadFile)
	{

		Map resultMap = new HashMap<>();
		try
		{
			//生成一个新的文件名
			//取得文件的原始名
			String oldName = uploadFile.getOriginalFilename();
			//生成新文件名
			//UUID.randomUUID();
			String newName = IDUtils.genImageName();
			newName = newName + oldName.substring(oldName.lastIndexOf("."));
			//图片上传
			boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH,
					new DateTime().toString("/yyyy/MM/dd"), newName, uploadFile.getInputStream());
			String imagePath = new DateTime().toString("/yyyy/MM/dd");
			if (!result)
			{
				resultMap.put("error", 1);
				resultMap.put("message", "文件上传出错");
			}
			resultMap.put("error", 0);
			resultMap.put("url", IMAGE_BASE_URL+imagePath+"/" + newName);
			return resultMap;
		} catch (Exception e)
		{
			resultMap.put("error", 1);
			resultMap.put("message", "文件上传发生异常");
			return resultMap;
		}
	}

}
