package com.example.tjjunlintx.diyview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.tjjunlintx.diyview.OkResultListener;
import com.example.tjjunlintx.diyview.R;
import com.example.tjjunlintx.diyview.utils.HttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SecondActivity extends AppCompatActivity {
    private  ExecutorService fixedThreadPool;
    @BindView(R.id.textview) TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
        initView();
        getData();
    }

    private void initView() {
        fixedThreadPool = Executors.newFixedThreadPool(3);
    }

    private void getData(){
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++){

                    HashMap<String, String> map = new HashMap<>();
                    final int finalI = i;
                    HttpUtils.getResult(map, "http://www.baidu.com", new OkResultListener() {
                        @Override
                        public void onSuccess(final Object result) {
                            try {
                                Thread.sleep(3*1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(finalI == 5){
                                fixedThreadPool.shutdown();
                            }
                            Log.e("TAG", finalI +"");
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                textview.setText(result.toString());
//                            }
//                        });
                        }

                        @Override
                        public void onFail(IOException e) {

                        }
                    });

                }
            }
        });
    }

}
