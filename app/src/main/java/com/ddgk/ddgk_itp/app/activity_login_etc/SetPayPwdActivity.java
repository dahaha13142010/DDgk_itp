package com.ddgk.ddgk_itp.app.activity_login_etc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.admin.control.PassCodeView;
import com.admin.control.topTitleBackBar.TopTitleBackBar;
import com.admin.mine.BaseActivity;
import com.admin.utils.ABLogUtil;
import com.ddgk.ddgk_itp.R;
import com.ddgk.ddgk_itp.app.MainActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Android-PC on 2016/11/22 0022.
 */

public class SetPayPwdActivity extends BaseActivity {
    @Bind(R.id.title)
    TopTitleBackBar title;
    @Bind(R.id.pass_code_view)
    PassCodeView passCodeView;
    @Bind(R.id.btn_complete)
    Button btnComplete;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_set_pay_pwd;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        passCodeView.postDelayed(new Runnable() {
            @Override
            public void run() {
                passCodeView.requestToShowKeyboard();
            }
        }, 400);

        passCodeView.setPasscodeEntryListener(new PassCodeView.PasscodeEntryListener() {
            @Override
            public void onPasscodeEntered(String passcode) {
                showToast("Passcode entered: "+ passcode);
            }
        });

        ABLogUtil.d("SAMPLE", "Activity created");

    }

    @OnClick(R.id.btn_complete)
    public void onClick() {
        startActivity(new Intent(mContext, MainActivity.class));
    }
}
