package com.top.yuanopen.password.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.top.yuanopen.password.R;
import com.top.yuanopen.password.bean.thing;
import com.top.yuanopen.password.model.Db_Every_Thing;

import java.util.List;

/**
 * Created by yuanopen on 2018/4/15/015.
 */

public class Adapter_History_Things extends BaseAdapter {
    private Context context;
    private List<thing> list;
    private Db_Every_Thing date;

    public Adapter_History_Things(Context context, List<thing> list) {
        this.context = context;
        this.list = list;
        date =new Db_Every_Thing(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view1= LayoutInflater.from(context).inflate(R.layout.item_history_things, null);
        TextView create_time=view1.findViewById(R.id.tv_history_thing_create_time);
        TextView content=view1.findViewById(R.id.tv_things_everyday_todo);
        FloatingActionButton button=view1.findViewById(R.id.btn_delete_things);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSureDetele(i);
            }
        });

        thing t=new thing();
        t=list.get(i);
        create_time.setText(t.getCreate_time());
        content.setText(t.getThing_content());
        return view1;
    }

    private  void DeleteDate(int id){
        date.Delete(list.get(id));
        list.remove(id);
        Toast.makeText(context,"删除成功!",Toast.LENGTH_SHORT).show();
       notifyDataSetChanged();
    }

    public void isSureDetele(final int id){


                        AlertDialog.Builder builder =
                                new AlertDialog.Builder(context);

                        builder.setTitle(R.string.confirm_title);
                        builder.setMessage(R.string.confirm_message);

                        // provide an OK button that simply dismisses the dialog
                        builder.setPositiveButton(R.string.button_delete,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        DeleteDate(id);
                                    }
                                }
                        );

                        builder.setNegativeButton(R.string.button_cancel, null);

              builder.create(); // return the AlertDialog
        builder.show();

    }

}
