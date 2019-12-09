package com.lyh.guanbei.mvp.contract;

import com.lyh.guanbei.base.ICallbackListener;
import com.lyh.guanbei.base.IModel;
import com.lyh.guanbei.base.IPresenter;
import com.lyh.guanbei.base.IView;
import com.lyh.guanbei.bean.RecordBean;

import java.util.List;

/**
 * 先在本地数据库查询，显示加载动画，然后服务端查询，无论成败，关闭动画，成功的话显示，并保存到本地数据库
 */
public interface QueryRecordContract {

    interface IQueryRecordView extends IView{
        void onQueryRecordSuccess(List<RecordBean> recordBeans);
        void onQueryRecordFailed(String msg);
        void startLoading();     //查询服务器时的加载动画
        void endLoading();
    }
    interface IQueryRecordPresenter extends IPresenter<IQueryRecordView,IQueryRecordModel>{
        void queryRecordById(String type,long id);
        void queryRecordById(String type,List<Long> ids);
    }
    interface IQueryRecordModel extends IModel{
        String USERID="userId";
        String BOOKID="bookId";
        void queryRecordFromServiceById(String type,List<Long> ids, ICallbackListener<List<RecordBean>> iCallbackListener);
        void queryRecordFromLocalById(String type,List<Long> ids,ICallbackListener<List<RecordBean>> iCallbackListener);
    }
}
