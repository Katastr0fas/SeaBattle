package com.bam.seabattle_v1.ui;

import android.content.Intent;
import android.os.Bundle;

import com.bam.seabattle_v1.R;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.newGameBtn).setOnClickListener(v -> {

            startActivity(new Intent(this, GameActivity.class));
        });
    }

}