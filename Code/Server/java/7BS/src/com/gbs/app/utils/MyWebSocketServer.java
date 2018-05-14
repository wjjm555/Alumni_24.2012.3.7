package com.gbs.app.utils;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class MyWebSocketServer extends WebSocketServer {

	private List<WebSocket> clients;

	private int maxLength;
	private int port;

	public MyWebSocketServer(int address, int maxlength) {
		super(new InetSocketAddress(address));
		this.maxLength = maxlength;
		this.port = address;
		init();
	}

	public MyWebSocketServer(int address) {
		this(address, Integer.MAX_VALUE);
	}

	private void init() {
		clients = new ArrayList<>();
		this.start();
	}

	@Override
	public void onClose(WebSocket ws, int code, String reason, boolean remote) {
		if (clients != null)
			clients.remove(ws);

	}

	@Override
	public void onError(WebSocket ws, Exception e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onMessage(WebSocket ws, String message) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onOpen(WebSocket ws, ClientHandshake handshake) {
		if (clients != null && clients.size() < maxLength) {
			clients.add(ws);
		}

	}

	public List<WebSocket> getClients() {
		return clients;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public int getPort() {
		return port;
	}

	public boolean connectEnable() {
		if (clients != null && clients.size() < maxLength)
			return true;

		return false;
	}

	public void emit(String content) {
		for (WebSocket client : clients) {
			client.send(content);
		}
	}

	public void emit(Object obj) {
		emit(JsonUtils.getInstance().toJson(obj));
	}
}
