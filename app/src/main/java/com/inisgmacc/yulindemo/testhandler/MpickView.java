package com.inisgmacc.yulindemo.testhandler;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsh on 2017/7/31 10 17.
 */

public class MpickView {
    private final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.BOTTOM
    );
    private PopListAdapter popListAdapter;
    private final Context mcontext;
    private ListView pop_list;
    private View inflate;
    private FrameLayout viewById;
    private MOnItemclickListener monitemclicklistener;

    public MpickView(Context content) {
        popListAdapter = null;
        this.mcontext = content;
    }

    private final View.OnTouchListener onCancelableTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                dismiss();
            }
            return false;
        }
    };

    public void dismiss() {
        viewById.post(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(mcontext, R.anim.pickerview_slide_out_bottom);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        viewById.removeView(inflate);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                pop_list.startAnimation(animation);
            }
        });
    }

    public void addData(List<String> data) {
        if (popListAdapter != null) {
            popListAdapter.addData(data);
        }
    }

    public void show() {
        viewById = ((FrameLayout) ((Activity) mcontext).getWindow().getDecorView().findViewById(android.R.id.content));
        TextView textView = new TextView(mcontext);
        textView.setText("123123");
        textView.setTextSize(40);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(i + "sadasdasdasdas");
        }
        inflate = LayoutInflater.from(mcontext).inflate(R.layout.camera_pop_menu, viewById, false);
        pop_list = (ListView) inflate.findViewById(R.id.pop_list);
        popListAdapter = new PopListAdapter(mcontext);
        popListAdapter.addData(list);
        Animation animation = AnimationUtils.loadAnimation(mcontext, R.anim.pickerview_slide_in_bottom);
        pop_list.startAnimation(animation);
        pop_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (monitemclicklistener != null)
                    monitemclicklistener.setOnclickLinstener(parent, view, position, id);
            }
        });

        pop_list.setAdapter(popListAdapter);
        viewById.addView(inflate, params);
        viewById.requestFocus();
        pop_list.requestFocus();
        inflate.setOnTouchListener(onCancelableTouchListener);
    }

    public void setMonitemclicklistener(MOnItemclickListener monitemclicklistener) {
        this.monitemclicklistener = monitemclicklistener;
    }

   public static interface MOnItemclickListener {
        void setOnclickLinstener(AdapterView<?> parent, View view, int position, long id);
    }
}
