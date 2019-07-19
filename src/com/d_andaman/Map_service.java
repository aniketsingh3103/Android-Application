package com.d_andaman;

import android.app.ProgressDialog;
import android.content.Context;

import android.os.AsyncTask;
import android.widget.TextView;


public class Map_service extends AsyncTask<String,Void,String>{
   private TextView lat,lon;
   private Context context;
   private Location_Manager m;

   
   
   public  Map_service(Context contex,TextView lat,TextView lon) {
     this.context=contex;
     this.lat=lat;
     this.lon=lon;
      m=new Location_Manager();
   }
   
   protected void onPreExecute(){
	  
	  
   }
   
   @Override
   protected String doInBackground(String... arg0) {
     String lat= (String)arg0[0];
     String lon= (String)arg0[1];
     
     double lat1=Double.parseDouble(lat);
     double lon1=Double.parseDouble(lon);
     	
     String location=m.checklocation(lat1, lon1);
     
     return location;
   }
   
   @Override
   protected void onPostExecute(String result){
	
	   if(result=="X"){
		   lat.setText("Location Service is Unavilable to find your location");
		   lon.setText("Your location is still under discovery");
	   }else{
		   lon.setText(result);
	   }
	   
    }


   
  

 
        
}