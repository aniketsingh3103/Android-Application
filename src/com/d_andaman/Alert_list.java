package com.d_andaman;
 
public class Alert_list {
     
   
    int id;
    String alert_text;
     
    public Alert_list(){
    	
    }
    // constructor
    public Alert_list(int id, String name){
        this.id = id;
        this.alert_text = name;
        
    }
     
  
    public int getID(){
        return this.id;
    }
     
    // setting id
    public void setID(int id){
        this.id = id;
    }
     
    // getting name
    public String getName(){
        return this.alert_text;
    }
     
    // setting name
    public void setName(String name){
        this.alert_text = name;
    }
     
  
}