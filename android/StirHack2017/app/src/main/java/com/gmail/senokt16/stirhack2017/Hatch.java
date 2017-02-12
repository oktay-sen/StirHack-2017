package com.gmail.senokt16.stirhack2017;

import android.app.Application;

/**
 * Created by senok on 12/2/2017.
 */

public class Hatch extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Server.init();
    }
}
