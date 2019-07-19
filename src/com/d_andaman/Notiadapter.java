package com.d_andaman;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Notiadapter extends ArrayAdapter<Noti>{

	public Notiadapter(Context context, List<Noti> user) {
		
		super(context,-1,user);
		
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			
			
			
			if(row == null) {
			row = LayoutInflater.from(getContext()).inflate(R.layout.seoc_message, parent, false);
			}
			Noti item = getItem(position);
			
			ImageView left =(ImageView)row.findViewById(R.id.notiimage);
			
			TextView name = (TextView)row.findViewById(R.id.notitext);
			TextView date = (TextView)row.findViewById(R.id.notidate);
		
			
			left.setImageResource(R.drawable.feedback2);
			
			name.setText(item.notification);
			date.setText("25-1-2017");
			
			
			return row;
			}

}
