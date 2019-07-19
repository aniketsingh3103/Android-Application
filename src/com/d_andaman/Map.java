package com.d_andaman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class Map extends Activity{

	private WebView webView;

    public class WebAppInterface {
        /** Show a toast from svg */
        @JavascriptInterface
        public void showToast(String toast) {
          //Toast.makeText(MainActivity.this, toast, Toast.LENGTH_SHORT).show();
        }
      }
  

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.map);

    webView = (WebView) findViewById(R.id.mapview);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.addJavascriptInterface(new WebAppInterface(), "Android");

    String svg = loadSvg();
    webView.loadData(svg, "image/svg+xml", "utf-8");
   
    
  }

  String loadSvg() {
    try {
      BufferedReader input = new BufferedReader(new InputStreamReader(
          getAssets().open("and.html")));
      StringBuffer buf = new StringBuffer();
      String s = null;
      while ((s = input.readLine()) != null) {
        buf.append(s);
        buf.append('\n');
      }
      input.close();
      return buf.toString();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return null;
  }
  
  
	
	
}
