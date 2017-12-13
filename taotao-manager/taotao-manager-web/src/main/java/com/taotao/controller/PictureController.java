package com.taotao.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.util.JsonUtils;
import com.taotao.service.PictureService;

/**
 * 上传图片处理
 * <p>Title: PictureController</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2016-11-27下午2:03:04
 * @version  1.0
 */
@Controller
public class PictureController
{
	@Autowired
	private PictureService pictureService;
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String pictureUpload(MultipartFile uploadFile){
		Map result = pictureService.uploadPicture(uploadFile);
		//为了保证功能的兼容新，需要把Result转换为json格式的功能工具
		String json=JsonUtils.objectToJson(result);
		return json;
	}
}
