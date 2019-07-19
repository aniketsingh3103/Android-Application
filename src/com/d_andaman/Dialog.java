package com.d_andaman;

import android.app.Activity;
import android.app.ProgressDialog;




import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.TextView;

public class Dialog extends Activity{


	private TextView txt,txt1;
	private Button cancle,send;
	private String subject,message;
	private Alert_database db;
	private View view;
	private String lat,lon;
	private GPSTracker gps;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup);
		db = new Alert_database(this);
		cancle=(Button) findViewById(R.id.cancle);
		send=(Button) findViewById(R.id.send);
		txt=(TextView) findViewById(R.id.alert_sub);
		txt1=(TextView)findViewById(R.id.alert_message);


		//lat=getIntent().getExtras().getString("latitude");
		//lon=getIntent().getExtras().getString("longitude");

		send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {


				subject=txt.getText().toString().trim();
				message=txt1.getText().toString().trim();
				db.addContact(new Alert_list(1,message));
				 lat=null;
				 lon=null;
				gps = new GPSTracker(Dialog.this);

				// check if GPS enabled		
		        if(gps.canGetLocation()){
		        	
		        	double latitude = gps.getLatitude();
		        	double longitude = gps.getLongitude();
		        	 lat=""+latitude;
		        	 lon=""+longitude;
		        }
		        
		        if(lat==null&lon==null){
		        	ProgressDialog dialog = new ProgressDialog(getApplicationContext()); 
					dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
					dialog.setMessage("Fetching your location...");
					dialog.setIndeterminate(true);
					dialog.setCanceledOnTouchOutside(false);
					dialog.show();
				}else{
		        
		        
				Intent i = new Intent(getApplicationContext(),
						Send_popup.class);
				i.putExtra("latitude",lat);
				i.putExtra("longitude", lon);
				i.putExtra( "message",message);
				startActivity(i);
				finish();
				}
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



}



