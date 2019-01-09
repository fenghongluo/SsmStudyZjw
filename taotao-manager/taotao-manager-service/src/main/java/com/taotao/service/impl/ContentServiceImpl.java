package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.result.EasyUIDataGridResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ContentService;

import utils.IDUtils;
@Service
public class ContentServiceImpl  implements ContentService{
	@Autowired
	private TbContentMapper contentMapper;
	@Override
	public EasyUIDataGridResult getContentList(Long catgoryId, int page, int rows) {
		// TODO Auto-generated method stub
		//分页处理
		PageHelper.startPage(page, rows);
		//执行查询
		TbContentExample example = new TbContentExample();
		TbContentExample.Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(catgoryId);
		List<TbContent> list = contentMapper.selectByExample(example);
		//取分页信息
		PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
		//返回处理结果
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
		
	}
	@Override
	public TaotaoResult addContent(TbContent content) {
		// TODO Auto-generated method stub
		content.setId(IDUtils.genItemId());
		Date date = new Date();
		content.setUpdated(date);
		content.setCreated(date);
		contentMapper.insert(content);
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult updateContent(TbContent content) {
		// TODO Auto-generated method stub
		contentMapper.updateByPrimaryKeySelective(content);
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult deleteContent(Long[] ids) {
		// TODO Auto-generated method stub
		for (Long id : ids) {
			contentMapper.deleteByPrimaryKey(id);
		}
		return TaotaoResult.ok();
	}

}
