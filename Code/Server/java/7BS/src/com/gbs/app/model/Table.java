package com.gbs.app.model;

import org.java_websocket.WebSocket;

import com.gbs.app.json.JsonBeanBase;
import com.gbs.app.json.JsonBeanConstant;
import com.gbs.app.utils.JsonUtils;
import com.gbs.app.utils.MyWebSocketServer;

public class Table extends MyWebSocketServer {

	public Table(int address, int maxlength) {
		super(address, maxlength);
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
