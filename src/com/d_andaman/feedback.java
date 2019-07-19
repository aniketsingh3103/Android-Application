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
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

@SuppressLint("NewApi")
public class feedback extends Activity implements OnMenuItemClickListener{
	
	EditText feed;
	Button but;
	private RadioButton service,network,app;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);
		
		
		ActionBar action = getActionBar();
		action.setIcon(R.drawable.meanu_bar);
		action.setTitle("Feedback");
		
		//wservice=(RadioButton)findViewById(R.id.radioButton1);
		feed=(EditText)findViewById(R.id.feed_text);
		but=(Button)findViewById(R.id.feedback_s);
		
		
		but.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				String email = feed.getText().toString().trim();
				
				if (!email.isEmpty()) {
					if(email.length()>100){
						
						Toast.makeText(getApplicationContext(),
								"Content cannot exceed 100 character", Toast.LENGTH_SHORT).show();	
						
							}else{send_feed();}
					}else{
						Toast.makeText(getApplicationContext(),
								"Please enter the feedback", Toast.LENGTH_SHORT).show();
						}
				
				Toast.makeText(getApplicationContext(),
						"Please enter the feedback1", Toast.LENGTH_SHORT).show();
			
				}
				
				
			});
		
}
	
public void send_feed(){
		
		ConnectivityManager connMgr = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {
			
			
			String feedback = feed.getText().toString();
			//new Sendfeed(getBaseContext(),1).execute(feedback);
			
		}
		
		else
		{
			Toast.makeText(getApplicationContext(),
					"No Network connection", Toast.LENGTH_SHORT).show();
			
		}
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.head, menu);
		return true;
}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
		View view=findViewById(R.id.setting);
	    switch (item.getItemId()) {
	        case R.id.setting:
			Popup(view);
	      default:
	            return super.onOptionsItemSelected(item);
	    }
	    
	}
	
	
	public void Popup(View v) {
	    PopupMenu popup = new PopupMenu(this, v);
	    MenuInflater inflater = popup.getMenuInflater();
	    popup.setOnMenuItemClickListener(this);
	    inflater.inflate(R.menu.actions, popup.getMenu());
	    popup.show();
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	
}
