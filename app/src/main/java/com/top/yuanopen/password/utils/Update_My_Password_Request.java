package com.top.yuanopen.password.utils;

import java.io.IOException;

/**
 * Created by yuanopen on 2018/4/16/016.
 */

public class Update_My_Password_Request  extends BaseRequest {

    private static final String Action = "http:106.14.182.157:8080/mypassword/roomServlet?action=update";

    private static final String RequestParamKey_Id= "id";
    private static final String RequestParamKey_Name = "name";
    private static final String RequestParamKey_Image_Uri = "image_uri";
    private static final String RequestParamKey_Password = "password";
    private static final String RequestParamKey_Update_Time = "update_time";
    private static final String RequestParamKey_Question_1 = "question_1";
    private static final String RequestParamKey_Answer_1 = "answer_1";
    private static final String RequestParamKey_Question_2 = "question_2";
    private static final String RequestParamKey_Answer_2 = "answer_2";
    private static final String RequestParamKey_Question_3 = "question_3";
    private static final String RequestParamKey_Answer_3 = "answer_3";
    ;


    public static class CreatePasswordParam {
        public   int id;//密码名称
        public   String name;//密码名称
        public  String image_uri;//图片地址
        public  String  password;//密码内容，ssh加密
        public  String create_time;//创建时间
        public  String update_time;//更新时间
        public String question_1;//问题1
        public String answer_1;//问题1答案
        public String question_2;//问题1
        public String answer_2;//问题1答案
        public String question_3;//问题1
        public String answer_3;//问题1答案
    }

    public String getUrl(CreatePasswordParam param) {
        return Action
                + "&" + RequestParamKey_Id+ "=" + param.id
                + "&" + RequestParamKey_Name + "=" + param.name
                + "&" + RequestParamKey_Image_Uri + "=" + param.image_uri
                + "&" + RequestParamKey_Password + "=" + param.password
                + "&" + RequestParamKey_Update_Time + "=" + param.update_time
                +"&"+RequestParamKey_Question_1+"="+param.question_1
                +"&"+RequestParamKey_Answer_1+"="+param.answer_1
                +"&"+RequestParamKey_Question_2+"="+param.question_2
                +"&"+RequestParamKey_Answer_2+"="+param.answer_2
                +"&"+RequestParamKey_Question_3+"="+param.question_3
                +"&"+RequestParamKey_Answer_3+"="+param.answer_3
                ;
    }

    @Override
    protected void onFail(IOException e) {
        sendFailMsg(-100, e.getMessage());
    }


    @Override
    protected void onResponseFail(int code) {
        sendFailMsg(code, "服务出现异常");
    }

    @Override
    protected void onResponseSuccess(String body) {
        PasswordInfoResponseObj responseObject = gson.fromJson(body, PasswordInfoResponseObj.class);
        if (responseObject == null) {
            sendFailMsg(-101, "数据格式错误");
            return;
        }

        if (responseObject.code.equals(ResponseObject.CODE_SUCCESS)) {
            sendSuccMsg(responseObject.data);
        } else if (responseObject.code.equals(ResponseObject.CODE_FAIL)) {
            sendFailMsg(Integer.valueOf(responseObject.errCode), responseObject.errMsg);
        }
    }
}

