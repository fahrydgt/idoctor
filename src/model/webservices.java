package model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class webservices {

	 ArrayList<medicine> medicines = new ArrayList<medicine>();
	
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
	
	

public void AddPrescreptionToDb(String wrt)
{
	 
    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httppost = new HttpPost("http://192.168.43.224/aa/add.php");  

    try {
        // Adding data
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

public void addDeseaceToDb(String wrt2)
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
