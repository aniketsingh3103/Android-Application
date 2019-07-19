package com.d_andaman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.d_andaman.Meanu.Array_adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class list_class extends FragmentActivity {

public List<String> list = new ArrayList<>();
	
	public ListView listview;
	public Database db;
	public static int pos=0;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_list1);
		
		
		db=new Database(this);
		
		
		
		
		List<Notification_data> contacts = db.getAllContacts();       
        
        for (Notification_data cn : contacts) {
            String log = cn.getName();
         list.add(log);   
        }
        
        listview=(ListView) findViewById(R.id.notilist1);
		
		Array_adapter array=new Array_adapter(this, R.layout.dis,list);
		
		listview.setAdapter(array); 
		
	}
	
	
	
	
	public class Array_adapter extends ArrayAdapter {
		
		Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/fontawesome-webfont.ttf");
		public Array_adapter(Context context, int resource,List<String> item) {
			super(context, resource,item);
			
		}
		
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
				View row = convertView;
				
				int[] color={R.color.icon1,R.color.icon2,R.color.icon3,
						R.color.icon4,R.color.icon5,R.color.icon6};
				
				if(row == null) {
				row = LayoutInflater.from(getContext()).inflate(R.layout.dis, parent, false);
				}
				String item = (String) getItem(position);
				
				LinearLayout rel=(LinearLayout)row.findViewById(R.id.img1);
				TextView img=(TextView) row.findViewById(R.id.img_text);
				TextView text = (TextView)row.findViewById(R.id.txt1);
				//ImageView img1=(ImageView) row.findViewById(R.id.callbut);
				
				if(position%5==0){
					pos=0;
				}
				
				text.setTypeface(typeFace);
			    rel.setBackgroundResource(color[pos++]);
			   	img.setText(""+(position+1));
			  // 	img1.setImageResource(R.drawable.call);
				text.setText(item);
				return row;
				}
			
			

	}
	
}
