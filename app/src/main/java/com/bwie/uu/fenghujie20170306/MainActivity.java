package com.bwie.uu.fenghujie20170306;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.bwie.uu.fenghujie20170306.bean.Bean;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.youth.banner.Banner;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * B卷
 * 冯虎杰
 * 主界面
 */
public class MainActivity extends AppCompatActivity {
    private String path = "http://www.babybuy100.com/API/getShopOverview.ashx";
    private List<String> images = new ArrayList<>();
    private TextView text_pinpai2;
    private TextView text_pinpai;


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String  str = (String) msg.obj;
            initData(str);
        }
    };
    private GridView gridView;
    private TextView text_guojia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        http();



    }
    //网络请求
    public void http(){
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, path, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Message msg = Message.obtain();
                msg.obj = result;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });
    }

    public void initData(final String str){
        //解析数据
        Gson gson = new Gson();
        Bean bean = gson.fromJson(str, Bean.class);
        final Bean.ResultBean result1 = bean.getResult();
//            Log.i("TAG", "handleMessage: "+advs.get(2).getUrl());
        for (int i = 0; i < result1.getAdvs().size(); i++) {
            images.add(result1.getAdvs().get(i).getPic());
        }
        Banner banner = (Banner) findViewById(R.id.banner);
        banner.setImages(images).setImageLoader(new GlideImageLoader()).start();


        List<Bean.ResultBean.BrandsBean> brands = result1.getBrands();

        text_pinpai.setText(brands.get(0).getTitle());
        text_pinpai2.setText(brands.get(1).getTitle());

        gridView.setAdapter(new MyAdapter(this,result1.getIndexProducts()));
        //点击跳转详情页面
        text_guojia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,Details.class);
                it.putExtra("detail",str);
                startActivity(it);
            }
        });




    }

    //初始化操作
    public void initView(){
        text_pinpai = (TextView) findViewById(R.id.text_pinpai);
        text_pinpai2 = (TextView) findViewById(R.id.text_pinpai2);
        gridView = (GridView) findViewById(R.id.gridView);
        text_guojia = (TextView) findViewById(R.id.text_guojia);

    }

}
