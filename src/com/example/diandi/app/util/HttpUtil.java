package com.example.diandi.app.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

	public static boolean isExitent(final String address){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpURLConnection connection = null;
				try{
					URL url=new URL(address);
					connection=(HttpURLConnection) url.openConnection();
					connection.setRequestMethod("POST");
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					InputStream in=connection.getInputStream();
					BufferedReader reader=new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
					response.append(line);
					} 
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		
		return false;
	
	}
}
