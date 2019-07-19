package com.d_andaman;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DrawerAdapter extends ArrayAdapter<String>{

	public static int[] type={1,2,2,2,3,3};
	public Typeface typeface;
	public DrawerAdapter(Context context,String[] item,Typeface typeface) {
		super(context,-1,item);
		this.typeface=typeface;
		
	}
	
	

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return type[position];
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		Drawer_data_holder holder;

		int laytype=getItemViewType(position);

		if(row == null) {

			if(laytype==1){
				row = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
				TextView text = (TextView)row.findViewById(R.id.list_text);
				String item = (String) getItem(position);
				text.setText(item);
				ImageView left =(ImageView)row.findViewById(R.id.list_img);
				left.setImageResource(R.drawable.emblom);
			}

			if(laytype==2){
				row = LayoutInflater.from(getContext()).inflate(R.layout.data_list, parent, false);
				String item = (String) getItem(position);
				TextView text = (TextView)row.findViewById(R.id.textdata);
				text.setTypeface(typeface);
				text.setText(item);
			}

			if(laytype==3){
				row = LayoutInflater.from(getContext()).inflate(R.layout.small_data, parent, false);
				TextView text = (TextView)row.findViewById(R.id.notitext);
				String item = (String) getItem(position);
				text.setText(item);
				ImageView left =(ImageView)row.findViewById(R.id.notiimage);
				left.setImageResource(R.drawable.main);
			}
		}

		return row;
	}
}
