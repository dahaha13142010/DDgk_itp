package com.admin.utils;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by Android-PC on 2016/11/8 0008.
 */

public class ABCountDownTimer extends CountDownTimer {
    private Button btn;
    private int resAbleBgID, resUnableBgID, resAbleTextColorID, resUnableTextColorID;

    /**
     * Button倒计时
     *
     * @param btn               Button
     * @param millisInFuture    总时长
     * @param countDownInterval 计时时间间隔
     */
    public ABCountDownTimer(Button btn, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        this.btn = btn;
    }

    /**
     * Button倒计时
     *
     * @param millisInFuture    总时长
     * @param countDownInterval 计时时间间隔
     * @param btn               Button
     * @param resAbleBgID       可按状态下 background ID
     * @param resUnableBgID     不可按状态下 background ID
     */
    public ABCountDownTimer(long millisInFuture, long countDownInterval, Button btn, int resAbleBgID, int resUnableBgID) {
        super(millisInFuture, countDownInterval);
        this.btn = btn;
        this.resAbleBgID = resAbleBgID;
        this.resUnableBgID = resUnableBgID;
    }

    /**
     * Button倒计时
     *
     * @param millisInFuture       总时长
     * @param countDownInterval    计时时间间隔
     * @param btn                  Button
     * @param resAbleBgID          可按状态下 background ID
     * @param resUnableBgID        不可按状态下 background ID
     * @param resAbleTextColorID   可按状态下 text color
     * @param resUnableTextColorID 不可按状态下 text color
     */
    public ABCountDownTimer(long millisInFuture, long countDownInterval, Button btn, int resAbleBgID, int resUnableBgID, int resAbleTextColorID, int resUnableTextColorID) {
        super(millisInFuture, countDownInterval);
        this.btn = btn;
        this.resAbleBgID = resAbleBgID;
        this.resUnableBgID = resUnableBgID;
        this.resAbleTextColorID = resAbleTextColorID;
        this.resUnableTextColorID = resUnableTextColorID;
    }

    /**
     * Button倒计时
     * 默认60秒倒计时，间隔1秒
     *
     * @param btn button
     */
    public ABCountDownTimer(Button btn) {
        super(60000, 1000);
        this.btn = btn;
    }

    /**
     * Button倒计时
     * 默认60秒倒计时，间隔1秒
     *
     * @param btn           Button
     * @param resAbleBgID   可按状态下 background ID
     * @param resUnableBgID 不可按状态下 background ID
     */
    public ABCountDownTimer(Button btn, int resAbleBgID, int resUnableBgID) {
        super(60000, 1000);
        this.btn = btn;
        this.resAbleBgID = resAbleBgID;
        this.resUnableBgID = resUnableBgID;
    }

    /**
     * Button倒计时
     * 默认60秒倒计时，间隔1秒
     *
     * @param btn                  Button
     * @param resAbleBgID          可按状态下 background ID
     * @param resUnableBgID        不可按状态下 background ID
     * @param resAbleTextColorID   可按状态下 text color
     * @param resUnableTextColorID 不可按状态下 text color
     */
    public ABCountDownTimer(Button btn, int resAbleBgID, int resUnableBgID, int resAbleTextColorID, int resUnableTextColorID) {
        super(60000, 1000);
        this.btn = btn;
        this.resAbleBgID = resAbleBgID;
        this.resUnableBgID = resUnableBgID;
        this.resAbleTextColorID = resAbleTextColorID;
        this.resUnableTextColorID = resUnableTextColorID;
    }

    @Override
    public void onFinish() {//计时完毕时触发
        btn.setText("重新验证");
        btn.setClickable(true);
        if (resAbleBgID != 0) btn.setBackgroundResource(resAbleBgID);
        if (resAbleTextColorID != 0) btn.setTextColor(resAbleTextColorID);

    }

    @Override
    public void onTick(long millisUntilFinished) {//计时过程显示
        btn.setClickable(false);
        btn.setText(millisUntilFinished / 1000 + "秒");
        if (resUnableBgID != 0) btn.setBackgroundResource(resUnableBgID);
        if (resUnableTextColorID != 0) btn.setTextColor(resUnableTextColorID);
    }
}