package com.gmail.senokt16.stirhack2017;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Server {

    private static PrintWriter pw;
    private static BufferedReader br;

    private static List<Connection> listeners = new ArrayList<>();

    private static Queue<String> msgBuffer = new PriorityBlockingQueue<>();

    private static AsyncTask<Void, Void, Void> initTask = new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void... voids) {
            String userName = "Default";

            try {
                Log.d("SERVER", "Connecting to server...");
                Socket sc = new Socket("192.168.43.175", 8888);
                Log.d("SERVER", "Connected!");

                pw = new PrintWriter(sc.getOutputStream(), true);
                br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
                pw.println(userName);
                pw.println("Hello");
                send("LOL");
                send("LEL");
                Log.d("SERVER", "JUST SENT THE THING");

/*            Reader reader = new Reader(br);
            reader.start();*/

/*            do {
            } while (reader.isAlive());*/
                while (true) {
                    try {
                        while (msgBuffer.size() > 0) {
                            String msg = msgBuffer.poll();
                            Log.d("SERVER", "Sending message: " + msg);
                            pw.println(msg);
                            Log.d("SERVER", "Sent message: " + msg);
                        }
                    } catch (Exception e) {/*Log.v("SERVER_LISTEN", "", e);*/}
                    try {
                        final String msg = br.readLine();
                        for (final Connection ls : listeners) {
                            Runnable toDo = new Runnable() {
                                @Override
                                public void run() {
                                    Log.d("SERVER", "Received: " + msg);
                                    ls.onMessageReceived(msg);
                                }
                            };
                            if (ls.activity != null) {
                                ls.activity.runOnUiThread(toDo);
                            } else {
                                new Handler().post(toDo);
                            }
                        }
                    } catch (Exception e) {}
                }
            } catch (IOException ex) {
                Log.d("SERVER", "IOException", ex);
            }
            return null;
        }
    };

    public static void init() {
        Log.d("SERVER", "init called.");
        if (initTask.getStatus() == AsyncTask.Status.PENDING)
            initTask.execute();
    }

    public static void send(final String msg) {
        msgBuffer.add(msg);
    }

    public static Request request(Activity activity, String cmd, final String... params) {
        final Request r = new Request(cmd, params);
        send(r.msg);
        final Connection c = new Connection(activity) {
            @Override
            public void onMessageReceived(String msg) {
                final String[] parts = msg.split("`");
                if (parts.length > 0 && parts[0].equals(r.id) && r.listener != null) {
                    terminate();
                    Runnable trigger = new Runnable() {
                        @Override
                        public void run() {
                            r.listener.onResponse(Arrays.copyOfRange(parts, 1, parts.length));
                        }
                    };
                    if (activity != null) {
                        activity.runOnUiThread(trigger);
                    } else {
                        new Handler().post(trigger);
                    }
                }
            }
        };
        addConnection(c);
        return r;
    }

    public static class Request {
        String id;
        String cmd;
        String[] params;
        String msg;

        OnResponseListener listener;

        private Request(String cmd, String... params) {
            this.cmd = cmd;
            this.params = params;
            this.id = rand();

            if (params != null && params.length > 0) {
                String prm = params[0];
                for (int i=1; i < params.length; i++) {
                    prm += "`" + params[i];
                }
                msg = id + "`" + cmd + "`" + prm;
            } else {
                msg = id + "`" + cmd;
            }
        }

        public void onResponse(OnResponseListener l) {
            this.listener = l;
        }
    }

    public static interface OnResponseListener {
        void onResponse(String[] params);
    }

    public static void addConnection(Connection ls) {
        listeners.add(ls);
    }

    public static void removeConnection(Connection ls) {
        listeners.remove(ls);
    }

    private static String rand() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }
}
