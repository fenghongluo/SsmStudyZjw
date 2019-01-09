package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.result.TaotaoResult;
import com.taotao.service.ContentService;

@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	@RequestMapping("/content/{cid}")
	@ResponseBody
	public TaotaoResult getContent(@PathVariable Long cid) {
		return contentService.getContentById(cid);
	}
}
