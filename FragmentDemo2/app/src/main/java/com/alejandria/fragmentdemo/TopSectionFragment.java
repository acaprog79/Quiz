package com.alejandria.fragmentdemo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;



public class TopSectionFragment extends Fragment {

    private static EditText topET;
    private static EditText bottomET;

    public TopSectionFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater, ViewGroup container, Bundle savedInstanceState){
        View rootview = inflater.inflate(R.layout.fragment_top_section, container, false);

        topET = (EditText) rootview.findViewById(R.id.topET);
        bottomET = (EditText) rootview.findViewById(R.id.bottomET);
        final Button button = (Button) rootview.findViewById(R.id.memifyButton);

        button.setOnclickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                buttonClicked(v);
            }
        });
        return rootview;
    }

    private void buttonClicked(View v){

    }
}
