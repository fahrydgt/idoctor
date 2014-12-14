package com.iDoct.i_doctor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	private static List<medicine> mlist;
	 
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
    private Button bt;
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
		
		bt = (Button) findViewById(R.id.done);
		
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
	    	
	        mlist=getData();
	       
	        if(mlist.size()==0)
	        {
	        	Toast t = Toast.makeText(getApplicationContext(),
                        "Opps! Your device not connected with DATABASE",
                        Toast.LENGTH_SHORT);
                t.show();
	        }
	        else
	        {
	        	Toast t = Toast.makeText(getApplicationContext(),
                        "i-Doctor is Ready to use..",
                        Toast.LENGTH_SHORT);
                t.show();
	        	
	        }
	        btnSpeak.setOnClickListener(new View.OnClickListener() {
	        	
	        
	        	 
	            @Override
	            public void onClick(View v) {
	 
	                Intent intent = new Intent(
	                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
	 
	                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
	 
	                try {
	                    startActivityForResult(intent, RESULT_SPEECH);
	                    //txtText.setText("");
	                } catch (ActivityNotFoundException a) {
	                    Toast t = Toast.makeText(getApplicationContext(),
	                            "Opps! Your device doesn't support Speech to Text",
	                            Toast.LENGTH_SHORT);
	                    t.show();
	            
	                }
	            }
	        });
	        
	    
	        bt.setOnClickListener(new View.OnClickListener() {
	        	 
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
	        
	        //desease tab
	        btnSpeak1.setOnClickListener(new View.OnClickListener() {
	        	 
	            @Override
	            public void onClick(View v) {
	 
	                Intent intent1 = new Intent(
	                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
	 
	                intent1.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
	 
	                try {
	                    startActivityForResult(intent1, RESULT_SPEECH);
	                    //txtText.setText("");
	                } catch (ActivityNotFoundException a) {
	                    Toast t = Toast.makeText(getApplicationContext(),
	                            "Opps! Your device doesn't support Speech to Text",
	                            Toast.LENGTH_SHORT);
	                    t.show();
	                    
	                   
	                }
	            }
	        });
	        //delelte last recognized in disease
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
	    //write desesease to db
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
		            HttpClient httpclient = new DefaultHttpClient();
			        HttpPost httppost = new HttpPost("http://192.168.43.224/aa/addDeseace.php");  
			 
			    
			        try {
			            // Add your data
			            List nameValuePairs = new ArrayList(1);
			            nameValuePairs.add(new BasicNameValuePair("data2", wrt2));
			            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));  
			 
			            // Execute HTTP Post Request
			            HttpResponse response = httpclient.execute(httppost);
			 
			            InputStream is = response.getEntity().getContent();
			            BufferedInputStream bis = new BufferedInputStream(is);
			            ByteArrayBuffer baf = new ByteArrayBuffer(20);
			 
			            int current = 0;
			             
			            while((current = bis.read()) != -1){
			                baf.append((byte)current);
			            }  
			 
			            /* Convert the Bytes read to a String. */
			      
			     
			        } catch (ClientProtocolException e) {
			            // TODO Auto-generated catch block
			        } catch (IOException e) {
			            // TODO Auto-generated catch block
			        }
		            	
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
	   
	        //delelte last recognized
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
	        				String[] ar1=null;
	        				ar1=drg.getCapacity().split(",");
	        				
	        		 List<String> list2 = new ArrayList<String>();
	        		 for(int k=0;k<ar1.length;k++)
	        		 {
	             	list2.add(ar1[k]);
	        		 }
	        	
	             	
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
	//method for add prescreption#
	
public void addpres()
{
	 String wrt=(String) txtView.getText();
	  if(i1==0)
      {
      	Toast t = Toast.makeText(getApplicationContext(),
                     "Prescreption field is EMPTY!!",
                     Toast.LENGTH_SHORT);
             t.show();
      	
      }
      else
      {
	 
	 
     HttpClient httpclient = new DefaultHttpClient();
     HttpPost httppost = new HttpPost("http://192.168.43.224/aa/add.php");  

     try {
         // Add your data
         List nameValuePairs = new ArrayList(1);
         nameValuePairs.add(new BasicNameValuePair("data1", wrt));
         httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));  

         // Execute HTTP Post Request
         HttpResponse response = httpclient.execute(httppost);

         InputStream is = response.getEntity().getContent();
         BufferedInputStream bis = new BufferedInputStream(is);
         ByteArrayBuffer baf = new ByteArrayBuffer(20);

         int current = 0;
          
         while((current = bis.read()) != -1){
             baf.append((byte)current);
         }  

         /* Convert the Bytes read to a String. */
   
  
     } catch (ClientProtocolException e) {
         // TODO Auto-generated catch block
     } catch (IOException e) {
         // TODO Auto-generated catch block
     }
     	

      }
}
	
public void pop()
{
	CustomDialogClass cdd=new CustomDialogClass(this);
	cdd.show();  	
}
public void setAdep(List<String> list2)

{
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

	
	 public static String [] list(String gt)
	    {
		 
	
		 String[] arrDrugList=new String[mlist.size()];
		 for(int j=0;j<mlist.size();j++)
		 {
			 arrDrugList[j]=mlist.get(j).getName();
			 
		 }

	    	
	    	String arr[]=new String[5];//creating returning array
	    	int count[]=new int[arrDrugList.length];//for count probability
	    	int count1[]=new int[arrDrugList.length];//for sort probaility
	        
	    	char cgt1;//get char value for google apn word 
	    	char clt1;//get char value for Array value
	    	for(int i=0;i<gt.length();i++)//recognized text gt
	    	{
	    		 cgt1=gt.charAt(i);
	    		 
	    		for(int m=0;m<arrDrugList.length;m++)//number of drugs in array
	    		{
	    			
	    			if(arrDrugList[m].length()>i)//Check length of a drug to avoid null exception 
	                        {
	                            clt1=arrDrugList[m].charAt(i);
	    				
	    				if(Character.toLowerCase(cgt1)==Character.toLowerCase(clt1))
	    				{
	    					 if(i==0)//firstLetter Priority
                             {
                                   
                                     count[m]=count[m]+2;
                                       count1[m]=count1[m]+2;
                              }
                          if(i==1)//Second Letter Priority
                             {
                                   
                                     count[m]=count[m]+1;
                                       count1[m]=count1[m]+1;
                              }
                        
	                                   
	                                
	                          
	    					count[m]++;
	                                        count1[m]++;
	    
	                                }	
	   System.out.println(arrDrugList[m]+"--"+count[m]);
	                        }
	    			
	    			
	    			
	    		}
	    		
	    		
	    	}
	// sorting array
	        Arrays.sort(count1);
	       
	     
	   int a=0;
	   int b=arrDrugList.length-1;
	   while(a!=5)
	   {
	   for(int i=arrDrugList.length-1;i>=0;i--)
	   {
	   if(count1[b]==count[i])
	   {
	    arr[a]=arrDrugList[i];
	    		   a++;
	                   if(a==5) {
	           break;
	       }
	                   
	                   b--;
	   }
	   }
	   }
	   
	       return arr;
	    }
	 
	 
	 
//get from db
	 public ArrayList<medicine> getData(){
	        String db_url = "http://192.168.43.224/aa/hh.php";
	        InputStream is = null;
	        String line = null;
	        ArrayList<NameValuePair> request = new ArrayList<NameValuePair>();
	        request.add(new BasicNameValuePair("bssid","bssid"));
	        Object returnValue[] = new Object[2];
	        try
	        {
	            HttpClient httpclient = new DefaultHttpClient();
	            HttpContext localContext = new BasicHttpContext();
	            HttpPost httppost = new HttpPost(db_url);
	            httppost.setEntity(new UrlEncodedFormEntity(request));
	            HttpResponse response = httpclient.execute(httppost, localContext);
	            HttpEntity entity = response.getEntity();
	            is = entity.getContent();
	        }catch(Exception e){
	            Log.e("log_tag", "Error in http connection" +e.toString());
	        }
	        String result = "";
	        try
	        {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	            StringBuilder sb = new StringBuilder();

	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            is.close();
	            result=sb.toString();
	        }catch(Exception e){
	            Log.e("log_tag", "Error in http connection" +e.toString());
	        }
	        ArrayList<medicine> medicines = new ArrayList<medicine>();
	        try
	        {
	            JSONArray jArray = new JSONArray(result);
	            
	            for (int i = 0; i < jArray.length(); i++) {
	            	medicine m=new medicine();
	            JSONObject json_data = jArray.getJSONObject(i);
	            returnValue[0] = (json_data.getString("MedicineName"));
	            returnValue[1] = (json_data.getString("Quantity"));
	            m.setName(json_data.getString("MedicineName"));
	            m.setCapacity(json_data.getString("Quantity"));
	            medicines.add(m);
	            
	            }
	            
	        }catch(JSONException e){
	            Log.e("log_tag", "Error parsing data" +e.toString());
	        }
	        return medicines;
	    }

	 
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	 
	        switch (requestCode) {
	        case RESULT_SPEECH: {
	            if (resultCode == RESULT_OK && null != data) {
	 
	                ArrayList<String> text = data
	                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
	                
	                //String gtxt=text.get(0);
	                
	                spin = (Spinner) findViewById(R.id.spinner1);
	            	List<String> list = new ArrayList<String>();
	            	list.add(list(text.get(0))[0]);
	            	list.add(list(text.get(0))[1]);
	            	list.add(list(text.get(0))[2]);
	            	list.add(list(text.get(0))[3]);
	            	list.add(list(text.get(0))[4]);
	            	
	            	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
	            		android.R.layout.simple_spinner_item, list);
	            	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	            	spin.setAdapter(dataAdapter);
	                
	            	
	       //after meal,b4 meal
	            	 spin1 = (Spinner) findViewById(R.id.spinner2);
	        		 
	        		 List<String> list1 = new ArrayList<String>();
	             	list1.add("BD");
	             	list1.add("TDS");
	             	list1.add("One/Day");
	             	
	             	ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
	             		android.R.layout.simple_spinner_item, list1);
	             	dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	             	spin1.setAdapter(dataAdapter1);
	            	
	            	
	            	 //mg
	            	 spin2 = (Spinner) findViewById(R.id.spinner3);
	            	 
	        		for(medicine drg:mlist)
	        		{
	        			if(String.valueOf(spin.getSelectedItem()).equals(drg.getName()))
	        			{
	        				String[] ar1=null;
	        				ar1=drg.getCapacity().split(",");
	        				
	        		 List<String> list2 = new ArrayList<String>();
	        		 for(int k=0;k<ar1.length;k++)
	        		 {
	             	list2.add(ar1[k]);
	        		 }
	        		
	             dataAdapter2 = new ArrayAdapter<String>(this,
	             		android.R.layout.simple_spinner_item, list2);
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
