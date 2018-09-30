package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.model.Resource;



public interface ResourceRepository extends JpaRepository<Resource, Long>{

}
