package com.utils;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 */
public class mallQuery extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	//
	private Integer offset;
	// 每页条数
	private Integer limit;

	private Integer pageNum;

    public mallQuery(Map<String, Object> params) {
        this.putAll(params);
        // 分页参数
        this.pageNum = Integer.parseInt(params.get("pageNum").toString());
        this.limit = Integer.parseInt(params.get("pageSize").toString());
        this.put("offset", (pageNum - 1) * limit);
        this.put("limit", limit);
        this.remove("pageNum");
        this.remove("pageSize");

    }

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.put("offset", (pageNum-1)*limit);
	}


	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

}
