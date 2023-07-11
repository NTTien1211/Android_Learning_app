package com.hqsoft.esales.doancuoiky;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

public class FollowusActivity extends AppCompatActivity {
    TextView intagram , facebook , linkdin , twitter , playstore ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followus);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Follow Us");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#D2B4F4")));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        anhxa();
        intagram.setMovementMethod(LinkMovementMethod.getInstance());
        facebook.setMovementMethod(LinkMovementMethod.getInstance());
        linkdin.setMovementMethod(LinkMovementMethod.getInstance());
        twitter.setMovementMethod(LinkMovementMethod.getInstance());
        playstore.setMovementMethod(LinkMovementMethod.getInstance());


    }


    private void anhxa() {
        intagram = findViewById(R.id.instagram_link);
        facebook = findViewById(R.id.facebook_link);
        linkdin = findViewById(R.id.linkdin_link);
        twitter = findViewById(R.id.twitter_link);
        playstore= findViewById(R.id.playstore_link);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}