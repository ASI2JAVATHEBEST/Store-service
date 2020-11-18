package com.cpe.springboot.store.controller;

import java.util.List;
import java.util.Set;

import com.cpe.springboot.http.HttpClient;
import com.cpe.springboot.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpe.springboot.card.model.CardModel;
import com.cpe.springboot.store.model.StoreEntityModel;

@Service
public class StoreService {

	@Autowired
	StoreRepository storeRepository;
	
	private StoreEntityModel store;
	
	public void generateNewStore(String name, int nb) {
		StoreEntityModel store =new StoreEntityModel();
		store.setName(name);

		HttpClient httpClient = new HttpClient();
		List<CardModel> cardList = httpClient.getRandCards();

		for(CardModel c: cardList) {
			c.setStore(store.getId());
		}
		storeRepository.save(store);
		this.store=store;
	}
	
	public boolean buyCard(Integer user_id, Integer card_id) {
		HttpClient httpClient = new HttpClient();
		UserModel u = httpClient.getUserById(user_id);
		CardModel c = httpClient.getCardById(card_id);

		if(u.getAccount() > c.getPrice()) {
			c.setUser(u.getId());
			c.setStore(0);
			u.setAccount(u.getAccount()-c.getPrice());
			httpClient.updateCard(c, card_id);
			httpClient.updateUser(u, user_id);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean sellCard(Integer user_id, Integer card_id) {
		HttpClient httpClient = new HttpClient();
		UserModel u = httpClient.getUserById(user_id);
		CardModel c = httpClient.getCardById(card_id);

		c.setStore(1);
		c.setUser(0);
		httpClient.updateCard(c, card_id);
		u.setAccount(u.getAccount()+c.computePrice());
		httpClient.updateUser(u, user_id);
		return true;
	}
}
