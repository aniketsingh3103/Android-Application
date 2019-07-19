package com.d_andaman;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Button;

@SuppressLint("NewApi")
public class Flash extends Activity{

	private Button but;

	@Override

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flash);
		hideSystemUI();  
		Thread background = new Thread() {
			public void run() {

				try {
					sleep(2*1000);
					Intent i=new Intent(getBaseContext(),MainActivity.class);
					startActivity(i);
					finish();

				} catch (Exception e) {

				}
			}
		};


		background.start();
	}


	private void hideSystemUI() {
		getWindow().getDecorView().setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
				| View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
				// remove the following flag for version < API 19
				| View.SYSTEM_UI_FLAG_IMMERSIVE
				);
	}


}
