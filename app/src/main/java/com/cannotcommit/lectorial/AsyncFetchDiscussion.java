package com.cannotcommit.lectorial;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charlie on 2015-08-15.
 */
public class AsyncFetchDiscussion  extends AsyncTask<String, String, String> {

    private DiscussionActivity mainThread;

    public AsyncFetchDiscussion(DiscussionActivity listActivity){
        super();
        mainThread = listActivity;
    }

    @Override
    protected String doInBackground(String... params) {
        String urlString=params[0]; // URL to call
        String resultToDisplay = "";


        InputStream in = null;

        // HTTP Get
        try {

            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            in = new BufferedInputStream(urlConnection.getInputStream());

        } catch (Exception e ) {

            System.out.println(e.getMessage());

            Log.e("AysyncFetchDiscussions", e.getMessage());
            resultToDisplay = "Failed to load user:Failed to load message";
            return resultToDisplay;
        }

        byte[] contents = new byte[1024];

        int bytesRead=0;
        resultToDisplay = "";
        try {
            while( (bytesRead = in.read(contents)) != -1){
                resultToDisplay += new String(contents, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultToDisplay;
    }

    protected void onPostExecute(String result){
        mainThread.displayDiscussions(result);
    }
}
