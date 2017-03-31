package com.demo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.demo.constants.IUrls;
import com.demo.model.MovieBean;

import java.util.HashMap;

/**
 * Created by Swapnil on 31/3/17.
 */

public class MovieDetailActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private SliderLayout mDemoSlider;
    MovieBean movieBean;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_movie_detail);
        Intent i = getIntent();
        movieBean = (MovieBean) i.getSerializableExtra("Detail");
        mDemoSlider = (SliderLayout) findViewById(R.id.slider);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(movieBean.getTitle());


        HashMap<String, String> url_maps = new HashMap<String, String>();
        url_maps.put(movieBean.getTitle()+" 1", IUrls.IMAGE_BASE + movieBean.getBackdrop_path());
        url_maps.put(movieBean.getTitle()+" 2", IUrls.IMAGE_BASE + movieBean.getPoster_path());
        url_maps.put(movieBean.getTitle()+" 3", IUrls.IMAGE_BASE + movieBean.getBackdrop_path());
        url_maps.put(movieBean.getTitle()+" 4", IUrls.IMAGE_BASE + movieBean.getPoster_path());

        ((TextView) findViewById(R.id.tvTitle)).setText(movieBean.getOriginal_title());
        ((TextView) findViewById(R.id.tvOverview)).setText(movieBean.getOverview());

        ((RatingBar) findViewById(R.id.ratingBar)).setRating(movieBean.getVote_average()/2);

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterInside)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            mDemoSlider.addSlider(textSliderView);
            mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
            mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            mDemoSlider.setDuration(4000);
            mDemoSlider.addOnPageChangeListener(this);

        }


    }

    @Override
    protected void onStop() {
// To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
