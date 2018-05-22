package com.top.yuanopen.password.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.top.yuanopen.password.R;
import com.top.yuanopen.password.bean.password;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/12/29/029.
 */

public class My_Password_ListAdapter extends BaseAdapter {

    private Context mContext;
    private List<password> listPassword = new ArrayList<password>();
    HashMap<String ,Bitmap>map_image;

    public My_Password_ListAdapter(Context context) {
        this.mContext = context;
        map_image=new HashMap<>();
    }

    public void removeAllPasswods() {
        listPassword.clear();
    }
    public void addNewPasswords(List<password> passwords) {
        if (passwords != null) {
            listPassword.addAll(passwords);
            notifyDataSetChanged();
        }
    }
    public void addPasswords(List<password> passwords) {
        if (passwords != null) {
            listPassword.clear();
            listPassword.addAll(passwords);
            notifyDataSetChanged();
        }
    }
    public List<password> getListPassword(){
        return listPassword;
    }

    @Override
    public int getCount() {
        return listPassword.size();
    }

    @Override
    public password getItem(int position) {
        return listPassword.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PasswordHolder holder = null;
        if (convertView == null) {
           convertView = LayoutInflater.from(mContext).inflate(R.layout.item_my_password, null);
            holder = new PasswordHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (PasswordHolder) convertView.getTag();
        }

        holder.bindData(listPassword.get(position));

        return convertView;
    }


    /**
     *
     */
    private class PasswordHolder {

        View itemView;
        CircleImageView password_Cover;
        TextView password_name;
        TextView password_create_time;
        TextView password_update_time;

        public PasswordHolder(View view) {
            itemView = view;
            password_Cover = (CircleImageView) view.findViewById(R.id.image);
            password_name = (TextView) view.findViewById(R.id.item_passord_name);
            password_create_time = (TextView) view.findViewById(R.id.tv_password_create_time);
            password_update_time = (TextView) view.findViewById(R.id.tv_password_update_time);
        }


        public void bindData(final password pass) {

            this.password_name.setText(pass.getName());
            this.password_Cover.setImageResource(R.drawable.ic_dynamic);
            this.password_create_time.setText(pass.getCreate_time());
            this.password_update_time.setText(pass.getUpdate_time());
// 给 ImageView 设置一个 tag

// 预设一个图片
//            holder.img.setImageResource(R.drawable.ic_launcher);
if(map_image.get(pass.getName())!=null)
    password_Cover.setImageBitmap(map_image.get(pass.getName()));
            else
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (!"".equals(pass.getImage_uri())&&pass.getImage_uri()!=null){
                            getImgae(pass.getImage_uri(),password_Cover,pass.getName());
                    }

                }
            }).start();
        }
    }

    //通过Handler方式
    private  void getImgae(String uri, final  CircleImageView imageView, final String name) {
        Handler handler;
        try {
             URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            InputStream in = conn.getInputStream();
            final Bitmap bitmap = BitmapFactory.decodeStream(in);
            handler=new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                    map_image.put(name,bitmap);
                }
            },1000);

        }catch (MalformedURLException e){

        }catch (IOException e){

        }
    }
}



