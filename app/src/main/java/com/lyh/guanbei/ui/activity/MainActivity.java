package com.lyh.guanbei.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lyh.guanbei.R;
import com.lyh.guanbei.adapter.HomePageAdapter;
import com.lyh.guanbei.base.BaseActivity;
import com.lyh.guanbei.common.NetRestartService;
import com.lyh.guanbei.mvp.contract.NetListenerContract;
import com.lyh.guanbei.mvp.presenter.NetListenerPresenter;
import com.lyh.guanbei.ui.fragment.BookPageFragment;
import com.lyh.guanbei.ui.fragment.ChartPageFragment;
import com.lyh.guanbei.ui.fragment.MePageFragment;
import com.lyh.guanbei.util.LogUtil;
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity implements View.OnClickListener, NetListenerContract.INetListenerView , EasyPermissions.PermissionCallbacks {
    private NetListenerPresenter mNetListenerPresenter;


    private RadioGroup radioGroup;
    private ImageView mAddImg;
    private RadioButton mBookBtn;
    private RadioButton mChartBtn;
    private RadioButton mMeBtn;
    private ViewPager mViewPager;

    private List<Fragment> mFragments;
    private static final int SMS_PERMISSION=100;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initUi() {
        radioGroup=findViewById(R.id.activity_main_radiogroup);
        mAddImg=findViewById(R.id.activity_main_add);
        mViewPager=findViewById(R.id.activity_main_viewpager);
        mBookBtn=findViewById(R.id.activity_main_book);
        mChartBtn=findViewById(R.id.activity_main_chart);
        mMeBtn=findViewById(R.id.activity_main_me);
        mAddImg.setOnClickListener(this);
    }

    @Override
    protected void init() {
        mNetListenerPresenter.startNetListener();
        mFragments=new ArrayList<>(3);
        mFragments.add(new ChartPageFragment());
        mFragments.add(new BookPageFragment());
        mFragments.add(new MePageFragment());
        mViewPager.setAdapter(new HomePageAdapter(getSupportFragmentManager(),mFragments));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        closeBookPage();
                        mChartBtn.setChecked(true);
                        break;
                    case 1:
                        showBookPage();
                        mBookBtn.setChecked(true);
                        break;
                    case 2:
                        closeBookPage();
                        mMeBtn.setChecked(true);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mViewPager.setCurrentItem(1,false);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int index=-1;
                switch (checkedId){
                    case R.id.activity_main_chart:
                        index=0;
                        break;
                    case R.id.activity_main_book:
                        index=1;
                        break;
                    case R.id.activity_main_me:
                        index=2;
                        break;
                }
                mViewPager.setCurrentItem(index,true);
            }
        });
    }

    private void showBookPage(){
        mAddImg.setVisibility(View.VISIBLE);
        mBookBtn.setVisibility(View.INVISIBLE);
    }
    private void closeBookPage(){
        mAddImg.setVisibility(View.INVISIBLE);
        mBookBtn.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onDestroy() {
        mNetListenerPresenter.closeNetListener();
        super.onDestroy();
    }

    @Override
    public void createPresenters() {
        mNetListenerPresenter=new NetListenerPresenter();

        addPresenter(mNetListenerPresenter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.activity_main_add:
                showAddButton();
                break;
        }
    }

    @Override
    public void onNetAvailable() {
        LogUtil.logD("有网了");
        startService(new Intent(this, NetRestartService.class));
    }

    @Override
    public void onNetUnavailable() {
        LogUtil.logD("断网了");
    }

    private void showAddButton(){
        /*
            添加方式：1.手工输入 2.读取短信  3.excel导入  4.图片识别
         */
        final int TAG_FROM_MYSELF = 0;
        final int TAG_FROM_MESSAGE = 1;
        final int TAG_FROM_EXCEL = 2;
        final int TAG_FROM_PIC = 3;

        QMUIBottomSheet.BottomGridSheetBuilder builder = new QMUIBottomSheet.BottomGridSheetBuilder(this);
        builder.addItem(R.mipmap.icon_more_operation_share_friend, "手工输入", TAG_FROM_MYSELF, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_more_operation_share_friend, "读取短信", TAG_FROM_MESSAGE, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_more_operation_share_friend, "表格导入", TAG_FROM_EXCEL, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .addItem(R.mipmap.icon_more_operation_share_friend, "图片识别", TAG_FROM_PIC, QMUIBottomSheet.BottomGridSheetBuilder.FIRST_LINE)
                .setOnSheetItemClickListener(new QMUIBottomSheet.BottomGridSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(QMUIBottomSheet dialog, View itemView) {
                        dialog.dismiss();
                        int tag = (int) itemView.getTag();
                        Intent intent=null;
                        switch (tag) {
                            case TAG_FROM_MYSELF:
                                intent=new Intent(MainActivity.this, AddByMyselfActivity.class);
                                break;
                            case TAG_FROM_MESSAGE:
                                checkPermission();
                                break;
                            case TAG_FROM_EXCEL:
                                Toast.makeText(MainActivity.this, "表格导入", Toast.LENGTH_SHORT).show();
                                break;
                            case TAG_FROM_PIC:
                                Toast.makeText(MainActivity.this, "图片识别", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        if(intent!=null)
                            startActivity(intent);
                    }
                }).build().show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }
    public void checkPermission(){
        if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_SMS)) {
            startSMSActivity();
            // 已经申请过权限，做想做的事
        } else {
            // 没有申请过权限，现在去申请
            EasyPermissions.requestPermissions(this, "短信权限，用于读取短信，快速添加账单",
                    SMS_PERMISSION,  Manifest.permission.READ_SMS);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        startSMSActivity();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this,"关闭权限后无法使用该功能，请开启相应权限后再使用",Toast.LENGTH_SHORT).show();
    }
    private void startSMSActivity(){
        startActivity(AddBySMSActivity.class);
    }

}