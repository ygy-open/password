package com.top.yuanopen.password.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.top.yuanopen.password.R;
import com.top.yuanopen.password.adapter.AdapterMainViewPager;
import com.top.yuanopen.password.fragment.Fragment_My_Password;
import com.top.yuanopen.password.fragment.Fragment_Things_Of_Today;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanopen on 2018/4/12/012.
 */

public class AtyMian extends AppCompatActivity {

    private TabLayout tabLayout;
    AdapterMainViewPager adapter;
    private List<TabLayout.Tab> tabList;
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init() {

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        tabList=new ArrayList<>();

        viewPager=(ViewPager)findViewById(R.id.vp_main);
        tabLayout=(TabLayout)findViewById(R.id.tl_main);

        adapter=new AdapterMainViewPager(getSupportFragmentManager());

        adapter.addFragment(new Fragment_Things_Of_Today());
        adapter.addFragment(new Fragment_My_Password());

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);

        tabLayout.setupWithViewPager(viewPager);

        tabList.add(tabLayout.getTabAt(0));
        tabList.add(tabLayout.getTabAt(1));

        tabList.get(0).setIcon(R.drawable.thing_evevrday);
        tabList.get(1).setIcon(R.drawable.my_password);

    }
}
