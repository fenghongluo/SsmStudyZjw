package com.taotao.controller;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.result.EasyUITreeNode;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ContentCatgoryService;

@Controller
public class ContentCatgoryController {
	@Autowired
	private ContentCatgoryService contentCatgoryService;
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatGory(@RequestParam(value = "id",defaultValue = "0")
													Long categoryId){
		return contentCatgoryService.getContentCatList(categoryId);

	}
	
	@RequestMapping("/content/category/delete/")
	@ResponseBody
	public TaotaoResult deleteContentCatGory(@RequestParam(value = "id",defaultValue = "0")
												Long id){
		return contentCatgoryService.deleteContentCatgory(id);
	}
	
	
	@RequestMapping("content/category/create")
	@ResponseBody
	public TaotaoResult addContentCatGory(Long parentId,String name){
		
		return contentCatgoryService.addContentCatgory(parentId, name);

	}
	@RequestMapping("content/category/update")
	@ResponseBody
	public TaotaoResult updateContentCatGory(Long id,String name){
		
		return contentCatgoryService.updateContentCatgory(id, name);

	}
}
