package com.gbs.app.model.cards.model;

public abstract class BaseCard extends Card {

	public BaseCard(Suit suit, Count count) {
		super(Card.Type.Base, suit, count);
	}

}
