package com.d_andaman;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.ProgressDialog;
import android.content.Context;

import android.os.AsyncTask;
import android.widget.TextView;


public class Login  extends AsyncTask<String,Void,JSONObject>{

   private Context context;
   private int byGetOrPost = 0;
   
   
   public Login(Context context,int flag) {
      this.context = context;
    
      byGetOrPost = flag;
      
   }
   
   protected void onPreExecute(){
	   
	   	

   }
   
   @Override
   protected JSONObject doInBackground(String... arg0) {
      if(byGetOrPost == 0){ //means by Get Method
      
      try{
         String phone = (String)arg0[0];
         String link = "http://127.0.0.1/login.php?phone="+phone;
         
         
         HttpClient client = new DefaultHttpClient();
         HttpGet request = new HttpGet();
         request.setURI(new URI(link));
         HttpResponse response = client.execute(request);
         BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

           StringBuffer sb = new StringBuffer("");
           String line="";
           
           while ((line = in.readLine()) != null) {
              sb.append(line);
              break;
            }
            in.close();
           
         }catch(Exception e){return null;}
      }
      else{
         try{
            String phone = (String)arg0[0];
            
            String link="http://192.168.83.1/dand_api/check.php";
            String data  = URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8");
            
            
            URL url = new URL(link);
            URLConnection conn = url.openConnection(); 
            
            conn.setDoOutput(true); 
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 
            
            wr.write( data ); 
            wr.flush(); 
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            StringBuilder sb = new StringBuilder();
            String line = null;
            
            while((line = reader.readLine()) != null)
            {
               sb.append(line);
               break;
            }
           
        return new JSONObject(sb.toString());
            
         }
         catch(Exception e){e.printStackTrace();}
         
      }
      
	return null;
   }
   
   @Override
   protected void onPostExecute(JSONObject result){
	  int i=convertjson(result);
	  
	  MainActivity.setid(i);
	
    }
   
   
   private int convertjson(JSONObject result) {
	   
	   
		try{
			JSONArray id=result.getJSONArray("id");
			
			JSONObject u=id.getJSONObject(0);
				
			return u.getInt("id");
		}catch(JSONException e){e.printStackTrace();		}
		return 0;
	}

 
        
}