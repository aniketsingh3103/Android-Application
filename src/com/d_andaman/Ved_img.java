package com.d_andaman;

import com.d_andaman.New_menu.Array_adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Ved_img extends Activity{
	TextView status;
	ListView listview;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ved_img);
		status=(TextView) findViewById(R.id.st_text);

		//checkconnection();

		listview=(ListView) findViewById(R.id.vidlist);

		String[] video_name={"Appu video for Earthquake","Appu video for Tsunami","Appu video for Fire",
				"Appu video for Flood","Appu video for Cyclone","Mock drill by Disaster Management at School",
		"Disaster Management of Andman Nicobar Official video"};

		Array_adapter array=new Array_adapter(this, R.layout.vedio_grid,video_name);

		listview.setAdapter(array); 


		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				checkconnection();

			}


		}); 


	}

	private void checkconnection() {
		ConnectivityManager connMgr = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {

			ProgressDialog dialog = new ProgressDialog(this); 
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			dialog.setMessage("Loading. Please wait...");
			dialog.setIndeterminate(true);
			dialog.setCanceledOnTouchOutside(false);
			dialog.show();

		}

		else
		{
			status.setText("NO NETWORK");

		}
	}

	public class Array_adapter extends ArrayAdapter {


		Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Zekton.ttf");
		public Array_adapter(Context context, int resource,String[] item) {
			super(context, resource,item);

		}



		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;

			int[] img={R.drawable.earthquake_ved,R.drawable.tsunami_vid,R.drawable.fire_vid,R.drawable.flood_vid,
					R.drawable.cyclone_vid,R.drawable.mock_drill,R.drawable.andman_nicobar};


			if(row == null) {
				row = LayoutInflater.from(getContext()).inflate(R.layout.vedio_grid, parent, false);
			}
			String item = (String) getItem(position);

			ImageView left =(ImageView)row.findViewById(R.id.ved_img);

			TextView des = (TextView)row.findViewById(R.id.ved_dis);
			TextView text = (TextView)row.findViewById(R.id.ved_text);

			left.setImageResource(img[position]);
			text.setTypeface(typeFace);
			text.setText(item);
			des.setText("Disaster Management Office");
			return row;
		}

	}
}
