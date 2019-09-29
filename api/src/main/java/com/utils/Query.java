package com.utils;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 */
public class Query extends LinkedHashMap<String, Object> {

	private static final long serialVersionUID = 1L;
	// 
	private Integer offset;
	// 每页条数
	private Integer limit;

	public  Query(){

	}

	public Query(Map<String, Object> params) {

		this.putAll(params);
		// 分页参数
		if (params.get("offset") != null){
			this.offset = Integer.parseInt(params.get("offset").toString());
			this.put("offset", offset);
		}
		if ( params.get("limit") != null){
			this.limit = Integer.parseInt(params.get("limit").toString());
			this.put("limit", limit);
		}
		if(params.get("offset") != null && params.get("limit") != null){
			this.put("page", offset / limit + 1);
		}
	}

	public Integer getOffset() {
		return offset;
	}


	public void setOffset(Integer offset) {
		this.put("offset", offset);
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
