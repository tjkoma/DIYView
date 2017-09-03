package com.example.tjjunlintx.diyview.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tjjunlintx.diyview.adapters.SearchAdapter;
import com.example.tjjunlintx.diyview.utils.HttpUtils;
import com.example.tjjunlintx.diyview.OkResultListener;
import com.example.tjjunlintx.diyview.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int INFO = 1;
    private String path = "http://apis.baidu.com/apistore/dhc/getalltemplate";
//    @BindView(R.id.textview) TextView textView;
    @BindView(R.id.searchview) SearchView searchview;
    @BindView(R.id.listview) ListView listview;
    @BindView(R.id.btn) Button btn;
    private HashMap<String,String> map = new HashMap<>();
    private List<String> list = new ArrayList<>();
    private SearchAdapter adapter;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case INFO:
//                    textView.setText(msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐去电池等图标和一切修饰部分（状态栏部分）
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        ButterKnife.bind(this);
        getInfo();
        list.add("aaafs");
        list.add("aafsffs");
        list.add("bbsfeefe");
        list.add("bbssgeg");
        adapter = new SearchAdapter(MainActivity.this,list);
        listview.setAdapter(adapter);
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!TextUtils.isEmpty(s)){
                    listview.setFilterText(s);
                    adapter.notifyDataSetChanged();
                }else{
                    listview.clearTextFilter();
                }
                return false;
            }
        });

    }
    private void getInfo(){
        map.put("user","7f6254b8f81f84709228d6d419d488ac");
        map.put("apikey","c25bc1c7b10c7e8d69c3b72ddf88f346");
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils.getResult(map,path, new OkResultListener() {
                    @Override
                    public void onSuccess(Object result) {
                        Log.d(TAG,result.toString());
                        Message message = new Message();
                        message.what = INFO;
                        message.obj = result;
                        handler.sendMessage(message);
                    }
                    @Override
                    public void onFail(IOException e) {
                        e.printStackTrace();
                    }
                });

            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.btn)
    public void gotoSecond(){
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);
    }
}
