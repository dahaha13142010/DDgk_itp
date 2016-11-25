package com.ddgk.ddgk_itp.app.activity_login_etc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.admin.control.topTitleBackBar.TopTitleBackBar;
import com.admin.mine.BaseActivity;
import com.ddgk.ddgk_itp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Android-PC on 2016/11/22 0022.
 */
// TODO: 2016/11/22 0022 登录、支付都用此页面
public class ForgetPwdYzActivity extends BaseActivity {
    @Bind(R.id.title)
    TopTitleBackBar title;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.btn_get_yzm)
    Button btnGetYzm;
    @Bind(R.id.et_yzm)
    EditText etYzm;
    @Bind(R.id.btn_next)
    Button btnNext;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_forget_pwd_yz;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_get_yzm, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_yzm:
                break;
            case R.id.btn_next:
                startActivity(new Intent(mContext,SetLoginPwdActivity.class));
                break;
        }
    }
}
