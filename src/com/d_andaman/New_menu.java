package com.d_andaman;

import java.io.ObjectOutputStream.PutField;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.d_andaman.R;


import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

import android.widget.TextView;

import android.widget.AdapterView.OnItemClickListener;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;

@SuppressLint("NewApi")
public class New_menu extends Activity implements OnMenuItemClickListener{
	GridView listView ;
	Button alert,sos;
	Animation animFadein;
	View layout;
	TextView day1,date1,month1,year1,weather;
	public Database db;
	public static int pos=0;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;   
	private ActionBarDrawerToggle mDrawerToggle;
	private GPSTracker gps;
	
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Calendar calendar = Calendar.getInstance(); 

		SimpleDateFormat day = new SimpleDateFormat("EEEE");
		SimpleDateFormat date = new SimpleDateFormat("dd");
		SimpleDateFormat month = new SimpleDateFormat("MMMM");
		SimpleDateFormat year = new SimpleDateFormat("yyyy");


		day1=(TextView) findViewById(R.id.day);
		date1=(TextView) findViewById(R.id.date);
		month1=(TextView) findViewById(R.id.month);
		year1=(TextView) findViewById(R.id.year);
		weather=(TextView) findViewById(R.id.weather);

		day1.setText(day.format(calendar.getTime()));
		date1.setText(date.format(calendar.getTime()));
		month1.setText(month.format(calendar.getTime()));
		year1.setText(year.format(calendar.getTime()));
		String str=""+Get_weather.get_weather()+"C";
		weather.setText(str);

		//TextView lblListHeader = (TextView) findViewById(R.id.movingtext);
		//lblListHeader.setSelected(true);
		 
		
	
	      
			mDrawerList=(ListView) findViewById(R.id.drawerlist);
		
		//LinearLayout DrawerList=(LinearLayout) findViewById(R.id.dra);
	       mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
	       getActionBar().setDisplayHomeAsUpEnabled(true);   
	       getActionBar().setHomeButtonEnabled(true);

	       mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.drawable.aniket3, R.string.app_name,
	    		   R.string.app_name) {
	    	   public void onDrawerClosed(View view) { 
               //getActionBar().setTitle(mTitle); 
               // calling onPrepareOptionsMenu() to show action bar icons 
                invalidateOptionsMenu(); 
                } 
    public void onDrawerOpened(View drawerView) {           
          // getActionBar().setTitle(mDrawerTitle); 
     // calling onPrepareOptionsMenu() to hide action bar icons 
           invalidateOptionsMenu();          } 
          }; 
		
		 		 
	       mDrawerLayout.setDrawerListener(mDrawerToggle); 


		ActionBar action = getActionBar();
		action.setIcon(R.drawable.meanu_bar);
		action.setTitle("Menu");

		listView = (GridView) findViewById(R.id.menu_list);

		alert=(Button) findViewById(R.id.alert_but);
		sos=(Button) findViewById(R.id.sos);

		
		
		String[] values = new String[] { "Friends","Do's and Dont's", "Videos", 
				"Feedback","Contact","Inbox","Location","Survivial Kit","Family"};
		String[] drwa_data={"Disaster Management","Notification","App Manual","About DDM"};
		
		Array_adapter array=new Array_adapter(this, R.layout.list_view,R.id.list_text,values);

		listView.setAdapter(array); 
		Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/prince.ttf");

		DrawerAdapter draw=new DrawerAdapter(this,drwa_data,typeFace);

		mDrawerList.setAdapter(draw); 


		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {


				int itemPosition  = position;


				String  itemValue    = (String) listView.getItemAtPosition(position);

				if(itemPosition==0){
					String MY_DB = "my_db";
					SharedPreferences sp = getSharedPreferences(MY_DB,Context.MODE_PRIVATE);

					int  id1 = sp.getInt("id",0);
					if (id1==0) {
						Intent i = new Intent(getApplicationContext(),
								Userprofil.class);
					startActivity(i);
										}else{

						Intent i = new Intent(getApplicationContext(),
								Readdata.class);
						startActivity(i);
					}

				}
				if(itemPosition==1){
					Intent i = new Intent(getApplicationContext(),
							Expand.class);
					startActivity(i);

				}
				if(itemPosition==2){
					Intent i = new Intent(getApplicationContext(),
							Ved_img.class);
					startActivity(i);

				}
				
				if(itemPosition==3){
					Intent i = new Intent(getApplicationContext(),
							Send_feedback.class);
					startActivity(i);

				}
				if(itemPosition==4){
					Intent i = new Intent(getApplicationContext(),
							Contact.class);
					startActivity(i);

				}
				if(itemPosition==5){



					Intent i = new Intent(getApplicationContext(),
							list_class.class);
					startActivity(i);

				}
				
				if(itemPosition==6){



					Intent i = new Intent(getApplicationContext(),
							Location_class.class);
					startActivity(i);

				}
				if(itemPosition==7){
					Intent i = new Intent(getApplicationContext(),
							Kit_display.class);
					startActivity(i);

				}
				
				if(itemPosition==8){
					Intent i = new Intent(getApplicationContext(),
							Family.class);
					startActivity(i);

				}




			}


		}); 



		alert.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				
				gps = new GPSTracker(New_menu.this);
				gps.canGetLocation();
				
		       Intent i = new Intent(getApplicationContext(),
						Dialog.class);
				
				startActivity(i);

			}   });
		
		
		/*sos.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent callIntent = new Intent(Intent.ACTION_DIAL);
				callIntent.setData(Uri.parse("tel:"+1077));
				try{
					startActivity(callIntent);
				}catch (android.content.ActivityNotFoundException ex){
					Toast.makeText(getApplicationContext(),"yourActivity is not founded",Toast.LENGTH_SHORT).show();
				}

			}   });*/
		
		sos.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						Dialog_sos.class);
				
				startActivity(i);

			}   });
		
		
		
		mDrawerList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					
				if(position==1){
					Intent i = new Intent(getApplicationContext(),
							Seoc_message.class);
					startActivity(i);
				}
				
				if(position==2){
					Intent i = new Intent(getApplicationContext(),
						Help.class);
					startActivity(i);
				}
				
				if(position==3){
					Intent i = new Intent(getApplicationContext(),
							About.class);
					startActivity(i);
				}
				
				
			}
		});
				
				


		
		
	}

	public class Array_adapter extends ArrayAdapter {


		Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Zekton.ttf");
		public Array_adapter(Context context, int resource,int text,String[] item) {
			super(context, resource,text,item);

		}



		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;

			
			//String[] tx_val=new String[] {"F","D","V","F","C","N","L","S","F"};
			int[] img={R.drawable.friends,R.drawable.dodont5,R.drawable.vedios2,R.drawable.feedback5,
					R.drawable.contact5,R.drawable.notifi4,R.drawable.location4,R.drawable.kit4,R.drawable.family};
			
			
			if(row == null) {
				row = LayoutInflater.from(getContext()).inflate(R.layout.grid_view, parent, false);
			}
			String item = (String) getItem(position);

			
			ImageView left=(ImageView)row.findViewById(R.id.grid_img);
			TextView text = (TextView)row.findViewById(R.id.grid_text);
			//TextView text1 = (TextView)row.findViewById(R.id.icon_text);
			
			

			left.setImageResource(img[position]);
			//rel.setBackgroundResource(color1[position]);
			//text1.setText(tx_val[position]);
			text.setTypeface(typeFace);
			text.setText(item);
			
			return row;
		}
	


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.head, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		  
			 if (mDrawerToggle.onOptionsItemSelected(item)) {                   
			 return true;          
			 }
		View view=findViewById(R.id.setting);
		//View view1=findViewById(R.id.notification);
		switch (item.getItemId()) {
		case R.id.setting:
			Popup(view);
			return super.onOptionsItemSelected(item);
		case R.id.notification:
			notification();
			return super.onOptionsItemSelected(item);
		default:
			return super.onOptionsItemSelected(item);
		}

	}




	public void Popup(View v) {
		PopupMenu popup = new PopupMenu(this, v);
		popup.setOnMenuItemClickListener( this);
		MenuInflater inflater = popup.getMenuInflater();

		inflater.inflate(R.menu.actions, popup.getMenu());
		popup.show();
	}

	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.about1:
			display_popup();
			return true;
		//case R.id.setting1:
			//return false;
		case R.id.alert1:
			Intent i = new Intent(getApplicationContext(),
					Alert.class);
			startActivity(i);
			return true;
			
		default:return false;
		}
	}

	public void display_popup(){

		String url = "http://www.ddm.and.nic.in/";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}



	private void notification() {
		Intent i = new Intent(getApplicationContext(),
				Seoc_message.class);
		startActivity(i);


	}



}

