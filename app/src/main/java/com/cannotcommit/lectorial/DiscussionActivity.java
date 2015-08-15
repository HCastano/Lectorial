package com.cannotcommit.lectorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DiscussionActivity extends AppCompatActivity {
    private String mCourse;
    private ListView mDiscussionList;
    private ArrayAdapter<String> mDiscussionAdapter;
    public final static String getURL = "http://www.charliezhang.xyz/lectorial-get.php?course=";

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
}
