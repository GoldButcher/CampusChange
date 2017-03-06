package com.campus.util;

import net.sf.json.JSONObject;

public class JsonUtil {

	public static JSONObject success(){
		JSONObject object = new JSONObject();
		object.put("result", "success");
		return object;
	}
	
	public static JSONObject error()
	{
		JSONObject object = new JSONObject();
		object.put("result", "error");
		return object;
	}
}
