package com.gbs.app.model.cards.model;

import java.util.ArrayList;
import java.util.List;

import com.gbs.app.model.Base;
import com.gbs.app.model.players.Player;
import com.gbs.app.model.skills.model.Skill;

public abstract class Card extends Base {

	private final Suit suit;
	private final Count count;
	private final Type type;

	private List<Skill> skills = new ArrayList<>();

	public Card(Type type, Suit suit, Count count) {
		this.suit = suit;
		this.count = count;
		this.type = type;
	}

	public enum Type {
		Base, Equip, Kit
	}

	public enum Suit {
		Spades, Hearts, Diamonds, Clubs;

		public boolean equal(Suit suit) {
			return this.equals(suit);
		}
	}

	public enum Count {
		_A((byte) 1), _2((byte) 2), _3((byte) 3), _4((byte) 4), _5((byte) 5), _6((byte) 6), _7((byte) 7), _8((byte) 8), _9((byte) 9), _10((byte) 10), _J((byte) 11), _Q((byte) 12), _K((byte) 13);

		private final byte count;

		public byte getCount() {
			return count;
		}

		private Count(byte count) {
			this.count = count;
		}

		public boolean scope(Count start, Count end) {
			return getCount() >= start.getCount() && getCount() <= end.getCount();
		}

		public boolean greater_equal(Count count) {
			return equal(count) || greater(count);
		}

		public boolean less_equal(Count count) {
			return equal(count) || less(count);
		}

		public boolean greater(Count count) {
			return count.getCount() < this.getCount();
		}

		public boolean less(Count count) {
			return count.getCount() > this.getCount();
		}

		public boolean equal(Count count) {
			return count.getCount() == this.getCount();
		}
	}

	public Type getType() {
		return type;
	}

	public Suit getSuit() {
		return suit;
	}

	public Count getCount() {
		return count;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void addSkill(Skill skill) {
		skills.add(skill);
	}

	public abstract Object onInstall(Player... players);

	public abstract Object onUninstall(Player... players);
}
