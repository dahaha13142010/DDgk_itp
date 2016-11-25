package com.ddgk.ddgk_itp.app.activity_login_etc;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.admin.control.topTitleBackBar.TopTitleBackBar;
import com.admin.mine.BaseActivity;
import com.admin.utils.ABCountDownTimer;
import com.alibaba.fastjson.JSONObject;
import com.ddgk.ddgk_itp.R;
import com.ddgk.ddgk_itp.constant.H;
import com.ddgk.ddgk_itp.http.HttpMethods;
import com.ddgk.ddgk_itp.tools.subscribers.ProgressSubscriber;
import com.ddgk.ddgk_itp.tools.subscribers.SubscriberOnNextListener;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 项目名称：TestGitApp
 * 类描述：
 * 创建人：Michael
 * 创建时间：2016/4/25 0025 11:26
 * 修改人：Michael
 * 修改时间：2016/4/25 0025 11:26
 * 修改备注：
 */
public class RegistActivity extends BaseActivity {

    @Bind(R.id.title)
    TopTitleBackBar title;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.et_sfz)
    EditText etSfz;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.btn_get_yzm)
    Button btnGetYzm;
    @Bind(R.id.et_yzm)
    EditText etYzm;
    @Bind(R.id.et_referrer_phone)
    EditText etReferrerPhone;
    @Bind(R.id.et_referrer_name)
    EditText etReferrerName;
    @Bind(R.id.et_bank)
    EditText etBank;
    @Bind(R.id.et_bank_account)
    EditText etBankAccount;
    @Bind(R.id.btn_next)
    Button btnNext;
    private SubscriberOnNextListener registerSendSMSListener, registerCheckSMSListener;
    private ABCountDownTimer timer;
    private String strYzm;


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        btnNext.setClickable(false);
        btnNext.setClickable(true);
        btnNext.setTextColor(getResColor(R.color.unable));
        timer = new ABCountDownTimer(btnGetYzm, R.mipmap.btn_get_yzm, R.mipmap.btn_get_yzm_n, getResColor(R.color.white), getResColor(R.color.unable));
        registerSendSMSListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) {
                showToast("短信发送成功！");
                strYzm = "";
                timer.start();
                etYzm.addTextChangedListener(mTextWatcher);
            }
        };
        registerCheckSMSListener = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) {
                showToast("验证成功！");
                startActivity(new Intent(RegistActivity.this, SetLoginPwdActivity.class)
                        .putExtra(H.TAG_INTENT_PHONE, etPhone.getText().toString()));
                finish();
            }
        };
//        etAccount.setText("18716325424");
    }

    @OnClick({R.id.btn_get_yzm, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_yzm:
                if (TextUtils.isEmpty(etPhone.getText())) {
                    showToast("请输入手机号码！");
                } else {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("userName", etPhone.getText());
                    httpSendSMS(jsonObject);
                }
                break;
            case R.id.btn_next:
//                if (TextUtils.isEmpty(etAccount.getText())) {
//                    showToast("请输入手机号码！");
//                } else if (TextUtils.isEmpty(etYzm.getText())) {
//                    showToast("请输入验证码！");
//                } else {
//                    JSONObject jsonObject = new JSONObject();
//                    jsonObject.put("userName", etAccount.getText());
//                    jsonObject.put("verificationCode", etYzm.getText());
//                    httpCheckSMS(jsonObject);
//                }
                startActivity(new Intent(RegistActivity.this, SetLoginPwdActivity.class)
                        .putExtra(H.TAG_INTENT_PHONE, etPhone.getText().toString()));
                finish();
                break;
        }
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        public void afterTextChanged(Editable s) {
            // 先去掉监听器，否则会出现栈溢出
            etYzm.removeTextChangedListener(mTextWatcher);
            if (s.toString().equals(strYzm)) {
                btnNext.setClickable(true);
                btnNext.setTextColor(getResColor(R.color.white));
            } else {
                btnNext.setClickable(false);
                btnNext.setTextColor(getResColor(R.color.unable));
            }
            // 恢复监听器
            etYzm.addTextChangedListener(mTextWatcher);
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
    };

    /**
     * http获取短信
     **/
    private void httpSendSMS(JSONObject jO) {
        HttpMethods.getInstance().sendVerificationCode(new ProgressSubscriber(registerSendSMSListener, RegistActivity.this, "正在获取…", true), jO);
    }

    /**
     * http验证验证码
     **/
    private void httpCheckSMS(JSONObject jO) {
        HttpMethods.getInstance().checkVerificationCode(new ProgressSubscriber(registerCheckSMSListener, RegistActivity.this, "正在验证…", true), jO);
    }

}
