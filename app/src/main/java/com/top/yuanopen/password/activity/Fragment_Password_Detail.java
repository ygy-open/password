// DetailFragment.java
// Fragment subclass that displays one contact's details
package com.top.yuanopen.password.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.top.yuanopen.password.R;
import com.top.yuanopen.password.bean.password;
import com.top.yuanopen.password.databinding.FragmentMyPasswordDetailBinding;
import com.top.yuanopen.password.presenter.Presenter_for_Update_Password;

public class Fragment_Password_Detail extends Activity {

   public FragmentMyPasswordDetailBinding fragmentMyPasswordDetailBinding;
   private Presenter_for_Update_Password presenterforupdatepassword;
    private  password pass;
   boolean btn_save=true;
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      fragmentMyPasswordDetailBinding= DataBindingUtil.setContentView(this, R.layout.fragment_my_password_detail);
      pass= (password) getIntent().getSerializableExtra("password");
      if(pass!=null)
      init();
   }

   private void init() {
      presenterforupdatepassword=new Presenter_for_Update_Password(this);
      fragmentMyPasswordDetailBinding.nameOfPassword.setText(pass.getName());
      fragmentMyPasswordDetailBinding.tvPassword.setText(pass.getPassword());
      fragmentMyPasswordDetailBinding.tvQuestion1.setText(pass.getQuestion_1());
      fragmentMyPasswordDetailBinding.tvAnswer1.setText(pass.getAnswer_1());
      fragmentMyPasswordDetailBinding.tvQuestion2.setText(pass.getQuestion_2());
      fragmentMyPasswordDetailBinding.tvAnswer2.setText(pass.getAnswer_2());
      fragmentMyPasswordDetailBinding.tvQuestion3.setText(pass.getQuestion_3());
      fragmentMyPasswordDetailBinding.tvAnswer3.setText(pass.getAnswer_3());

      fragmentMyPasswordDetailBinding.editFloatingActionButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            if(btn_save){
               fragmentMyPasswordDetailBinding.editFloatingActionButton.setImageResource(R.drawable.ic_save_24dp);
               fragmentMyPasswordDetailBinding.tvPassword.setEnabled(true);
               fragmentMyPasswordDetailBinding.tvQuestion1.setEnabled(true);
               fragmentMyPasswordDetailBinding.tvAnswer1.setEnabled(true);
               fragmentMyPasswordDetailBinding.tvQuestion2.setEnabled(true);
               fragmentMyPasswordDetailBinding.tvAnswer2.setEnabled(true);
               fragmentMyPasswordDetailBinding.tvQuestion3.setEnabled(true);
               fragmentMyPasswordDetailBinding.tvAnswer3.setEnabled(true);
               btn_save=false;
            }
            else{
               presenterforupdatepassword.requestCreatePassword(getOnePassWord());
               Toast.makeText(Fragment_Password_Detail.this,"修改成功",Toast.LENGTH_SHORT).show();
               fragmentMyPasswordDetailBinding.editFloatingActionButton.setImageResource(R.drawable.ic_mode_edit_24dp);
               fragmentMyPasswordDetailBinding.tvPassword.setEnabled(false);
               fragmentMyPasswordDetailBinding.tvQuestion1.setEnabled(false);
               fragmentMyPasswordDetailBinding.tvAnswer1.setEnabled(false);
               fragmentMyPasswordDetailBinding.tvQuestion2.setEnabled(false);
               fragmentMyPasswordDetailBinding.tvAnswer2.setEnabled(false);
               fragmentMyPasswordDetailBinding.tvQuestion3.setEnabled(false);
               fragmentMyPasswordDetailBinding.tvAnswer3.setEnabled(false);
               btn_save=true;
            }
         }
      });
   }
   public password getOnePassWord(){
      password p=new password();
      p.setId(pass.getId());
      p.setName(pass.getName());
      p.setPassword(  fragmentMyPasswordDetailBinding.tvPassword.getText().toString());
      p.setImage_uri( pass.getImage_uri());
      p.setQuestion_1( fragmentMyPasswordDetailBinding.tvQuestion1.getText().toString());
      p.setAnswer_1(  fragmentMyPasswordDetailBinding.tvAnswer1.getText().toString());
      p.setQuestion_2(fragmentMyPasswordDetailBinding.tvQuestion2.getText().toString());
      p.setAnswer_2( fragmentMyPasswordDetailBinding.tvAnswer2.getText().toString());
      p.setQuestion_3(fragmentMyPasswordDetailBinding.tvQuestion3.getText().toString());
      p.setAnswer_3( fragmentMyPasswordDetailBinding.tvAnswer3.getText().toString());
      return p;
   }

}


