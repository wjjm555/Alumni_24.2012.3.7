package com.gbs.app.model;

import com.gbs.app.json.JsonBeanConstant;
import com.google.gson.annotations.Expose;

public class Account extends Base {
	@Expose
	public String hostAddress, userId, gameStatus = JsonBeanConstant.GAME_STATUS_UNVORBEREITET;
}
