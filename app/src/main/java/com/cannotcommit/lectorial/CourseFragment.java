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


public class CourseFragment extends Fragment {
    private ListView mDrawerList;
    private String[] mDrawerString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_course, container, false);

        mDrawerString = new String[10];
        for(int i = 0; i < 10; i++){
            mDrawerString[i] = "Route Number " + (i+1);
        }
        mDrawerList = (ListView)v.findViewById(R.id.drawer_list);
        mDrawerList.setAdapter(new ArrayAdapter<String>(this.getActivity(), R.layout.drawer_list_item, mDrawerString));

        return v;
    }

}
