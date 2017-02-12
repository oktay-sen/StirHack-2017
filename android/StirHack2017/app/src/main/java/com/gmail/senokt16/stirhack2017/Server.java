package com.gmail.senokt16.stirhack2017;


import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static PrintWriter pw;
    private static BufferedReader br;

    private static List<Connection> listeners = new ArrayList<>();

    private static AsyncTask<Void, Void, Void> initTask = new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void... voids) {
            String userName = "Default";

            try {
                Log.d("SERVER", "Connecting to server...");
                Socket sc = new Socket("192.168.43.175", 8888);
                Log.d("SERVER", "Connected!;");

                pw = new PrintWriter(sc.getOutputStream(), true);
                br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
                pw.println(userName);
                send("LOL");
                Log.d("SERVER", "JUST SENT THE THING");

/*            Reader reader = new Reader(br);
            reader.start();*/

/*            do {
            } while (reader.isAlive());*/
            } catch (IOException ex) {
                Log.d("SERVER", "IOException", ex);
            }
            return null;
        }
    };

    private static AsyncTask<Void, Void, Void> listenTask = new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void... voids) {
            while (true) {
                try {
                    final String msg = br.readLine();
                    for (final Connection ls : listeners) {
                        ls.activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("SERVER", "Received: " + msg);
                                ls.onMessageReceived(msg);
                            }
                        });
                    }
                } catch (IOException e) {Log.v("SERVER_LISTEN", "", e);}
            }
        }
    };

    public static void init() {
        Log.d("SERVER", "init called.");
        if (initTask.getStatus() == AsyncTask.Status.PENDING)
            initTask.execute();
        if (listenTask.getStatus() == AsyncTask.Status.PENDING)
            listenTask.execute();
    }

    public static boolean send(final String msg) {
        try {
            if (pw != null) {
                AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        pw.println(msg);
                        return null;
                    }
                }.execute();
                Log.d("SERVER", "Send: pw println: " + msg);
                return true;
            } else {
                Log.d("SERVER", "Send: pw is null");
                return false;
            }
        } catch (Exception e) {
            Log.d("SERVER", "Send: exception: ", e);
            return false;
        }
    }

    public static void addConnection(Connection ls) {
        listeners.add(ls);
    }

    public static void removeListener(Connection ls) {
        listeners.remove(ls);
    }

}
