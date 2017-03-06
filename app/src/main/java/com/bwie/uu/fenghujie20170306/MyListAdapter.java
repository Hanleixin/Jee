package com.bwie.uu.fenghujie20170306;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bwie.uu.fenghujie20170306.bean.Bean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by dell on 2017/3/6.
 * listView的适配器
 */
public class MyListAdapter extends BaseAdapter {
    private Context context;
    private List<Bean.ResultBean.NationsBean> list;

    public MyListAdapter(Context context, List<Bean.ResultBean.NationsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHold v;
        if(convertView == null){
            convertView = View.inflate(context,R.layout.list_item,null);
            v = new ViewHold();
            v.imageView = (ImageView) convertView.findViewById(R.id.image_detaril);
            v.text_name = (TextView) convertView.findViewById(R.id.text_detaril);
            convertView.setTag(v);
        }else{
            v = (ViewHold) convertView.getTag();
        }

        ImageLoader.getInstance().displayImage(list.get(position).getFqPic(),v.imageView);
        v.text_name.setText(list.get(position).getName());

        return convertView;
    }
    class ViewHold{
        ImageView imageView;
        TextView text_name;

    }
}
