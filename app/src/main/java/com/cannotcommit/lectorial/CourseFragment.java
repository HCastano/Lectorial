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

import java.util.List;


public class CourseFragment extends Fragment {
    private ListView mCoursesList;
    private String mSubject;

    private final String apiURL = "https://api.uwaterloo.ca/v2/courses/";
    private final String apiKey = "444364245f044a73f69a5e3a306a4790";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_course, container, false);

        mCoursesList = (ListView) v.findViewById(R.id.full_courses_list);

        Bundle arguments = getArguments();
        mSubject = (String)arguments.get("subject");

        String urlString = apiURL + mSubject + ".xml?key=" + apiKey;
        new AsyncFetchCatalog(this).execute(urlString);

        return v;
    }

    public void displayCourses(List<String> result){
        String[] mResults = new String[result.size()];
        if(!result.get(0).equals("No courses found.")) {
            for (int i = 0; i < result.size(); i++) {
                mResults[i] = mSubject + " " + result.get(i);
            }
        }else {
            mResults[0] = result.get(0);
        }
        mCoursesList.setAdapter(new ArrayAdapter<String>(this.getActivity(), R.layout.subject_row, mResults));
    }


}
