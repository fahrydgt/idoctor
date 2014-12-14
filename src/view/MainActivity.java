package view;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.iDoct.i_doctor.R;

import controller.controller;





import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class MainActivity extends Activity {
	
	private List<medicine> mlist;
	// <objects for exterrnal classes>
    webservices websv=new webservices();// get drugList from MySQL
	controller c=new controller();
	 
	protected static final int RESULT_SPEECH = 1;
 
    private ImageButton btnSpeak;
    private ImageButton btnSpeak1;
    private ImageButton btnSpeak2;
    private TextView txtView1;
    private	ArrayAdapter<String> dataAdapter2;
    private Button bt1;
    private Button print;

    private Button exit;
    private ImageButton delt;
    private Spinner spin;
    private Spinner spin1;
    private static int i1=0,h=0;
    private Spinner spin2;
    private TextView txtView;
    private Button doneBotton ;
    private String arr[]=null;
    private int count=0;
    private    static String lastdl="";
    private    static String lastdl1="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
		tabHost.setup();

		TabSpec spec1=tabHost.newTabSpec("Tab 1");
		spec1.setContent(R.id.Prescription);
		spec1.setIndicator("Prescription");

		TabSpec spec2=tabHost.newTabSpec("Tab 2");
		spec2.setIndicator("Decease");
		spec2.setContent(R.id.Dicease);

        tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		
		doneBotton= (Button) findViewById(R.id.done);
		bt1 = (Button) findViewById(R.id.button6);
		
        print = (Button) findViewById(R.id.button2);
		exit = (Button) findViewById(R.id.button3);
        txtView1 = (TextView) findViewById(R.id.textView1);
		btnSpeak1= (ImageButton) findViewById(R.id.button4);
        btnSpeak2= (ImageButton) findViewById(R.id.button5);
		spin = (Spinner) findViewById(R.id.spinner1);
		delt= (ImageButton) findViewById(R.id.del);
		txtView = (TextView) findViewById(R.id.txtText);
		btnSpeak = (ImageButton) findViewById(R.id.button1);
	    	
	    //Getting Druglist Data. 
	    mlist=websv.getData();
	       if(mlist.size()==0)
	        {
	        	Toast t = Toast.makeText(getApplicationContext(),"Opps! Your device not connected with DATABASE",Toast.LENGTH_SHORT);
                t.show();
	        }
	        else
	        {
	        	Toast t = Toast.makeText(getApplicationContext(),"i-Doctor is Ready to use..",Toast.LENGTH_SHORT);
                t.show();	
	        }
	       
	     //Speech Recogniser buttonClick  
	     btnSpeak.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
	            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
	            try { 
	            	startActivityForResult(intent, RESULT_SPEECH);
	                
	                } catch (ActivityNotFoundException a) {
	                    Toast t = Toast.makeText(getApplicationContext(), "Opps! Your device doesn't support Speech to Text",Toast.LENGTH_SHORT);
	                    t.show();
	                }
	            }
	        });
	        
	     	//add a drug to prescription text view from recognised drug list spinner
	        doneBotton.setOnClickListener(new View.OnClickListener() {
	        	 @Override
	            public void onClick(View v) {
	            	
	            if(spin.getSelectedItem()!=null)
	            {
	            	lastdl=(String) txtView.getText();
	            	lastdl=lastdl+"\n"+(++i1)+") "+String.valueOf(spin.getSelectedItem())+" "+String.valueOf(spin2.getSelectedItem())+" ("+String.valueOf(spin1.getSelectedItem())+")";
	            	txtView.setText(lastdl);
	            }
	            else{
	            	
	            	Toast t = Toast.makeText(getApplicationContext(),
                            "Please retry to input drugs.",
                            Toast.LENGTH_SHORT);
                    t.show();
	            	
	            }
	            }
	        });
	        
	        //Delete last recognised drug
		       delt.setOnClickListener(new View.OnClickListener() {
		        	 
		            @Override
		            public void onClick(View v) {
		            	
		            if(i1==0){

		            	Toast t = Toast.makeText(getApplicationContext(),
	                            "Prescription is EMPTY!!",
	                            Toast.LENGTH_SHORT);
	                    t.show();
		            }
		            else{
		            	
		            	String lst=lastdl.substring(0, lastdl.lastIndexOf("\n"));
		            	
		            	lastdl=lst;
		            	txtView.setText(lastdl);
		            	i1--;
		            }
		            }
		        });
		       
	        
		       //write prescription to database
			       print.setOnClickListener(new View.OnClickListener() {
			        	 
			            @Override
			            public void onClick(View v) {
			             
			            	 AlertDialog diaBox = AskOption();
			        		 diaBox.show();
			        	        
			                       }
			        });
	        
	        //Decease tab Speech recogniser
	        btnSpeak1.setOnClickListener(new View.OnClickListener() {
	        	 
	            @Override
	            public void onClick(View v) {
	 
	                Intent intent1 = new Intent(
	                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
	 
	                intent1.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
	 
	                try {
	                    startActivityForResult(intent1, RESULT_SPEECH);
	                } catch (ActivityNotFoundException a) {
	                    Toast t = Toast.makeText(getApplicationContext(),
	                            "Opps! Your device doesn't support Speech to Text",
	                            Toast.LENGTH_SHORT);
	                    t.show();
	                    
	                   
	                }
	            }
	        });
	        
	        //Delete last recognised  disease from text view
		       btnSpeak2.setOnClickListener(new View.OnClickListener() {
		        	 
		            @Override
		            public void onClick(View v) {
		            	
		            	   if(h==0){

		   	            	Toast t = Toast.makeText(getApplicationContext(),
		                               "Desease field is EMPTY!!",
		                               Toast.LENGTH_SHORT);
		                       t.show();
		   	            }
		   	            else{
		   	            	
		   	            	String lst1=lastdl1.substring(0, lastdl1.lastIndexOf("\n"));
		   	            	
		   	            	lastdl1=lst1;
		   	            	txtView1.setText(lastdl1);
		   	            	h--;
		   	            }
		            	
		            }
		        });
		       
	    //write deceases to db
		      bt1.setOnClickListener(new View.OnClickListener() {
		        	 
		            @Override
		            public void onClick(View v) {
		            	
		            	
		            String wrt2=(String) txtView1.getText();
		            
		            if(h==0)
			        {
			        	Toast t = Toast.makeText(getApplicationContext(),
	                               "Desease field is EMPTY!!",
	                               Toast.LENGTH_SHORT);
	                       t.show();
			        	
			        }
			        else
			        {
			        	websv.addDeseaceToDb(wrt2);
		            	
		            }
			        }
		        });
	    
	       
	       exit.setOnClickListener(new View.OnClickListener() {
	        	 
	            @Override
	            public void onClick(View v) {
	            	
	            	pop();
	            	
	            
	            }
	        });
	       
	       spin.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					for(medicine drg:mlist)
	        		{
	        			if(String.valueOf(spin.getSelectedItem()).equals(drg.getName()))
	        			{
	        				List<String> list2=c.setCapacity(drg);
	        	            setAdep(list2);
	             	        spin2.setAdapter(dataAdapter2);
	             	
	             	break;
	        			}
	        		}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
	        });
	}
	
	
//method for add prescription#
public void addpres(){
	 String wrt=(String) txtView.getText();
	  if(i1==0) {
      	Toast t = Toast.makeText(getApplicationContext(),
                     "Prescreption field is EMPTY!!",
                     Toast.LENGTH_SHORT);
             t.show();
      	
      }
      else {
	 
    	  websv.AddPrescreptionToDb(wrt);

      }
}
	
//calling Dialog Box for exit 
public void pop(){
	CustomDialogClass cdd=new CustomDialogClass(this);
	cdd.show();  	
}

public void setAdep(List<String> list2){
	 dataAdapter2 = new ArrayAdapter<String>(this,
      		android.R.layout.simple_spinner_item, list2);
      	dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
   
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	
	
	
	
	
	 //Setting up the spinners with data afeter speech 
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 
		 super.onActivityResult(requestCode, resultCode, data);
	 
	        switch (requestCode) {
	        case RESULT_SPEECH: {
	            if (resultCode == RESULT_OK && null != data) {
	 
	                ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
	               
	                spin = (Spinner) findViewById(R.id.spinner1);
	               
	                //class to do the processing parts
	                List<String> list=c.GetList(text,mlist);
	            	
	            	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
	            	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	            	spin.setAdapter(dataAdapter);
	                
	            	
	                 //after meal,b4 meal
	            	 spin1 = (Spinner) findViewById(R.id.spinner2);
	            	// get list from controller class
	        		 List<String> list1 = c.getList1(); 
	             	 
	             	
	             	ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list1);
	             	dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	             	spin1.setAdapter(dataAdapter1);
	            	
	            	
	            	 //Drug Capacity 
	            	 spin2 = (Spinner) findViewById(R.id.spinner3);
	            	 
	        		for(medicine drg:mlist)
	        		{
	        			if(String.valueOf(spin.getSelectedItem()).equals(drg.getName()))
	        			{
	        				List<String> list2=c.setCapacity(drg);
	        			    dataAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list2);
	             	       dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	             	       spin2.setAdapter(dataAdapter2);
	             	
	             	       break;
	        			}
	        		}
	            	//txtView.setText(String.valueOf(spin.getSelectedItem()));
	        		TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
	        		if(tabHost.getCurrentTab()==1)
	        		{
	             	//desease
	                lastdl1=(String) txtView1.getText();
	            	lastdl1=lastdl1+"\n"+(++h)+") "+text.get(0);
	            	//h++;
	            	txtView1.setText(lastdl1);
	        
	        		}
	            	
	            	
	                  }
	            break;
	        }
	 
	        }
	    }
	  
//confirm for presc add
	 private AlertDialog AskOption()
	 {
	    AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this) 
	        //set message, title, and icon
	        .setTitle("Add Prescreption") 
	        .setMessage("Do you want to Add Prescreption??") 
	        .setIcon(R.drawable.ic_launcher)

	        .setPositiveButton("Add", new DialogInterface.OnClickListener() {

	            public void onClick(DialogInterface dialog, int whichButton) { 
	                //adding db code
	            	addpres();
	                dialog.dismiss();
	            }   

	        })



	        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	            
	                dialog.dismiss();

	            }
	        })
	        .create();
	        return myQuittingDialogBox;

	    }
}
