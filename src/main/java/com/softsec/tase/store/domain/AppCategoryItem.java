/**
 * 
 */
package com.softsec.tase.store.domain;

/**
 * AppCategory.java
 * @author yanwei
 * @date 2013-3-12 下午2:23:57
 * @description
 */
public class AppCategoryItem {

	private int categoryId;
	
	private String storeName;
	
	private String category;
	
	private int masterId;
	
	private int collectedCount;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getMasterId() {
		return masterId;
	}
	
	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}
	
	public int getCollectedCount() {
		return collectedCount;
	}
	
	public void setCollectedCount(int collectedCount) {
		this.collectedCount = collectedCount;
	}
}
