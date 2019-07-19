package com.d_andaman;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

@SuppressLint("NewApi")
public class Send_feedback extends Activity {
	
	EditText edit,feed;
	EditText edit2;
	Button but;
	TextView status;
	RadioGroup service,app,look,ease,down;
	RadioButton check;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);
		edit2=(EditText) findViewById(R.id.feed_text);
		but=(Button) findViewById(R.id.feedback_s);
		status=(TextView) findViewById(R.id.status_text);
		
		ActionBar action = getActionBar();
	
		action.setTitle("Feedback");
		action.setIcon(R.drawable.circle1);
		
		service=(RadioGroup)findViewById(R.id.radioGroup1);
		look=(RadioGroup)findViewById(R.id.radioGroup3);
		ease=(RadioGroup)findViewById(R.id.radioGroup4);
		app=(RadioGroup)findViewById(R.id.radioGroup2);
		down=(RadioGroup)findViewById(R.id.radioGroup5);
		feed=(EditText)findViewById(R.id.feed_text);
		but=(Button)findViewById(R.id.feedback_s);
		
		
		
		
		
		
		
		but.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				String email = feed.getText().toString().trim();
				
				if (!email.isEmpty()) {
					if(email.length()>60){
						
						Toast.makeText(getApplicationContext(),
								"Content cannot exceed 60 character", Toast.LENGTH_SHORT).show();	
						
							}else{send_feed();}
					}else{
						Toast.makeText(getApplicationContext(),
								"Please enter the feedback", Toast.LENGTH_SHORT).show();
						}
				
				
			
				}
				
				
			});
		
}
	
public void send_feed(){
		
		ConnectivityManager connMgr = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {
			
			int id1=service.getCheckedRadioButtonId();
			int id2=app.getCheckedRadioButtonId();
			int id3=look.getCheckedRadioButtonId();
			int id4=ease.getCheckedRadioButtonId();
			int id5=down.getCheckedRadioButtonId();
			check=(RadioButton) findViewById(id1);
			String feedback = feed.getText().toString().trim();
			String service_text=check.getText().toString().trim();
			check=(RadioButton) findViewById(id2);
			String app_text=check.getText().toString().trim();
			check=(RadioButton) findViewById(id3);
			String look_text=check.getText().toString().trim();
			check=(RadioButton) findViewById(id4);
			String ease_text=check.getText().toString().trim();
			check=(RadioButton) findViewById(id5);
			String down_text=check.getText().toString().trim();
			new Sendfeed(getBaseContext(),status).execute(feedback,service_text,app_text,look_text,ease_text,down_text);
			
		}
		
		else
		{
			Toast.makeText(getApplicationContext(),
					"No Network connection", Toast.LENGTH_SHORT).show();
			
		}
		
	}
			}
