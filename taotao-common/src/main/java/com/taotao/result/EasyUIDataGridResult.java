package com.taotao.result;

import java.util.List;
/**
 * 商品列表返回值
 * @author admin
 *
 */
public class EasyUIDataGridResult {
	//条目总数
	private long total;
	//结果集合
	private List<?> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
