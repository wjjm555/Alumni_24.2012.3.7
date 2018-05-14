package com.gbs.app.model;

import org.java_websocket.WebSocket;

import com.gbs.app.json.JsonBeanBase;
import com.gbs.app.json.JsonBeanConstant;
import com.gbs.app.utils.JsonUtils;
import com.gbs.app.utils.MyWebSocketServer;

public class Lobby extends MyWebSocketServer {

	public final int TABLENUM = 5;
	public final int TABLEITEMNUM = 5;
	public int SOCKETPORT = 10000;

	private Table[] tables = new Table[TABLENUM];

	public Lobby(int address) {
		super(address);
		SOCKETPORT = ++address;

		for (int i = 0; i < tables.length; ++i) {
			Table table = new Table(SOCKETPORT + i, TABLEITEMNUM);
			tables[i] = table;
		}
	}

	@Override
	public void onMessage(WebSocket ws, String message) {
		super.onMessage(ws, message);
		routes(JsonUtils.getInstance().fromJson(message, JsonBeanBase.class));
	}

	private void routes(JsonBeanBase bean) {
		if (bean != null) {
			if (bean.type.equals(JsonBeanConstant.CHAT)) {
				// JsonBeanChat chat = JsonUtils.getInstance().fromJson(bean.content, JsonBeanChat.class);
				emit(bean);
			} else {

			}
		}
	}

}
