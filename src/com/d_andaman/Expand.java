package com.d_andaman;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;
 
public class Expand extends Activity {
 
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expand);
 
        
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
 
       
        prepareListData();
 
        listAdapter = new Expandablelist(this, listDataHeader, listDataChild);
 
       
        expListView.setAdapter(listAdapter);
        
        expListView.setOnGroupClickListener(new OnGroupClickListener() {
        	 
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                    int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });
 
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
 
            @Override
            public void onGroupExpand(int groupPosition) {
                
            }
        });
 
        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
 
            @Override
            public void onGroupCollapse(int groupPosition) {
               
 
            }
        });
 
        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {
 
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                    int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
            	 String itemPosition=listDataHeader.get(groupPosition)
                         + listDataChild.get(
                                 listDataHeader.get(groupPosition)).get(
                                 childPosition);
               
                 Intent i = new Intent(getApplicationContext(),
						Display_dos.class);
			   i.putExtra("arg", itemPosition);
			   
				startActivity(i);
                return false;
            }
        });
        
        
        
    }
 
   
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        listDataHeader.add("Earthquake");
        listDataHeader.add("Cyclone");
        listDataHeader.add("Tsunami");
        listDataHeader.add("Fire");
        listDataHeader.add("Flood");
        List<String> dodont = new ArrayList<String>();
        dodont.add("Do's");
        dodont.add("Dont's");
        listDataChild.put(listDataHeader.get(0), dodont);
        listDataChild.put(listDataHeader.get(1), dodont);
        listDataChild.put(listDataHeader.get(2), dodont);
        listDataChild.put(listDataHeader.get(3), dodont);
        listDataChild.put(listDataHeader.get(4), dodont);
        
    }
}