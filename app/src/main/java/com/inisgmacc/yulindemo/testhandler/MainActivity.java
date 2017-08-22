package com.inisgmacc.yulindemo.testhandler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.Observable.create;

public class MainActivity extends AppCompatActivity {
    private final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.BOTTOM
    );
    private ListView pop_list;
    private View inflate;
    private FrameLayout viewById;
    private MpickView mpickView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        show();
        WebView webView = (WebView) findViewById(R.id.webview);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(i + "sadasdasdasdas");
        }
        mpickView = new MpickView(this);
        mpickView.addData(list);
        mpickView.show();
        mpickView.setMonitemclicklistener(new MpickView.MOnItemclickListener() {
            @Override
            public void setOnclickLinstener(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {

            }
        }).subscribeOn(Schedulers.io());

        Observable.just("12").subscribe();
        create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onComplete();
                e.onNext(3);

            }
        }).subscribe(new Observer<Integer>() {
            Disposable d;
            @Override
            public void onSubscribe(Disposable d) {
                d=d;
            }

            @Override
            public void onNext(Integer value) {
                Log.d("MainActivity", "value:" + value);
                d.dispose();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("MainActivity", "onComplete");
            }
        });
//        pop_list.setOnTouchListener(onCancelableTouchListener);
    }

//    private void show() {
//        viewById = ((FrameLayout) getWindow().getDecorView().findViewById(android.R.id.content));
//        TextView textView = new TextView(this);
//        textView.setText("123123");
//        textView.setTextSize(40);
//
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            list.add(i + "sadasdasdasdas");
//        }
//        inflate = LayoutInflater.from(this).inflate(R.layout.camera_pop_menu, viewById, false);
//
//        pop_list = (ListView) inflate.findViewById(R.id.pop_list);
//        PopListAdapter popListAdapter = new PopListAdapter(getApplicationContext());
//        popListAdapter.addData(list);
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.pickerview_slide_in_bottom);
//        pop_list.startAnimation(animation);
//        pop_list.setAdapter(popListAdapter);
//        viewById.addView(inflate, params);
//        viewById.requestFocus();
//        pop_list.requestFocus();
//        inflate.setOnTouchListener(onCancelableTouchListener);
//    }

    /**
     * Called when the user touch on black overlay in order to dismiss the dialog
     */
    private final View.OnTouchListener onCancelableTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                dismiss();
            }
            return false;
        }
    };


    private void dismiss() {
        viewById.post(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.pickerview_slide_out_bottom);
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

    public void a(View view) {
        mpickView.show();
    }
}
