package com.gbs.app.model.skills.model;

import com.gbs.app.model.Base;
import com.gbs.app.model.players.Player;

public abstract class Skill extends Base {

	private final Type type;
	private final Mode mode;

	public Skill(Type type, Mode mode) {
		this.type = type;
		this.mode = mode;
	}

	public enum Mode {
		Initiative, Passive;
	}

	public enum Type {
		Common, Master, Awaken, Lock;
	}

	public Mode getMode() {
		return mode;
	}

	public Type getType() {
		return type;
	}

	public boolean isInitiative() {
		return getMode().equals(Mode.Initiative);
	}

	public boolean isPassive() {
		return getMode().equals(Mode.Passive);
	}

	public abstract Object execute(Player... players);

	public abstract Object judge(Player... players);
}
