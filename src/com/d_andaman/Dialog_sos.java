package com.d_andaman;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dialog_sos extends Activity{


	private TextView txt,txt1;
	private Button temp1,temp2,temp3,temp4,cancle;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sos_dialog);
		
		temp1=(Button)findViewById(R.id.button1);
		temp2=(Button)findViewById(R.id.button2);
		temp3=(Button)findViewById(R.id.button3);
		temp4=(Button)findViewById(R.id.button4);
		cancle=(Button)findViewById(R.id.cancle);
		

		temp1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(),
						Send_popup.class);
					i.putExtra("message","IAD");
				
				startActivity(i);
				finish();
				
			}
		});
		temp2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(),
						Send_popup.class);
					i.putExtra("message","IAL");
				
				startActivity(i);
				finish();
				
			}
		});
		temp3.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(),
						Send_popup.class);
					i.putExtra("message","SBFM");
				
				startActivity(i);
				finish();
				
			}
		});
		temp4.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(),
						Send_popup.class);
					i.putExtra("message","SID");
				
				startActivity(i);
				finish();
				
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



