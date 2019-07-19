package com.d_andaman;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class Reg_user  extends AsyncTask<String,Void,String>{ 

	private TextView statusField;
	private Context context;




	public  Reg_user(Context context,TextView statusField,int flag) {
		this.context = context;
		this.statusField = statusField;



	}

	protected void onPreExecute(){  }

	@Override
	protected String doInBackground(String... params) {

		try{
			String name = (String)params[0];
			String location = (String)params[1];
			String pincode=(String)params[2];
			String lat=(String)params[3];
			String lon =(String)params[4];
			String id=""+MainActivity.getid();
			String link="http://192.168.83.1/dand_api/register.php";
			String data  = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
			data += "&" + URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");
			data += "&" + URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(location, "UTF-8");
			data+="&"+ URLEncoder.encode("pincode", "UTF-8") + "=" + URLEncoder.encode(pincode, "UTF-8");
			data+="&"+ URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(lat, "UTF-8");
			data+="&"+ URLEncoder.encode("lon", "UTF-8") + "=" + URLEncoder.encode(lon, "UTF-8");

			URL url = new URL(link);
			URLConnection conn = url.openConnection(); 

			conn.setDoOutput(true); 
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 

			wr.write( data ); 
			wr.flush(); 

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			StringBuilder sb = new StringBuilder();
			String line = null;


			while((line = reader.readLine()) != null)
			{
				sb.append(line);
				break;
			}

			return sb.toString();
		}
		catch(Exception e){
			return new String("Exception: " + e.getMessage());
		}
	}

	protected void onPostExecute(String result){

		this.statusField.setText(result);
		String s=statusField.getText().toString().trim();

	}




}
