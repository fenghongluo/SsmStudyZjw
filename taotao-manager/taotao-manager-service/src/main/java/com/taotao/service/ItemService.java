package com.taotao.service;

import com.taotao.pojo.TbItem;
import com.taotao.result.EasyUIDataGridResult;
import com.taotao.result.TaotaoResult;

public interface ItemService {
	//获取商品列表
	public EasyUIDataGridResult getItemList(int page, int rows);
	
	//添加商品
	public TaotaoResult addItem(TbItem item,String ItemDesc,String itemParams);
	
	//删除商品
	public TaotaoResult deleteItem(Long[] ids);
	
	//获取商品描述
	public TaotaoResult getItemDesc(Long itemId);
	
	//获取商品参数
	public TaotaoResult getItemParam(Long itemId);
	
	//更新商品
	public TaotaoResult updateItem(TbItem item,String ItemDesc,String itemParams);
}
