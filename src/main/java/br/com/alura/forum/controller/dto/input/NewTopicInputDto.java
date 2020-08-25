package br.com.alura.forum.controller.dto.input;

import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.repository.CourseRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewTopicInputDto {
	
	private String shortDescription;
	private String content;
	private String courseName;
	
	public Topic build(User owner, CourseRepository courseRepository) {
		Course course = courseRepository.findByName(this.courseName);
		return new Topic(this.shortDescription, this.content, owner, course);
	}
	
}
