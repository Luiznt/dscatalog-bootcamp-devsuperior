package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		
		List<Category> list = repository.findAll();
		
		/*
		// simple way to load into listDto  
		List<CategoryDTO> listDto = new ArrayList<>();
		for (Category cat: list) {
			listDto.add(new CategoryDTO(cat));
		}
		return listDto;
		*/

		//another way to load listDto, more "elegant"
		// List<CategoryDTO> listDto = list.stream().map(x-> new CategoryDTO(x)).collect(Collectors.toList());
		
		// but it can return the listDto directly. Great!
		return list.stream().map(x-> new CategoryDTO(x)).collect(Collectors.toList());
		
	}

	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found."));
		return new CategoryDTO(entity);
	}

}