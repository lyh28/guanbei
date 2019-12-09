package com.lyh.guanbei.mvp.contract;

import com.lyh.guanbei.base.ICallbackListener;
import com.lyh.guanbei.base.IModel;
import com.lyh.guanbei.base.IPresenter;
import com.lyh.guanbei.base.IView;
import com.lyh.guanbei.bean.BookBean;

import java.util.List;

public interface UpdateBookContract {
    interface IUpdateBookView extends IView{
        void onUpdateBookFailed(String msg);
    }
    interface IUpdateBookPresenter extends IPresenter<IUpdateBookView,IUpdateBookModel>{
        void updateBook(BookBean book);
        void updateBook(List<BookBean> bookList);
        void updateBookService(List<BookBean> bookList);
    }
    interface IUpdateBookModel extends IModel{
        void updateBookLocal(List<BookBean> bookList);
        void updateBookService(List<BookBean> bookList,ICallbackListener<String> iCallbackListener);
    }
}
