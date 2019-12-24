package com.lyh.guanbei.ui.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lyh.guanbei.R;
import com.lyh.guanbei.adapter.RecordAdapter;
import com.lyh.guanbei.adapter.SMSAdapter;
import com.lyh.guanbei.base.BaseActivity;
import com.lyh.guanbei.bean.RecordBean;
import com.lyh.guanbei.bean.SMSBean;
import com.lyh.guanbei.manager.CustomSharedPreferencesManager;
import com.lyh.guanbei.ui.widget.BottomRecordDialog;
import com.lyh.guanbei.ui.widget.CustomFloatingBtn;
import com.lyh.guanbei.util.DateUtil;
import com.lyh.guanbei.util.LogUtil;
import com.lyh.guanbei.util.SMSUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AddBySMSActivity extends BaseActivity implements View.OnClickListener {
    private ImageView mClose;
    private ImageView mDone;
    private RecyclerView mRecyclerView;
    private SMSAdapter mSMSAdapter;
    private RecordAdapter mRecordAdapter;
    private CustomFloatingBtn mFloatingBtn;
    private BottomRecordDialog mDialog;

    private Map<Integer,RecordBean> chooseMap;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sms;
    }

    @Override
    protected void initUi() {
        mClose = findViewById(R.id.activity_sms_close);
        mDone = findViewById(R.id.activity_sms_done);
        mRecyclerView = findViewById(R.id.activity_sms_recyclerview);
        mFloatingBtn = findViewById(R.id.activity_sms_floatingbtn);
        mDialog = new BottomRecordDialog(this);
        mDialog.setListener(new BottomRecordDialog.onItemOnClickListener() {
            @Override
            public void onDelete(int record) {
                LogUtil.logD("删除账单");
            }
            @Override
            public void onEdit(RecordBean record) {
                LogUtil.logD("编辑账单");
            }
        });
        mFloatingBtn.setListener(new CustomFloatingBtn.Listener() {
            @Override
            public void onClick() {
                //点击事件
                mRecordAdapter.setNewData(new ArrayList<>(chooseMap.values()));
                mDialog.show();
            }
        });
    }

    @Override
    protected void init() {
        chooseMap = new HashMap<>();
        mRecordAdapter = mDialog.getAdapter();
        //设置列表
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mSMSAdapter = new SMSAdapter();
        mSMSAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SMSBean sms = mSMSAdapter.getItem(position);
                LogUtil.logD(sms.toString());
                sms.setChoose(!sms.isChoose());
                boolean isChoose=sms.isChoose();
                if(isChoose){
                    chooseMap.put(position,createRecordBean());
                }else
                    chooseMap.remove(position);
                mSMSAdapter.notifyItemChanged(position);
                mFloatingBtn.setAmount(chooseMap.size());
            }
        });
        mRecyclerView.setAdapter(mSMSAdapter);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mSMSAdapter.setNewData(SMSUtil.getSmsFromPhone(this));

    }

    private RecordBean createRecordBean() {
        Random random = new Random();
        long userId= CustomSharedPreferencesManager.getInstance(this).getUser().getUser_id();
        long bookId=CustomSharedPreferencesManager.getInstance(this).getCurrBookId();
        return new RecordBean(userId,bookId, DateUtil.getNowDateTime(),""+random.nextInt(100)*50,"");
    }

    @Override
    public void createPresenters() {

    }

    @Override
    public void onClick(View v) {

    }
}