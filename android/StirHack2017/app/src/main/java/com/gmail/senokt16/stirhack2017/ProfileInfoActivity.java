package com.gmail.senokt16.stirhack2017;

import android.annotation.SuppressLint;
import android.os.PersistableBundle;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileInfoActivity extends AppCompatActivity {
    EditText name;
    Spinner university;
    RecyclerView languages;
    Button addLanguage;

    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null)
            currentUser = (User) savedInstanceState.getSerializable("user");
        if (currentUser == null) {
            currentUser = new User();
            currentUser.languages = new ArrayList<>();
        }

        setContentView(R.layout.activity_profile_info);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        name = (EditText) findViewById(R.id.name);
        if (currentUser.name != null) {
            name.setText(currentUser.name);
        }
        //Force typing uppercase. (From: http://stackoverflow.com/questions/15961813/in-android-edittext-how-to-force-writing-uppercase)
        name.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        university = (Spinner) findViewById(R.id.university);

        List<String> unis = Arrays.asList(getResources().getStringArray(R.array.universities));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.universities));
        adapter.setDropDownViewResource(R.layout.spinner_item);
        university.setAdapter(adapter);
        university.setPopupBackgroundResource(R.drawable.spinner_bg);
        if (currentUser.university != null) {
            university.setSelection(unis.indexOf(currentUser.university));
        }

        languages = (RecyclerView) findViewById(R.id.languages);
        languages.setLayoutManager(new LinearLayoutManager(this));
        languages.setAdapter(new LanguagesAdapter(currentUser.languages, this));
        addLanguage = (Button) findViewById(R.id.add_language);
        addLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentUser.languages.add(null);
                languages.getAdapter().notifyItemInserted(currentUser.languages.size()-1);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("user", currentUser);
    }
}
