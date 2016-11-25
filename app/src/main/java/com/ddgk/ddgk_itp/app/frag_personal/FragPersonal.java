package com.ddgk.ddgk_itp.app.frag_personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.admin.mine.BaseFragment;
import com.ddgk.ddgk_itp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Android-PC on 2016/11/2 0002.
 */

public class
FragPersonal extends BaseFragment {
    @Bind(R.id.civ_icon)
    CircleImageView civIcon;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.iv_level)
    ImageView ivLevel;
    @Bind(R.id.btn_equity_integral)
    Button btnEquityIntegral;
    @Bind(R.id.btn_bonus_integral)
    Button btnBonusIntegral;
    @Bind(R.id.btn_bonus_gav)
    Button btnBonusGav;
    @Bind(R.id.btn_peddle_income)
    Button btnPeddleIncome;
    @Bind(R.id.tv_wallet)
    TextView tvWallet;

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_center;
    }

    @Override
    protected void onBaseCreate(View view) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.civ_icon, R.id.iv_level, R.id.btn_equity_integral, R.id.btn_bonus_integral, R.id.btn_bonus_gav, R.id.btn_peddle_income, R.id.rL_wallet, R.id.rl_trade, R.id.rl_personal_info, R.id.rl_register, R.id.rl_order, R.id.rl_change_pwd, R.id.rL_setting, R.id.swipeLayout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.civ_icon:
                break;
            case R.id.iv_level:
                break;
            case R.id.btn_equity_integral:
                break;
            case R.id.btn_bonus_integral:
                break;
            case R.id.btn_bonus_gav:
                break;
            case R.id.btn_peddle_income:
                break;
            case R.id.rL_wallet:
                startActivity(new Intent(mContext, MyWalletActivity.class));
                break;
            case R.id.rl_trade:
                break;
            case R.id.rl_personal_info:
                break;
            case R.id.rl_register:
                break;
            case R.id.rl_order:
                break;
            case R.id.rl_change_pwd:
                break;
            case R.id.rL_setting:
                break;
            case R.id.swipeLayout:
                break;
        }
    }
}
