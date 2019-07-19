package com.d_andaman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.net.rtp.RtpStream;
import android.os.AsyncTask;

@SuppressLint("NewApi")
public class Getuser1 extends AsyncTask<URL, Void, JSONObject>{

	
	private Readdata read;
	
	
	@Override
	protected JSONObject doInBackground(URL... params) {
		HttpURLConnection connection=null;
		
		try{
			connection=(HttpURLConnection)params[0].openConnection();
			int response=connection.getResponseCode();
			
			if(response==HttpURLConnection.HTTP_OK){
				StringBuilder builder=new StringBuilder();
				
				try(BufferedReader reader =new BufferedReader(new InputStreamReader(connection.getInputStream()))){
					
					String line;
					
					while((line=reader.readLine())!=null){
						builder.append(line);
					}
					
				}catch (IOException e) {e.printStackTrace();}
				
				return new JSONObject(builder.toString());
				
				}else{
					//make tost
				}
		}catch(Exception e){e.printStackTrace();}
		
		finally{connection.disconnect();}
		
		return null;
	
	}
	
	
	@Override
	protected void onPostExecute(JSONObject result) {
		
		convertjson(result);
		read.useradapter.notifyDataSetChanged();
		read.listview.smoothScrollToPosition(0);
	}


	private void convertjson(JSONObject result) {
		read.userlist.clear();
		try{
			JSONArray list=result.getJSONArray("post");
			
			for(int i=0;i<list.length();i++){
				
				JSONObject u=list.getJSONObject(i);
				
				//JSONObject name=u.getJSONObject("name");
				//JSONObject location=u.getJSONObject("location");
				//JSONObject pincode=u.getJSONObject("pincode");
				
				read.userlist.add(new User(u.getString("name"),
						u.getString("location"), u.getLong("pincode")));
				
			}
		}catch(JSONException e){e.printStackTrace();		}
		
	}
	

}
