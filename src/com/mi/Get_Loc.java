package com.mi;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @author Metro-In
 *
 *@size: size is used to get the no of stops the bus has.
 *@i: i is the no of buses in the searched route
 *@places: places contains all the names of the locations where the bus stops
 *@nm: nm contains bus numbers travelling in the specified route
 *@fm1: frm1 contains the 'from' location given by user
 *@too1: too1 contains the 'to' destination given by the user
 *@location: location contains all the stops possible for all buses to be shown in autocomplete
 */

public class Get_Loc extends Activity {
	
		
		public int size=0;
		public int i=0;
		public String[] places;
		public String[] nm;
		public String frm1;
		public String too1;
			static final String[] location = new String[] {
        	"Adambakkam" ,"Adyar BS","Agaram Then",
        	"Alwarpet" ,"Amarambedu" ,"Amb Ot","Ambathur Estate" ,"Aminjikarai" ,
        	"Anna Nagar" ,"Anna Nagar West","Anna Salai" ,"Anna Square","Anna University","Arakkonam" ,"Arambakkam" ,"Arcot Road" ,"Arumbakkam" ,
        	"Avadi","Ayanavaram BS","Ayapakkam","Ayyappan Thangal","AGS Office Colony",
        	
        	"Besant Nagar","Bharat Electronics","Broadway",
        	
        	"Central","Chemmanchery SCB" ,"Chengelpet NT" ,
        	"Chennai Beach RS" ,"Chepauk" ,"Chetpet" ,"Chindadripet",
        	"CMBT","Chitlapakkam" ,"Choolai","Chromepet" ,
        	
        	"DMS" ,"Dasarathapuram",
        	
        	"ECR","Egmore","Egmore North RS" ,"Ennore" ,"Elango Nagar Officer Colony",
        	
        	"Fore Shore Estate" ,
        	
        	"Gemini PH","Guduvanchery","Guindy","Guindy RS","Guindy Tvk Estate","Gummidipoondi","Gummidipoondi RS",
        	
        	"High Court","Hasthinapuram",
        	
        	"ICF","Injambakkam" ,"IOC",
        	
        	"Jaffarkhanpet","Jamalaya",
        	
        	"KK Nagar" ,"Kalavakkam" ,"Kandigai","Karanodai","Karapakkam","Kasimedu" ,
        	"Kattankulathur","Kavaraipettai" ,"Keerapakkam","Kelambakkam","Kellys" ,
        	"Kilkattalai","Kilpauk","Kodambakkam" ,"Kanniamman Nagar","Kannagi Nagar SCBoard",
        	"Kolathur" ,"Korattur","Korukkupet RS",
        	"Kottivakkam","Kotturpuram","Kovalam",
        	"Koyambedu","Koyambedu Market","Kundrathur BS" ,"Kilkattalai","Kundrathur Murugan Temple",
        	"Kadaperi","Kalaignar Nagar","Kalaignar Nagar West","Kanathur",
        	
        	        	
        	"Mathur MMDA" ,"Madambakkam","Madhavaram Village" ,"Madipakkam","Maduravoyal",
        	"Mamallapuram","Madipakkam","Manali","Madipakkam BS","Muthamil Nagar","Moolakadai",
        	"Mambakkam Rd","Manali New Town","Manapakkam" ,"Mandaveli","Mangadu","Manivakkam" ,"Maraimalai Nagar",
        	"Marina Beach","Medavakkam" ,"Meenambakkam","Minjur New Terminus","Mogalivakkam","Mogappair East",
        	"Mogappair West","Madarapakkam Bus Terminus","Mangadu","Moovarasampet" ,"Moulivakkam" ,"Mount Road","Mudichur",
        	"Mugalivakkam" ,"Muttukkadu","Meppur","Mahakavi Bharathi Nagar","Muthapudupet",
        	"Mylapore" ,"MKB Nagar","MKB Nagar East","Madhavaram","MMDA Colony",
        	
        	"Nandambakkam","Nandambakkam","NGO Colony",
        	"Nanganallur" ,"Navalur" ,"Nungambakkam" ,"Naduveerapattu",
        	
        	"Oragadam Koot Road",
        	
        	"Padappai","Padi" ,"Pudur" ,"Palavakkam" ,"Palavanthangal" ,"Pallavaram" ,"Pallikaranai" ,"Pammal" ,
        	"Paranur","Park Town","Parrys" ,"Periyapalayam","Periyar Nagar","Prithiyangara Devi Temple",
        	"Pattabiram","Pattaravakkam" ,"Pazhanthandalam","Pazhavanthangal","Pazhaverkadu","Pennalur",
        	"Perambakkam" ,"Perambur","Perambur RS" ,"Perambur Barracks Road","Periya Kottamedu",
        	"Periyapanichery","Perumbakkam","Perungudi" ,"Perungulathur","Pondy Bazaar","Ponmar",
        	"Ponneri","Ponniammanmedu","Poonamallee","Poonamallee High Road" ,"Poondi","Porur" ,
        	"Potheri" ,"Pozhichalur","Pudupakkam" ,"Pulipakkam" ,"Purasawalkam" ,"Puthuchathiram",
        	"Putlur" ,"Puzhal" ,"Puzhithivakkam" ,"Padiyanallur","Peavallur Kumaran Nagar","Pattur",
        	"Perambur BS" ,"Padurmedu","Poombuhar",
        	
        	"QM Arts College",
        	
        	"RA Puram","Rajiv Gandhi Salai" ,"Ramapuram" ,"Red Hills" ,"Red Hills Road" ,"Royapettah","Royapuram" ,
        	"Ramachandra Medical College",
        	
        	"SRM University", "Saidapet","Saidapet West","Saligramam","Santhome","Santhoshapuram","Selaiyur" ,"Semmancheri",
        	"Sholavaram" ,"Sozhinganallur PU Office","SP Koil" ,"Siruseri","Siruseri IT Park" ,
        	"Sithalapakkam","Sriperumbudur" ,"St.Thomas Mount","Strahans Road" ,"Sullurpeta",
        	"Sunguvarchathram","Sunnambu Kolathur","Senthil Nagar","Somangalam","Saidapet Court",
        	
        	"T Nagar","TVS" ,"Tada" ,"Tambaram" ,"Tambaram East" , "Tollgate",
        	"Taramani" ,"Teynampet" ,"Thaiyur" ,"Thandalam" ,"Tamilnadu Housing Board Colony",
        	"Thandalam Koot Road","Thazambur","Thillai Ganga Nagar","Thirumalisai",
        	"Thirumudivakkam","Thirumullaivoyal" ,"Thiruneermalai","Thirunindravoor",
        	"Thirupporur","Thiruvallur","Thiruvanmiyur","Thiruvedanthai","Thiruverkadu",
        	"Thiruvetriyur BS","Thorappakkam" ,"Tidel Park" ,"Tirusulam","Tiruvottiyur","Tondiarpet",
        	"Triplicane" ,"Thandarai","TVK Nagar","Thirumalisai","Teachers Coloy",
        	
        	
        	
        	"VOC Nagar","Vadanemili","Vadapalani BS","Valasaravakkam" ,
        	"Vallakkottai" ,"Vallalar Nagar" ,"Valluvar Kottam","Vanagaram" ,"Vandaloor Zoo" ,
        	"Vazhuthalambedu Rd","Velachery" ,"Velappanchavadi" ,"Vellavedu","Vengaivasal",
        	"Venkatamangam","Veppampattu","Vettuvankanni" ,"Villivakkam" ,"Virugambakkam" ,
        	"Vyasarpadi","V House","V Nagar","Velachery Vijaya Nagar" ,"Vandaloor" ,"Vinayagapuram",
        	"Velachery Hospital","Vengaivasal PHCentre"
        	
        	
        };
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sloc_get);
        
        /**
		 * txt-textview for heading
		 * font-typeface to change font of the textview
		 */
		
        TextView txt = (TextView) findViewById(R.id.textView3);   
        Typeface font = Typeface.createFromAsset(getAssets(), "VIVALDII.TTF");   
        txt.setTypeface(font);
        
        
        /**
		 * opening the database to search for  the routes available
		 */
		final SQLiteDatabase db;
        
        db = openOrCreateDatabase(
       		"metroin1.db"
       		, SQLiteDatabase.CREATE_IF_NECESSARY
       		, null
       		);
        db.setVersion(1);
        db.setLocale(Locale.getDefault());
        db.setLockingEnabled(true);
        
        
        /**
		 * Autocomplete to get the from location
		 */
		final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.afrom);  
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item, location); 
        // adapter: A ListAdapter that manages a ListView backed by an array of arbitrary objects  
        textView.setAdapter(adapter);
        
        
        /**
		 * Autocomplete to get the to destination
		 */
        final AutoCompleteTextView textView1 = (AutoCompleteTextView) findViewById(R.id.ato);  
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,R.layout.list_item, location); 
        // adapter: A ListAdapter that manages a ListView backed by an array of arbitrary objects  
        textView1.setAdapter(adapter1);
        
        
        /**
		 * alert box for throwing 'from' location empty error
		 */
        final AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
    	alertbox.setMessage("From Location not Specified");
    	alertbox.setPositiveButton("OK",new DialogInterface.OnClickListener(){
    		
			

			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				textView.requestFocus(1);
				
			}
    		});
    	
    	/**
		 * alert box for throwing 'to' destination empty error
		 */
    	final AlertDialog.Builder alertbox1 = new AlertDialog.Builder(this);
    	alertbox1.setMessage("To Destination not Specified");
    	alertbox1.setPositiveButton("OK",new DialogInterface.OnClickListener(){
    		
			

			@Override
			public void onClick(DialogInterface dialog, int which) {
				textView1.requestFocus(1);
			}
    		});
    	
    	
    	/**
		 * alert box for throwing 'from' and 'to' being the same error
		 */
    	final AlertDialog.Builder alertbox2 = new AlertDialog.Builder(this);
    	alertbox2.setMessage("Both From and To Location are the same,Please Enter Again.");
    	alertbox2.setPositiveButton("OK",new DialogInterface.OnClickListener(){
    		
			

			@Override
			public void onClick(DialogInterface dialog, int which) {
				textView1.setText("");
				textView1.requestFocus(1);
			}
    		});
    	
    	
    	/**
		 * button1 is the button to start the search
		 */
        Button button1 = (Button) findViewById(R.id.locb);
        button1.setOnClickListener(new View.OnClickListener() {
        	/*
        	 * (non-Javadoc)
        	 * @see android.view.View.OnClickListener#onClick(android.view.View)
        	 */
        public void onClick(View view) {
        	/**
    		 * flag is being used to check whether the entered 'from' and 'to'
    		 * are starting and ending points for a specific bus
    		 */
        	String flag="0";
        	
        	/**
    		 * flag1 is being used to check whether a route has been found when 
    		 * either from or to or both is not the starting or ending points 
    		 * of the bus
       		 */
    		String flag1="0";
    		
    		/**
    		 * flag1a is being used to check whether 'from' has been found in the
    		 * route of a bus being checked
    		 */
    		String flag1a="0";
    		
    		/**
    		 * flag1b is being used to check whether 'to' has been found in the
    		 * route of a bus being checked
    		 */
    		String flag1b="0";
    		
    		/**
    		 * flag2 is to inform print activity whether route is  starting and ending point
    		 * or middle stops of route
    		 */
    		String flag2="0";
    		            
            final AutoCompleteTextView t2 = (AutoCompleteTextView) findViewById(R.id.afrom);
            final AutoCompleteTextView t1 = (AutoCompleteTextView) findViewById(R.id.ato);
            
            /**
    		 * frm-gets the 'from' location selected by the user from the autocomplete box t2
    		 * too-gets the 'to' destination selected by the user from the autocomplete box t1
    		 */
            String frm=t2.getText().toString().trim();
            String too=t1.getText().toString().trim();
            
            
            if(frm.equals(""))
            {
            	/**
        		 * calling alertbox when 'from' is empty
        		 */
            	alertbox.show();
            }
            else if(too.equals(""))
            {
            	/**
        		 * calling alertbox when 'to' is empty
        		 */
            	alertbox1.show();
            }
            else if(too.equals(frm))
            {
            	/**
        		 * calling alertbox when 'from' and 'to' is the same
        		 */
            	alertbox2.show();
            }
            else
            {
            	/**
        		 * Finding the buses travelling in the route specified by the user
        		 */
            Cursor cur = db.query("bus_det", 
               		null, null, null, null, null, null);
                
                cur.moveToFirst();
                int i=0;
               
                while (cur.isAfterLast() == false) {
                	
                	/**
            		 * checking whether the entered 'from' and 'to' are starting 
            		 * and ending points of the bus
            		 */
               	if((cur.getString(3).equals(frm) && cur.getString(4).equals(too) )||(cur.getString(4).equals(frm) && cur.getString(3).equals(too)) )
                    {
               		  	flag="1";
               		}
               	cur.moveToNext();
                }
                
                if(flag.equals("0"))
                {
                	Cursor cur1 = db.query("bus_det",
                       		null, null, null, null, null, null);
                        cur1.moveToFirst();
                        
                        /**
                		 * checking whether route specified is present in any bus route 
                		 * when it is not starting or ending point
                		 */
                       while (cur1.isAfterLast() == false) {
                        	
                        	if(flag1.equals("0"))
                        	{
                        		
                        		
                        		/**
                        		 * getting all the stops of the bus being searched
                        		 */
                        		places=cur1.getString(7).split(",");
                        		/**
                        		 * getting the no of stops for the bus being searched
                        		 */
                        		size=places.length;
                        	
                            for(int k=0;k<size;k++)
                            {
                            	/**
                        		 * checking whether the entered 'from' and 'to' are present in the
                        		 * stops of the bus being searched
                        		 */
                            	if(places[k].equals(frm))
                            	{
                            		/**
                            		 * checking 'from' part
                            		 */
                            	flag1a="1";
                            	frm1=cur1.getString(3);
                            	}
                            	if(places[k].equals(too))
                            	{
                            		/**
                            		 * checking 'to' part
                            		 */
                            	flag1b="1";
                            	too1=cur1.getString(4);
                            	
                            	}
                            	if(flag1a.equals("1") && flag1b.equals("1")){
                            		/**
                            		 * checking whether 'from' and 'to' are present in the
                            		 * stops of the bus being searched
                            		 */
                            		flag1="1";
                            		frm=frm1;
                            		too=too1;
                            		flag2="1";
                            		break;
                            	}  
                            	}
                        	}
                        	flag1a="0";
                        	flag1b="0";
                        	cur1.moveToNext();
                        	
                        }
                       
                }
                if(flag1.equals("1")){
                	
                	/**
            		 * if a route is found not as the starting and ending points,making the main 
            		 * flag 1 to continue operations
            		 */
            		flag="1";
            	
            		
            	}  
                
                
                	/**
                	 * Toasting error message when route is not found either as 
                	 * starting or ending or as a stop in between
                	 */
                    if(flag.equals("0")){ 
                    Toast.makeText(getApplicationContext(),"Route does not Exist", Toast.LENGTH_LONG).show();
                    textView.setText("");
                	textView1.setText("");
                	
                	/**
            		 * Focusing the cursor to the 'from' autocomplete box so that user can
            		 * enter a valid route for searching again
            		 */
                	textView.requestFocus(1);
                 }
                cur.moveToFirst();
                
                i=0;
                
                while (cur.isAfterLast() == false) {
                	
               	if((cur.getString(3).equals(frm) && cur.getString(4).equals(too) )||(cur.getString(4).equals(frm) && cur.getString(3).equals(too)) )
                    {
               		
               		/**
            		 * getting the number of buses available in the specified route
            		 */
               		  	i=i+1;
                    }
               	cur.moveToNext();
                }
                cur.moveToFirst();
                /**
        		 * creating a string array to save the number of the buses in the
        		 * specified route
        		 */
                nm=new String[i];
                i=0;
                
                while (cur.isAfterLast() == false) {
                	
               	if((cur.getString(3).equals(frm) && cur.getString(4).equals(too) )||(cur.getString(4).equals(frm) && cur.getString(3).equals(too)) )
                    {
               		
               		/**
            		 * getting the number of the buses available in the specified route
            		 */
               			nm[i]=cur.getString(1);
               		  	i=i+1;
                    }
               	cur.moveToNext();
                }
         	
       	    cur.close();
           }
            if(flag.equals("1")){
            	
            	/**
        		 * bundling all the data to send to the next activity to print the results
        		 */
            	Bundle bundle = new Bundle();
            	bundle.putStringArray("data", nm);
            	bundle.putString("too", too);
            	bundle.putString("frm", frm);
            	bundle.putString("mid", flag2);
            	bundle.putInt("size", i);
            	
            	/**
        		 * starting the new activity to print results
        		 */
            	Intent myIntent1 = new Intent(view.getContext(),  Print_Loc.class);
            	myIntent1.putExtras(bundle);
            	startActivity(myIntent1);
            	textView.setText("");
            	textView1.setText(""); 
            	
            	/**
        		 * resetting all the flags for use the next time
        		 */
            	flag="0";
            	flag1="0";
            	flag1a="0";
            	flag1b="0";
            	flag2="0";
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
     menu.add(1, 1, Menu.NONE, "Search By Bus No:");
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
  		 * starting activity to search by bus number
  		 */
    	  Intent myIntent1 = new Intent(Get_Loc.this, Get_Busno.class);
          startActivity(myIntent1);
          finish();
          return true;
      case 2:
    	  
    	  /**
  		 * starting activity to view frequent routes
  		 */
    	  Intent myIntent2 = new Intent(Get_Loc.this, Freq_Routes.class);
          startActivity(myIntent2);
          finish();
          return true;
     }
     return super.onOptionsItemSelected(item);
    }
}


