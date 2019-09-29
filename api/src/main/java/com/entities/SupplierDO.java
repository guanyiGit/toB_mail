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
public class SupplierDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer supId;
	//供应商名称
	private String supName;
	//
	private String description;
	//状态 1有效 2无效
	private Integer status;
	//
	private Integer orgId;
	//供应商分类id
	private Integer scId;
	//地址
	private String address;
	//法人代表
	private String legalPerson;
	//电话
	private String phone;
	//联系人
	private String linkman;
	//电子邮件
	private String email;
	//社会信用代码
	private String socialCreditCode;
	//注册资本
	private Double registeredAssets;
	//占地面积
	private Double floorArea;
	//成立日期
	private Date dateEstablishment;
	//营业额
	private Double businessVolume;
	//银行信用状况
	private String bankCreditStatus;
	//人力资源状况
	private String humanResourceSituation;
	//设备状况
	private String statusEquipment;
	//经营范围
	private String businessScope;
	//主要产品及服务主要产品及服务
	private String productServe;
	//主要协议条款
	private String articlesAgreement;
	//
	private Date createTime;
	//
	private Date updateTime;

	/**
	 * 设置：
	 */
	public void setSupId(Integer supId) {
		this.supId = supId;
	}
	/**
	 * 获取：
	 */
	public Integer getSupId() {
		return supId;
	}
	/**
	 * 设置：供应商名称
	 */
	public void setSupName(String supName) {
		this.supName = supName;
	}
	/**
	 * 获取：供应商名称
	 */
	public String getSupName() {
		return supName;
	}
	/**
	 * 设置：
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：状态 1有效 2无效
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 1有效 2无效
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	/**
	 * 获取：
	 */
	public Integer getOrgId() {
		return orgId;
	}
	/**
	 * 设置：供应商分类id
	 */
	public void setScId(Integer scId) {
		this.scId = scId;
	}
	/**
	 * 获取：供应商分类id
	 */
	public Integer getScId() {
		return scId;
	}
	/**
	 * 设置：地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：法人代表
	 */
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	/**
	 * 获取：法人代表
	 */
	public String getLegalPerson() {
		return legalPerson;
	}
	/**
	 * 设置：电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：电话
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：联系人
	 */
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	/**
	 * 获取：联系人
	 */
	public String getLinkman() {
		return linkman;
	}
	/**
	 * 设置：电子邮件
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：电子邮件
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：社会信用代码
	 */
	public void setSocialCreditCode(String socialCreditCode) {
		this.socialCreditCode = socialCreditCode;
	}
	/**
	 * 获取：社会信用代码
	 */
	public String getSocialCreditCode() {
		return socialCreditCode;
	}
	/**
	 * 设置：注册资本
	 */
	public void setRegisteredAssets(Double registeredAssets) {
		this.registeredAssets = registeredAssets;
	}
	/**
	 * 获取：注册资本
	 */
	public Double getRegisteredAssets() {
		return registeredAssets;
	}
	/**
	 * 设置：占地面积
	 */
	public void setFloorArea(Double floorArea) {
		this.floorArea = floorArea;
	}
	/**
	 * 获取：占地面积
	 */
	public Double getFloorArea() {
		return floorArea;
	}
	/**
	 * 设置：成立日期
	 */
	public void setDateEstablishment(Date dateEstablishment) {
		this.dateEstablishment = dateEstablishment;
	}
	/**
	 * 获取：成立日期
	 */
	public Date getDateEstablishment() {
		return dateEstablishment;
	}
	/**
	 * 设置：营业额
	 */
	public void setBusinessVolume(Double businessVolume) {
		this.businessVolume = businessVolume;
	}
	/**
	 * 获取：营业额
	 */
	public Double getBusinessVolume() {
		return businessVolume;
	}
	/**
	 * 设置：银行信用状况
	 */
	public void setBankCreditStatus(String bankCreditStatus) {
		this.bankCreditStatus = bankCreditStatus;
	}
	/**
	 * 获取：银行信用状况
	 */
	public String getBankCreditStatus() {
		return bankCreditStatus;
	}
	/**
	 * 设置：人力资源状况
	 */
	public void setHumanResourceSituation(String humanResourceSituation) {
		this.humanResourceSituation = humanResourceSituation;
	}
	/**
	 * 获取：人力资源状况
	 */
	public String getHumanResourceSituation() {
		return humanResourceSituation;
	}
	/**
	 * 设置：设备状况
	 */
	public void setStatusEquipment(String statusEquipment) {
		this.statusEquipment = statusEquipment;
	}
	/**
	 * 获取：设备状况
	 */
	public String getStatusEquipment() {
		return statusEquipment;
	}
	/**
	 * 设置：经营范围
	 */
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	/**
	 * 获取：经营范围
	 */
	public String getBusinessScope() {
		return businessScope;
	}
	/**
	 * 设置：主要产品及服务主要产品及服务
	 */
	public void setProductServe(String productServe) {
		this.productServe = productServe;
	}
	/**
	 * 获取：主要产品及服务主要产品及服务
	 */
	public String getProductServe() {
		return productServe;
	}
	/**
	 * 设置：主要协议条款
	 */
	public void setArticlesAgreement(String articlesAgreement) {
		this.articlesAgreement = articlesAgreement;
	}
	/**
	 * 获取：主要协议条款
	 */
	public String getArticlesAgreement() {
		return articlesAgreement;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

    @Override
    public String toString() {
        return "SupplierDO{" +
                "supId=" + supId +
                ", supName='" + supName + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", orgId=" + orgId +
                ", scId=" + scId +
                ", address='" + address + '\'' +
                ", legalPerson='" + legalPerson + '\'' +
                ", phone='" + phone + '\'' +
                ", linkman='" + linkman + '\'' +
                ", email='" + email + '\'' +
                ", socialCreditCode='" + socialCreditCode + '\'' +
                ", registeredAssets=" + registeredAssets +
                ", floorArea=" + floorArea +
                ", dateEstablishment=" + dateEstablishment +
                ", businessVolume=" + businessVolume +
                ", bankCreditStatus='" + bankCreditStatus + '\'' +
                ", humanResourceSituation='" + humanResourceSituation + '\'' +
                ", statusEquipment='" + statusEquipment + '\'' +
                ", businessScope='" + businessScope + '\'' +
                ", productServe='" + productServe + '\'' +
                ", articlesAgreement='" + articlesAgreement + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
