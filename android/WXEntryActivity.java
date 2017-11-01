package com.doabit.weex.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.doabit.weex.extend.model.WeChatAuthResultModel;
import com.doabit.weex.extend.model.WeChatShareResultModel;
import com.doabit.weex.extend.module.WeChatModule;
import com.taobao.weex.utils.WXLogUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

//import android.content.Intent;
/**
 * Created by doabit on 2017/10/20.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = "WXEntryActivity";

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
        if (WeChatModule.wxapi!= null) {
            WeChatModule.wxapi.handleIntent(intent, this);
        }
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }


    @Override
    public void onResp(BaseResp resp) {

        WXLogUtils.e(TAG, "error_code:---->" + resp.errCode);

        if (resp instanceof SendAuth.Resp) {
            SendAuth.Resp newResp = (SendAuth.Resp) resp;

            WXLogUtils.e(newResp.code, newResp.url);

            //获取微信传回的code
            String code = newResp.code;
            WeChatAuthResultModel result = new WeChatAuthResultModel();
            result.resCode = newResp.errCode;
            result.msg = newResp.errStr;
            result.code = newResp.code;

            WXLogUtils.e(TAG, code);

            WeChatModule.getInstance().reciverResult(result);

//            Map<String,Object> params=new HashMap<>();
//            params.put("key", code);
//            WeChatModule.getInstance().mWXSDKInstance.fireGlobalEventCallback("wx_login",params);


            finish();
        } else if (resp instanceof SendMessageToWX.Resp) {
            SendMessageToWX.Resp newResp = (SendMessageToWX.Resp) (resp);
            WeChatShareResultModel result = new WeChatShareResultModel();
            result.resCode = newResp.errCode;
            result.msg = newResp.errStr;
            result.openid = newResp.openId;

            WeChatModule.getInstance().reciverResult(result);
            finish();
        }
        finish();
    }

}

