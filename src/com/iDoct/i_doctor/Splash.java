package com.iDoct.i_doctor;


 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
 
public class Splash extends Activity {
 
    // Splash screen timer
    private static int SPLASH_TIME_OUT =1000;

	   public ArrayList<medicine> arr;
 
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spl);
        
        
        new Handler().postDelayed(new Runnable() {
 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
 
}
