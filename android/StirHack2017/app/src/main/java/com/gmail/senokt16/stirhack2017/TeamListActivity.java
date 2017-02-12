package com.gmail.senokt16.stirhack2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

public class TeamListActivity extends AppCompatActivity {

    RecyclerView teamList;
    EditText joinCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_list);

        teamList = (RecyclerView) findViewById(R.id.team_list);
        joinCode = (EditText) findViewById(R.id.join_code);

        teamList.setLayoutManager(new LinearLayoutManager(this));

    }
}
