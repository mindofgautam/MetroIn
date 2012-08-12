package com.mi;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 
 * @author Metro-In
 *
 */

public class Main_Page extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        
              
        /**
         * DataBaseHelper is a class being used to copy the database to 
         * each device from the assets folder.
         */
        
        DataBaseHelper myDbHelper = new DataBaseHelper(this);
       
 
        try {
 
        	myDbHelper.createDataBase();
 
 	} catch (IOException ioe) {
 
 		throw new Error("Unable to create database");
 
 	}
 
 	try {
 
 		myDbHelper.openDataBase();
 
 	}catch(SQLException sqle){
 
 		throw sqle;
 
 	}

     
 	
       TextView txt = (TextView) findViewById(R.id.textView1);   
       /**
        * txt-textview for heading
        */
       Typeface font = Typeface.createFromAsset(getAssets(), "VIVALDII.TTF");  
       /**
        * Typeface is being used to change the font of the textviews
        */
       txt.setTypeface(font);
       TextView txt1 = (TextView) findViewById(R.id.textView2);   
       /**
        * txt1-textview for mainmenu text
        */
       Typeface font1 = Typeface.createFromAsset(getAssets(), "AGENCYB.TTF");   
       txt1.setTypeface(font1);
        
        //Database
       /**
        * to create the databases the first time
        */
        /*SQLiteDatabase db;
        db = openOrCreateDatabase(
       		"metroin.db"
       		, SQLiteDatabase.CREATE_IF_NECESSARY
       		, null
      		);
        db.setVersion(1);
        db.setLocale(Locale.getDefault());
        db.setLockingEnabled(true);
        final String CREATE_TABLE_BUSDET =
        	"CREATE TABLE bus_det("
        	+ "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        	+ "busno TEXT,"
        	+ "clss TEXT,"
        	+ "frml TEXT,"
        	+ "tol TEXT,"
        	+ "ett TEXT,"
        	+ "fare TEXT,"
        	+ "route TEXT,"
        	+ "ftr TEXT);";
        	final String CREATE_TABLE_LATLONG =
        	"CREATE TABLE lat_lon("
        	+ "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        	+ "place TEXT,"
        	+ "latlon TEXT);";
        	
         db.execSQL(CREATE_TABLE_BUSDET);
        	db.execSQL(CREATE_TABLE_LATLONG);*/
         
        Typeface font2 = Typeface.createFromAsset(getAssets(), "CALIBRI.TTF");
        /**
         * bt1-button for search by location
         * bt2-button for search by bus no
         * bt3- button for view frequent routes
         */
        final Button bt1= (Button) findViewById(R.id.sloc);
        final Button bt2= (Button) findViewById(R.id.sno);
        final Button bt3= (Button) findViewById(R.id.ftr);
        final ImageButton bt4= (ImageButton) findViewById(R.id.helpb);
        final ImageButton bt5= (ImageButton) findViewById(R.id.abtb);
        bt1.setTypeface(font2);
        bt2.setTypeface(font2);
        bt3.setTypeface(font2);
        bt1.setOnClickListener(new OnClickListener()
        {
        		/*
        		 * (non-Javadoc)
        		 * @see android.view.View.OnClickListener#onClick(android.view.View)
        		 */
        	   	public void onClick(View v) {
        	   		 /**
        	         * intent to start search by location
        	         */
        	   	Intent myIntent1 = new Intent(Main_Page.this, Get_Loc.class);
        		startActivity(myIntent1);
            }
        });
        bt2.setOnClickListener(new OnClickListener()
        {
        	/*
        	 * (non-Javadoc)
        	 * @see android.view.View.OnClickListener#onClick(android.view.View)
        	 */
        	   	public void onClick(View v) {
        	   		 /**
        	         * intent to start search by bus no
        	         */
        	   	Intent myIntent2 = new Intent(Main_Page.this, Get_Busno.class);
        		startActivity(myIntent2);
            }
        });
        bt3.setOnClickListener(new OnClickListener()
        {
        	/*
        	 * (non-Javadoc)
        	 * @see android.view.View.OnClickListener#onClick(android.view.View)
        	 */
        	   	public void onClick(View v) {
        	   		 /**
        	         * intent to view frequent routes
        	         */
        	   	Intent myIntent3 = new Intent(Main_Page.this, Freq_Routes.class);
        		startActivity(myIntent3);
            }
        });
        bt4.setOnClickListener(new OnClickListener()
        {
        		/*
        		 * (non-Javadoc)
        		 * @see android.view.View.OnClickListener#onClick(android.view.View)
        		 */
        	   	public void onClick(View v) {
        	   		 /**
        	         * intent to start help page
        	         */
        	   	Intent myIntent4 = new Intent(Main_Page.this, Help.class);
        		startActivity(myIntent4);
            }
        });
        bt5.setOnClickListener(new OnClickListener()
        {
        		/*
        		 * (non-Javadoc)
        		 * @see android.view.View.OnClickListener#onClick(android.view.View)
        		 */
        	   	public void onClick(View v) {
        	   		 /**
        	         * intent to start help page
        	         */
        	   	Intent myIntent5 = new Intent(Main_Page.this, About.class);
        		startActivity(myIntent5);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	 /**
         * Creating the pop up menu
         */
     menu.add(1, 1, Menu.FIRST, "Exit");
     menu.add(2, 2, Menu.NONE, "Help");
     menu.add(2, 3, Menu.NONE, "About Us");
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
           * Help Page
           */
    	  Intent myIntent = new Intent(Main_Page.this, Help.class);
  		  startActivity(myIntent);                                                                     
      return true;
      case 3:
    	  /**
           * About Us Page
           */
    	  Intent myIntent1 = new Intent(Main_Page.this, About.class);
  		  startActivity(myIntent1);                                                                     
      return true;
     }
     return super.onOptionsItemSelected(item);
    }
  }