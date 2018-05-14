package com.gbs.app.utils;

import com.google.gson.Gson;

public class JsonUtils {
	private Gson gson;

	private static class JsonUtilsHolder {
		public static final JsonUtils instance = new JsonUtils();
	}

	public static final JsonUtils getInstance() {
		return JsonUtilsHolder.instance;
	}

	public JsonUtils() {
		gson = new Gson();
	}

	public <T> T fromJson(String json, Class<T> classOfT) {
		return gson.fromJson(json, classOfT);
	}

	public String toJson(Object src) {
		return gson.toJson(src);
	}

}
