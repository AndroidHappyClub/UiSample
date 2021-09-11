package com.water.uisample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.water.uisample.R;

public class FragmentSample extends Fragment implements View.OnClickListener {
    public interface OnItemSelectedListener{
        public void onItemSelected(View view);
    }

    private OnItemSelectedListener _listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sample, container,false);
        TextView tv =(TextView) view.findViewById(R.id.tvFrameContent);
        tv.setText(String.valueOf(getId()));
        tv.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            _listener =(OnItemSelectedListener)context;
        }catch(ClassCastException e){
            Toast.makeText(context, context.toString()
                    +"必须继承接口 OnItemSelectedListener",Toast.LENGTH_LONG);
        }
    }

    @Override
    public void onClick(View view) {
        _listener.onItemSelected(view);
    }
}