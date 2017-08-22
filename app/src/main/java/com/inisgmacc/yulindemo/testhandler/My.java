package com.inisgmacc.yulindemo.testhandler;

/**
 * Created by lsh on 2017/8/18 13 41.
 */

public class My {
    private String name;
    public String getName() {
        return name;
    }

    private Object oo;
    public void setName(String name) {
        this.name = name;
    }

    public Object getOo() {
        return oo;
    }

    public void setOo(Object oo) {
        this.oo = oo;
    }

    @Override
    public String toString() {
        return "My{" +
                "name='" + name + '\'' +
                '}';
    }
}
