package br.com.alura.forum.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.alura.forum.model.topic.domain.Topic;

public interface TopicRepository  extends JpaRepository<Topic, Long>, JpaSpecificationExecutor<Topic>{

	@Query("select t from Topic t")
	List<Topic> list();
	

	List<Topic> findAll();
	
	@Query("SELECT count(*) FROM Topic t "
			+ "join t.course c "
			+ "join c.subcategory sub "
			+ "join sub.category ca "
			+ "where ca.id = :id")
	Integer countCategory(@Param("id") Long id);

	Topic save(Topic topic);
	
}
