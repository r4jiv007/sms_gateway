package my.app.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rajiv on 8/16/13.
 */
public class MyService extends IntentService {
    final static String webAPI="";

    public MyService() {
        super("mymsgsercvice");
    }


    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this, "starting service for posting", 5000).show();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle bd = intent.getBundleExtra("sms");
        String frm =bd.getString("frm");
        String msg = bd.getString("sms");

        postData(frm,msg);


    }
    public void postData(String from , String message) {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://yuktix01.cloudapp.net:8080/sensordb/v1/sms");

        try {
            // p001,s001,point1001
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("body", message));
            //nameValuePairs.add(new BasicNameValuePair("stringdata", message));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            int result = response.getStatusLine().getStatusCode();
            Log.i("Http response code", "response code is "+result);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }
}
