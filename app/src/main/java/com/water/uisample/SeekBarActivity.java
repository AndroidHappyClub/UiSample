package com.water.uisample;

import android.content.Context;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SeekBarActivity extends AppCompatActivity {
    private SeekBar sbNormal;
    private Context _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);
        _context = SeekBarActivity.this;
        bindViews();
    }

    private void bindViews() {
        SeekBar sbCustom = findViewById(R.id.sbCustom);
        sbNormal= findViewById(R.id.sbNormal);

        sbCustom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sbNormal.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(_context, "触碰SeekBar", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(_context, "放开SeekBar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
