package com.d_andaman;

import android.widget.TextView;

public class Drawer_data_holder {
private TextView text;

public Drawer_data_holder(TextView text){
	this.text=text;
}

public void settext(TextView text){
	this.text=text;
}


public TextView gettext(){
	return this.text;
}


}
