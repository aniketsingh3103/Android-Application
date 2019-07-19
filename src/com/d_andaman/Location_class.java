package com.d_andaman;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Location_class extends Activity {
	
	Button btnShowLocation,stoplocation;
	TextView lat,lat1;
	TextView lon,lon1;
	
	GPSTracker gps;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        
        ActionBar action = getActionBar();
		action.setIcon(R.drawable.circle1);
		action.setTitle("Location Manager");

        btnShowLocation = (Button) findViewById(R.id.showlocation);
        stoplocation=(Button)findViewById(R.id.stop);
        
        lat=(TextView) findViewById(R.id.lat);
        lon=(TextView) findViewById(R.id.lon);
       // lat1=(TextView) findViewById(R.id.lat1);
       // lon1=(TextView) findViewById(R.id.lon1);
        
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {		
				// create class object
		        gps = new GPSTracker(Location_class.this);

				// check if GPS enabled		
		        if(gps.canGetLocation()){
		        	
		        	double latitude = gps.getLatitude();
		        	double longitude = gps.getLongitude();
		        	String latstr=""+latitude;
		        	String lonstr=""+longitude;
		        	
		        	//new Map_service(getApplicationContext(),lat,lon).execute(latstr,lonstr);
		        	
		        	
		        	lat.setText("LAT"+latstr);
		        	lon.setText("LON"+lonstr);
		        	//lat1.setText("LAT:"+latitude);
		        	//lon1.setText("LON:"+longitude);
		        	
		        }else{
		        	gps.showSettingsAlert();
		        }
		        }
			
			
		});
    
    stoplocation.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			 gps.stopUsingGPS();
			 Toast.makeText(getApplicationContext(),
						"Service stoped", Toast.LENGTH_SHORT).show();
			
		}
	});
    
    }
    
}