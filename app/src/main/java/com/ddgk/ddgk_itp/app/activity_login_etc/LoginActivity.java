package com.ddgk.ddgk_itp.app.activity_login_etc;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.admin.control.topTitleBackBar.TopTitleBackBar;
import com.admin.mine.BaseActivity;
import com.admin.utils.ABCountDownTimer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ddgk.ddgk_itp.R;
import com.ddgk.ddgk_itp.app.MainActivity;
import com.ddgk.ddgk_itp.been.AppUserEntity;
import com.ddgk.ddgk_itp.been.LoginEntity;
import com.ddgk.ddgk_itp.been.MyWalletEntity;
import com.ddgk.ddgk_itp.http.HttpMethods;
import com.ddgk.ddgk_itp.tools.MSPUtils;
import com.ddgk.ddgk_itp.tools.TextTools;
import com.ddgk.ddgk_itp.tools.subscribers.ProgressSubscriber;
import com.ddgk.ddgk_itp.tools.subscribers.SubscriberOnNextListener;

import java.io.UnsupportedEncodingException;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * LoginActivity
 *
 * @author dahaha
 *         created at 15:06 2016/11/1 0001
 */
public class LoginActivity extends BaseActivity {

    @Bind(R.id.title)
    TopTitleBackBar title;
    @Bind(R.id.et_name)
    EditText etPhone;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.btn_get_yzm)
    Button btnGetYzm;
    @Bind(R.id.et_yzm)
    EditText etYzm;
    @Bind(R.id.btn_login)
    Button btnLogin;
    private SubscriberOnNextListener loginOnNestListener, queryAppUserListener, walletSearchListener;
    private LoginEntity loginEntity;
    private AppUserEntity appUser;
    private MyWalletEntity myWalletEntity;
    private ABCountDownTimer timer;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        timer = new ABCountDownTimer(btnGetYzm, R.mipmap.btn_get_yzm, R.mipmap.btn_get_yzm_n, getResColor(R.color.white), getResColor(R.color.unable));
        //FIXME debug用
        etPhone.setText("18716325424");
        etPwd.setText("123456");

        loginOnNestListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object s) {
                loginEntity = JSON.parseObject(TextTools.DataDecode(s.toString()), LoginEntity.class);
                MSPUtils.getInstance(LoginActivity.this).setLoginSp(loginEntity);
                JSONObject jO = new JSONObject();
                jO.put("userId", loginEntity.id);
                httpQueryUser(jO);
            }

        };
        queryAppUserListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object s) {
                appUser = JSON.parseObject(TextTools.DataDecode(s.toString()), AppUserEntity.class);
                MSPUtils.getInstance(LoginActivity.this).setAppUserSp(appUser);
                JSONObject jO = new JSONObject();
                jO.put("userId", loginEntity.id);
                httpWalletSearch(jO);
            }
        };
        walletSearchListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) throws UnsupportedEncodingException {
                myWalletEntity = JSON.parseObject(TextTools.DataDecode(o.toString()), MyWalletEntity.class);
                MSPUtils.getInstance(LoginActivity.this).setMyWalletSp(myWalletEntity);
                showToast("登陆成功");
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        };

    }

    @OnClick({R.id.btn_get_yzm, R.id.btn_login, R.id.tv_regist, R.id.tv_forget})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_yzm:
                timer.start();
                // TODO: 2016/11/8 0008
                break;
            case R.id.btn_login:
                if (TextUtils.isEmpty(etPhone.getText())) {
                    showToast("请输入用户名！");
                } else if (TextUtils.isEmpty(etPwd.getText())) {
                    showToast("请输入密码！");
                } else if (TextUtils.isEmpty(etYzm.getText())) {
                    showToast("请输入验证码！");
                } else {
//                    C.openProgressDialog(this, null, "正在登录");
                    JSONObject jO = new JSONObject();
                    jO.put("username", etPhone.getText().toString());
                    jO.put("password", etPwd.getText().toString());
//                    httpLogin(jO);
                    startActivity(new Intent(mContext, MainActivity.class));
                }
                break;
            case R.id.tv_regist:
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));
                break;
            case R.id.tv_forget:
                startActivity(new Intent(LoginActivity.this, ForgetPwdYzActivity.class));
                break;
        }
    }


    /**
     * 登录
     *
     * @param jO 用户名：username 密码：password
     */
    private void httpLogin(JSONObject jO) {
        HttpMethods.getInstance().login(new ProgressSubscriber(loginOnNestListener, LoginActivity.this, "正在登录…", true), jO);
    }


    /**
     * 查询appUser
     *
     * @param jO userId : 登录成功后的（id）
     */
    private void httpQueryUser(JSONObject jO) {
        HttpMethods.getInstance().appUserShow(new ProgressSubscriber(queryAppUserListener, LoginActivity.this, "正在查询用户信息…", false), jO);
    }

    /**
     * http钱包查询
     *
     * @param jO userId : 当前登录者（id）
     */
    private void httpWalletSearch(JSONObject jO) {
        HttpMethods.getInstance().walletSearch(new ProgressSubscriber(walletSearchListener, LoginActivity.this, "正在查询钱包信息…", false), jO);
    }
}
