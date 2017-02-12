package com.gmail.senokt16.stirhack2017;

import android.content.Intent;
import android.media.Image;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class FindOrCreateActivity extends AppCompatActivity {

    ImageButton findTeam, createTeam;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_or_create);

        findTeam = (ImageButton) findViewById(R.id.find_team);
        createTeam = (ImageButton) findViewById(R.id.create_team);
        logo = (ImageView) findViewById(R.id.logo);

        findTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FindOrCreateActivity.this, TeamListActivity.class);
                //ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(FindOrCreateActivity.this, logo, "appIcon");
                //startActivity(i, options.toBundle());
                startActivity(i);
            }
        });
    }
}
