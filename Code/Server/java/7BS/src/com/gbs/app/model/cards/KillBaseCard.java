package com.gbs.app.model.cards;

import com.gbs.app.model.cards.model.BaseCard;
import com.gbs.app.model.players.Player;
import com.gbs.app.model.skills.KillSkill;

public class KillBaseCard extends BaseCard {

	public KillBaseCard(Suit suit, Count count) {
		super(suit, count);
		addSkill(new KillSkill());
	}

	@Override
	public Object onInstall(Player... players) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object onUninstall(Player... players) {
		// TODO Auto-generated method stub
		return null;
	}

}
