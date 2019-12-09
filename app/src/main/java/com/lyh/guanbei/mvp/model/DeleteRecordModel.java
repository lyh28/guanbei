package com.lyh.guanbei.mvp.model;

import android.util.Log;

import com.lyh.guanbei.base.ICallbackListener;
import com.lyh.guanbei.bean.DeleteRecordBean;
import com.lyh.guanbei.bean.RecordBean;
import com.lyh.guanbei.common.GuanBeiApplication;
import com.lyh.guanbei.http.APIManager;
import com.lyh.guanbei.http.BaseObscriber;
import com.lyh.guanbei.mvp.contract.DeleteRecordContract;
import com.lyh.guanbei.util.LogUtil;

import java.util.List;

public class DeleteRecordModel implements DeleteRecordContract.IDeleteRecordModel {
    @Override
    public void deleteService(final List<Long> idList, final ICallbackListener<String> iCallbackListener) {
        APIManager.deleteRecord(idList, new BaseObscriber<String>() {
            @Override
            protected void onSuccess(String data) {
                iCallbackListener.onSuccess(data);
            }
            @Override
            protected void onFailed(String msg) {
                iCallbackListener.onFailed(msg);
            }
        });
    }

    @Override
    public void deleteLocal(List<Long> idList) {
        GuanBeiApplication.getDaoSession().getRecordBeanDao().deleteByKeyInTx(idList);
    }
}