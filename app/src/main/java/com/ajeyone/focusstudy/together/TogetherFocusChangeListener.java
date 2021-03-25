package com.ajeyone.focusstudy.together;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public abstract class TogetherFocusChangeListener implements View.OnFocusChangeListener {
    private static int sTagIdTogetherFocused;

    public static void init(int tagIdTogetherFocused) {
        sTagIdTogetherFocused = tagIdTogetherFocused;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        ViewParent parent = v.getParent();
        if (!(parent instanceof ViewGroup)) {
            return;
        }
        ViewGroup container = ((ViewGroup) parent);
        boolean last = getLastTogetherFocusedState(container);
        boolean current = hasAnyFocusedChild(container);
        if (last != current) {
            onTogetherFocusChange(container, v, current);
            setLastTogetherFocusedState(container, current);
        }
    }

    protected abstract void onTogetherFocusChange(ViewGroup container, View v, boolean focused);

    private static boolean getLastTogetherFocusedState(ViewGroup container) {
        Object tag = container.getTag(sTagIdTogetherFocused);
        return (tag instanceof Boolean) && (Boolean) tag;
    }

    private void setLastTogetherFocusedState(ViewGroup container, boolean focused) {
        container.setTag(sTagIdTogetherFocused, focused);
    }

    private static boolean hasAnyFocusedChild(ViewGroup container) {
        final int n = container.getChildCount();
        for (int i = 0; i < n; i++) {
            View child = container.getChildAt(i);
            if (child.isFocused()) {
                return true;
            }
        }
        return false;
    }
}
