package com.ajeyone.focusstudy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        View view = findViewById(R.id.title);
        view.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                Log.d(TAG, "onGlobalFocusChanged: oldFocus=" + oldFocus);
                Log.d(TAG, "onGlobalFocusChanged: newFocus=" + newFocus, new Exception());
            }
        });

        setupButtonContainer(findViewById(R.id.button_container), findViewById(R.id.button_resume));
        setupButtonContainer(findViewById(R.id.button_container2), findViewById(R.id.button_resume2));
    }

    @Override
    public void onClick(View v) {
        v.setSelected(!v.isSelected());
    }

    private void setupButtonContainer(final ViewGroup container, View resumeButton) {
        final int n = container.getChildCount();
        for (int i = 0; i < n; i++) {
            container.getChildAt(i).setOnClickListener(this);
        }

        resumeButton.setOnClickListener(v -> {
            final int n1 = container.getChildCount();
            for (int i = 0; i < n1; i++) {
                container.getChildAt(i).setSelected(false);
            }
        });
    }
}