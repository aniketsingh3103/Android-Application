package com.d_andaman;
import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
 
public class Alert_database extends SQLiteOpenHelper {
 
    private static final int DATABASE_VERSION = 1;
 
  
    private static final String DATABASE_NAME = "aniket";
 
    
    private static final String TABLE_NOTIFICATION = "alert_table";
   
 
   
    private static final String KEY_ID = "id";
    private static final String KEY_NOTIFICATION = "alert_txt";
   
 
    public  Alert_database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTIFICATION_TABLE = "CREATE TABLE " + TABLE_NOTIFICATION + "("
                + KEY_ID + " INTEGER," + KEY_NOTIFICATION + " TEXT)";
       
        db.execSQL(CREATE_NOTIFICATION_TABLE);
    }
 
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
 
        
        onCreate(db);
    }
 
  
    
    void addContact(Alert_list contact) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_NOTIFICATION, contact.getName()); 
        values.put(KEY_ID, contact.getID()); 
 
   
        db.insert(TABLE_NOTIFICATION, null, values);
        db.close(); 
    }
 
  
    Alert_list getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_NOTIFICATION, new String[] { KEY_ID,
                KEY_NOTIFICATION }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Alert_list contact = new Alert_list(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
       
        return contact;
    }
     
   
    public List<Alert_list> getAllContacts() {
        List<Alert_list> contactList = new ArrayList<Alert_list>();
        
        String selectQuery = "SELECT  * FROM " + TABLE_NOTIFICATION;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
       
        if (cursor.moveToFirst()) {
            do {
            	Alert_list contact = new Alert_list();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
               
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
 
    // Updating single contact
    public int updateContact(Notification_data contact) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_ID, contact.getID());
        values.put(KEY_NOTIFICATION, contact.getName());
        
 
     
        return db.update(TABLE_NOTIFICATION, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }
 

    public void deleteContact(Alert_list contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NOTIFICATION, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }
 
 
    
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NOTIFICATION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 

        return cursor.getCount();
    }
 
}