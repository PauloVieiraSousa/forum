package br.com.alura.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.alura.forum.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	
}
