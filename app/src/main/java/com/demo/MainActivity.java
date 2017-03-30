package com.demo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ((Button)findViewById(R.id.login)).setOnClickListener(this);
        ((Button)findViewById(R.id.profile)).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                Intent i = new Intent(this,LoginActivity.class);
                startActivity(i);
                break;
            case R.id.profile:
                Intent i1 = new Intent(this,ProfileActivity.class);
                startActivity(i1);
                break;
        }
    }
}
