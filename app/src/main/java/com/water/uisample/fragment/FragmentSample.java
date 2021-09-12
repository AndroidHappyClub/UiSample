package com.water.uisample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.water.uisample.R;

public class FragmentSample extends Fragment {

    public interface OnItemSelectedListener {
        void onItemSelected(View view);
    }

    private OnItemSelectedListener _listener;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        com.water.uisample.databinding.FragmentSampleBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sample, container, false);

        //FragmentActivity页面下点击文本可以切换导航按钮的颜色
        binding.tvFrameContent.setOnClickListener(v -> _listener.onItemSelected(v));

        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            _listener = (OnItemSelectedListener) context;
        } catch (ClassCastException e) {
            Toast.makeText(context, context.toString() + "必须继承接口 OnItemSelectedListener", Toast.LENGTH_LONG).show();
        }
    }
}