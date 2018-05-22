package com.top.yuanopen.password.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.top.yuanopen.password.R;
import com.top.yuanopen.password.activity.Aty_History_Things;
import com.top.yuanopen.password.databinding.AtyEverydayThingBinding;
import com.top.yuanopen.password.model.IEveryday_Thing;
import com.top.yuanopen.password.presenter.Presenter_For_Fragment_Day_Thing;

;

/**
 * Created by yuanopen on 2018/4/12/012.
 */

public class Fragment_Things_Of_Today extends Fragment implements IEveryday_Thing{
  AtyEverydayThingBinding binding;
    Presenter_For_Fragment_Day_Thing presenter_for_fragment_day_thing;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter_for_fragment_day_thing=new Presenter_For_Fragment_Day_Thing(this);
        binding= DataBindingUtil.inflate(inflater,R.layout.aty_everyday_thing,null,false);
        View  view = binding.getRoot();
        getActivity().getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        init();

        return  view;
    }

    private void init() {

        presenter_for_fragment_day_thing.queryData();

        binding.btnAddThings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateThings();
            }
        });
        binding.btnOtherDayThings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Aty_History_Things.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void setThings(String str) {
        binding.etThingsEverydayTodo.setText(str);
    }

    @Override
    public void updateThings() {
       if( presenter_for_fragment_day_thing.hasData())
           presenter_for_fragment_day_thing.writeDate();
        else
           presenter_for_fragment_day_thing.updateData();

    }

    @Override
    public void setCreateTime(String time) {
        binding.tvThingCreateTime.setText(time);
    }

    @Override
    public String getThing() {
        return   binding.etThingsEverydayTodo.getText().toString();
    }
}
