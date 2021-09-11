package com.water.uisample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.water.uisample.databinding.ActivityProgressBarBinding;

public class ProgressBarActivity extends AppCompatActivity {

    private ActivityProgressBarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_progress_bar);
        bindViews();
    }

    private void bindViews()
    {
        binding.btnPlay.setOnClickListener(view -> {
            int progress = binding.pbPlay.getProgress();
            binding.pbPlay.setProgress(progress + 1);
            binding.pbPlay.setSecondaryProgress(binding.pbPlay.getSecondaryProgress() + 1);
        });
    }
}
