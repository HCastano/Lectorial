package com.cannotcommit.lectorial;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class CourseFragment extends Fragment {
    private ListView mDiscussionList;
    private String[] mDiscussionString;
    private String mSubject;

    private final String apiURL = "";
    private final String apiKey = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_course, container, false);

        mDiscussionString = new String[10];
        for(int i = 0; i < 10; i++){
            mDiscussionString[i] = "Discussion " + (i+1);
        }
        mDiscussionList = (ListView)v.findViewById(R.id.discussion_list);
        mDiscussionList.setAdapter(new ArrayAdapter<String>(this.getActivity(), R.layout.drawer_list_item, mDiscussionString));

        Bundle arguments = getArguments();
        mSubject = (String)arguments.get("subject");

        String urlString = apiURL + "?key=" + apiKey;
        //new AsyncFetchSubjects(this).execute(urlString);

        return v;
    }



}
