package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.result.EasyUITreeNode;
import com.taotao.service.ItemCatService;
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper ItemCatMapper;
	@Override
	public List<EasyUITreeNode> getItemCatList(long parentId) {
		// TODO Auto-generated method stub
		//根据parentId查询商品分类
		TbItemCatExample tbItemCatExample  = new TbItemCatExample();
		//设置查询条件
		Criteria criteria = tbItemCatExample.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//由mapper对象执行查询
		List<TbItemCat> list = ItemCatMapper.selectByExample(tbItemCatExample);
		//将返回的数据转换为easyuiTReeNode对象
		List<EasyUITreeNode> resultList = new ArrayList<EasyUITreeNode>();
		for (TbItemCat tbItemCat : list) {
			//创建一个节点对象
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			//添加到列表中
			resultList.add(node);

		}
		return resultList;
	}

}
