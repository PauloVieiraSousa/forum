package br.com.alura.forum.controller;


import java.net.URI;
import java.time.Instant;
import java.time.chrono.Chronology;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.input.NewTopicInputDto;
import br.com.alura.forum.controller.dto.input.TopicFilterDTO;
import br.com.alura.forum.controller.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.controller.dto.output.TopicDashboardDto;
import br.com.alura.forum.controller.dto.output.TopicOutputDto;
import br.com.alura.forum.model.Category;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.Dashboard;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.repository.CategoryRepository;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;

@RequestMapping("/api/topics")
@Controller
public class TopicController {
	
	private TopicRepository topicRepository;
	private CategoryRepository categoryRepository;
	private CourseRepository courseRepository;
	
	TopicController(TopicRepository topicRepository, CategoryRepository categoryRepository, CourseRepository courseRepository){
		this.topicRepository = topicRepository;
		this.categoryRepository = categoryRepository;
		this.courseRepository = courseRepository;
	}

	@ResponseBody
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TopicBriefOutputDto> listTopics(TopicFilterDTO topicSearch, 
			@PageableDefault(sort = "creationInstant", direction = Sort.Direction.DESC) Pageable pageRequest){
		
		Specification<Topic> topicSearchSpecification = topicSearch.criaQueryBaseadaNosParametros();
		Page<Topic> topics = topicRepository.findAll(topicSearchSpecification, pageRequest);
		return TopicBriefOutputDto.listFromTopics(topics);	
	
	}
	
	@ResponseBody
	@GetMapping(value = "/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TopicDashboardDto> dashboard(){
		List<Category> categories = categoryRepository.findAll();
		
		List<Dashboard> topicDashboards = categories.stream().map(category -> {
			Dashboard dashboard = new Dashboard();
			
			dashboard.setSubcategories(category.getSubcategoryNames());
			dashboard.setCategoryName(category.getName());
			dashboard.setAllTopics(topicRepository.countCategory(category.getId()));
			
			return dashboard;
		}).collect(Collectors.toList());
		
		return TopicDashboardDto.listDashboard(topicDashboards);
	}
	
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TopicOutputDto> createTopic(
			@RequestBody NewTopicInputDto newTopicDto, 
			@AuthenticationPrincipal User loggedUser, 
			UriComponentsBuilder uriBuilder){
		
		Topic topic = newTopicDto.build(loggedUser, this.courseRepository);
		this.topicRepository.save(topic);
		
		URI path = uriBuilder.path("/api/topics/{id}")
				.buildAndExpand(topic.getId()).toUri();
		
		return ResponseEntity.created(path).body(new TopicOutputDto(topic));
	}
	
}
