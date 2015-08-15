package com.cannotcommit.lectorial;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DiscussionActivity extends AppCompatActivity {
    private String mCourse;
    private ListView mDiscussionList;
    private ArrayAdapter<String> mDiscussionAdapter;
    public final static String getURL = "http://www.charliezhang.xyz/lectorial-get.php?course=";
    public final static String addURL = "http://www.charliezhang.xyz/lectorial-add.php?course=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        mDiscussionList = (ListView) findViewById(R.id.discussion_list);

        mCourse =  getIntent().getStringExtra("course");

        String urlString = getURL + mCourse;
        new AsyncFetchDiscussion(this).execute(urlString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_discussion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displayDiscussions(String result){
        String[] discussions = result.split(";");
        mDiscussionAdapter = new ArrayAdapter<String>(this, R.layout.discussion_row, discussions);
        mDiscussionList.setAdapter(mDiscussionAdapter);
    }

    public void messageClick(View v) throws IOException {
        String message = ((EditText)findViewById(R.id.message)).getText().toString();
        if(message.equals("")) return;

        SharedPreferences settings = getSharedPreferences("Username", 0);
        String name = settings.getString("username", "Username");
        sendMessage(name, message);
    }

    public void sendMessage(String name, String message) throws IOException {
        String urlString = addURL + mCourse + "&name=" + name + "&message=" + message;
        URL url = new URL(urlString);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        BufferedInputStream in = new BufferedInputStream(urlConnection.getInputStream());

        urlString = getURL + mCourse;
        new AsyncFetchDiscussion(this).execute(urlString);
    }
}
