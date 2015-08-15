package com.cannotcommit.lectorial;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ViewCourseFragment extends Fragment {

    public final static String apiKey = "444364245f044a73f69a5e3a306a4790";
    public final static String apiURL = "https://api.uwaterloo.ca/v2/codes/subjects.xml";

    private ListView mCoursesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_view_course, container, false);
        mCoursesList = (ListView) v.findViewById(R.id.course_list);
        mCoursesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int position, long id) {
                // Insert the fragment by replacing any existing fragment
                Fragment fragment = new CourseFragment();
                Bundle bundle = new Bundle();
                bundle.putString("subject",((TextView)v.findViewById(R.id.list_textview)).getText().toString() );
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.drawer_content, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        String urlString = apiURL + "?key=" + apiKey;
        new AsyncFetchSubjects(this).execute(urlString);

        return v;
    }

    public void displaySubjects(List<String> result){
        String[] mResults = new String[result.size()];
        for(int i = 0; i < result.size(); i++){
            mResults[i] = result.get(i);
        }
        mCoursesList.setAdapter(new ArrayAdapter<String>(this.getActivity(), R.layout.subject_row, mResults));
    }

}
