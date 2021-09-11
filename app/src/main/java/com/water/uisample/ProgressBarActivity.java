package com.water.uisample;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class ProgressBarActivity extends AppCompatActivity {
    private ProgressBar pbPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        bindViews();
    }

    private void bindViews()
    {
        pbPlay = findViewById(R.id.pbPlay);
        Button btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(view -> {
            int progress = pbPlay.getProgress();
            pbPlay.setProgress(progress + 1);
            pbPlay.setSecondaryProgress(pbPlay.getSecondaryProgress() + 1);
        });
    }
}
