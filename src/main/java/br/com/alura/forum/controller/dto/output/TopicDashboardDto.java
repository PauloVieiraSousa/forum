package br.com.alura.forum.controller.dto.output;

import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.model.Category;
import br.com.alura.forum.model.Dashboard;

public class TopicDashboardDto {
	
	private String categoryName;
	private List<String> subcategories;
	private Integer allTopics;
	private Integer lastWeekTopics;
	private Integer unansweredTopics;
	
	public TopicDashboardDto(Dashboard dashboard) {

		this.categoryName = dashboard.getCategoryName();
		this.subcategories = dashboard.getSubcategories();
		this.allTopics = dashboard.getAllTopics();
		this.lastWeekTopics = 10;
		this.unansweredTopics = 10;
	}
	
	public static List<TopicDashboardDto> listDashboard(List<Dashboard> dashboards) {
		return dashboards.stream().map(TopicDashboardDto::new)
				.collect(Collectors.toList());
	}
	
	
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
