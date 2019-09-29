package com.entities;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author none
 * @email guanyimial@126.com
 * @date 2019-01-09 17:45:29
 */
public class RoleInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String roleName;
	//
	private String roleSign;
	//
	private String remarks;
	//
	private Integer creator;
	//
	private Date createDate;
	//
	private String roleno;
	//
	private Date updateDate;
	//角色可添加类型：0系统静态导入，平台管理员可添加类型1; 总部管理员可添加2,3; 子公司管理员可添加3
	private Integer type;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * 获取：
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * 设置：
	 */
	public void setRoleSign(String roleSign) {
		this.roleSign = roleSign;
	}
	/**
	 * 获取：
	 */
	public String getRoleSign() {
		return roleSign;
	}
	/**
	 * 设置：
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置：
	 */
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	/**
	 * 获取：
	 */
	public Integer getCreator() {
		return creator;
	}
	/**
	 * 设置：
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：
	 */
	public void setRoleno(String roleno) {
		this.roleno = roleno;
	}
	/**
	 * 获取：
	 */
	public String getRoleno() {
		return roleno;
	}
	/**
	 * 设置：
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置：角色可添加类型：0系统静态导入，平台管理员可添加类型1; 总部管理员可添加2,3; 子公司管理员可添加3
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：角色可添加类型：0系统静态导入，平台管理员可添加类型1; 总部管理员可添加2,3; 子公司管理员可添加3
	 */
	public Integer getType() {
		return type;
	}
}
