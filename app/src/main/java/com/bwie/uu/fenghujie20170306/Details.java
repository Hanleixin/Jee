package com.bwie.uu.fenghujie20170306;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bwie.uu.fenghujie20170306.bean.Bean;
import com.google.gson.Gson;

/**
 * Created by dell on 2017/3/6.
 * 冯虎杰
 * 详情页面
 */

public class Details extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        listView = (ListView) findViewById(R.id.listView);
        //获取到传过来的数据
        Intent it = getIntent();
        String detail = it.getStringExtra("detail");
        Log.i("TAG", "onCreate: "+detail);
        //解析
        Gson gson = new Gson();
        Bean bean = gson.fromJson(detail, Bean.class);
        Bean.ResultBean result1 = bean.getResult();

        listView.setAdapter(new MyListAdapter(Details.this,result1.getNations()));


    }
}
