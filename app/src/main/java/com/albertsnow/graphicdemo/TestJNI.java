package com.albertsnow.graphicdemo;

import com.albertsnow.graphicdemo.Utils.LogUtil;

public class TestJNI {
    public int testField = 1115;

    public int testMethod(int[] size) {
        LogUtil.nativeDebug("testMethod be invoked");
        return 19921115;
    }


}
