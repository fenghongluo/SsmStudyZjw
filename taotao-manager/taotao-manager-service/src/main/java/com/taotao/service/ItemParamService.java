package com.taotao.service;

import com.taotao.result.EasyUIDataGridResult;
import com.taotao.result.TaotaoResult;

public interface ItemParamService {
	//获取商品列表
	public EasyUIDataGridResult getItemParamList(int page, int rows);
	
	//验证当前条目是否已经存在参数模板
	public TaotaoResult isExistParam(Long itemcatId);
	
	//添加商品类目参数模板
	public TaotaoResult addParamToItemCat(Long itemcatId,String paramData);
	
	//删除商品类目的模板
	public TaotaoResult deleteParam(Long[] ids);
}
