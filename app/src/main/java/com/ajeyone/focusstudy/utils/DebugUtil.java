package com.ajeyone.focusstudy.utils;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class DebugUtil {
    public static void debugViewFocus(String tag, String tag2, ViewGroup viewGroup) {
        Log.i(tag, tag2 +
                ": debugViewFocus: getFocusable()=" + (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? focusableToString(viewGroup.getFocusable()) : "UNKNOWN") +
                ", getDescendantFocusability()=" + descendantFocusabilityToString(viewGroup.getDescendantFocusability()) +
                ", isFocusable()=" + viewGroup.isFocusable());
    }

    public static String focusableToString(int focusable) {
        switch (focusable) {
            case View.FOCUSABLE:
                return "FOCUSABLE";
            case View.FOCUSABLE_AUTO:
                return "FOCUSABLE_AUTO";
            case View.NOT_FOCUSABLE:
                return "NOT_FOCUSABLE";
            default:
                return "UNKNOWN";
        }
    }

    public static String descendantFocusabilityToString(int descendantFocusability) {
        switch (descendantFocusability) {
            case ViewGroup.FOCUS_AFTER_DESCENDANTS:
                return "FOCUS_AFTER_DESCENDANTS";
            case ViewGroup.FOCUS_BEFORE_DESCENDANTS:
                return "FOCUS_BEFORE_DESCENDANTS";
            case ViewGroup.FOCUS_BLOCK_DESCENDANTS:
                return "FOCUS_BLOCK_DESCENDANTS";
            default:
                return "UNKNOWN";
        }
    }
}
