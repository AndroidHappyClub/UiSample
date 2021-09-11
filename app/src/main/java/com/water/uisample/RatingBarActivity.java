package com.water.uisample;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class RatingBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.water.uisample.databinding.ActivityRatingBarBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_rating_bar);
        binding.ribNormal.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> Toast.makeText(RatingBarActivity.this, "rating:" + rating,
                Toast.LENGTH_LONG).show());
    }
}
