package com.cpe.springboot.store.controller;

import java.util.List;
import java.util.Set;

import com.cpe.springboot.http.HttpClient;
import com.cpe.springboot.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpe.springboot.card.model.CardModel;
import com.cpe.springboot.store.model.StoreModel;

@Service
public class StoreService {

	@Autowired
	StoreRepository storeRepository;
	
	private StoreModel store;
	
	public void generateNewStore(String name, int nb) {
		StoreModel store =new StoreModel();
		store.setName(name);

		HttpClient httpClient = new HttpClient();
		List<CardModel> cardList = httpClient.getRandCards();

		for(CardModel c: cardList) {
			store.addCard(c);
		}
		storeRepository.save(store);
		this.store=store;
	}
	
	public boolean buyCard(Integer user_id, Integer card_id) {
		HttpClient httpClient = new HttpClient();
		UserModel u = httpClient.getUserById(user_id);
		CardModel c = httpClient.getCardById(card_id);

		if(u.getAccount() > c.getPrice()) {
			u.addCard(c);
			u.setAccount(u.getAccount()-c.getPrice());
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

		c.setUser(null);
		httpClient.updateCard(c, card_id);
		u.setAccount(u.getAccount()+c.computePrice());
		httpClient.updateUser(u, user_id);
		return true;
	}
	
	public Set<CardModel> getAllStoreCard(){
		return this.store.getCardList();
	}
}
