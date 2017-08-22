package com.inisgmacc.yulindemo.testhandler;

/**
 * Created by lsh on 2017/8/21 13 52.
 */

public class Utils {
    Main2Activity a2;
    static Utils u;

    public static Utils getinstance() {
        if (Utils.u == null) {
            u = new Utils();
            return u;
        }
        return u;
    }

    public Main2Activity getA2() {
        return a2;
    }

    public void setA2(Main2Activity a2) {
        this.a2 = a2;
    }
}
