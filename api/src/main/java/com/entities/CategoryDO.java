package com.entities;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author none
 * @email guanyimial@126.com
 * @date 2019-01-09 17:45:28
 */
public class CategoryDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer cid;
	//商品分类名称
	private String cName;
	//是否叶子节点 1叶子节点 2非叶子节点
	private Integer hasLeaf;
	//类目级别 1一级目录 2二级目录 3三级目录 只支持三级
	private Integer lev;
	//创建时间
	private Date createTime;
	//修改时间
	private Date updateTime;
	//父类id
	private Integer parentCid;
	//类别状态 1有效 2无效
	private Integer status;

	/**
	 * 设置：
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：商品分类名称
	 */
	public void setCName(String cName) {
		this.cName = cName;
	}
	/**
	 * 获取：商品分类名称
	 */
	public String getCName() {
		return cName;
	}
	/**
	 * 设置：是否叶子节点 1叶子节点 2非叶子节点
	 */
	public void setHasLeaf(Integer hasLeaf) {
		this.hasLeaf = hasLeaf;
	}
	/**
	 * 获取：是否叶子节点 1叶子节点 2非叶子节点
	 */
	public Integer getHasLeaf() {
		return hasLeaf;
	}
	/**
	 * 设置：类目级别 1一级目录 2二级目录 3三级目录 只支持三级
	 */
	public void setLev(Integer lev) {
		this.lev = lev;
	}
	/**
	 * 获取：类目级别 1一级目录 2二级目录 3三级目录 只支持三级
	 */
	public Integer getLev() {
		return lev;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：父类id
	 */
	public void setParentCid(Integer parentCid) {
		this.parentCid = parentCid;
	}
	/**
	 * 获取：父类id
	 */
	public Integer getParentCid() {
		return parentCid;
	}
	/**
	 * 设置：类别状态 1有效 2无效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：类别状态 1有效 2无效
	 */
	public Integer getStatus() {
		return status;
	}
}
