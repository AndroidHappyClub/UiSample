package com.water.uisample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class ProgressBarActivity extends AppCompatActivity {
    private ProgressBar pbPlay;
    private Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        bindViews();
    }

    private void bindViews()
    {
        pbPlay = findViewById(R.id.pbPlay);
        btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int progress = pbPlay.getProgress();
                pbPlay.setProgress(progress + 1);
                pbPlay.setSecondaryProgress(pbPlay.getSecondaryProgress() + 1);
            }
        });
    }
}
