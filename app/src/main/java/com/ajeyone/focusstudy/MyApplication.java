package com.ajeyone.focusstudy;

import android.app.Application;

import com.ajeyone.focusstudy.together.TogetherFocusChangeListener;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TogetherFocusChangeListener.init(R.id.tag_together_focus_state);
    }
}
