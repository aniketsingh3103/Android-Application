package com.d_andaman;

import com.d_andaman.R;

import android.annotation.SuppressLint;
import android.app.ActionBar;
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
public class Register extends Activity{
	private Button register;
	private Button login;
	private EditText name;
	private EditText user;
	private EditText password;
	private ProgressDialog pDialog;
	private TextView status;
	
	@Override
protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		ActionBar action = getActionBar();
		action.setIcon(R.drawable.meanu_bar);
		action.setTitle("Register");
		
		
		name = (EditText) findViewById(R.id.name);
		user = (EditText) findViewById(R.id.username);
		password=(EditText)findViewById(R.id.password);
		login = (Button) findViewById(R.id.btnLinkToLoginScreen);
		register = (Button) findViewById(R.id.btnRegister);
		status = (TextView) findViewById(R.id.regst);
		pDialog = new ProgressDialog(this);
		pDialog.setCancelable(false);
		
		
		register.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				String f_name = name.getText().toString().trim();
				String f_user = user.getText().toString().trim();
				String f_password=password.getText().toString().trim();

			
				if (!f_name.isEmpty() && !f_user.isEmpty()&&!f_password.isEmpty()) {
					
					register_api(f_name,f_user,f_password);

				} else {
					// Prompt user to enter credentials
					Toast.makeText(getApplicationContext(),
							"Please enter the credentials!", Toast.LENGTH_SHORT).show();
				}
			} });
		
		login.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				
					Intent i = new Intent(getApplicationContext(),
						MainActivity.class);
						startActivity(i);
						finish();


				
			} });
		
		
		
	}
	
	
	
	public void register_api(String name,String user,String password){
		
	
		ConnectivityManager connMgr = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		
		 if (networkInfo != null && networkInfo.isConnected()) {
			 
		
		new Reg_user(this,status,1).execute(name,user,password);
		 														
		 }else{
			 status.setText("No network connection available.");}
		 			
	}

}
