package com.lyh.guanbei.mvp.contract;

import com.lyh.guanbei.base.ICallbackListener;
import com.lyh.guanbei.base.IModel;
import com.lyh.guanbei.base.IPresenter;
import com.lyh.guanbei.base.IView;
import com.lyh.guanbei.bean.UserBean;

public interface RegisterContract {
    interface IRegisterView extends IView {
        void onRegisterSuccess(UserBean userBean);
        void onRegisterFailed(String msg);
        //填写信息有误
        void onMessageError(String msg);
    }
    interface IRegisterPresenter extends IPresenter<IRegisterView,IRegisterModel>{
        void register(String name,String phone,String pwd);
    }
    interface IRegisterModel extends IModel{
        void register(UserBean userBean, ICallbackListener<UserBean> iCallbackListener);
    }
}
