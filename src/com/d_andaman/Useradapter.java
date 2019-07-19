package com.d_andaman;

import java.util.List;
import java.util.Random;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Useradapter extends ArrayAdapter<User>{

	public Useradapter(Context context, List<User> user) {
		
		super(context,-1,user);
		
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			int[] img={R.color.icon1,R.color.icon2,R.color.icon3};
			
			
			if(row == null) {
			row = LayoutInflater.from(getContext()).inflate(R.layout.new_user_list, parent, false);
			}
			User item = getItem(position);
			
			ImageView left =(ImageView)row.findViewById(R.id.user_img);
			
			TextView name = (TextView)row.findViewById(R.id.name);
			TextView location = (TextView)row.findViewById(R.id.location);
			TextView pincode = (TextView)row.findViewById(R.id.pincode);
			
			Random rand = new Random();
		    int pos = rand.nextInt(3);
		    
		   left.setBackgroundResource(img[pos]);
			name.setText(item.name);
			location.setText(item.location);
			String pin=""+item.pincode;
			pincode.setText(pin);
			
			return row;
			}

}
