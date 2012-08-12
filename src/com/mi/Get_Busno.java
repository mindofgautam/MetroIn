package com.mi;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Metro-In
 * 
 * @i: flag to check whether the bus number exists in database
 *
 */
public class Get_Busno extends Activity {
	
	public int i=0;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sbno_get);
        
        /**
		 * txt-textview for heading
		 * font-typeface to change font of the textview
		 * txt1-textview to print the error message
		 */
        
        TextView txt = (TextView) findViewById(R.id.textView3);   
        Typeface font = Typeface.createFromAsset(getAssets(), "VIVALDII.TTF");   
        txt.setTypeface(font);
        TextView txt1 = (TextView) findViewById(R.id.tv1);   
        Typeface font1 = Typeface.createFromAsset(getAssets(), "AGENCYB.TTF");   
        txt1.setTypeface(font1);
        
        final EditText t1 = (EditText) findViewById(R.id.bdta);
               
        final SQLiteDatabase db;
        db = openOrCreateDatabase(
       		"metroin1.db"
       		, SQLiteDatabase.CREATE_IF_NECESSARY
       		, null
       		);
        db.setVersion(1);
        db.setLocale(Locale.getDefault());
        db.setLockingEnabled(true);
        
        final TextView rr1=(TextView) findViewById(R.id.tv1);
        
        Typeface font2 = Typeface.createFromAsset(getAssets(), "CALIBRI.TTF");
        
        /**
		 * button1-to start search
		 */
        final Button button1 = (Button) findViewById(R.id.bnob);
        button1.setTypeface(font2);
        button1.setOnClickListener(new View.OnClickListener() {
        	/*
        	 * (non-Javadoc)
        	 * @see android.view.View.OnClickListener#onClick(android.view.View)
        	 */
        public void onClick(View view) {
        i=0;        
        
        String bno=t1.getText().toString().toUpperCase().trim();
                
        Cursor cur = db.query("bus_det", 
           		null, null, null, null, null, null);
            cur.moveToFirst();
            
            while (cur.isAfterLast() == false) {
                if(cur.getString(1).equals(bno))
                {
                	i=1;
                }
           	    cur.moveToNext();
            }
            cur.close(); 
            
        
        if(i==1)
        {
        	
        	/**
    		 * bundling the bus number and sending it to the next activity to print
    		 * the details
    		 */
        	rr1.setText("");
        	Bundle bundle = new Bundle(); 
           	bundle.putString("bno", bno);
           	
           	/**
    		 * starting the activity to print details
    		 */
           	Intent myIntent = new Intent(view.getContext(), Print_Busno.class);
           	myIntent.putExtras(bundle);
            startActivity(myIntent);
            t1.setText("");
        }
        else
        {
        	
        	/**
    		 *Printing the error message when route does not exist
    		 */
        	t1.setText("");
        	rr1.setText("The specified bus number does not exist, please search again with a valid bus no:");
        	
            
        }
        if(bno.equals(""))
        {
        	/**
    		 * Toasting error message when the editbox is left empty
    		 */
        	rr1.setText("");
        	Toast.makeText(getApplicationContext(),"Bus No not Specified", Toast.LENGTH_LONG).show();
        	t1.requestFocus(1);
        	
        }
        }
        
        });
         
        
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	/**
		 * creating the popup menu
		 */
     menu.add(1, 1, Menu.NONE, "Search By Location");
     menu.add(1, 2, Menu.NONE, "Frequently Travelled Routes");
     return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
     switch(item.getItemId())
     {
      case 1:
    	  /**
    		 * starting activity to search by location
    		 */
   	  Intent myIntent1 = new Intent(Get_Busno.this, Get_Loc.class);
         startActivity(myIntent1);
         finish();
         return true;
      case 2:
    	  
    	  /**
    		 * starting activity to view frequent routes
    		 */
    	  Intent myIntent2 = new Intent(Get_Busno.this, Freq_Routes.class);
          startActivity(myIntent2);
          finish();
          return true;
     }
     return super.onOptionsItemSelected(item);
    }
}


