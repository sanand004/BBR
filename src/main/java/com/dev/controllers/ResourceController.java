package com.dev.controllers;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.exception.ResourceException;
import com.dev.model.Resource;
import com.dev.repository.ResourceRepository;

@RestController
@RequestMapping("/api/ressource")
public class ResourceController {
	
Logger logger=LoggerFactory.getLogger(ResourceController.class);

@Autowired
private ResourceRepository resourcerepository;

@PostMapping("/addresource")
public Resource createReport(@Valid @RequestBody Resource resource) {
	logger.info("create report entered");
    return resourcerepository.save(resource);
}


@GetMapping("/resources/{id}")
public Resource getReportsById(@PathVariable(value="id") Long reportId) {

	return resourcerepository.findById(reportId)
			.orElseThrow(() -> new ResourceException("reports", "id", reportId));
	
		}

@GetMapping("/title/{id}")
public Resource getTitleById(@PathVariable(value="id") Long titleId) {

	return resourcerepository.findById(titleId)
			.orElseThrow(() -> new ResourceException("title", "id", titleId));
	
		}

@GetMapping("/authorname/{id}")
public Resource getAuthorById(@PathVariable(value="id") Long authorId) {

	return resourcerepository.findById(authorId)
			.orElseThrow(() -> new ResourceException("auhtor_name", "id", authorId));
	
		}
@DeleteMapping("/report/{id}")
public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long reportId) {
    Resource resource = resourcerepository.findById(reportId)
            .orElseThrow(() -> new ResourceException("Report", "id", reportId));

    resourcerepository.delete(resource);

    return ResponseEntity.ok().build();
}

}
