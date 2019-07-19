package com.d_andaman;
 
public class Notification_data {
     
   
    int id;
    String name;
     
    public Notification_data(){
    	
    }
    // constructor
    public Notification_data(int id, String name){
        this.id = id;
        this.name = name;
        
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
        return this.name;
    }
     
    // setting name
    public void setName(String name){
        this.name = name;
    }
     
    // getting phone number
    //public String getPhoneNumber(){
       // return this.phone_number;
   // }
     
    // setting phone number
   //public void setPhoneNumber(String phone_number){
       // this.phone_number = phone_number;
   // }
}