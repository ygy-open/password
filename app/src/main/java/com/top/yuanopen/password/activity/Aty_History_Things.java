package com.top.yuanopen.password.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.top.yuanopen.password.R;
import com.top.yuanopen.password.adapter.Adapter_History_Things;
import com.top.yuanopen.password.bean.thing;
import com.top.yuanopen.password.model.Db_Every_Thing;
import com.top.yuanopen.password.model.Db_QueryListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanopen on 2018/4/15/015.
 */

public class Aty_History_Things extends Activity {
    private ListView ls_history_things;
    private Adapter_History_Things adapter;
    List<thing> list;
    private Db_Every_Thing date;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_history_things);
        init();
    }

    private void init() {
        ls_history_things=findViewById(R.id.ls_history_things);
        list=new ArrayList<>();
        date=new Db_Every_Thing(this);
        getDate();
    }

    private  void getDate(){
        date.QueryDates(new Db_QueryListener() {
            @Override
            public void OnSuccess(Object object) {
                list= (List<thing>) object;
                adapter=new Adapter_History_Things(Aty_History_Things.this,list);
                ls_history_things.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Log.i("list",list.size()+"");
                Toast.makeText(Aty_History_Things.this,"查询完毕！",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void OnFail() {
                Toast.makeText(Aty_History_Things.this,"获取内容失败！",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
