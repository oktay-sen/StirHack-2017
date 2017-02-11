package com.gmail.senokt16.stirhack2017;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

public class FindOrCreateActivity extends AppCompatActivity {

    ImageButton findTeam, createTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_or_create);

        findTeam = (ImageButton) findViewById(R.id.find_team);
        createTeam = (ImageButton) findViewById(R.id.create_team);


    }
}
