package com.hqsoft.esales.doancuoiky;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.hqsoft.esales.doancuoiky.premium.Photo;
import com.hqsoft.esales.doancuoiky.premium.PhotoAdapter;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import pl.droidsonroids.gif.GifImageView;

public class PremiumActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    private List<Photo> mListphoto ;
    private GifImageView gifImageView;
    private ImageButton imageButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premium);

        viewPager2 = findViewById(R.id.viewpager2);
        circleIndicator3 = findViewById(R.id.circle_center);

        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1- Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        mListphoto =getListphoto();

        PhotoAdapter photoAdapter = new PhotoAdapter(mListphoto);
        viewPager2.setAdapter(photoAdapter);
        circleIndicator3.setViewPager(viewPager2);
        imageButton = findViewById(R.id.close);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private List<Photo> getListphoto(){

        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.laurqjtunscreen));
        list.add(new Photo(R.drawable.animation));
        list.add(new Photo(R.drawable.laurfenkunscreen));
        list.add(new Photo(R.drawable.unscreen));

        return list;
    }

}