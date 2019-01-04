package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureTask;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.result.EasyUIDataGridResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemParamService;

import utils.IDUtils;
@Service
public class ItemParamServiceImpl implements ItemParamService{
	@Autowired
	private TbItemParamMapper itemParamMapper;
	@Override
	public EasyUIDataGridResult getItemParamList(int page, int rows) {
		// TODO Auto-generated method stub
		//分页处理
		PageHelper.startPage(page, rows);
		//执行查询
		TbItemParamExample example = new TbItemParamExample();
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		//取分页信息
		PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);
		//返回处理结果
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		return result;
		
	}
	@Override
	public TaotaoResult isExistParam(Long itemcatId) {
		// TODO Auto-generated method stub
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(itemcatId);
		List<TbItemParam> list= itemParamMapper.selectByExampleWithBLOBs(example);
		TaotaoResult result = null;
		if (list.size() != 0) {
			result = new TaotaoResult(200, "", list.get(0));
		}else {
			result = new TaotaoResult(-100, "", false);
		}
		return result;
	}
	@Override
	public TaotaoResult addParamToItemCat(Long itemcatId,String paramData) {
		// TODO Auto-generated method stub
		TaotaoResult result = isExistParam(itemcatId);
		if (result.getStatus() == 200) {
			result = new TaotaoResult(-100,"该类目已经存在参数模板",null);
			return result;
		}
		TbItemParam itemParam = new TbItemParam();
		itemParam.setId(IDUtils.genItemId());
		itemParam.setItemCatId(itemcatId);
		Date date = new Date();
		itemParam.setCreated(date);
		itemParam.setUpdated(date);
		itemParam.setParamData(paramData);
		itemParamMapper.insert(itemParam);
		result = new TaotaoResult(200,"",true);
		return result;
	}
	@Override
	public TaotaoResult deleteParam(Long[] ids) {
		// TODO Auto-generated method stub
		for (Long id : ids) {
			itemParamMapper.deleteByPrimaryKey(id);
		}
		return TaotaoResult.ok();
	}

}
