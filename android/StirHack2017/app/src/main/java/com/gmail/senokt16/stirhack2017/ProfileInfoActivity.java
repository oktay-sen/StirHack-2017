package com.gmail.senokt16.stirhack2017;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileInfoActivity extends AppCompatActivity {
    EditText username, name;
    Spinner university;
    RecyclerView languages;
    Button addLanguage, save;

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

        username = (EditText) findViewById(R.id.username);
        if (currentUser.username != null) {
            username.setText(currentUser.username);
        }
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                currentUser.username = username.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        name = (EditText) findViewById(R.id.name);
        if (currentUser.name != null) {
            name.setText(currentUser.name);
        }
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                currentUser.name = name.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //Force typing uppercase. (From: http://stackoverflow.com/questions/15961813/in-android-edittext-how-to-force-writing-uppercase)
        name.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        university = (Spinner) findViewById(R.id.university);

        final List<String> unis = Arrays.asList(getResources().getStringArray(R.array.universities));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, getResources().getStringArray(R.array.universities));
        adapter.setDropDownViewResource(R.layout.spinner_item);
        university.setAdapter(adapter);
        university.setPopupBackgroundResource(R.drawable.spinner_bg);
        if (currentUser.university != null) {
            university.setSelection(unis.indexOf(currentUser.university));
        }
        university.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currentUser.university = unis.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                currentUser.university = null;
            }
        });

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

        save = (Button) findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentUser != null
                        && currentUser.username != null
                        && currentUser.name != null
                        && currentUser.university != null) {
                    //TODO: save this user.
                    getSharedPreferences(getString(R.string.shared_preference), MODE_PRIVATE).edit().putString("user", currentUser.username).commit();
                    Intent i = new Intent(ProfileInfoActivity.this, MainActivity.class);
                    i.putExtra("user", currentUser);
                    //TODO: transition animation.
                    startActivity(i);
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("user", currentUser);
    }

}
