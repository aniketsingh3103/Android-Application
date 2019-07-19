package com.d_andaman;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Userprofil extends Activity {

	private EditText name;
	private EditText location;
	private EditText pincode;
	private Button create;

	private TextView status;
	private String sta;
	private GPSTracker gps;
	private String lat ,lon;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_profil);


		name=(EditText) findViewById(R.id.name);
		location=(EditText) findViewById(R.id.location);
		pincode=(EditText) findViewById(R.id.pincode);
		create=(Button) findViewById(R.id.create);
		status=(TextView) findViewById(R.id.status);
		gps = new GPSTracker(Userprofil.this);
		lat=null;
		lon=null;
		if(gps.canGetLocation()){

			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();
			lat=""+latitude;
			lon=""+longitude;
		}

		create.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String name1=name.getText().toString().trim();
				String location1=location.getText().toString().trim();
				String pincode1=pincode.getText().toString().trim();

				String MY_DB = "my_db";
				SharedPreferences sp = getSharedPreferences(MY_DB,Context.MODE_PRIVATE);

				Editor e = sp.edit();
				e.putString("name",name1);
				e.commit();


				if(!name1.isEmpty()&&!location1.isEmpty()&&!pincode1.isEmpty()){

					registeruser(name1,location1,pincode1,lat,lon);

				}else{Toast.makeText(getApplicationContext(),"Please enter the credentials!", Toast.LENGTH_SHORT).show();}

			}		});

}




	private void registeruser(String name1, String location1,String pincode1,String lat,String lon) {



		ConnectivityManager connMgr = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();



		if (networkInfo != null && networkInfo.isConnected()) {
			new Reg_user(this,status,1).execute(name1,location1,pincode1,lat,lon);

			int id1=1;//MainActivity.getid();

			String MY_DB = "my_db";
			SharedPreferences sp = getSharedPreferences(MY_DB,Context.MODE_PRIVATE);

			Editor e = sp.edit();
			e.putInt("id",id1);
			e.commit();

		}
		else
		{
			status.setText("No network connection available.");
		}


	}




}
