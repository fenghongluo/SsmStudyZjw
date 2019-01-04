package com.taotao.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.taotao.result.EasyUIDataGridResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemParamService;

import utils.CastUtils;

@Controller
public class ItemParamController {
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/item/param/list")
	@ResponseBody
	public EasyUIDataGridResult getItemParamList(Integer page, Integer rows) {
		EasyUIDataGridResult result = itemParamService.getItemParamList(page, rows);
		return result;
	}
	
	//验证当前的商品类目是否已经含有参数信息
	@RequestMapping("/item/param/query/itemcatid/{itemcatId}")
	@ResponseBody
	public TaotaoResult isExistParam(@PathVariable Long itemcatId) {
		TaotaoResult result =  itemParamService.isExistParam(itemcatId);
		return result;
	}
	
	//添加参数模板
	@RequestMapping("/item/param/save/{itemcatId}")
	@ResponseBody
	public TaotaoResult AddParamToItemCat(@PathVariable Long itemcatId,
			@RequestParam(value = "paramData",defaultValue = "") String paramData) {
		TaotaoResult result =  itemParamService.addParamToItemCat(itemcatId, paramData);
		return result; 
	}
	//删除参数模板
	@RequestMapping("/item/param/delete")
	@ResponseBody
	public TaotaoResult delete(@RequestParam( value =  "ids",defaultValue = "")String ids){
		Long[] idsArray = CastUtils.ConvertToLong(ids.split(","));
		return itemParamService.deleteParam(idsArray);  
		 
	}
}
