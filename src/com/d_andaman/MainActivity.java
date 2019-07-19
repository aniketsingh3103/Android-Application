package com.d_andaman;


import java.net.MalformedURLException;
import java.net.URL;

import com.d_andaman.R;


import android.annotation.SuppressLint;

import android.app.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	private Button btnLogin;
	//private Button btnLinkToRegister;
	private EditText inputEmail;
	//private EditText inputPassword; 
	private ProgressDialog pDialog;
	private TextView status;
	private Button menu;
	private static int id;
	private static String phone_no;
	private URL url ;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		hideSystemUI();
		//fetch_weather();
		//Get_weather.get_weather();

		inputEmail = (EditText) findViewById(R.id.email);
		//inputPassword = (EditText) findViewById(R.id.password);
		btnLogin = (Button) findViewById(R.id.btnLogin);

		pDialog = new ProgressDialog(this);
		pDialog.setCancelable(false);

		btnLogin.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				String email = inputEmail.getText().toString().trim();
				int internet;
				if (!email.isEmpty()) {
					if(email.length()==10){
						internet=check_internet();
						//int id=getid();
						if(internet==1){
							int id=1;
							if(id!=0){

								Intent i = new Intent(getApplicationContext(),
										New_menu.class);
								startActivity(i);
								
							}else{login();}

						}else{ Intent i = new Intent(getApplicationContext(),
								Startpopup.class);
								startActivity(i);  
								}

					}else{Toast.makeText(getApplicationContext(),
							"Please enter the correct phone number", Toast.LENGTH_SHORT).show();	}

				}else {Toast.makeText(getApplicationContext(),
						"Please enter the credentials!", Toast.LENGTH_SHORT).show();	}




			}

		});



		try {
			int w_id=1;
			url = new URL("http://192.168.83.1/dand_api/weather.php?w_id="+w_id);
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}

	}





	private void fetch_weather() {
		ConnectivityManager connMgr = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {


			new Weather(getBaseContext()).execute(url);

		}

		else
		{
			status.setText("No network connection available.");

		}

	}

	private int check_internet() {
		ConnectivityManager connMgr = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {return 1;}

		else
		{return 0;}

	}


	private void hideSystemUI() {
		getWindow().getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION 
				| View.SYSTEM_UI_FLAG_FULLSCREEN 
				| View.SYSTEM_UI_FLAG_IMMERSIVE
				);
	}

	public void login(){

		ConnectivityManager connMgr = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {


			String phone = inputEmail.getText().toString();
			new Login(getBaseContext(),1).execute(phone);

		}

		else
		{
			//setid(-1);
			//status.setText("No network connection available.");

		}

	}



	public static void setid(int i){
		id=i;
	}

	public static int getid(){
		return id;
	}

	public static void setphone(String i){
		phone_no=i;
	}

	public static String getphone(){
		return phone_no;
	}


}


