// AddEditFragment.java
// Fragment for adding a new contact or editing an existing one
package com.top.yuanopen.password.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.top.yuanopen.password.R;
import com.top.yuanopen.password.bean.password;
import com.top.yuanopen.password.databinding.FragmentAddPasswordBinding;
import com.top.yuanopen.password.presenter.Presenter_for_Create_Password;

public class Fragment_Add_Password extends Activity {

private Presenter_for_Create_Password presenterforcreatepassword;
   private FragmentAddPasswordBinding fragmentAddPasswordBinding;

   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      fragmentAddPasswordBinding=DataBindingUtil.setContentView(this,R.layout.fragment_add_password);
      init();
   }


   private void init() {
      //初始化
      presenterforcreatepassword=new Presenter_for_Create_Password(this);
      //点击事件
      fragmentAddPasswordBinding.saveFloatingActionButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            presenterforcreatepassword.requestCreatePassword(getOnePassWord());
         }
      });
   }

   public password getOnePassWord(){
      password p=new password();
      p.setName(fragmentAddPasswordBinding.edPasswordName.getText().toString());
      p.setPassword(fragmentAddPasswordBinding.edPasswordContent.getText().toString());
      p.setImage_uri(fragmentAddPasswordBinding.edPasswordImageUri.getText().toString());
      p.setQuestion_1(fragmentAddPasswordBinding.edPasswordQuestion1.getText().toString());
      p.setAnswer_1(fragmentAddPasswordBinding.edPasswordAnswer1.getText().toString());
      p.setQuestion_2(fragmentAddPasswordBinding.edPasswordQuestion2.getText().toString());
      p.setAnswer_2(fragmentAddPasswordBinding.edPasswordAnswer2.getText().toString());
      p.setQuestion_3(fragmentAddPasswordBinding.edPasswordQuestion3.getText().toString());
      p.setAnswer_3(fragmentAddPasswordBinding.edPasswordAnswer3.getText().toString());
      return p;
   }

}


