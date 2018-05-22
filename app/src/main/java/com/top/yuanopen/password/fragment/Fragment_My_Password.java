package com.top.yuanopen.password.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.top.yuanopen.password.R;
import com.top.yuanopen.password.activity.Fragment_Add_Password;
import com.top.yuanopen.password.activity.Fragment_Password_Detail;
import com.top.yuanopen.password.adapter.My_Password_ListAdapter;
import com.top.yuanopen.password.bean.password;
import com.top.yuanopen.password.databinding.FragmentMyPasswordListBinding;
import com.top.yuanopen.password.utils.BaseRequest;
import com.top.yuanopen.password.utils.Get_My_Password_ListRequest;
import com.top.yuanopen.password.widget.MySwipeReflashLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanopen on 2018/4/12/012.
 */

public class Fragment_My_Password extends Fragment {

    FragmentMyPasswordListBinding myPasswordListBinding;
    private My_Password_ListAdapter mLiveListAdapter;
    private  password currentPassword=null;
 private  int Request_Time=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view ;
        myPasswordListBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_my_password_list,null,false);
        view=myPasswordListBinding.getRoot();
        init();
        requestLiveList(Request_Time);
        return  view;
    }


    private void requestLiveList(int time) {

        //请求前20个数据
        Get_My_Password_ListRequest liveListRequest = new Get_My_Password_ListRequest();
        liveListRequest.setOnResultListener(new BaseRequest.OnResultListener<List<password>>() {
            @Override
            public void onSuccess(List<password> passwords) {
//                mLiveListAdapter.removeAllPasswods();//下拉刷新，先移除掉之前的room信息
                mLiveListAdapter.addNewPasswords(passwords);/* 再添加新的信息 */
                myPasswordListBinding.swipeRefreshLayoutList.setRefreshing(false);
                Toast.makeText(getActivity(), "请求列表成功：" , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFail(int code, String msg) {
                Toast.makeText(getActivity(), "请求列表失败：" + msg, Toast.LENGTH_SHORT).show();
                myPasswordListBinding.swipeRefreshLayoutList.setRefreshing(false);
            }
        });

        Get_My_Password_ListRequest.LiveListParam param = new Get_My_Password_ListRequest.LiveListParam();
        param.pageIndex = time;//从0开始，也就是第一页。
        String url = liveListRequest.getUrl(param);
        liveListRequest.request(url);
    }

    private void init() {
        mLiveListAdapter = new My_Password_ListAdapter(getContext());
        myPasswordListBinding.liveList.setAdapter(mLiveListAdapter);
// 设置下拉刷新时的颜色值,可以根据你自己的喜好来设置
        myPasswordListBinding.swipeRefreshLayoutList.
                setColorSchemeColors(
                       getResources().getColor(R.color.colorAccent),
                        getResources().getColor(R.color.base_bg),
                                getResources().getColor( R.color.aliceblue),
                                        getResources().getColor(R.color.colorPrimaryDark));

        myPasswordListBinding.swipeRefreshLayoutList.setOnRefreshListener(new MySwipeReflashLayout.OnRefreshListener(){

            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), "刷新中" , Toast.LENGTH_SHORT).show();
                requestLiveList(++Request_Time);
                // 更新完后调用该方法结束刷新
                myPasswordListBinding.swipeRefreshLayoutList.setRefreshing(false);
            }
        });
        myPasswordListBinding.swipeRefreshLayoutList.setOnLoadListener(new MySwipeReflashLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                Toast.makeText(getActivity(), "刷新中" , Toast.LENGTH_SHORT).show();
                myPasswordListBinding.swipeRefreshLayoutList.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        requestLiveList(++Request_Time);
                        myPasswordListBinding.swipeRefreshLayoutList.setLoading(false);
//                        getData().add(new Date().toGMTString());
//                        adapter.notifyDataSetChanged();
//                        // 加载完后调用该方法
//                        mySwipeRefreshLayout.setLoading(false);
                    }
                }, 2000);
            }
        });

//        myPasswordListBinding.swipeRefreshLayoutList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                Toast.makeText(getActivity(), "刷新中" , Toast.LENGTH_SHORT).show();
//                requestLiveList(++Request_Time);
//            }
//        });

        //添加信息
        myPasswordListBinding.btnAddPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),Fragment_Add_Password.class);
                startActivity(intent);
            }
        });
        myPasswordListBinding.liveList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                getEditCustomDialog( mLiveListAdapter.getListPassword().get(i)).show();
                password p=mLiveListAdapter.getListPassword().get(i);
                currentPassword=p;
                List<question>list=new ArrayList<question>();
                 list.add(new question(p.getQuestion_1(),p.getAnswer_1()));
                list.add(new question(p.getQuestion_2(),p.getAnswer_2()));
                list.add(new question(p.getQuestion_3(),p.getAnswer_3()));
                showDialog(list);
            }
        });
    }
    private int cur=0;
    public void showDialog(final List<question>list) {
        LayoutInflater inflater = getLayoutInflater(getArguments());
        View view = inflater.inflate(R.layout.dialog, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);
        final EditText content=view.findViewById(R.id.ed_answer_content);
        Button btn_cancle=view.findViewById(R.id.btn_cancel);
        Button btn_next=view.findViewById(R.id.btn_next);

        builder.setTitle(list.get(cur).getQuestion_content());

        final AlertDialog dialog = builder.create();

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(showQuestion(dialog,list,cur,content.getText().toString())){
                    content.setText("");
                    cur++;
                }

            }
        });
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                cur=0;
            }
        });
        dialog.show();

    }

    private boolean showQuestion(AlertDialog dialog,List<question>list,int t,String answer) {


        if (answer.equals(list.get(t).getAnswer_content())) {
            Toast.makeText(getContext(), "答对第" + (t + 1) + "题", Toast.LENGTH_SHORT).show();
            if(cur==2){
                dialog.cancel();
                Intent intent=new Intent(getContext(), Fragment_Password_Detail.class);
                intent.putExtra("password", currentPassword);
                startActivity(intent);
                cur=0;
            }
                else
                dialog.setTitle(list.get(t+1).getQuestion_content());
            return true;
        } else {
            Toast.makeText(getContext(), "答案错误！", Toast.LENGTH_SHORT).show();
            return  false;
        }
    }


        class  question{
            private  String question_content;
            private  String answer_content;

            public question(String question_content, String answer_content) {
                this.question_content = question_content;
                this.answer_content = answer_content;
            }

            public String getQuestion_content() {
                return question_content;
            }

            public void setQuestion_content(String question_content) {
                this.question_content = question_content;
            }

            public String getAnswer_content() {
                return answer_content;
            }

            public void setAnswer_content(String answer_content) {
                this.answer_content = answer_content;
            }
        }


}
