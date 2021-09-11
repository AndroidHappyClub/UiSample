package com.water.uisample;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RatingBarActivity extends AppCompatActivity {
    private RatingBar ribNormal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);
        ribNormal = (RatingBar) findViewById(R.id.ribNormal);
        ribNormal.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> Toast.makeText(RatingBarActivity.this, "rating:" + String.valueOf(rating),
                Toast.LENGTH_LONG).show());
    }

}
