package com.taotao.service;

import com.taotao.result.TaotaoResult;

public interface ContentService {
	
	//获取商品内容
	public TaotaoResult getContentById(Long cid);
}
