package com.d_andaman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.ListView;
import android.widget.TextView;

public class Alert extends Activity{
public List<String> list = new ArrayList<>();
	
	public ListView listview;
	public Alert_database db;
	public static int pos=0;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alert_list);
		
		
		db=new Alert_database(this);
		
		
		
		
		List<Alert_list> contacts = db.getAllContacts();       
        
        for (Alert_list cn : contacts) {
            String log = cn.getName() ;
         list.add(log);   
        }
        
        listview=(ListView) findViewById(R.id.alert);
		
		Array_adapter array=new Array_adapter(this, R.layout.dis,list);
		
		listview.setAdapter(array); 
		
	}
	
	
	
	
	public class Array_adapter extends ArrayAdapter {

		public Array_adapter(Context context, int resource,List<String> item) {
			super(context, resource,item);
			
		}
		
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
				View row = convertView;
				
				
				
				if(row == null) {
				row = LayoutInflater.from(getContext()).inflate(R.layout.dis, parent, false);
				}
				String item = (String) getItem(position);
				
				LinearLayout rel=(LinearLayout)row.findViewById(R.id.img1);
				TextView img=(TextView) row.findViewById(R.id.img_text);
				TextView text = (TextView)row.findViewById(R.id.txt1);
				ImageView ig=(ImageView)row.findViewById(R.id.delimg);
				//ImageView img1=(ImageView) row.findViewById(R.id.callbut);
				
				if(position%5==0){
					pos=0;
				}
				
			    rel.setBackgroundResource(R.color.but_can);
			   	img.setText(""+(position+1));
			   	ig.setImageResource(R.drawable.trash);
			   //	img1.setImageResource(R.drawable.call);
				text.setText(item);
				return row;
				}
			
			

	}
}
