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
public class AsyncSendMessage  extends AsyncTask<String, String, String> {

    public AsyncSendMessage(DiscussionActivity listActivity){
        super();
    }

    @Override
    protected String doInBackground(String... params) {
        String urlString=params[0]; // URL to call
        String resultToDisplay = "Success";
        System.out.println(urlString);

        InputStream in = null;

        // HTTP Get
        try {

            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            in = new BufferedInputStream(urlConnection.getInputStream());

        } catch (Exception e ) {

            System.out.println(e.getMessage());

            Log.e("AsyncSendMessage", e.getMessage());
            resultToDisplay = "Fail";
            return resultToDisplay;
        }

        return resultToDisplay;
    }
}
