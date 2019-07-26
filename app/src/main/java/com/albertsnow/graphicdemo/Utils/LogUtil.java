package com.albertsnow.graphicdemo.Utils;

import android.util.Log;

public class LogUtil {
    private static String DEBUG_TAG = "DebugTag";

    private static final String TAG = "NativeTest";

    private static boolean allowLog(String tag, String msg) {
        return true;
    }

    public static void nativeDebug(String msg) {
        if (allowLog(TAG, msg)) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (allowLog(tag, msg)) {
            Log.d(tag, msg);
        }
    }

    public static void debug(String msg) {
        if (allowLog(DEBUG_TAG, msg)) {
            Log.d(DEBUG_TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (allowLog(tag, msg)) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (allowLog(tag, msg)) {
            Log.e(tag, msg);
        }
    }


}
