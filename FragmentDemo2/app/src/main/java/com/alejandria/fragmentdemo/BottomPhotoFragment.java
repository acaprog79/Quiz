package com.alejandria.fragmentdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class BottomPhotoFragment extends Fragment {
    private static TextView topTV;
    private static TextView bottomTV;

    public BottomPhotoFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootview = inflater.inflate(R.layout.fragment_bottom_photo, container, false);

        topTV = (TextView) rootview.findViewById(R.id.topTV);
        bottomTV = (TextView) rootview.findViewById(R.id.bottomTV);
        return rootview;
    }
}
