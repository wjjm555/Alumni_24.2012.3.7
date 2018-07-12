package com.gbs.app.json;

import com.google.gson.annotations.Expose;

public class JsonBeanChat extends JsonBean{
	@Expose
	public String target, content;
	@Expose
	public int time;
}
