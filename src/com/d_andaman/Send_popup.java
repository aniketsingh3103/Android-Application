package com.d_andaman;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;



import android.app.Activity;
import android.content.Context;


import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Send_popup extends Activity{


	private TextView txt1;
	private Button cancle,send;
	//private String subject,message;
	//private Alert_database db;
	//private View view;
	private String lat,lon,message;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_popup);
		//db = new Alert_database(this);
		cancle=(Button) findViewById(R.id.cancle);
		send=(Button) findViewById(R.id.send);

		txt1=(TextView)findViewById(R.id.alert_message);


		lat=getIntent().getExtras().getString("latitude");
		lon=getIntent().getExtras().getString("longitude");
		message=getIntent().getExtras().getString("message");


		if(lat==null&lon==null){
			txt1.setText(message+"\tWe are still fetching your location please wait and send it after some time");
		}else{
			txt1.setText(message+"\t"+lat+"\t"+lon);
		}

		send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String message=txt1.getText().toString().trim();

				new Send_popup_data(getBaseContext()).execute(message);
				
			}
		});

		cancle.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//closeContextMenu();
				finish();

			}
		});

	}


	public class Send_popup_data  extends AsyncTask<String,Void,String>{



		public Send_popup_data(Context context) {


		}

		protected void onPreExecute(){}

		@Override
		protected String doInBackground(String... arg0) {




			try{
				String message = (String)arg0[0];

				String link="http://192.168.83.1/dand_api/recive_alert.php";
				String data  = URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");


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

				return  sb.toString();

			}
			catch(Exception e){e.printStackTrace();}



			return null;
		}

		@Override
		protected void onPostExecute(String result){

			Toast.makeText(getApplicationContext(),"Message Send",Toast.LENGTH_SHORT).show();


		}






	}



}



