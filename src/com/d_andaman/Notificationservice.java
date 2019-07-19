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
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

public class Notificationservice extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	public void update_notification(){
		@SuppressLint("NewApi")
		class Getuser extends AsyncTask<URL, Void, JSONObject>{

				
			private Context context;


			public Getuser(Context context) {
			      this.context = context;
			   }
				
				
				@Override
				protected JSONObject doInBackground(URL... params) {
					HttpURLConnection connection=null;
					
					try{
						connection=(HttpURLConnection) params[0].openConnection();
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
								Toast.makeText(context, "eror in connection1", Toast.LENGTH_SHORT).show();
							}
					}catch(Exception e){e.printStackTrace();}
					
					finally{connection.disconnect();}
					
					return null;
				
				}
				
				
				@Override
				protected void onPostExecute(JSONObject result) {
					
					convertjson(result);
				}


				private void convertjson(JSONObject result) {
					//userlist.clear();
					try{
						JSONArray list=result.getJSONArray("posts");
						
						for(int i=0;i<list.length();i++){
							
							JSONObject u=list.getJSONObject(i);
							
							JSONObject post=u.getJSONObject("post");
							//JSONObject location=u.getJSONObject("location");
							//JSONObject pincode=u.getJSONObject("pincode");
							
							//userlist.add(new User(post.getString("name"),
									//post.getString("location"), post.getLong("pincode")));
							
						}
					}catch(JSONException e){e.printStackTrace();		}
					
				}
				

			}
	}

}
