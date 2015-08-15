package com.cannotcommit.lectorial;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class CourseFragment extends Fragment {
    private ListView mCoursesList;
    private String mSubject;
    private EditText mSearchCourses;
    private ArrayAdapter<String> mCoursesAdapter;
    private String shortCourseName[];

    private final String apiURL = "https://api.uwaterloo.ca/v2/courses/";
    private final String apiKey = "444364245f044a73f69a5e3a306a4790";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_course, container, false);

        mCoursesList = (ListView) v.findViewById(R.id.full_courses_list);
        mCoursesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int position, long id) {
                Intent intent = new Intent(getActivity(), DiscussionActivity.class);
                String course = shortCourseName[position];
                intent.putExtra("course", course);
                startActivity(intent);
            }
        });

        mSearchCourses = (EditText) v.findViewById(R.id.search_courses);
        mSearchCourses.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                if(cs.toString().equals("")) CourseFragment.this.mCoursesAdapter.getFilter().filter(cs);;
                CourseFragment.this.mCoursesAdapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        Bundle arguments = getArguments();
        mSubject = (String)arguments.get("subject");

        String urlString = apiURL + mSubject + ".xml?key=" + apiKey;
        new AsyncFetchCatalog(this).execute(urlString);

        return v;
    }

    public void displayCourses(List<String> result){
        String[] mResults = new String[result.size()];
        shortCourseName = new String[result.size()];
        if(!result.get(0).equals("No courses found.")) {
            for (int i = 0; i < result.size(); i++) {
                mResults[i] = mSubject + " " + result.get(i);
                shortCourseName[i] = mSubject + result.get(i).split("-")[0].trim();
            }
        }else {
            mResults[0] = result.get(0);
        }
        mCoursesAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.subject_row, mResults);
        mCoursesList.setAdapter(mCoursesAdapter);
    }


}
