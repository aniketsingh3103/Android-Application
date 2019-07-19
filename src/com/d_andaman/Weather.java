package com.d_andaman;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;



@SuppressLint("NewApi")
public class Weather  extends AsyncTask<URL,Void,JSONObject>{

	private Context context;



	public Weather(Context context) {
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
		return null;

		

	}


	@Override
	protected void onPostExecute(JSONObject result) {

		int i=convertjson(result);
		Get_weather.set_weather(i);

	}


	private int convertjson(JSONObject result) {


		try{
			JSONArray list=result.getJSONArray("id");
			JSONObject u=list.getJSONObject(0);

			int i=u.getInt("max");
			//JSONObject location=u.getJSONObject("location");
			//JSONObject pincode=u.getJSONObject("pincode");
			
			return i;
			
		}catch(JSONException e){e.printStackTrace();		return 0;}
		
	}

}