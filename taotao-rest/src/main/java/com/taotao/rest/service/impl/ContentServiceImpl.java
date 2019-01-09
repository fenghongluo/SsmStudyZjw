package com.taotao.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ContentService;
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper  contentMapper ;
	@Override
	public TaotaoResult getContentById(Long cid) {
		// TODO Auto-generated method stub
		TbContentExample example = new TbContentExample();
		TbContentExample.Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		return TaotaoResult.build(200, "", list);
	}

}
