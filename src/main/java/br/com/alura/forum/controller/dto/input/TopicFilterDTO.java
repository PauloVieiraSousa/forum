package br.com.alura.forum.controller.dto.input;

import java.util.ArrayList;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Path;

import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.model.topic.domain.TopicStatus;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TopicFilterDTO {

	private String categoryName;
	private TopicStatus status;
	
	public boolean temAlgunFiltro() {
		return categoryName != null || status !=null;
	}
	
	
	public Specification<Topic> criaQueryBaseadaNosParametros(){
		return ((root, criteriaQuery, criteriaBuilder) -> {
			
			ArrayList<Predicate> predicate = new ArrayList<>();
			
			if(status != null) {
				predicate.add(criteriaBuilder.equal(root.get("status"), status));
			}
			
			if(categoryName != null) {
				Path<String> categoryPath = root.get("course").get("subcategory").get("category").get("name");
				predicate.add(criteriaBuilder.equal(categoryPath, categoryName));
			}
			
			return criteriaBuilder.and(predicate.toArray(new Predicate[0]));
			
		});
	}
}
