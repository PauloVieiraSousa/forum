package br.com.alura.forum.model;

import java.util.List;

public class Dashboard {

	private String categoryName;
	private List<String> subcategories;
	private Integer allTopics;
	private Integer lastWeekTopics;
	private Integer unansweredTopics;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<String> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(List<String> subcategories) {
		this.subcategories = subcategories;
	}
	public Integer getAllTopics() {
		return allTopics;
	}
	public void setAllTopics(Integer allTopics) {
		this.allTopics = allTopics;
	}
	public Integer getLastWeekTopics() {
		return lastWeekTopics;
	}
	public void setLastWeekTopics(Integer lastWeekTopics) {
		this.lastWeekTopics = lastWeekTopics;
	}
	public Integer getUnansweredTopics() {
		return unansweredTopics;
	}
	public void setUnansweredTopics(Integer unansweredTopics) {
		this.unansweredTopics = unansweredTopics;
	}
	
}
