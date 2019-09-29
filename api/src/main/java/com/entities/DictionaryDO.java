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
public class DictionaryDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer dictionaryId;
	//字典字段
	private String dictionaryField;
	//值
	private Integer dictionaryValue;
	//含义
	private String dictionaryDescribe;
	//
	private Date creationTime;

	/**
	 * 设置：
	 */
	public void setDictionaryId(Integer dictionaryId) {
		this.dictionaryId = dictionaryId;
	}
	/**
	 * 获取：
	 */
	public Integer getDictionaryId() {
		return dictionaryId;
	}
	/**
	 * 设置：字典字段
	 */
	public void setDictionaryField(String dictionaryField) {
		this.dictionaryField = dictionaryField;
	}
	/**
	 * 获取：字典字段
	 */
	public String getDictionaryField() {
		return dictionaryField;
	}
	/**
	 * 设置：值
	 */
	public void setDictionaryValue(Integer dictionaryValue) {
		this.dictionaryValue = dictionaryValue;
	}
	/**
	 * 获取：值
	 */
	public Integer getDictionaryValue() {
		return dictionaryValue;
	}
	/**
	 * 设置：含义
	 */
	public void setDictionaryDescribe(String dictionaryDescribe) {
		this.dictionaryDescribe = dictionaryDescribe;
	}
	/**
	 * 获取：含义
	 */
	public String getDictionaryDescribe() {
		return dictionaryDescribe;
	}
	/**
	 * 设置：
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreationTime() {
		return creationTime;
	}
}
