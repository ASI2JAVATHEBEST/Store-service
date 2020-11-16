package com.cpe.springboot.store.model;

import com.cpe.springboot.card.model.CardModel;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "store_model")
public class StoreModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;

	@OneToMany
    private Set<CardModel> cardList = new HashSet<>();

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

	public Set<CardModel> getCardList() {
		return cardList;
	}

	public void setCardList(Set<CardModel> cardList) {
		this.cardList = cardList;
	}

	public void addCard(CardModel card) {
		card.setStore(this);
		this.cardList.add(card);
	}
	

}
