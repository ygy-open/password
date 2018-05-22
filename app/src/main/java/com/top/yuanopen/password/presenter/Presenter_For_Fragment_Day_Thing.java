package com.top.yuanopen.password.presenter;

import android.widget.Toast;

import com.top.yuanopen.password.bean.thing;
import com.top.yuanopen.password.fragment.Fragment_Things_Of_Today;
import com.top.yuanopen.password.model.Db_Every_Thing;
import com.top.yuanopen.password.model.Db_QueryListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yuanopen on 2018/4/12/012.
 */

public class Presenter_For_Fragment_Day_Thing  {
    private Fragment_Things_Of_Today fragment_things_of_today;
    private Db_Every_Thing db_every_thing;
    Date day=new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public Presenter_For_Fragment_Day_Thing(Fragment_Things_Of_Today fragment_things_of_today) {
        this.fragment_things_of_today = fragment_things_of_today;
        db_every_thing=new Db_Every_Thing(fragment_things_of_today.getContext());
    }

    public void queryData(){
        db_every_thing.QueryDate(new Db_QueryListener() {
            @Override
            public void OnSuccess(Object object) {
                thing t= (thing) object;
                fragment_things_of_today.setThings(t.getThing_content());
                fragment_things_of_today.setCreateTime(df.format(day));
                Toast.makeText(fragment_things_of_today.getContext(),"查询完毕！",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void OnFail() {
                Toast.makeText(fragment_things_of_today.getContext(),"获取内容失败！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public  void updateData(){
        thing t=new thing();
        t.setThing_content(fragment_things_of_today.getThing());
        t.setCreate_time(df.format(day));

        if(!"".equals(t.getThing_content())){
            db_every_thing.UpdateDate(t);
            Toast.makeText(fragment_things_of_today.getContext(),"更新成功",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(fragment_things_of_today.getContext(),"更新失败！内容不能为空",Toast.LENGTH_SHORT).show();

    }

    public  void writeDate(){
        thing t=new thing();
        t.setThing_content(fragment_things_of_today.getThing());
        t.setCreate_time(df.format(day));
        if(!"".equals(t.getThing_content())){
            db_every_thing.WriteDate(t);
            Toast.makeText(fragment_things_of_today.getContext(),"添加成功",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(fragment_things_of_today.getContext(),"添加失败！内容不能为空",Toast.LENGTH_SHORT).show();

    }

    public boolean hasData(){
        return db_every_thing.hasData();
    }
}
