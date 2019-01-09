package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.result.EasyUITreeNode;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ContentCatgoryService;

import utils.IDUtils;
@Service
public class ContentCatgoryServiceImpl implements ContentCatgoryService {

	@Autowired
	private TbContentCategoryMapper ContentCatgoryMapper;
	@Override
	public List<EasyUITreeNode> getContentCatList(Long parentId) {
		// TODO Auto-generated method stub
		//初始化查询条件
		TbContentCategoryExample example = new TbContentCategoryExample();
		TbContentCategoryExample.Criteria  criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = ContentCatgoryMapper.selectByExample(example);
		//定义返回信息
		List<EasyUITreeNode> result = new ArrayList<EasyUITreeNode>();
		for (TbContentCategory contentCategory : list) {
			EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
			easyUITreeNode.setId(contentCategory.getId());
			easyUITreeNode.setState(contentCategory.getIsParent()? "closed":"open" );
			easyUITreeNode.setText(contentCategory.getName());
			result.add(easyUITreeNode);
		}
		return result;
	}
	@Override
	public TaotaoResult addContentCatgory(Long parentId, String name) {
		// TODO Auto-generated method stub
		TbContentCategory category  = new TbContentCategory();
		category.setId(IDUtils.genItemId());
		category.setParentId(parentId);
		category.setName(name);
		category.setStatus(1);
		category.setSortOrder(1);
		category.setIsParent(false);
		Date date = new Date();
		category.setCreated(date);
		category.setUpdated(date);
		ContentCatgoryMapper.insert(category);
		//更细父节点状态标志
		TbContentCategory pCategorycategory = ContentCatgoryMapper.selectByPrimaryKey(parentId);
		pCategorycategory.setIsParent(true);
		ContentCatgoryMapper.updateByPrimaryKeySelective(pCategorycategory);
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult deleteContentCatgory(Long id) {
		// TODO Auto-generated method stub
		ContentCatgoryMapper.deleteByPrimaryKey(id);
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult updateContentCatgory(Long id, String name) {
		// TODO Auto-generated method stub
		TbContentCategory categorycategory = ContentCatgoryMapper.selectByPrimaryKey(id);
		categorycategory.setName(name);
		Date date = new Date();
		categorycategory.setUpdated(date);
		ContentCatgoryMapper.updateByPrimaryKey(categorycategory);
		return TaotaoResult.ok();
	}
}
