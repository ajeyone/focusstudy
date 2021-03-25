package com.ajeyone.focusstudy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.ajeyone.focusstudy.together.TogetherFocusChangeListener;
import com.ajeyone.focusstudy.utils.DebugUtil;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "SecondActivity";

    private static final boolean GLOBAL_FOCUS_CHANGE_DEBUG = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        if (GLOBAL_FOCUS_CHANGE_DEBUG) {
            View view = findViewById(R.id.title);
            view.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
                @Override
                public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                    Log.d(TAG, "onGlobalFocusChanged: oldFocus=" + oldFocus);
                    Log.d(TAG, "onGlobalFocusChanged: newFocus=" + newFocus, new Exception());
                }
            });
        }
        setupButtonContainer(findViewById(R.id.button_container), findViewById(R.id.button_resume));
        setupTogetherFocus(findViewById(R.id.button_container));

        findViewById(R.id.card).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                v.setActivated(hasFocus);
            }
        });
        ViewGroup card = findViewById(R.id.card);
        DebugUtil.debugViewFocus(TAG, "card", card);
        FrameLayout frameLayout = new FrameLayout(this);
        DebugUtil.debugViewFocus(TAG, "default", frameLayout);
    }

    private void setupTogetherFocus(ViewGroup viewGroup) {
        final TogetherFocusChangeListener listener = new TogetherFocusChangeListener() {
            @Override
            protected void onTogetherFocusChange(ViewGroup container, View v, boolean focused) {
                Log.d(TAG, "onTogetherFocusChange: hasFocus=" + container.hasFocus() + ", together focused=" + focused);
                if (focused) {
                    container.setBackgroundResource(R.drawable.bordered_bg);
                } else {
                    container.setBackgroundResource(0);
                }
            }
        };
        final int n = viewGroup.getChildCount();
        for (int i = 0; i < n; i++) {
            View child = viewGroup.getChildAt(i);
            child.setOnFocusChangeListener(listener);
        }
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