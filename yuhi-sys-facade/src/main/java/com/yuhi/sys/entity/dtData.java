package com.yuhi.sys.entity;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class dtData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	   private boolean success=true;
	    private Long totalRows;
	    private int curPage;
	    private List<JSONObject> data;
		public boolean isSuccess() {
			return success;
		}
		public void setSuccess(boolean success) {
			this.success = success;
		}
		public Long getTotalRows() {
			return totalRows;
		}
		public void setTotalRows(Long totalRows) {
			this.totalRows = totalRows;
		}
		public int getCurPage() {
			return curPage;
		}
		public void setCurPage(int curPage) {
			this.curPage = curPage;
		}
		public List<JSONObject> getData() {
			return data;
		}
		public void setData(List<JSONObject> data) {
			this.data = data;
		}
	
	
	
}
