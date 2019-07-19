package com.d_andaman;

import java.util.List;
import java.util.Random;




import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Display_dos extends Activity{
	private ListView listview;
	public Array_adapter array;
	public  static int pos=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dispay_dos);
		
		String[] earthquake =getResources().getStringArray(R.array.earthquake);
		String[] cyclone =getResources().getStringArray(R.array.cyclone);
		String[] fire =getResources().getStringArray(R.array.fire);
		String[] flood =getResources().getStringArray(R.array.flood);
		String[] tsunami =getResources().getStringArray(R.array.tsunami);
		String[] tsunamid =getResources().getStringArray(R.array.tsunamid);
		String[] earthquaked =getResources().getStringArray(R.array.earthquaked);
		String[] cycloned =getResources().getStringArray(R.array.cycloned);
		String[] floodd =getResources().getStringArray(R.array.floodd);
		String[] fired =getResources().getStringArray(R.array.fired);
		
		//TextView text=(TextView) findViewById(R.id.do_text);
		ImageView image=(ImageView) findViewById(R.id.do_img);
		
		String Arg = getIntent().getExtras().getString("arg");
		String[] values = new String[] { "earthquake","cyclone","tsunami","fire","flood","Add Do/Donts"};	
		
		
		
		listview=(ListView) findViewById(R.id.dolist);
		
		switch(Arg){
		case "EarthquakeDo's": array=new Array_adapter(this,earthquake);
		break;
		case "CycloneDo's": array=new Array_adapter(this,cyclone);
		break;
		case "TsunamiDo's": array=new Array_adapter(this,tsunami);
		break;
		case "FireDo's": array=new Array_adapter(this,fire);
		break;
		case "FloodDo's": array=new Array_adapter(this,flood);
		break;
		case "EarthquakeDont's": array=new Array_adapter(this,earthquaked);
		break;
		case "CycloneDont's": array=new Array_adapter(this,cycloned);
		break;
		case "TsunamiDont's": array=new Array_adapter(this,tsunamid);
		break;
		case "FireDont's": array=new Array_adapter(this,fired);
		break;
		case "FloodDont's": array=new Array_adapter(this,floodd);
		break;
		
	
			}
		
		listview.setAdapter(array); 
		
		
		}
	
	
	public class Array_adapter extends ArrayAdapter {

		private int color;
		
		Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/fontawesome-webfont.ttf");
		public Array_adapter(Context context,String[] item) {
			super(context,-1,item);
			
		}
		
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
				View row = convertView;
			
				//int[] color={R.color.icon1};
				
				if(row == null) {
				row = LayoutInflater.from(getContext()).inflate(R.layout.do_list, parent, false);
				}
				String item = (String) getItem(position);
				
				LinearLayout rel=(LinearLayout)row.findViewById(R.id.img1);
				TextView img=(TextView) row.findViewById(R.id.img_text);
				TextView text = (TextView)row.findViewById(R.id.txt1);
				//ImageView img1=(ImageView) row.findViewById(R.id.callbut);
				
				
				
				text.setTypeface(typeFace);
			    rel.setBackgroundResource(R.color.but_ok);
			   	img.setText(""+(position+1));
			   
				text.setText(item);
				    
				return row;
				}
			
			

	}	
	
	


}
