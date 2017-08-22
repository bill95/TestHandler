package com.inisgmacc.yulindemo.testhandler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.inisgmacc.yulindemo.testhandler.bus.EventBus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    }

    public void A(View view) {
        EventBus.getDefault().post(new My());
        Log.d("Main3Activity", "EventBus.getDefault():" + ((Object) EventBus.getDefault()).toString());
//        finish();
    }

    public void b(View view) {
        Class<Main2Activity> main2ActivityClass = Main2Activity.class;
        Method[] declaredMethods = main2ActivityClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.getName().equals("go")) {
                My my = new My();
                my.setName("如花");
                try {
                    declaredMethod.invoke(Utils.getinstance().getA2(), my);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
