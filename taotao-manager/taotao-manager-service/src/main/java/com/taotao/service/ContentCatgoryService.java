package com.taotao.service;

import java.util.List;

import com.taotao.result.EasyUITreeNode;
import com.taotao.result.TaotaoResult;

public interface ContentCatgoryService {
	//内容分类展示
	public List<EasyUITreeNode> getContentCatList(Long parentId) ;
	
	//内容分类增加
	public TaotaoResult addContentCatgory(Long parentId,String name) ;
	//内容分类删除
	public TaotaoResult deleteContentCatgory(Long Id) ;
	//内容分类修改
	public TaotaoResult updateContentCatgory(Long Id,String name) ;
}
