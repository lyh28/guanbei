package com.lyh.guanbei.bean;

import com.lyh.guanbei.common.GuanBeiApplication;
import com.lyh.guanbei.db.RecordBeanDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

@Entity
public class RecordBean {
    @Id(autoincrement = true)
    private Long record_id;
    @NotNull
    private long user_id;
    @NotNull
    private long book_id;
    private String time;
    @NotNull
    private String amount;
    private String payto;
    private String content;
    private String remark;      //备注
    private String category;    //分类
    private boolean commit;     //是否上传服务端       为ture时即与服务端同步
    private boolean change;     //是否更新服务端       为false时即不需要同步，没有改变

    public RecordBean(String time, String amount, String payto, String content, String remark) {
        this.time = time;
        this.amount = amount;
        this.payto = payto;
        this.content = content;
        this.remark = remark;
    }

    public RecordBean(long user_id, long book_id, String time, String amount, String payto, String content, String remark, String category) {
        this.user_id = user_id;
        this.book_id = book_id;
        this.time = time;
        this.amount = amount;
        this.payto = payto;
        this.content = content;
        this.remark = remark;
        this.category = category;
    }

    @Generated(hash = 1797708838)
    public RecordBean(Long record_id, long user_id, long book_id, String time, @NotNull String amount,
            String payto, String content, String remark, String category, boolean commit,
            boolean change) {
        this.record_id = record_id;
        this.user_id = user_id;
        this.book_id = book_id;
        this.time = time;
        this.amount = amount;
        this.payto = payto;
        this.content = content;
        this.remark = remark;
        this.category = category;
        this.commit = commit;
        this.change = change;
    }

    @Generated(hash = 96196931)
    public RecordBean() {
    }

    public Long getRecord_id() {
        return this.record_id;
    }

    public void setRecord_id(Long record_id) {
        this.record_id = record_id;
    }

    public long getUser_id() {
        return this.user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getBook_id() {
        return this.book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayto() {
        return this.payto;
    }

    public void setPayto(String payto) {
        this.payto = payto;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getCommit() {
        return this.commit;
    }

    public void setCommit(boolean commit) {
        this.commit = commit;
    }

    public boolean getChange() {
        return this.change;
    }

    public void setChange(boolean change) {
        this.change = change;
    }

    public static List<RecordBean> query(WhereCondition cond,WhereCondition... condMore){
        return GuanBeiApplication.getDaoSession().getRecordBeanDao().queryBuilder().where(cond,condMore).list();
    }
    public static void deleteByBookId(List<Long> ids){
        List<RecordBean> recordBeans=query(RecordBeanDao.Properties.Book_id.in(ids));
        GuanBeiApplication.getDaoSession().getRecordBeanDao().deleteInTx(recordBeans);
    }
    public static void deleteByUserId(List<Long> ids){
        List<RecordBean> recordBeans=query(RecordBeanDao.Properties.User_id.in(ids),RecordBeanDao.Properties.Commit.eq(true));
        GuanBeiApplication.getDaoSession().getRecordBeanDao().deleteInTx(recordBeans);
    }
    @Override
    public String toString() {
        return "RecordBean{" +
                "record_id=" + record_id +
                ", user_id=" + user_id +
                ", book_id=" + book_id +
                ", time='" + time + '\'' +
                ", amount='" + amount + '\'' +
                ", payto='" + payto + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                ", category='" + category + '\'' +
                ", commit=" + commit +
                ", change=" + change +
                '}';
    }
}