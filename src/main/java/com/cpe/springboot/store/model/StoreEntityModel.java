package com.cpe.springboot.store.model;

import com.cpe.springboot.card.model.CardModel;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "store_model")
public class StoreEntityModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;

	public StoreEntityModel() { }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StoreModel asStoreModel() {
		StoreModel storeModel = new StoreModel();
		storeModel.setId(this.getId());
		storeModel.setName(this.getName());
		return storeModel;
	}
	

}
