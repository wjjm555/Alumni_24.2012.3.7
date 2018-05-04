package com.gbs.app.model.cards.model;

public abstract class EquipCard extends Card {

	private final EquipType type;

	public EquipCard(Suit suit, Count count, EquipType type) {
		super(Card.Type.Equip, suit, count);
		this.type = type;
	}

	public enum EquipType {
		Weapon, Shield, Mounts
	}

	public EquipType getEquipType() {
		return type;
	}
}
