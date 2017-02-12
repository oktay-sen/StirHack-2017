package com.gmail.senokt16.stirhack2017;

import android.app.Activity;

/**
 * Created by senok on 12/2/2017.
 */

public abstract class Connection {
    public Activity activity;
    public abstract void onMessageReceived(String msg);

    public Connection(Activity activity) {
        this.activity = activity;
    }
}