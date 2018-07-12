package com.gbs.app.utils;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import com.gbs.app.json.JsonBeanBase;

public class MyWebSocketServer extends WebSocketServer {

	private Map<String, WebSocket> clients;

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
		clients = new HashMap<>();
		this.start();
	}

	@Override
	public void onClose(WebSocket ws, int code, String reason, boolean remote) {
		if (clients != null)
			clients.remove(ws.getRemoteSocketAddress().getAddress().getHostAddress());
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
			clients.put(ws.getRemoteSocketAddress().getAddress().getHostAddress(), ws);
		}

	}

	public Collection<WebSocket> getClients() {
		return clients.values();
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
		for (WebSocket client : clients.values())
			client.send(content);
	}

	public void emit(Object obj) {
		emit(JsonUtils.getInstance().toJson(obj));
	}

	public void sendMessage(String type, Object obj, String... keys) {
		sendMessage(type, JsonUtils.getInstance().toJson(obj), keys);
	}

	public void sendMessage(String type, String content, String... keys) {
		for (String key : keys)
			sendMessage(type, content, key);
	}

	public void sendMessage(String type, Object obj, WebSocket... wss) {
		sendMessage(type, JsonUtils.getInstance().toJson(obj), wss);
	}

	public void sendMessage(String type, String content, WebSocket... wss) {
		for (WebSocket ws : wss)
			sendMessage(type, content, ws);
	}

	public void sendMessage(String type, Object obj, String key) {
		sendMessage(type, JsonUtils.getInstance().toJson(obj), key);
	}

	public void sendMessage(String type, String content, String key) {
		WebSocket ws = clients.get(key);
		if (ws != null)
			sendMessage(type, content, ws);
	}

	public void sendMessage(String type, Object obj, WebSocket ws) {
		sendMessage(type, JsonUtils.getInstance().toJson(obj), ws);
	}

	public void sendMessage(String type, String content, WebSocket ws) {
		JsonBeanBase bean = new JsonBeanBase(type, content);
		sendMessage(JsonUtils.getInstance().toJson(bean), ws);
	}

	private void sendMessage(String msg, WebSocket ws) {
		if (ws != null && !msg.isEmpty())
			ws.send(msg);
	}

}
