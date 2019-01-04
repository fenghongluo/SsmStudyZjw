package com.taotao.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.taotao.result.PictureResult;
import com.taotao.service.PictrueService;

import utils.FtpUtils;
@Service
public class PictrueServiceImpl implements PictrueService {
	//获取配置文件的ftp信息
	@Value("${ftp.url}")
	private String ftpUrl;
	@Value("${ftp.host}")
	private int port;  
	@Value("${ftp.user}")
	private String ftpUSer ;
	@Value("${ftp.password}")
	private String ftpPassWord ;
	@Value("${ftp.pictrueroot}")
	private String pictrueRoot ;
	@Value("${IMAGE_URL}")
	private String IMAGE_URL ;
	
	@Override
	public PictureResult uploadPic(MultipartFile picFile,String subPath) {
		// TODO Auto-generated method stub
		//定义图片上传的路径
		String  picPath = pictrueRoot+ subPath;
		//判断图片信息是否为空
		PictureResult pictureResult = new PictureResult();
		if (picFile.isEmpty()) {
			pictureResult.setError(1);
			pictureResult.setMessage("图片为空");
		}
		InputStream input;
		try {
			input = picFile.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pictureResult.setError(1);
			pictureResult.setMessage("图片上传失败");
			return pictureResult;
		}
		//生成ftp文件名，名字组成为uuid+原文件后缀
		String originalFilename = picFile.getOriginalFilename();
		String ftpFileName = UUID.randomUUID().toString() + 
				originalFilename.substring(originalFilename.lastIndexOf("."));
		Boolean isUpLoaded = FtpUtils.uploadFile(ftpUrl, port, ftpUSer, ftpPassWord, picPath, ftpFileName, input);
		if (isUpLoaded) {
			pictureResult.setError(0);
			pictureResult.setMessage("图片上传成功");
			String url = IMAGE_URL+ picPath+"/"+ftpFileName;
			pictureResult.setUrl(url);
		}else {
			pictureResult.setError(1);
			pictureResult.setMessage("图片上传失败");
		}
		return pictureResult;
	}

}
