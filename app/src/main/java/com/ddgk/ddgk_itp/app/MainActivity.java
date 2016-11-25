package com.ddgk.ddgk_itp.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;

import com.admin.mine.BaseActivity;
import com.alibaba.fastjson.JSON;
import com.ddgk.ddgk_itp.R;
import com.ddgk.ddgk_itp.app.frag_home.FragHome;
import com.ddgk.ddgk_itp.app.frag_market.FragMarket;
import com.ddgk.ddgk_itp.app.frag_personal.FragPersonal;
import com.ddgk.ddgk_itp.app.frag_trade.FragTrade;
import com.ddgk.ddgk_itp.been.AppUserEntity;
import com.ddgk.ddgk_itp.been.LoginEntity;
import com.ddgk.ddgk_itp.been.MyWalletEntity;
import com.ddgk.ddgk_itp.tools.MSPUtils;
import com.ddgk.ddgk_itp.tools.subscribers.SubscriberOnNextListener;

import java.io.UnsupportedEncodingException;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private FragHome fragHome;
    private FragMarket fragContacts;
    private FragPersonal fragMessage;
    private FragTrade fragCenter;
    private Fragment mContent;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private SubscriberOnNextListener logoutListener, queryAppUserListener, walletSearchListener;


    private MyWalletEntity myWalletEntity;
    private AppUserEntity appUser;
    private LoginEntity loginEntity;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setExit(true);//是否需要直接退出程序
        mFragmentManager = this.getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        fragHome = new FragHome();
        fragContacts = new FragMarket();
        fragMessage = new FragPersonal();
        fragCenter = new FragTrade();
        mFragmentTransaction.add(R.id.fragment, fragHome).commit();
        mContent = fragHome;

        logoutListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object s) {
                MSPUtils.getInstance(MainActivity.this).setLogout();
                showToast("已注销登录！");
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (MSPUtils.getInstance(MainActivity.this).isLogin()) {
            loginEntity = MSPUtils.getInstance(MainActivity.this).getLoginSp();
            queryAppUserListener = new SubscriberOnNextListener() {
                @Override
                public void onNext(Object s) {
                    appUser = JSON.parseObject(TextTools.DataDecode(s.toString()), AppUserEntity.class);
                    MSPUtils.getInstance(MainActivity.this).setAppUserSp(appUser);
//                    tvLevel.setText(TextTools.getLevel(appUser.getNowRole()));
//                    tvPhone.setText(appUser.getUserName());
//                    tvShopActivate.setVisibility(TextUtils.equals("YES", appUser.getIsActivation()) ? View.INVISIBLE : View.VISIBLE);
//                    tvVerified.setText(TextTools.getVerify(appUser.getIsValidate()) );
                }
            };

            walletSearchListener = new SubscriberOnNextListener() {
                @Override
                public void onNext(Object o) throws UnsupportedEncodingException {
                    myWalletEntity = JSON.parseObject(TextTools.DataDecode(o.toString()), MyWalletEntity.class);
                    MSPUtils.getInstance(MainActivity.this).setMyWalletSp(myWalletEntity);
//                    tvMyWallet.setText("￥：" + String.valueOf(myWalletEntity.getBalance()));
                }
            };
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        MSPUtils.getInstance(MainActivity.this).setLogout();
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 切换fragment
     *
     * @param from
     * @param to
     */
    public void switchContent(Fragment from, Fragment to) {
        if (mContent != to) {
            mContent = to;
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.setCustomAnimations(R.anim.right_in,
                    R.anim.left_out);
            mFragmentTransaction.replace(R.id.fragment, to)
                    .commit();
        }
    }

    @OnClick({R.id.rbtn_home, R.id.rbtn_contacts, R.id.rbtn_message, R.id.rbtn_center})
    public void onClick(View view) {
        switch (view.getId()) {
            /** 导航栏 **/
            case R.id.rbtn_home:
                switchContent(mContent, fragHome);
                break;
            case R.id.rbtn_contacts:
                switchContent(mContent, fragContacts);
                break;
            case R.id.rbtn_message:
                switchContent(mContent, fragMessage);
                break;
            case R.id.rbtn_center:
                switchContent(mContent, fragCenter);
                break;
        }
    }
}
