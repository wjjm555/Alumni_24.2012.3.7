package com.gbs.app;

import java.util.ArrayList;
import java.util.Random;

import com.gbs.app.model.cards.KillBaseCard;
import com.gbs.app.model.cards.model.Card;
import com.gbs.app.model.cards.model.Card.Count;
import com.gbs.app.model.cards.model.Card.Suit;
import com.gbs.app.model.players.Role;

public class Main {

	public static void main(String[] args) {

		ArrayList<Integer> allCards = new ArrayList<>(5);
		// allCards.add(2,2);
		// ArrayList<Integer> standardCards = (ArrayList<Integer>) allCards.clone();
		//
		// standardCards.add(2);
		for (int i = 0; i < 10; i++) {
			allCards.add(i);
		}
		//
		//
		System.out.println(allCards.size());
		// System.out.println(standardCards.toString());

		// Random r = new Random();
		// System.out.println(r.nextInt(2));

	}
}
