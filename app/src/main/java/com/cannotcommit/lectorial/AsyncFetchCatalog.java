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
public class AsyncFetchCatalog  extends AsyncTask<String, String, List<String>> {

    private CourseFragment mainThread;

    public AsyncFetchCatalog(CourseFragment listActivity){
        super();
        mainThread = listActivity;
    }

    @Override
    protected List<String> doInBackground(String... params) {
        String urlString=params[0]; // URL to call
        List<String> resultToDisplay = new ArrayList<String>();


        InputStream in = null;

        // HTTP Get
        try {

            URL url = new URL(urlString);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            in = new BufferedInputStream(urlConnection.getInputStream());

        } catch (Exception e ) {

            System.out.println(e.getMessage());

            Log.e("AsyncFetchCatalog", e.getMessage());
            resultToDisplay.add("Failed to load");
            return resultToDisplay;

        }

        // Parse XML
        XmlPullParserFactory pullParserFactory;

        try {
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();

            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            resultToDisplay = parseXML(parser);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultToDisplay;
    }

    protected void onPostExecute(List<String> result){
        mainThread.displayCourses(result);
    }

    private List<String> parseXML(XmlPullParser parser) throws XmlPullParserException, IOException {

        int eventType = parser.getEventType();
        List<String> result = new ArrayList<String>();
        String course = "";

        while( eventType!= XmlPullParser.END_DOCUMENT) {
            String name = null;
            switch(eventType)
            {
                case XmlPullParser.START_TAG:
                    name = parser.getName();

                    if(name.equals("message")){
                        if(parser.nextText().equals("No data returned")){
                            result.add("No courses found.");
                            return result;
                        }
                    }

                    if( name.equals("catalog_number")) {
                        course = parser.nextText();
                    }
                    if(name.equals("title")){
                        course += " - " + parser.nextText();
                        result.add(course);
                    }

                    break;

                case XmlPullParser.END_TAG:
                    break;
            } // end switch

            eventType = parser.next();
        } // end while

        return result;
    }
}
