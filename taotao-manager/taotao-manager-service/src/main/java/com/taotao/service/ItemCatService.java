package com.taotao.service;

import java.util.List;

import com.taotao.result.EasyUITreeNode;

/**
 * 商品类目服务
 * @author admin
 *
 */
public interface ItemCatService {
	//商品类目获取
	public List<EasyUITreeNode> getItemCatList(long parentId);

}