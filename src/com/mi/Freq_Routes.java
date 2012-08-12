package com.mi;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Metro-in
 * 
 *@bno: bno contains the bus number being searched
 *@frm: frm contains the 'from' location given by user
 *@too: too contains the 'to' destination given by the user
 *@sa: sa is the from locations latitude,longitude
 *@da: da is the to destinations latitude,longitude
 *@fl: fl is used to toast the from locations latitude and longitude
 *@dl: dl is used to toast the to destinations latitude,longitude
 */
public class Freq_Routes extends Activity {
	
	public String bno;
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
        setContentView(R.layout.freqroute);
        
        /**
		 * txt-textview for heading
		 * font-typeface to change font of the textview
		 */
        TextView txt = (TextView) findViewById(R.id.q33);   
        Typeface font = Typeface.createFromAsset(getAssets(), "VIVALDII.TTF");   
        txt.setTypeface(font);
        
        /**
		 * tv1-printing from location
		 * tv2-printing to destination
		 * tv3-printing bus no
		 * tv4-printing the class of bus
		 * tv5-printing the fare of the bus
		 * tv6-printing the expected travel time
		 * tv7-printing the route
		 */
        final Button pf=(Button)findViewById(R.id.pfl);
        pf.setVisibility(Button.INVISIBLE);
        final Button pd=(Button)findViewById(R.id.pdn);
        pd.setVisibility(Button.INVISIBLE);
        final TextView tv4 =(TextView)findViewById(R.id.f4);
        final TextView tv5 =(TextView)findViewById(R.id.f5);
        final TextView tv6 =(TextView)findViewById(R.id.f6);
        final TextView tv7 =(TextView)findViewById(R.id.f7);
        Typeface font2 = Typeface.createFromAsset(getAssets(), "RUPEE FORADIAN.TTF");
        tv4.setTypeface(font2);
        tv5.setTypeface(font2);
        tv6.setTypeface(font2);
        tv7.setTypeface(font2);
        
        
        /**
		 * Getting the bus numbers for the routes checked as frequently travelled
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
            int i=0;
           
            while (cur.isAfterLast() == false) {
            	
           	if(cur.getString(8).equals("1")){
           		  	i=i+1;
                }
           	cur.moveToNext();
            }
            cur.moveToFirst();
            String[] nm=new String[i];
            i=0;
           
            while (cur.isAfterLast() == false) {
            	
            	if(cur.getString(8).equals("1"))
            	{               
           			nm[i]=cur.getString(1);
           		  	i=i+1;
                }
           	cur.moveToNext();
            }
            db.close();
            Spinner s = (Spinner) findViewById(R.id.ftr1);
            ArrayAdapter<CharSequence> adapter5 = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item, nm);
            adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);    
            s.setAdapter(adapter5);
            final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
        	alertbox.setMessage("No Routes Selected");
        	alertbox.setPositiveButton("OK",new DialogInterface.OnClickListener(){
        		
    			

    			@Override
    			public void onClick(DialogInterface dialog, int which) {
    				
    				finish();
    				
    			}
        		});
           
            String k=i+"";
            if(k.equals("0")){
            	/**
        		 * Toast to show that no bus has been selected by the user
        		 */
              s.setVisibility(Spinner.INVISIBLE);
              alertbox.show();
            }
            
            /**
    		 * printing the bus numbers selected in the spinner
    		 */
            
            s.setOnItemSelectedListener(new OnItemSelectedListener()
            {  
            /*
             * 	        	(non-Javadoc)
             * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
             */
            public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) 
            {
            	
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
                bno=parent.getItemAtPosition(pos).toString();
                Cursor cur = db.query("bus_det",
                   		null, null, null, null, null, null);
                    cur.moveToFirst();
                    while (cur.isAfterLast() == false) {
                        if(cur.getString(1).equals(bno))
                        {
                        	pf.setVisibility(Button.VISIBLE);
                        	pd.setVisibility(Button.VISIBLE);
                        	pf.setText(cur.getString(3));
                        	frm=cur.getString(3);
                        	too=cur.getString(4);
                        	pd.setText(cur.getString(4));
                        	tv4.setText(cur.getString(2));
                        	tv5.setText(cur.getString(6)+"`");
                        	tv6.setText(cur.getString(5)+"Mins");
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
    		public void onNothingSelected(AdapterView<?> parent) {
    			// TODO Auto-generated method stub
    			
    		}
    		});
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	/**
		 * creating the popup menu
		 */
     menu.add(1, 1, Menu.FIRST, "Search By Location");
     menu.add(1, 2, Menu.FIRST+1, "Search By Bus no:");
     menu.add(2, 3, Menu.NONE, "Remove from Frequently Travelled Routes");
     menu.add(2, 4, Menu.NONE, "Show in Map");
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
    	  Intent myIntent1 = new Intent(Freq_Routes.this, Get_Loc.class);
          startActivity(myIntent1);
          finish();
          return true;
      case 2:
    	  
    	  	 /**
    		 * starting activity to search by bus number
    		 */
    	  Intent myIntent2 = new Intent(Freq_Routes.this, Get_Busno.class);
          startActivity(myIntent2);
          finish();
          return true;
      case 3:
    	  
    	  /**
  		 *removing from frequently travelled routes
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
          ftr.put("ftr", "0");
          db.update("bus_det", ftr, "busno=?",new String[] {bno});
          Toast.makeText(getApplicationContext(),"Route Removed", Toast.LENGTH_LONG).show();
          Intent myIntent3 = new Intent(Freq_Routes.this, Freq_Routes.class);
          startActivity(myIntent3);
          finish();      
    	  return true;
      	case 4:
      		
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


