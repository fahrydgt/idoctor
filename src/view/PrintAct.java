package view;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.ByteArrayBuffer;

import android.app.Activity;
import android.os.Bundle;

public class PrintAct {
	

    public void getmed(){
    //    String wrt=(String) txtView.getText();
        String wrt="fahry";
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
