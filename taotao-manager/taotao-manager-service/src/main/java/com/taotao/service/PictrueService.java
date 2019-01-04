package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;
import com.taotao.result.PictureResult;

public interface PictrueService {
	public PictureResult uploadPic(MultipartFile picFile,String subPath);
}
