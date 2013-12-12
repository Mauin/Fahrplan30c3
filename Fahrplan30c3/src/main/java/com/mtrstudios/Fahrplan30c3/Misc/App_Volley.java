package com.mtrstudios.Fahrplan30c3.Misc;

import android.app.Application;

/**
 * Created by Marvin on 12.12.13.
 */
public class App_Volley extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        MyVolley.init(this);
    }
}
