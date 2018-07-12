package com.gbs.app.model;

import java.util.ArrayList;
import java.util.List;

import org.java_websocket.WebSocket;

import com.gbs.app.json.JsonBeanBase;
import com.gbs.app.json.JsonBeanConstant;
import com.gbs.app.json.JsonBeanEnter;
import com.gbs.app.json.JsonBeanInit;
import com.gbs.app.json.JsonBeanPort;
import com.gbs.app.utils.JsonUtils;
import com.gbs.app.utils.MyWebSocketServer;
import com.google.gson.annotations.Expose;

public class Lobby extends MyWebSocketServer {

	public final int TABLENUM = 5;
	public final int TABLEITEMNUM = 5;
	public int SOCKETPORT = 10000;

	@Expose(deserialize = false, serialize = true)
	private Table[] tables = new Table[TABLENUM];
	@Expose(deserialize = false, serialize = true)
	private List<Account> accounts;

	public Lobby(int address) {
		super(address);
		SOCKETPORT = ++address;
		accounts = new ArrayList<>();
		for (int i = 0; i < tables.length; ++i) {
			Table table = new Table(SOCKETPORT + i, TABLEITEMNUM);
			tables[i] = table;
		}
	}

	@Override
	public void onMessage(WebSocket ws, String message) {
		super.onMessage(ws, message);
		routes(JsonUtils.getInstance().fromJson(message, JsonBeanBase.class), ws);
	}

	private void routes(JsonBeanBase bean, WebSocket ws) {
		if (bean != null) {
			if (bean.type.equals(JsonBeanConstant.INIT)) {
				JsonBeanInit init = JsonUtils.getInstance().fromJson(bean.content, JsonBeanInit.class);
				init.account.hostAddress = ws.getRemoteSocketAddress().getAddress().getHostAddress();
				accounts.add(init.account);
				emit(Lobby.this);
			} else if (bean.type.equals(JsonBeanConstant.CHAT)) {
				// JsonBeanChat chat = JsonUtils.getInstance().fromJson(bean.content, JsonBeanChat.class);
				emit(bean);
			} else if (bean.type.equals(JsonBeanConstant.ENTER)) {
				JsonBeanEnter enter = JsonUtils.getInstance().fromJson(bean.content, JsonBeanEnter.class);
				if (enter != null && enter.tableIndex >= 0 && enter.tableIndex < tables.length) {
					Table table = tables[enter.tableIndex];
					if (table != null && table.connectEnable()) {
						sendMessage(JsonBeanConstant.PORT, new JsonBeanPort(table.getPort()), ws);
					} else {
						sendMessage(JsonBeanConstant.PORT, new JsonBeanPort(JsonBeanConstant.PORT_INVALID), ws);
					}
				}
			} else {
				// TODO
			}
		}
	}

}
