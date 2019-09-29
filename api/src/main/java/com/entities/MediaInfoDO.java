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
public class MediaInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String name;
	//
	private String title;
	//
	private String url;
	//
	private String size;
	//
	private String imageExt;
	//
	private String thumbnailUrl;
	//
	private String thumbnailSize;
	//
	private Date createDate;
	//
	private Date updateDate;

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
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：
	 */
	public void setSize(String size) {
		this.size = size;
	}
	/**
	 * 获取：
	 */
	public String getSize() {
		return size;
	}
	/**
	 * 设置：
	 */
	public void setImageExt(String imageExt) {
		this.imageExt = imageExt;
	}
	/**
	 * 获取：
	 */
	public String getImageExt() {
		return imageExt;
	}
	/**
	 * 设置：
	 */
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	/**
	 * 获取：
	 */
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	/**
	 * 设置：
	 */
	public void setThumbnailSize(String thumbnailSize) {
		this.thumbnailSize = thumbnailSize;
	}
	/**
	 * 获取：
	 */
	public String getThumbnailSize() {
		return thumbnailSize;
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
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
}
