package com.gbs.app.json;

import com.google.gson.annotations.Expose;

public class JsonBeanPort extends JsonBean {
	@Expose
	public int port;

	public JsonBeanPort(int port) {
		this.port = port;
	}
}
