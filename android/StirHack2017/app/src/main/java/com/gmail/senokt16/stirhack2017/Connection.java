package com.gmail.senokt16.stirhack2017;

import android.app.Activity;

public abstract class Connection {
    public Activity activity;
    public abstract void onMessageReceived(String msg);

    public Connection(Activity activity) {
        this.activity = activity;
    }

    public void terminate() {
        Server.removeConnection(this);
    }
}