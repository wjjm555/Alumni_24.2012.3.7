package com.gbs.app.json;

import com.google.gson.annotations.Expose;

public class JsonBeanBase extends JsonBean {
	@Expose
	public String type, content;

	public JsonBeanBase(String type, String content) {
		this.type = type;
		this.content = content;
	}
}
