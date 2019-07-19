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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import android.content.Context;

import android.os.AsyncTask;
import android.widget.TextView;


public class Sendfeed  extends AsyncTask<String,Void,String>{

	private Context context;
	private TextView sta;



	public Sendfeed(Context context,TextView status) {
		this.context = context;
		this.sta=status;



	}

	protected void onPreExecute(){



	}

	@Override
	protected String doInBackground(String... arg0) {


		try{
			String feedback,service,app,look,ease,down;
			feedback = (String)arg0[0];
			service=(String)arg0[1];
			app=(String)arg0[2];
			look=(String)arg0[3];
			ease=(String)arg0[4];
			down=(String)arg0[5];

			String link = "http://192.168.83.1/dand_api/feedback.php?id=1&app="+app+"&gernal="+feedback+
					"&service="+service+"&look="+look+"&ease="+ease+"&down="+down;


			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
			request.setURI(new URI(link));
			HttpResponse response = client.execute(request);
			BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer sb = new StringBuffer("");
			String line="";

			while ((line = in.readLine()) != null) {
				sb.append(line);
				break;
			}
			in.close();
			return sb.toString();

		}catch(Exception e){return null;}

		//return null;
	}

	@Override
	protected void onPostExecute(String result){

		sta.setText(result);
	}






}