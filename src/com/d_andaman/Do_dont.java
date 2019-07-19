package com.d_andaman;

import com.d_andaman.Contact.Array_adapter;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.Menu;
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
public class Do_dont extends Activity {
	public static int pos=0;
	
	ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dos);

		ActionBar action = getActionBar();
		action.setIcon(R.drawable.circle1);
		action.setTitle("Do and Donts");

		listView = (ListView) findViewById(R.id.do_list);

		String[] values = new String[] { "Earthquake","Cyclone","Tsunami","Fire","Flood","Add Do/Donts"};	

		Array_adapter array=new Array_adapter(this, R.layout.vedio_grid,values);

		listView.setAdapter(array); 
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) {
				
		    
			   int itemPosition  = position;
			   
			   
			   String  itemValue    = (String) listView.getItemAtPosition(position);
				  
			 
			   
			   
			   Intent i = new Intent(getApplicationContext(),
						Display_dos.class);
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

			int[] img={R.drawable.earthquake_ved,R.drawable.cyclone_vid,R.drawable.tsunami_vid,
					R.drawable.fire_vid,R.drawable.flood_vid,
					R.drawable.mock_drill};


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
			des.setText("Guidelines from Disaster Management Office");
			return row;
			}



	}

}