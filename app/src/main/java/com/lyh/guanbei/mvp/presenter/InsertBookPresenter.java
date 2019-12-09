package com.lyh.guanbei.mvp.presenter;

import android.text.TextUtils;

import com.lyh.guanbei.base.BasePresenter;
import com.lyh.guanbei.base.ICallbackListener;
import com.lyh.guanbei.bean.BookBean;
import com.lyh.guanbei.common.GuanBeiApplication;
import com.lyh.guanbei.http.BaseObscriber;
import com.lyh.guanbei.mvp.contract.InsertBookContract;
import com.lyh.guanbei.mvp.model.InsertBookModel;
import com.lyh.guanbei.util.LogUtil;
import com.lyh.guanbei.util.NetUtil;

import java.util.ArrayList;
import java.util.List;

public class InsertBookPresenter extends BasePresenter<InsertBookContract.IInsertBookView, InsertBookContract.IInsertBookModel> implements InsertBookContract.IInsertBookPresenter {
    @Override
    public void insert(BookBean book) {
        if(TextUtils.isEmpty(book.getBook_name())){
            getmView().onMessageError("账本名不能为空");
            return ;
        }
        List<BookBean> list=new ArrayList<>();
        list.add(book);
        insert(list);
    }

    @Override
    public void insert(final List<BookBean> bookList) {
        getmModel().insertLocal(bookList);
        insertService(bookList);
    }

    @Override
    public void insertService(final List<BookBean> bookList) {
        if(bookList==null||bookList.size()==0)  return ;
        if(NetUtil.isNetWorkAvailable()){
            getmModel().insertService(bookList, new ICallbackListener<List<BookBean>>() {
                @Override
                public void onSuccess(List<BookBean> data) {
                    GuanBeiApplication.getDaoSession().getBookBeanDao().deleteInTx(bookList);
                    getmModel().insertLocal(data);
                }

                @Override
                public void onFailed(String msg) {
                    getmView().onMessageError(msg);
                }
            });
        }
    }

    @Override
    public InsertBookContract.IInsertBookModel createModel() {
        return new InsertBookModel();
    }
}