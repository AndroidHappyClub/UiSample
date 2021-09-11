package com.water.uisample;

import android.content.Context;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.water.uisample.databinding.ActivitySeekBarBinding;

public class SeekBarActivity extends AppCompatActivity {

    private Context _context;

    private ActivitySeekBarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_seek_bar);
        _context = SeekBarActivity.this;
        bindViews();
    }

    private void bindViews() {

        binding.sbCustom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.sbNormal.setProgress(progress);
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
