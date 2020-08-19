package br.com.alura.forum.controller;


import java.util.Arrays;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.forum.controller.dto.input.TopicFilterDTO;
import br.com.alura.forum.controller.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.model.Category;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.repository.TopicRepository;

@Controller
public class TopicController {
	
	private TopicRepository topicRepository;
	
	TopicController(TopicRepository topicRepository){
		this.topicRepository = topicRepository;
	}

	@ResponseBody
	@GetMapping(value = "/api/topics", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TopicBriefOutputDto> listTopics(TopicFilterDTO topicSearch){
		Specification<Topic> topicSearchSpecification = topicSearch.criaQueryBaseadaNosParametros();
		List<Topic> topics = topicRepository.findAll(topicSearchSpecification);
		return TopicBriefOutputDto.listFromTopics(topics);	
	
	}
}
