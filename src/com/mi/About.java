package com.mi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class About extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        TextView txt = (TextView) findViewById(R.id.textView1);
        Typeface font = Typeface.createFromAsset(getAssets(), "VIVALDII.TTF");  
        txt.setTypeface(font);
        TextView txt5 = (TextView) findViewById(R.id.t1);
        Typeface font2 = Typeface.createFromAsset(getAssets(), "AGENCYB.TTF");  
        txt5.setTypeface(font2);
        
        TextView t1 = (TextView) findViewById(R.id.t1);
        TextView t2 = (TextView) findViewById(R.id.t2);
        TextView t3 = (TextView) findViewById(R.id.t3);
        TextView t4 = (TextView) findViewById(R.id.t4);
        TextView t5 = (TextView) findViewById(R.id.t5);
        TextView t6 = (TextView) findViewById(R.id.t6);
        Typeface font3 = Typeface.createFromAsset(getAssets(), "CALIBRI.TTF");  
        t1.setTypeface(font3);
        t2.setTypeface(font3);
        t3.setTypeface(font3);
        t4.setTypeface(font3);
        t5.setTypeface(font3);
        t6.setTypeface(font3);
}
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	 /**
         * Creating the pop up menu
         */
     menu.add(1, 1, Menu.FIRST, "Back");
     menu.add(1, 2, Menu.NONE, "Help");
     return true;
    } 
	@Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
     switch(item.getItemId())
     {
      case 1:
    	  /**
           * Exiting from program
           */
    	  this.finish();                                                                     
      return true;
      case 2:
    	  /**
           * Exiting from program
           */
    	  Intent myIntent = new Intent(About.this, Help.class);
  		  startActivity(myIntent); 
  		  finish();
      return true;
     }
     return super.onOptionsItemSelected(item);
    }
}
