package com.inisgmacc.yulindemo.testhandler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.inisgmacc.yulindemo.testhandler.bus.EventBus;
import com.inisgmacc.yulindemo.testhandler.bus.Subscribe;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {

//    @BindView(R.id.text)
//    TextView text;
//    @BindView(R.id.text1)
//    TextView text1;
//    @BindView(R.id.text2)
//    TextView text2;
//    @BindView(R.id.text3)
//    TextView text3;
//    @BindView(R.id.text4)
//    TextView text4;
//    @BindView(R.id.text5)
//    TextView text5;
//    private Disposable subscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
//        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        ImageView viewById = (ImageView) findViewById(R.id.img);

//        final PhotoViewAttacher mAttacher = new PhotoViewAttacher(viewById);
//        mAttacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
//            @Override
//            public void onViewTap(View view, float x, float y) {
//                Toast.makeText(Main2Activity.this, "asdas", Toast.LENGTH_SHORT).show();
//            }
//        });
//        Utils.getinstance().setA2(this);

    }

//    @OnClick({R.id.text, R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.text:
//                Observable.create(new ObservableOnSubscribe<Integer>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//
//                    }
//                }).flatMap(new Function<Integer, ObservableSource<String>>() {
//                    @Override
//                    public ObservableSource<String> apply(Integer integer) throws Exception {
//                        final List<String> list = new ArrayList<>();
//                        for (int i = 0; i < 3; i++) {
//                            list.add("i" + i);
//                        }
//                        return Observable.fromIterable(list);
//                    }
//                }).subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        Log.d("Main2Activity", s);
//                    }
//                });
//                break;
//            case R.id.text1:
//                startActivity(new Intent(this, Main3Activity.class));
//                break;
//            case R.id.text2:
//                break;
//            case R.id.text3:
//
//                break;
//            case R.id.text4:
//                break;
//            case R.id.text5:
//                break;
//        }
//    }

    private void map() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer i) throws Exception {

                return i + "哈哈";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Toast.makeText(Main2Activity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void login() {
        Retrofit retrofit = create();
        Api api = retrofit.create(Api.class);
        api.login(new LoginRequest()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<LoginResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(LoginResponse value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });//在IO线程进行网络请求 .observeOn(AndroidSchedulers.mainThread()) //回到主线程去处理请求结果 .subscribe(new Observer<LoginResponse>() { @Override public void onSubscribe(Disposable d) {} @Override public void onNext(LoginResponse value) {} @Override public void onError(Throwable e) { Toast.makeText(mContext, "登录失败", Toast.LENGTH_SHORT).show(); } @Override public void onComplete() { Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show(); } });
    }

//    private void ioThread() {
//        Observable<String> stringObservable = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                e.onNext("21");
//                e.onNext("21");
//                e.onNext("21");
//                text1.setText("2132");
//                e.onComplete();
//            }
//        });
//        subscribe = stringObservable.observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                Toast.makeText(Main2Activity.this, s, Toast.LENGTH_SHORT).show();
//                text.setText(s);
//            }
//        });
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        CompositeDisposable c = new CompositeDisposable();
//        if (subscribe != null)
//            c.add(subscribe);
//        c.clear();
//    }

    private static Retrofit create() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(9, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        return new Retrofit.Builder().baseUrl("21312").client(builder.build()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    @Subscribe
    public void go(My my) {
        Toast.makeText(this, my.toString(), Toast.LENGTH_SHORT).show();
    }
}
