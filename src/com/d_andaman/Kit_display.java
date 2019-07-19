package com.d_andaman;



import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class Kit_display extends Activity {
	ListView listView;
	public static int pos=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.survival_kit_display);

		ActionBar action = getActionBar();
		action.setIcon(R.drawable.circle1);
		action.setTitle("Survival kit");

		listView = (ListView) findViewById(R.id.kit_grid);

		String[] values = new String[] { "Documents","Emergency Equipments","Etable items","Clothes","Liquid cash","Add kit"};

		Array_adapter array=new Array_adapter(this,R.layout.vedio_grid,values);

		listView.setAdapter(array); 

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {


				int itemPosition  = position;


				String  itemValue    = (String) listView.getItemAtPosition(position);
				Intent i = new Intent(getApplicationContext(),
						Survival_kit.class);
				i.putExtra("arg", itemPosition);

				startActivity(i);



			}


		}); 

	}



	public class Array_adapter extends ArrayAdapter {
		Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Zekton.ttf");
		public Array_adapter(Context context, int resource,String[] item) {
			super(context, resource,item);

		}



		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;

			


			if(row == null) {
				row = LayoutInflater.from(getContext()).inflate(R.layout.vedio_grid, parent, false);
			}
			String item = (String) getItem(position);

			ImageView left =(ImageView)row.findViewById(R.id.ved_img);

			TextView des = (TextView)row.findViewById(R.id.ved_dis);
			TextView text = (TextView)row.findViewById(R.id.ved_text);

			left.setImageResource(R.drawable.docs);
			text.setTypeface(typeFace);
			text.setText(item);
			des.setText("Disaster Management Office");
			return row;
		}



	}

}