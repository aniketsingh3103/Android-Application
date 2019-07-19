package com.d_andaman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.d_andaman.Readdata.Getuser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Seoc_message extends FragmentActivity{
	public List<Noti> notilist = new ArrayList<>();
	
	public ListView listview;
	public Notiadapter notiadapter;
	public Database db;
	public Get_weather g;
	private static int n_id;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_list);

		
		
		db = new Database(this);
		
		listview=(ListView) findViewById(R.id.notilist);

		notiadapter =new Notiadapter(this,notilist);

		listview.setAdapter(notiadapter); 
		
		n_id=getnid();
		
		URL url = null;
		try {
			url = new URL("http://192.168.83.1/dand_api/notification.php?id="+n_id);
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}


		if(url!=null){

			ConnectivityManager connMgr = (ConnectivityManager)
					getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

			if (networkInfo != null && networkInfo.isConnected()) {

				Getnoti get=new Getnoti(this);
				get.execute(url);

			}else{ Toast.makeText(this, "NO network Avilable", Toast.LENGTH_SHORT).show();}

		}else{
			Toast.makeText(this, "eror in connection", Toast.LENGTH_SHORT).show();
		}



	}

	public static void setnid(int i){
		n_id=i;
	}
	
	public static int getnid() {
		return n_id;
	}

	@SuppressLint("NewApi")
	class Getnoti extends AsyncTask<URL, Void, JSONObject>{


		private Context context;


		public Getnoti(Context context) {
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
			notiadapter.notifyDataSetChanged();
			listview.smoothScrollToPosition(0);
		}


		private void convertjson(JSONObject result) {
			notilist.clear();
			int max=0,k;
			try{
				JSONArray list=result.getJSONArray("posts");

				for(int i=0;i<list.length();i++){

					JSONObject u=list.getJSONObject(i);

					JSONObject post=u.getJSONObject("post");
					//JSONObject location=u.getJSONObject("location");
					//JSONObject pincode=u.getJSONObject("pincode");

					notilist.add(new Noti(post.getString("noti")));
					
					k=post.getInt("n_id");
					
					if(max<k){
						max=k;
					}
					
					db.addContact(new Notification_data(post.getInt("n_id"),post.getString("noti")));
				}
				
				
				if(max>getnid())
				setnid(max);
				
				
			}catch(JSONException e){e.printStackTrace();		}

		}


	}



}
