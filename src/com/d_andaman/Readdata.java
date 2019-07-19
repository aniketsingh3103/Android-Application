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





import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class Readdata extends Activity {

	public List<User> userlist = new ArrayList<>();
	public ListView listview;
	public Useradapter useradapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_list);

		TextView tex=(TextView) findViewById(R.id.user_name);
		
		SharedPreferences sp = getSharedPreferences("my_db",Context.MODE_PRIVATE);
		String name=sp.getString("name","User");
		
		tex.setText("Welcome\t"+name);

		listview=(ListView) findViewById(R.id.userlist);


		useradapter =new Useradapter(this,userlist);

		listview.setAdapter(useradapter); 
		
		
		listview.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) {
				
				  Message d=new Message();
				  d.show(getFragmentManager(), null);
				  
				  }}); 

		URL url = null;
		try {
			url = new URL("http://192.168.83.1/dand_api/service.php?user=1&format=json");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(url!=null){

			ConnectivityManager connMgr = (ConnectivityManager)
					getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

			if (networkInfo != null && networkInfo.isConnected()) {

				Getuser get=new Getuser(this);
				get.execute(url);

			}else{ Toast.makeText(this, "NO network Avilable", Toast.LENGTH_SHORT).show();}

		}else{
			Toast.makeText(this, "eror in connection", Toast.LENGTH_SHORT).show();
		}

	}


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
			useradapter.notifyDataSetChanged();
			listview.smoothScrollToPosition(0);
		}


		private void convertjson(JSONObject result) {
			userlist.clear();
			try{
				JSONArray list=result.getJSONArray("posts");

				for(int i=0;i<list.length();i++){

					JSONObject u=list.getJSONObject(i);

					JSONObject post=u.getJSONObject("post");
					//JSONObject location=u.getJSONObject("location");
					//JSONObject pincode=u.getJSONObject("pincode");

					userlist.add(new User(post.getString("name"),
							post.getString("location"), post.getLong("pincode")));

				}
			}catch(JSONException e){e.printStackTrace();		}

		}
		
		
		


	}






}


































/*public void Readdata(Context context){

	try 
		{

        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams,TIMEOUT_MILLISEC);
        HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);

        HttpParams p = new BasicHttpParams();

        p.setParameter("user", "1");


        HttpClient httpclient = new DefaultHttpClient(p);
        String url = "http://192.168.23.1/dand_api/service.php?user=1&format=json";
        HttpPost httppost = new HttpPost(url);


        try {
            Log.i(getClass().getSimpleName(), "send  task - start");

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

            nameValuePairs.add(new BasicNameValuePair("user", "1"));
            httppost.setEntity((HttpEntity) new UrlEncodedFormEntity(nameValuePairs));
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String responseBody = httpclient.execute(httppost,
                    responseHandler);
            // Parse
            JSONObject json = new JSONObject(responseBody);
            JSONArray jArray = json.getJSONArray("posts");
            ArrayList<HashMap<String, String>> mylist = 
                   new ArrayList<HashMap<String, String>>();

            for (int i = 0; i < jArray.length(); i++) {
                HashMap<String, String> map = new HashMap<String, String>();
                JSONObject e = jArray.getJSONObject(i);
                String s = e.getString("post");
                JSONObject jObject = new JSONObject(s);

                map.put("id", jObject.getString("id"));
                map.put("name", jObject.getString("name"));
                map.put("location", jObject.getString("location"));
                map.put("pincode", jObject.getString("pincode"));

                mylist.add(map);
            }

            Toast.makeText(context,responseBody, Toast.LENGTH_SHORT).show();





        } 
        catch (ClientProtocolException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}


    } catch (Throwable t) {Toast.makeText(context, "Request failed: " + t.toString(),Toast.LENGTH_LONG).show();}

	}*/













