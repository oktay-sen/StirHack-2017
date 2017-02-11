package com.gmail.senokt16.stirhack2017;

import android.annotation.SuppressLint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class ProfileInfoActivity extends AppCompatActivity {
    EditText name;
    Spinner university;
    RecyclerView languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile_info);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        name = (EditText) findViewById(R.id.name);
        //Force typing uppercase. (From: http://stackoverflow.com/questions/15961813/in-android-edittext-how-to-force-writing-uppercase)
        name.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        university = (Spinner) findViewById(R.id.university);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.universities));
        adapter.setDropDownViewResource(R.layout.spinner_item);
        university.setAdapter(adapter);
        university.setPopupBackgroundResource(R.drawable.spinner_bg);

        languages = (RecyclerView) findViewById(R.id.languages);
        languages.setLayoutManager(new LinearLayoutManager(this));
        //languages.setAdapter(new LanguagesAdapter());
    }
}
