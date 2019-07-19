package com.d_andaman;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Show_relif_image extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_relif_image);
		
		ImageView img=(ImageView) findViewById(R.id.relif_img_view);
		TextView text=(TextView) findViewById(R.id.img_text);
		
		int Arg = getIntent().getExtras().getInt("arg");
		
		String[] values = new String[] { "BatuBasti", "Lamba Line", "Air Port",
				  "Kamraj Nagar", "Goal Ghar","Car Nicobar","Great Nicobar","Kamorta","Katchal","Little Andaman","Teressa"};
		int[] img1={R.drawable.map1,R.drawable.map2,R.drawable.map3,R.drawable.map4,R.drawable.map5,R.drawable.car_nicobar,
				R.drawable.great_nicobar,R.drawable.kamorta,R.drawable.katchal,R.drawable.little_andman,R.drawable.teressa};
		
		img.setImageResource(img1[Arg]);
		text.setText(values[Arg]);
		
		
	}
}
