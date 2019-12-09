package com.lyh.guanbei.http.api;


import com.lyh.guanbei.bean.UserBean;
import com.lyh.guanbei.http.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserServiceApi {
    //登录
    @POST("user/login")
    @FormUrlEncoded
    Observable<BaseResponse<UserBean>> login(@Field("user_pwd") String pwd,@Field("user_phone") String phone);
    //注册
    @POST("user/register")
    Observable<BaseResponse<UserBean>> register(@Body UserBean userBean);

}
