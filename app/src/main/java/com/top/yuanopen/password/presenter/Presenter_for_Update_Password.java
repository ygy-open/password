package com.top.yuanopen.password.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.top.yuanopen.password.bean.password;
import com.top.yuanopen.password.utils.BaseRequest;
import com.top.yuanopen.password.utils.Update_My_Password_Request;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yuanopen on 2018/4/16/016.
 */

public class Presenter_for_Update_Password {

  Context context;
    Date day=new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    String time=df.format(day);

    public Presenter_for_Update_Password(Context context) {
        this.context = context;
    }

    public void requestCreatePassword(password p) {

        Update_My_Password_Request.CreatePasswordParam param = new  Update_My_Password_Request.CreatePasswordParam();
        param.id=p.getId();
        param.name = p.getName();
        param.password=p.getPassword();
        param.image_uri=p.getImage_uri();
        param.update_time=time;
        param.question_1=p.getQuestion_1();
        param.answer_1=p.getAnswer_1();
        param.question_2=p.getQuestion_2();
        param.answer_2=p.getAnswer_2();
        param.question_3=p.getQuestion_3();
        param.answer_3=p.getAnswer_3();

        Update_My_Password_Request request = new Update_My_Password_Request();

        request.setOnResultListener(new BaseRequest.OnResultListener<password>() {
            @Override
            public void onFail(int code, String msg) {
                Toast.makeText(context, "请求失败：" + msg, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onSuccess(password pass) {
                Toast.makeText(context, "请求成功：" , Toast.LENGTH_SHORT).show();
            }
        });

        String requestUrl = request.getUrl(param);
        Log.i("uri",requestUrl);
        request.request(requestUrl);
    }

}
