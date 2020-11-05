package com.cpe.springboot.controller;

import org.springframework.data.repository.CrudRepository;

import com.cpe.springboot.model.StoreModel;

public interface StoreRepository extends CrudRepository<StoreModel, Integer> {
	

}
