package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbContent;
import com.taotao.result.EasyUIDataGridResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ContentService;

import utils.CastUtils;
@Controller
public class ContentController {
	@Autowired 
	private ContentService contentService;
	
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Long categoryId,Integer page, Integer rows) {
		EasyUIDataGridResult result = contentService.getContentList(categoryId,page, rows);
		return result;
	} 
	
	@RequestMapping("/content/save")
	@ResponseBody
	public TaotaoResult addContent(TbContent content) {
		return contentService.addContent(content);
	}
	
	@RequestMapping("/content/delete")
	@ResponseBody
	public TaotaoResult deleteContent(@RequestParam( value =  "ids",defaultValue = "")String ids) {
		Long[] idsArray = CastUtils.ConvertToLong(ids.split(","));
		return contentService.deleteContent(idsArray);
	}
	
	@RequestMapping("/rest/content/edit")
	@ResponseBody
	public TaotaoResult updateContent(TbContent content) {
		
		return contentService.updateContent(content);
	}
}
