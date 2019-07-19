package com.d_andaman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Startpopup extends Activity{


	
	private Button cancle,send;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_popup);
		
		cancle=(Button) findViewById(R.id.decline);
		send=(Button) findViewById(R.id.accept);
		
		send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(),
						New_menu.class);
						startActivity(i);
						finish();
						}
				});

		cancle.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				finish();

			}
		});

	}



}



