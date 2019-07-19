package com.d_andaman;

import java.util.Random;


import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;



public class Survival_kit extends Activity{

	private ListView listview;
	public Array_adapter array;
	public int pos=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.survivalkit);

		String[] earthquake =getResources().getStringArray(R.array.document);
		String[] cyclone =getResources().getStringArray(R.array.emergency);
		String[] fire =getResources().getStringArray(R.array.eatable );
		String[] flood =getResources().getStringArray(R.array.cloth);
		String[] tsunami =getResources().getStringArray(R.array.cash);
		String[] add={"Add"};
		TextView text=(TextView) findViewById(R.id.kittext);
		//ImageView image=(ImageView) findViewById(R.id.do_img);

		int Arg = getIntent().getExtras().getInt("arg");
		String[] values = new String[] { "Documents","Emergency Equipments","Etable items","Clothes","Liquid cash","Add kit"};	


		text.setText(values[Arg]);

		listview=(ListView) findViewById(R.id.kitlist);



		switch(Arg){
		case 0: array=new Array_adapter(this, R.layout.survivalkitlist,earthquake);
		break;
		case 1: array=new Array_adapter(this, R.layout.survivalkitlist,cyclone);
		break;
		case 2: array=new Array_adapter(this, R.layout.survivalkitlist,fire);
		break;
		case 3: array=new Array_adapter(this, R.layout.survivalkitlist,flood);
		break;
		case 4: array=new Array_adapter(this, R.layout.survivalkitlist,tsunami);
		break;
		case 5: array=new Array_adapter(this, R.layout.survivalkitlist,add);
		break;
		}

		listview.setAdapter(array); 


	}


	public class Array_adapter extends ArrayAdapter {



		Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/fontawesome-webfont.ttf");
		public Array_adapter(Context context, int resource,String[] item) {
			super(context, resource,item);

		}



		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;

			int[] img={R.color.icon1,R.color.icon2,R.color.icon3,R.color.icon4,R.color.icon5};

			if(row == null) {
				row = LayoutInflater.from(getContext()).inflate(R.layout.survivalkitlist, parent, false);
			}
			String item = (String) getItem(position);

			LinearLayout left =(LinearLayout)row.findViewById(R.id.surlay1);

			TextView text = (TextView)row.findViewById(R.id.sur_list_text2);
			TextView num=(TextView) row.findViewById(R.id.sur_list_text);
			
			if(position%5==0){
				pos=0;
			}
			
			num.setText(""+(position+1));
			left.setBackgroundResource(img[pos++]);
			text.setTypeface(typeFace);
			text.setText(item);
			return row;
		}



	}	




}
