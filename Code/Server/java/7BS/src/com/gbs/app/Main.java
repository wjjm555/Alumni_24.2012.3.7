package com.gbs.app;

import java.util.ArrayList;
import java.util.Random;

import com.gbs.app.model.cards.KillBaseCard;
import com.gbs.app.model.cards.model.Card;
import com.gbs.app.model.cards.model.Card.Count;
import com.gbs.app.model.cards.model.Card.Suit;
import com.gbs.app.model.players.Role;

public class Main {
	// public enum Stage {
	// Prepare_Initiative(2), Determine_Initiative(3), Scratch_Initiative(4), Play_Initiative(5), Discard_Initiative(6), Finish_Initiative(7), Prepare_Passive(8), Determine_Passive(9), Scratch_Passive(10), Play_Passive(11), Discard_Passive(12), Finish_Passive(13);
	//
	// public final int i;
	// private Stage(int i) {
	// this.i=i;
	// // TODO Auto-generated constructor stub
	// }
	// }

	public static void main(String[] args) {
		// Stage a = Stage.Prepare_Initiative;

		// System.out.println(a.i);
		// Card card = new Card(Card.Type.Base(1), Card.Suit.Spades(1), Card.Count._5);
		// System.out.print("this is main:" + card.getSuit().equal(Card.Suit.Diamonds));

		// Role role = new Role(Role.Group.A(1), Role.Gender.Male(1), 5);
		// role.setDescribe("").setName("");
		//
		// KillBaseCard kbc = new KillBaseCard(Suit.Hearts(1), Count._4);
		//
		// kbc.getSkills().get(0).execute();
		//
		// ArrayList<Integer> allCards = new ArrayList<>();
		// allCards.add(1);
		// ArrayList<Integer> standardCards = (ArrayList<Integer>) allCards.clone();
		//
		// standardCards.add(2);
		//
		//
		// System.out.println(allCards.toString());
		// System.out.println(standardCards.toString());

		Random r = new Random();
		System.out.println(r.nextInt(2));

	}
}