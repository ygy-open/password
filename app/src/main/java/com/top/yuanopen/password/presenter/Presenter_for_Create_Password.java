package com.top.yuanopen.password.presenter;

import android.util.Log;
import android.widget.Toast;

import com.top.yuanopen.password.bean.password;
import com.top.yuanopen.password.activity.Fragment_Add_Password;
import com.top.yuanopen.password.utils.BaseRequest;
import com.top.yuanopen.password.utils.Create_My_Password_Request;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yuanopen on 2018/4/13/013.
 */

public class Presenter_for_Create_Password  {

    Fragment_Add_Password fragment_add_password;
    Date day=new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String time=df.format(day);

    public Presenter_for_Create_Password( Fragment_Add_Password fragment_add_password) {
        this.fragment_add_password = fragment_add_password;
    }

    public void requestCreatePassword(password p) {

        Create_My_Password_Request.CreatePasswordParam param = new Create_My_Password_Request.CreatePasswordParam();
        param.name = p.getName();
        param.password=p.getPassword();
        param.image_uri=p.getImage_uri();
        param.create_time=time;
        param.update_time=time;
        param.question_1=p.getQuestion_1();
        param.answer_1=p.getAnswer_1();
        param.question_2=p.getQuestion_2();
        param.answer_2=p.getAnswer_2();
        param.question_3=p.getQuestion_3();
        param.answer_3=p.getAnswer_3();

        Create_My_Password_Request request = new Create_My_Password_Request();

        request.setOnResultListener(new BaseRequest.OnResultListener<password>() {
            @Override
            public void onFail(int code, String msg) {
               Toast.makeText(fragment_add_password, "请求失败：" + msg, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onSuccess(password pass) {
                Toast.makeText(fragment_add_password, "请求成功：" , Toast.LENGTH_SHORT).show();
                fragment_add_password.finish();
            }
        });

        String requestUrl = request.getUrl(param);
        Log.i("uri",requestUrl);
        request.request(requestUrl);
    }

}
