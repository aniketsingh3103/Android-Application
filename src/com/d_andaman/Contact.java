package com.d_andaman;



import java.util.Random;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class Contact extends Activity{
	ListView listView;
	public int pos=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact);


		//ActionBar action = getActionBar();
		//action.setIcon(R.drawable.meanu_bar);
		//action.setTitle("Contact");

		listView = (ListView) findViewById(R.id.list_contact);


		String[] values = new String[] { 
				"All India Radio","Ambulance","Child Care","Coast Guard (MRCC)","District Control Room",
				"Doordarshan","Echo Of India","Fire","Govt. Press","Women Care","State Control Room"};

		final String[] number= new String[]
				{"230360 ","102","1098","1093","1070","233766","230269","101 ","229217","1091","1077"};

		Array_adapter array=new Array_adapter(this, R.layout.contact_list,R.id.name,values);

		listView.setAdapter(array); 

		PhoneCallListener phoneListener = new PhoneCallListener();
		TelephonyManager telephonyManager = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(phoneListener,PhoneStateListener.LISTEN_CALL_STATE);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {


				int itemPosition  = position;


				Intent callIntent = new Intent(Intent.ACTION_DIAL);
				callIntent.setData(Uri.parse("tel:"+number[itemPosition]));
				try{
					startActivity(callIntent);
				}catch (android.content.ActivityNotFoundException ex){
					Toast.makeText(getApplicationContext(),"yourActivity is not founded",Toast.LENGTH_SHORT).show();
				}




			}


		}); 

	}



	//----------------------------------------------------------------------------------------------------------------------------

	public class Array_adapter extends ArrayAdapter {

		public Array_adapter(Context context, int resource,int text,String[] item) {
			super(context, resource,text,item);

		}



		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			
			//int pos;
			
		 String[] number= new String[]
					{"230360 ","102","1098","1093","1070","233766","230269","101 ","229217","1091","1077"};

			int[] color={R.color.icon1,R.color.icon2,R.color.icon3,
					R.color.icon4,R.color.icon5,R.color.icon6};

			String[] t={"A","A","C","C","D","D","E","F","G","W","S"};

			if(row == null) {
				row = LayoutInflater.from(getContext()).inflate(R.layout.contact_list, parent, false);
			}
			String item = (String) getItem(position);

			LinearLayout rel=(LinearLayout)row.findViewById(R.id.contact_img);

			TextView img=(TextView) row.findViewById(R.id.img_text);

			ImageView img1=(ImageView) row.findViewById(R.id.callbut);

			TextView left =(TextView)row.findViewById(R.id.number);

			TextView text = (TextView)row.findViewById(R.id.name);

			if(position%5==0){
				pos=0;
			}
			
			rel.setBackgroundResource(color[pos++]);

			left.setText(number[position]);
			img.setText(t[position]);
			img1.setImageResource(R.drawable.call);
			text.setText(item);
			return row;
		}



	}

	//--------------------------------------------------------------------------------------------------------------------------------
	private class PhoneCallListener extends PhoneStateListener {

		private boolean isPhoneCalling = false;

		String LOG_TAG = "LOGGING 123";
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {

			if (TelephonyManager.CALL_STATE_RINGING == state) {
				// phone ringing
				Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
			}

			if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
				// active
				Log.i(LOG_TAG, "OFFHOOK");

				isPhoneCalling = true;
			}

			if (TelephonyManager.CALL_STATE_IDLE == state) {
				// run when class initial and phone call ended,
				// need detect flag from CALL_STATE_OFFHOOK
				Log.i(LOG_TAG, "IDLE");

				if (isPhoneCalling) {

					Log.i(LOG_TAG, "restart app");

					// restart app
					Intent i = getBaseContext().getPackageManager()
							.getLaunchIntentForPackage(
									getBaseContext().getPackageName());
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);

					isPhoneCalling = false;
				}

			}
		}
	}


}
