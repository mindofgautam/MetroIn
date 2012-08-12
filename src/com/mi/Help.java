package com.mi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Help extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_screen);
        TextView txt = (TextView) findViewById(R.id.textView1);
        Typeface font = Typeface.createFromAsset(getAssets(), "VIVALDII.TTF");  
        txt.setTypeface(font);
        TextView txt5 = (TextView) findViewById(R.id.t1);
        Typeface font2 = Typeface.createFromAsset(getAssets(), "AGENCYB.TTF");  
        txt5.setTypeface(font2);
        
        TextView txt1 = (TextView) findViewById(R.id.textView2); 
        TextView txt2 = (TextView) findViewById(R.id.textView3);
        TextView txt3 = (TextView) findViewById(R.id.textView4);
        TextView txt4 = (TextView) findViewById(R.id.textView5);
        Typeface font1 = Typeface.createFromAsset(getAssets(), "CALIBRI.TTF");   
        txt1.setTypeface(font1);
        txt2.setTypeface(font1);
        txt3.setTypeface(font1);
        txt4.setTypeface(font1);
}
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	 /**
         * Creating the pop up menu
         */
     menu.add(1, 1, Menu.FIRST, "Back");
     menu.add(1, 2, Menu.NONE, "About Us");
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
    	  Intent myIntent = new Intent(Help.this, About.class);
  		  startActivity(myIntent);
  		  finish();
      return true;
     }
     return super.onOptionsItemSelected(item);
    }
}
