package com.albertsnow.graphicdemo;

import com.albertsnow.graphicdemo.Utils.LogUtil;

public class TestJNI {

    public int groupId = -1;
    public int rowNum = -1;
    public String lineText = "";
    public int testField = 1115;

    public int testMethod(int[] size) {
        LogUtil.nativeDebug("testMethod be invoked");
        return 19921115;
    }


}
