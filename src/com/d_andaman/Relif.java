package com.d_andaman;

import java.io.ObjectOutputStream.PutField;

import com.d_andaman.Meanu.Array_adapter;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class Relif extends Activity{
	
	 GridView listView;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.relif);
		
		ActionBar action = getActionBar();
		action.setIcon(R.drawable.meanu_bar);
		action.setTitle("Relif Shelter");
		
		 listView = (GridView) findViewById(R.id.reliflist);
			
			String[] values = new String[] { "South andaman", "Lamba Line", "Air Port",
					  "Kamraj Nagar", "Goal Ghar","Car Nicobar","Great Nicobar","Kamorta","Katchal","Little Andaman","Teressa"};	
		Array_adapter array=new Array_adapter(this, R.layout.relif_image,R.id.reliftext,values);
		
			listView.setAdapter(array); 
			
			listView.setOnItemClickListener(new OnItemClickListener() {
				  @Override
				  public void onItemClick(AdapterView<?> parent, View view,
				    int position, long id) {
					
			    
				   int itemPosition  = position;
				   
				   
				   String  itemValue    = (String) listView.getItemAtPosition(position);
					  
				   if(itemPosition==0){
					   Intent i = new Intent(getApplicationContext(),
								Map.class);
					   startActivity(i);
				   }else{
				   	Intent i = new Intent(getApplicationContext(),
								Show_relif_image.class);
				   	i.putExtra("arg", itemPosition);
						startActivity(i);
					}

				  }
				}); 
		
		
		

	}
	
	
	public class Array_adapter extends ArrayAdapter {

		public Array_adapter(Context context, int resource,int text,String[] item) {
			super(context, resource,text,item);
			
		}
		
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
				View row = convertView;
				
				int[] img={R.drawable.map1,R.drawable.map2,R.drawable.map3,R.drawable.map4,R.drawable.map5,R.drawable.car_nicobar,
						R.drawable.great_nicobar,R.drawable.kamorta,R.drawable.katchal,R.drawable.little_andman,R.drawable.teressa};
				
				if(row == null) {
				row = LayoutInflater.from(getContext()).inflate(R.layout.relif_image, parent, false);
				}
				String item = (String) getItem(position);
				
				ImageView left =(ImageView)row.findViewById(R.id.relifimage);
				
				TextView text = (TextView)row.findViewById(R.id.reliftext);
				
				left.setImageResource(img[position]);
				
				text.setText(item);
				return row;
				}
			
			

	}
	
	
	
}
