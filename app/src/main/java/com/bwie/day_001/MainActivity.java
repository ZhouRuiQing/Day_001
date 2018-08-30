package com.bwie.day_001;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.day_001.bean.UiBean;
import com.bwie.day_001.utils.HttpUtils;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements HttpUtils.NetCallBack {

    private ViewPager viewpager;
    private TextView titles;
    private HttpUtils httpUtils;
    private  String path="https://www.zhaoapi.cn/product/getProductDetail?pid=2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager = findViewById(R.id.viewpager);
        titles = findViewById(R.id.titles);

        httpUtils = HttpUtils.getInstance();
        httpUtils.getSeriviceData(path);
        httpUtils.setNetCallBack(this);
    }

    @Override
    public void onccess(String s) {

        Gson gson = new Gson();
        UiBean bean = gson.fromJson(s, UiBean.class);

        for (int i=1 ; i<bean.getSeller().getIcon().length();i++){
            ImageView imageView  = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        }
    }
}
