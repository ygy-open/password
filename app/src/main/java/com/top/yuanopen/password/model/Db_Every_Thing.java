package com.top.yuanopen.password.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.top.yuanopen.password.bean.thing;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yuanopen on 2018/4/12/012.
 */

public class Db_Every_Thing implements DbManager {
    Context context;
    SQLiteDatabase db;

    Date day=new Date();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    public Db_Every_Thing(Context context) {
        this.context = context;
    }

    @Override
    public void OpenDb() {
        db=context.openOrCreateDatabase("everyday_thing",Context.MODE_PRIVATE,null);
        db.execSQL("create table if not exists things(_id integer primary key autoincrement,thing_content text " +
                "not null,thing_rank integer,create_time text not null)");
    }

    @Override
    public void QueryDate(Db_QueryListener listener) {
        OpenDb();
       Cursor c=db.rawQuery("select * from things where create_time='"+df.format(day)+"'",null);
//        Cursor c=db.rawQuery("select * from things where ",null);
        if (c != null) {
            thing t = new thing();
            if (c.moveToNext()) {
                t.setId(c.getInt(0));
                t.setThing_content(c.getString(1));
                t.setThing_rank(c.getInt(2));
                t.setCreate_time(c.getString(3));
                Log.i("tmie",""+c.getString(1));
                Log.i("tmie",""+c.getString(2));
                Log.i("tmie",""+c.getString(3));
            }
            listener.OnSuccess(t);
            c.close();
            db.close();

        } else {
            listener.OnFail();
            c.close();
            db.close();
        }

    }
    public List<thing> QueryDates(Db_QueryListener listener) {
        OpenDb();
//        Cursor c=db.rawQuery("select * from things where create_time='"+df.format(day)+"'",null);
        List<thing> list=new ArrayList<>();
        Cursor c=db.rawQuery("select * from things order by _id desc ",null);
        if (c != null) {
            while (c.moveToNext()) {
                thing t = new thing();
                t.setId(c.getInt(0));
                t.setThing_content(c.getString(1));
                t.setThing_rank(c.getInt(2));
                t.setCreate_time(c.getString(3));
                Log.i("tmie",""+c.getString(1));
                Log.i("tmie",""+c.getString(2));
                Log.i("tmie",""+c.getString(3));
                list.add(t);
            }
            listener.OnSuccess(list);
            c.close();
            db.close();

        } else {
            listener.OnFail();
            c.close();
            db.close();
        }
 return  list;
    }
    @Override
    public void WriteDate(Object object) {
        thing t= (thing) object;
        OpenDb();
        Log.i("WriteDate",""+df.format(day));
        db.execSQL("insert into things(thing_content,create_time) values ('"+t.getThing_content()+"','"+df.format(day)+"')");
    }

    @Override
    public void UpdateDate(Object object) {
        thing t= (thing) object;
        OpenDb();
        Log.i("UpdateDate",""+df.format(day)+t.getThing_content());
        db.execSQL("update things set  thing_content='"+t.getThing_content()+"' where create_time='"+df.format(day)+"'");
    }


    public void Delete(Object object) {
        thing t= (thing) object;
        OpenDb();
        Log.i("Delete",""+df.format(day)+t.getThing_content());
        db.execSQL("Delete from things  where create_time='"+df.format(day)+"'");
    }

    public  boolean hasData(){
        OpenDb();
        Cursor c=db.rawQuery("select * from things where create_time='"+df.format(day)+"'",null);
        Log.i("count",c.getCount()+"");
           if (c.getCount()==0){
            c.close();
            db.close();
               return true;
        } else {
            c.close();
            db.close();
               return false;
        }
    }

}
