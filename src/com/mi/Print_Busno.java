package com.mi;

import java.util.Locale;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 
 * @author Metro-In
 * 
 *@bno: bno contains the bus number being searched
 *@frm: frm contains the 'from' location given by user
 *@too: too contains the 'to' destination given by the user
 *@sa: sa is the from locations latitude,longitude
 *@da: da is the to destinations latitude,longitude
 *@fl: fl is used to toast the from locations latitude and longitude
 *@dl: dl is used to toast the to destinations latitude,longitude
 */
public class Print_Busno extends Activity {
	
	String bno;
	String frm;
	String too;
	String sa="12.9141,80.2292";
	String da="12.9473,80.2400";
	String fl;
	String dl;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sbno_print);
        
        /**
		 * txt-textview for heading
		 * font-typeface to change font of the textview
		 * txt1-textview to print the details of the bus
		 */
        TextView txt = (TextView) findViewById(R.id.tv3);   
        Typeface font = Typeface.createFromAsset(getAssets(), "VIVALDII.TTF");   
        txt.setTypeface(font);
        TextView txt1 = (TextView) findViewById(R.id.tv4);   
        Typeface font2 = Typeface.createFromAsset(getAssets(), "AGENCYB.TTF");   
        txt1.setTypeface(font2);
        
        
        Bundle bundle = getIntent().getExtras(); 
        // extract the data   
        /**
		 * getting the data from the bundle sent by the activity of mi3a class
		 */
        bno = bundle.getString("bno"); 
        
        /**
		 * tv1-printing from location
		 * tv2-printing to destination
		 * tv3-printing bus no
		 * tv4-printing the class of bus
		 * tv5-printing the fare of the bus
		 * tv6-printing the expected travel time
		 * tv7-printing the route
		 */
       final TextView tv1=(TextView) findViewById(R.id.r1);
        final TextView tv2 =(TextView)findViewById(R.id.r2);
        final TextView tv3 =(TextView)findViewById(R.id.r3);
        final TextView tv4 =(TextView)findViewById(R.id.r4);
        final TextView tv5 =(TextView)findViewById(R.id.r5);
        final TextView tv6 =(TextView)findViewById(R.id.r6);
        final TextView tv7 =(TextView)findViewById(R.id.r7);
        Typeface font1 = Typeface.createFromAsset(getAssets(), "CALIBRI.TTF");   
        tv1.setTypeface(font1);
        tv2.setTypeface(font1);
        tv3.setTypeface(font1);
        tv4.setTypeface(font1);
        tv5.setTypeface(font1);
        tv6.setTypeface(font1);
        tv7.setTypeface(font1);
        
        /**
		 * printing the details of the selected bus from the database
		 */
        SQLiteDatabase db;
        
        db = openOrCreateDatabase(
       		"metroin1.db"
       		, SQLiteDatabase.CREATE_IF_NECESSARY
       		, null
       		);
        db.setVersion(1);
        db.setLocale(Locale.getDefault());
        db.setLockingEnabled(true);
        Cursor cur = db.query("bus_det",
           		null, null, null, null, null, null);
            cur.moveToFirst();
            while (cur.isAfterLast() == false) {
                if(cur.getString(1).equals(bno))
                {
                	tv3.setText("Bus no\t\t:"+cur.getString(1));
                	tv1.setText("From\t\t:"+cur.getString(3));
                	frm=cur.getString(3);
                	too=cur.getString(4);
                	tv2.setText("To\t\t\t:"+cur.getString(4));
                	tv4.setText("Class\t\t:"+cur.getString(2));
                	tv5.setText("Fare\t\t\t:"+cur.getString(6)+"Rs");
                	tv6.setText("Expected Travel Time :"+cur.getString(5)+"Mins");
                	tv7.setText("Via:"+cur.getString(7)+"");
                		
                }
                cur.moveToNext();
            }
       	cur.close();
       	
       	
       	/**
		 * retrieving the latitude and longitude for the route being searched
		 */
       	Cursor cur1 = db.query("lat_lon",
        		null, null, null, null, null, null);
         cur1.moveToFirst();
         while (cur1.isAfterLast() == false) {
             if(cur1.getString(1).equals(frm))
             {
             	sa=cur1.getString(2);
             	
             }
             if(cur1.getString(1).equals(too))
             {
             	da=cur1.getString(2);
             	
             }
             cur1.moveToNext();
         }
    	    
     
	    cur1.close();
   	    db.close();
   	    fl=frm+":"+sa;   
    	dl=too+":"+da;  
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	/**
		 * creating the popup menu
		 */
     menu.add(1, 1, Menu.FIRST, "Search Again");
     menu.add(1, 2, Menu.FIRST+1, "Show in Map");
     menu.add(2, 3, Menu.NONE, "Add to Frequently Travelled Routes");
     return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
     switch(item.getItemId())
     {
      case 1:
    	  /**
    		 * going back to previous activity
    		 */
    	  finish();
          return true;
      case 3:
    	  
    	  /**
    		 *adding to frequently travelled routes
    		 */
    	      	  SQLiteDatabase db;
          
          db = openOrCreateDatabase(
         		"metroin1.db"
         		, SQLiteDatabase.CREATE_IF_NECESSARY
         		, null
         		);
          db.setVersion(1);
          db.setLocale(Locale.getDefault());
          db.setLockingEnabled(true);
          ContentValues ftr = new ContentValues();
          ftr.put("ftr", "1");
          db.update("bus_det", ftr, "busno=?",new String[] {bno});
          Toast.makeText(getApplicationContext(),"Route Added", Toast.LENGTH_LONG).show();
          return true;
      case 2:
    	  
    	  /**
    		 * showing the route in Google map
    		 */
    	  Uri uri;
    	  uri =Uri.parse("http://maps.google.com/maps?&saddr="+sa+"&daddr="+da+"");
          Intent intent = new Intent(Intent.ACTION_VIEW, uri);
          startActivity(intent);
          return true;
     }
     return super.onOptionsItemSelected(item);
    }
}
