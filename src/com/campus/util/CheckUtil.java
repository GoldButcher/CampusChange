package com.campus.util;

import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;


public class CheckUtil {
	private static String api = "key-fd60f85f1aa200880f65ac9a166f1da2";
	public static void send(String mobile,String check){
        // just replace key here
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(
            "api",api));
        WebResource webResource = client.resource(
            "http://sms-api.luosimao.com/v1/send.json");
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("mobile", mobile);
        formData.add("message", "你的验证码为："+check+"打死都不要告诉别人哦！【校园交易网】");
//        formData.add("message", "你的验证码为："+check+"打死都不要告诉别人哦！【校园交易网】");
        ClientResponse response =  webResource.type( MediaType.APPLICATION_FORM_URLENCODED ).
        post(ClientResponse.class, formData);
        String textEntity = response.getEntity(String.class);
        try {
            JSONObject jsonObj = new JSONObject( textEntity );
            int error_code = jsonObj.getInt("error");
            String error_msg = jsonObj.getString("msg");
            if(error_code==0){
                System.out.println("Send message success.");
            }else{
                System.out.println("Send message failed,code is "+error_code+",msg is "+error_msg);
            }
        } catch (JSONException ex) {
           System.out.println("JSON解析出错请检查");
        }
        int status = response.getStatus();
        //System.out.print(textEntity);
        //System.out.print(status);
    }
	
	
}
