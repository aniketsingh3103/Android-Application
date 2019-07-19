package com.d_andaman;

import java.lang.ref.Reference;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class Showuser extends Activity{

	private ListView listView;
	private Button refresh;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_list);
		
		ActionBar action = getActionBar();
		action.setIcon(R.drawable.meanu_bar);
		action.setTitle("Contact");
		
		TextView tex=(TextView) findViewById(R.id.user_name);
		
		SharedPreferences sp = getSharedPreferences("my_db",Context.MODE_PRIVATE);
		String name=sp.getString("name","User");
		
		tex.setText("Welcome	\t"+name.toUpperCase());
		
		listView = (ListView) findViewById(R.id.userlist);
		//refresh=(Button) findViewById(R.id.refresh);
		
		
		
		

		String[] values = new String[] {"Aniket","Sandeepa","Sonia" };
		
		Array_adapter array=new Array_adapter(this, R.layout.contact_list,R.id.name,values);

		listView.setAdapter(array); 
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) {
				
				  Message d=new Message();
				  d.show(getFragmentManager(), null);
				  
				  }}); 
		
		
		refresh.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						Readdata.class);
				startActivity(i);
				
				
				
				
				
			}
		});
		
		
		
	}
//-------------------------------------------------------------------------------------------------
	
	
	public class Array_adapter extends ArrayAdapter {

		public Array_adapter(Context context, int resource,int text,String[] item) {
			super(context, resource,text,item);

		}



		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			String[] number= new String[]
					{"FCI godown","SBI atm","DBRAIT"};
			
			if(row == null) {
				row = LayoutInflater.from(getContext()).inflate(R.layout.contact_list, parent, false);
			}
			String item = (String) getItem(position);
			
			ImageView img=(ImageView) row.findViewById(R.id.contact_img);
			
			ImageView img1=(ImageView) row.findViewById(R.id.callbut);

			TextView left =(TextView)row.findViewById(R.id.number);

			TextView text = (TextView)row.findViewById(R.id.name);
			
			left.setText(number[position]);
			img.setImageResource(R.drawable.orange);
			img1.setImageResource(R.drawable.call);
			text.setText(item);
			return row;
		}



	}
	
}
