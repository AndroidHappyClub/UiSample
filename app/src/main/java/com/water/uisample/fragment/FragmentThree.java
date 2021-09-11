package com.water.uisample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.water.uisample.R;

public class FragmentThree extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container,false);
        TextView tv = view.findViewById(R.id.tvFrameContent);
        tv.setText("Three");
        return view;
    }
}