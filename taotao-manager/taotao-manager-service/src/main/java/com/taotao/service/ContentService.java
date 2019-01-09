package com.taotao.service;

import org.springframework.stereotype.Service;

import com.taotao.pojo.TbContent;
import com.taotao.result.EasyUIDataGridResult;
import com.taotao.result.TaotaoResult;

@Service
public interface ContentService {

	//获取内容列表
	public EasyUIDataGridResult getContentList(Long categoryId,int page, int rows);
	
	//添加内容
	public TaotaoResult addContent(TbContent content);
	
	//更新内容
	public TaotaoResult updateContent(TbContent content);
	//删除内容
	
	public TaotaoResult deleteContent(Long[] ids);
}
