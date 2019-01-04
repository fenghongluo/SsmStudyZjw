package com.taotao.service.impl;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemDescExample;
import com.taotao.pojo.TbItemDescExample.Criteria;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.result.EasyUIDataGridResult;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemService;

import utils.IDUtils;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;
	
	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
				//分页处理
				PageHelper.startPage(page, rows);
				//执行查询
				TbItemExample example = new TbItemExample();
				List<TbItem> list = itemMapper.selectByExample(example);
				//取分页信息
				PageInfo<TbItem> pageInfo = new PageInfo<>(list);
				//返回处理结果
				EasyUIDataGridResult result = new EasyUIDataGridResult();
				result.setTotal(pageInfo.getTotal());
				result.setRows(list);
				return result;
	}
	@Override
	public TaotaoResult addItem(TbItem item, String desc,String itemParams) {
		// TODO Auto-generated method stub
		// 生成商品id
				long itemId = IDUtils.genItemId();
				// 补全TbItem属性
				item.setId(itemId);
				// '商品状态，1-正常，2-下架，3-删除'
				item.setStatus((byte) 1);
				// 创建时间和更新时间
				Date date = new Date();
				item.setCreated(date);
				item.setUpdated(date);
				// 插入商品表
				itemMapper.insert(item);
				// 商品描述
				TbItemDesc itemDesc = new TbItemDesc();
				itemDesc.setItemId(itemId);
				itemDesc.setItemDesc(desc);
				itemDesc.setCreated(date);
				itemDesc.setUpdated(date);
				// 插入商品描述数据
				itemDescMapper.insert(itemDesc);
				//插入商品参数
				TbItemParamItem itemParamItem = new TbItemParamItem();
				itemParamItem.setId(IDUtils.genItemId());
				itemParamItem.setItemId(itemId);
				itemParamItem.setCreated(date);
				itemParamItem.setUpdated(date);
				itemParamItem.setParamData(itemParams);
				itemParamItemMapper.insert(itemParamItem);
				return TaotaoResult.ok();
				
	}
	@Override
	public TaotaoResult deleteItem(Long[] ids) {
		// TODO Auto-generated method stub
		for (Long id : ids) {
			itemMapper.deleteByPrimaryKey(id);
		}
		return TaotaoResult.ok();
	}
	@Override
	public TaotaoResult getItemDesc(Long itemId) {
		// TODO Auto-generated method stub
		TaotaoResult result;
		TbItemDescExample example = new TbItemDescExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemDesc> list = itemDescMapper.selectByExampleWithBLOBs(example);
		if (list.size() == 0 ) {
			result = new TaotaoResult(200,"",null);
		}else{
			result = new TaotaoResult(200,"",list.get(0));
		}
		return result;
	}
	@Override
	public TaotaoResult getItemParam(Long itemId) {
		// TODO Auto-generated method stub
		TaotaoResult result;
		TbItemParamItemExample example = new TbItemParamItemExample();
		TbItemParamItemExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list.size() == 0 ) {
			result = new TaotaoResult(200,"",null);
		}else{
			result = new TaotaoResult(200,"",list.get(0));
		}
		return result;
	}
	@Override
	public TaotaoResult updateItem(TbItem item, String desc, String paramsData) {
		// TODO Auto-generated method stub
		//更新商品信息
		Date date = new Date();
		item.setUpdated(date);
		itemMapper.updateByPrimaryKeySelective(item);
		
		//更新商品描述
		Long itemId = item.getId();
		TbItemDescExample exampleDesc = new TbItemDescExample();
		TbItemDescExample.Criteria criteriaDesc = exampleDesc.createCriteria();
		criteriaDesc.andItemIdEqualTo(itemId);
		List<TbItemDesc> itemDescList = itemDescMapper.selectByExampleWithBLOBs(exampleDesc);
		if (itemDescList.size() > 0 ) {
			TbItemDesc itemDesc = itemDescList.get(0);
			itemDesc.setItemDesc(desc);
			itemDesc.setUpdated(date);
			itemDescMapper.updateByExampleWithBLOBs(itemDesc, exampleDesc);
		}
		
		//更新商品参数
		TbItemParamItemExample exampleParam = new TbItemParamItemExample();
		TbItemParamItemExample.Criteria criteriaParam = exampleParam.createCriteria();
		criteriaParam.andItemIdEqualTo(itemId);
		List<TbItemParamItem> listParam = itemParamItemMapper.selectByExampleWithBLOBs(exampleParam);
		if (listParam.size() > 0 ) {
			TbItemParamItem itemParamitem = listParam.get(0);
			itemParamitem.setParamData(paramsData);
			itemParamitem.setUpdated(date);
			itemParamItemMapper.updateByExampleWithBLOBs(itemParamitem, exampleParam);
		}
		return TaotaoResult.ok();
	}

}
