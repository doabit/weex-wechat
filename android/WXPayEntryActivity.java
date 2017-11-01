package com.doabit.weex.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.doabit.weex.extend.model.BaseResultModel;
import com.doabit.weex.extend.module.WeChatModule;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册API
        WeChatModule.wxapi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (api != null) {
            api.handleIntent(intent, this);
        }
    }

    @Override
    public void onReq(BaseReq req) {

    }

    /**
     * int ERR_OK = 0; int ERR_COMM = -1; int ERR_USER_CANCEL = -2; int ERR_SENT_FAILED = -3; int
     * ERR_AUTH_DENIED = -4; int ERR_UNSUPPORT = -5;
     */
    @Override
    public void onResp(BaseResp resp) {
        Log.i(TAG, "PAY_ACTIVE");
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            BaseResultModel result = new BaseResultModel();
            result.msg = resp.errStr;
            result.resCode = resp.errCode;
            WeChatModule.getInstance().reciverResult(result);

            finish();
        }
    }
}