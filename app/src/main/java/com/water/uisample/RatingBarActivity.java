package com.water.uisample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

public class RatingBarActivity extends AppCompatActivity {
    private RatingBar ribNormal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);
        ribNormal = (RatingBar) findViewById(R.id.ribNormal);
        ribNormal.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(RatingBarActivity.this, "rating:" + String.valueOf(rating),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

}
