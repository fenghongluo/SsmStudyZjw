package com.taotao.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.pojo.TbItem;
import com.taotao.result.EasyUIDataGridResult;
import com.taotao.result.PictureResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemService;
import com.taotao.service.PictrueService;

import utils.CastUtils;
import utils.JsonUtils;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	@Autowired
	private PictrueService pictrueService;
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	} 
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String ItemUploadPic(MultipartFile  uploadFile) {
		String subPath = "/itemPic";
		PictureResult pictureResult = pictrueService.uploadPic(uploadFile, subPath);
		String json = JsonUtils.objectToJson(pictureResult);
		return json;
	} 
	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult addItem(TbItem item ,String desc,String itemParams) {
		TaotaoResult taotaoResult = itemService.addItem(item, desc ,itemParams);
		return taotaoResult;
	}
	
	@RequestMapping("/rest/item/delete")
	@ResponseBody
	public TaotaoResult delete(@RequestParam( value =  "ids",defaultValue = "")String ids){
		Long[] idsArray = CastUtils.ConvertToLong(ids.split(","));
		return itemService.deleteItem(idsArray);  
			 
	}
	//获取商品的desc
	@RequestMapping("/rest/item/query/item/desc/{itemId}")
	@ResponseBody
	public TaotaoResult getItemDesc (@PathVariable Long itemId) {
		return itemService.getItemDesc(itemId);
	}
	//获取商品的param
	@RequestMapping("/rest/item/param/item/query/{itemId}")
	@ResponseBody
	public TaotaoResult getItemParam (@PathVariable Long itemId) {
		return itemService.getItemParam(itemId);
	}

	//修改商品信息
	@RequestMapping("/rest/item/update")
	@ResponseBody
	public TaotaoResult updateItem(TbItem item ,String desc,String itemParams) {
		TaotaoResult taotaoResult = itemService.updateItem(item, desc ,itemParams);
		return taotaoResult;
	}
}
