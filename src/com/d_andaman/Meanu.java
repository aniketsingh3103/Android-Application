package com.d_andaman;

import com.d_andaman.R;

import android.R.layout;
import android.R.menu;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class Meanu extends Activity implements OnMenuItemClickListener{
	ListView listView ;
	Button alert;
	Animation animFadein;
	View layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		TextView tv = (TextView) this.findViewById(R.id.movingtext);  
        tv.setSelected(true);
		animFadein = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);  
		
		
		ActionBar action = getActionBar();
		action.setIcon(R.drawable.meanu_bar);
		action.setTitle("Menu");
		
		 listView = (ListView) findViewById(R.id.menu_list);
		 
		 alert=(Button) findViewById(R.id.alert_but);
		 
		 layout=findViewById(R.id.linearlayout3);
		
		String[] values = new String[] { "Your blog","Do's and Dont's", "Videos and Images",
				  "Relief Shelter map", "Feedback","Contact","Make note"};	
	Array_adapter array=new Array_adapter(this, R.layout.list_view,R.id.list_text,values);
	
		listView.setAdapter(array); 
		
		
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) {
				
		    
			   int itemPosition  = position;
			   
			   
			   String  itemValue    = (String) listView.getItemAtPosition(position);
				  
			   if(itemPosition==0){
				   String MY_DB = "my_db";
					SharedPreferences sp = getSharedPreferences(MY_DB,Context.MODE_PRIVATE);
					  
					   boolean hasVisited = sp.getBoolean("hasVisited",false);
					   if (!hasVisited) {
						   Intent i = new Intent(getApplicationContext(),
									Userprofil.class);
							startActivity(i);
							
						Editor e = sp.edit();
						e.putBoolean("hasVisited", true);
						e.commit();

						   
						}else{
				   
				   Intent i = new Intent(getApplicationContext(),
							Readdata.class);
					startActivity(i);
						}
					
			   }
			   if(itemPosition==1){
				   Intent i = new Intent(getApplicationContext(),
							Do_dont.class);
					startActivity(i);
					
			   }
			   if(itemPosition==2){
				   Intent i = new Intent(getApplicationContext(),
							Ved_img.class);
					startActivity(i);
					
			   }
			   if(itemPosition==3){
				   Intent i = new Intent(getApplicationContext(),
							Relif.class);
					startActivity(i);
					
			   }
			   if(itemPosition==4){
				   Intent i = new Intent(getApplicationContext(),
							feedback.class);
					startActivity(i);
					
			   }
			   if(itemPosition==5){
				   Intent i = new Intent(getApplicationContext(),
							Contact.class);
					startActivity(i);
				
			   }
			   if(itemPosition==6){
				   Intent i = new Intent(getApplicationContext(),
							list_class.class);
					startActivity(i);
				
			   }
			   
			  
			    
			 
			  }

			
			}); 
		
		
		
		alert.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(),
						Dialog.class);
				startActivity(i);
			}   });
		
		
		//animFadein.setAnimationListener(this);
		 
        // button click event
        layout.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View v) {
            	Intent i = new Intent(getApplicationContext(),
						Slidedown.class);
				startActivity(i);
            }
        });
		
	}
	
	
	
	public class Array_adapter extends ArrayAdapter {

		
		
		
		
		Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/museo_slab_300-webfont.ttf");
		
		public Array_adapter(Context context, int resource,int text,String[] item) {
			super(context, resource,text,item);
			
		}
		
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
				View row = convertView;
				
				int[] img={R.drawable.user,R.drawable.do_dont,R.drawable.vedio,R.drawable.relif1,R.drawable.feedback2,R.drawable.contact1,R.drawable.contact};
				
				if(row == null) {
				row = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
				}
				String item = (String) getItem(position);
				
				ImageView left =(ImageView)row.findViewById(R.id.list_img);
				
				TextView text = (TextView)row.findViewById(R.id.list_text);
				
				left.setImageResource(img[position]);
				
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
	    // Handle item selection
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
	       // case R.id.setting1:
	            //delete(item);
	        default:return false;
	    }
	}
	
	public void display_popup(){
		
		String url = "http://www.dmn.nic.in";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
	}

	
	
private void notification() {
	Intent i = new Intent(getApplicationContext(),
			Seoc_message.class);
	startActivity(i);
	finish();
		
	}
	
	
	
}

