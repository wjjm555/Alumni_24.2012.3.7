package com.gbs.app.model;

import org.java_websocket.WebSocket;

import com.gbs.app.json.JsonBeanBase;
import com.gbs.app.json.JsonBeanConstant;
import com.gbs.app.json.JsonBeanInit;
import com.gbs.app.utils.JsonUtils;
import com.gbs.app.utils.MyWebSocketServer;
import com.google.gson.annotations.Expose;

public class Table extends MyWebSocketServer {

	@Expose(deserialize = false, serialize = true)
	private Account[] accounts;

	public Table(int address, int maxlength) {
		super(address, maxlength);
		accounts = new Account[maxlength];
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
				// accounts.add(init.account);
				emit(Table.this);
			} else if (bean.type.equals(JsonBeanConstant.CHAT)) {
				// JsonBeanChat chat = JsonUtils.getInstance().fromJson(bean.content, JsonBeanChat.class);
				emit(bean);
			} else if (bean.type.equals(JsonBeanConstant.OUT)) {
				// TODO
			} else {
				// TODO
			}
		}
	}

	private class Position<T> {
		private int length;
		private T[] container;

		public Position() {
			// TODO Auto-generated constructor stub
		}
	}

}
