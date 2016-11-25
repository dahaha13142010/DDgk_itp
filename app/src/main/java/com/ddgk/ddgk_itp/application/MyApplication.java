package com.ddgk.ddgk_itp.application;

import android.text.TextUtils;

import com.admin.mine.MApplication;
import com.admin.utils.ABLogUtil;

import butterknife.ButterKnife;

//import com.tencent.tauth.Tencent;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class MyApplication extends MApplication {

    //需要使用的类对象
    /**
     * Application对象
     */
    public static MyApplication application;

    /**
     * QQ sdk
     */
//    public static Tencent mTencent;
    public static String mAppid = "222222";

    public void onCreate() {
        this.application = this;
        setIsGetLog(false); //是否抓取错误日志（默认抓取）
        ABLogUtil.setVerbose(true, true, true, true); //是否显示相应日志
        ButterKnife.setDebug(true);
//        if (mTencent == null) {
//            mTencent = Tencent.createInstance(mAppid, this);
//        }

        super.onCreate();
    }

    public static MyApplication getInstance() {
        return application;
    }

//    public static Tencent getTencent() {
//        return mTencent;
//    }

    public String getResultMsg(String resultMsg) {
        if (TextUtils.isEmpty(resultMsg))
            return "";
        switch (resultMsg) {
            //登陆
            case "UNKNOWN_ACCOUNT_ERROR":
                return "未知用户";
            case "DISABLED_ACCOUNT_ERROR":
                return "用户未激活";
            case "EXCESSIVE_ATTEMPTS_ERROR":
                return "登录过于频繁";
            case "INCORRECT_CREDENTIALS_ERROR":
                return "用户名或密码错误";
            case "REQUEST_FAILURE_ERROR":
                return "远程请求异常";
            case "UNKNOWN_ERROR":
                return "未知错误";
            //
            case "ACCOUNT_EXIST_ERROR":
                return "账户名(手机号码)已存在";
            case "VERIFICATION_CODE_NOT_EXIST_ERROR":
                return "验证码为空，请输入验证码";
            case "VERIFICATION_CODE_INVALIDATION_ERROR":
                return "验证码无效或错误";
            case "UNKNOWN_VERIFY_ACCOUNT_ERROR":
                return "未知验证账户，请输入验证账户";
            case "MAIL_SEND_FAILURE_ERROR":
                return "邮件发送失败";
            case "UPLOAD_FILE_ERROR":
                return "上传文件失败";
            default:
                return "其他未知错误";
        }
    }
}
