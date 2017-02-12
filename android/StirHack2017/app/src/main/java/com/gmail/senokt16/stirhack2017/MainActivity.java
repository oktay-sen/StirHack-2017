package com.gmail.senokt16.stirhack2017;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {

    User currentUser;
    EditText codeText;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null && getIntent().getSerializableExtra("user") != null) {
            currentUser = (User) getIntent().getSerializableExtra("user");
            Toast.makeText(this, "Username " + currentUser.username + " gotten from intent.", Toast.LENGTH_SHORT).show();
        } else if (getPreferences(MODE_PRIVATE).contains("user")) {
            currentUser = new User();
            currentUser.username = getSharedPreferences(getString(R.string.shared_preference), MODE_PRIVATE).getString("user", null);
            Toast.makeText(this, "Username " + currentUser.username + " gotten from shared preferences.", Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(this, "Please sign up before continuing.", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, ProfileInfoActivity.class);
            Toast.makeText(this, "Username not found. Opening register screen.", Toast.LENGTH_SHORT).show();
            startActivity(i);
        }
        setContentView(R.layout.activity_main);

        codeText = (EditText) findViewById(R.id.code);
        codeText.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        loading = (ProgressBar) findViewById(R.id.loading);
        setLoading(false);
        codeText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                final String text = editable.toString();
                if (text.length() == 6) {
                    setLoading(true);
                    Handler h = new Handler();
                    h.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent i = new Intent(MainActivity.this, FindOrCreateActivity.class);
                            startActivity(i);
                        }
                    }, 5000);
                    //TODO: check the code with server.
/*                    ref.child(text).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                setLoading(false);
                                Intent i = new Intent(StartActivity.this, BuilderActivity.class);
                                i.putExtra("code", text);
                                ActivityOptionsCompat options = ActivityOptionsCompat.
                                        makeSceneTransitionAnimation(StartActivity.this, codeText, "code");
                                //TODO: Do transition animation for the code.
                                startActivity(i, options.toBundle());
                            } else {
                                setLoading(false);
                                setMessageAndClear("TRY AGAIN");
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            setLoading(false);
                            setMessageAndClear("NO CONNECTION");
                        }
                    });*/
                }
            }
        });
    }

    private void setLoading(boolean b) {
        if (b && loading != null && codeText != null) {
            loading.setVisibility(View.VISIBLE);
            codeText.setEnabled(false);
        } else if (loading != null && codeText != null){
            loading.setVisibility(View.INVISIBLE);
            codeText.setEnabled(true);
        }
    }

    private void setMessageAndClear(String msg) {
        if (codeText != null) {
            codeText.setText("");
            codeText.setHint(msg);
        }
    }


}
