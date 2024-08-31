package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Heave {

    public String eString = "";

    public String status = "Organic";

    public Context context;

    /**
     * .class文件名中包含$ --> zzz$1.class
     * 这是因为在此类中有匿名类。 它们使用ClassName $ InnerClassName此命名约定进行编译。
     *
     * @param context
     * @return
     */
    public String initAF(Context context) {

        this.context = context;
        Log.e("xxx", "initAF from C");

        AppsFlyerLib appsFlyerLib = AppsFlyerLib.getInstance();
        appsFlyerLib.setDebugLog(true);

        Log.e("xxx","aaa");
        appsFlyerLib.init("2o5ebrDVwrE8RqB8DAdNo7",new MyAFListener(this), context);
        appsFlyerLib.start(context);

        Log.e("xxx","bbb");

        String appsflyer_id = appsFlyerLib.getAppsFlyerUID(context);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("appsflyer_id", appsflyer_id);
        map.put("token", "b801b903-4318-4bb0-9107-73efffdc7886");
        map.put("app_id", context.getPackageName());


        JSONObject jsonObject = new JSONObject(map);
        String afstr = jsonObject.toString();

        Log.e("xxx",afstr);

        eString = Base64.encodeToString(afstr.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);

        return eString;

    }

    public void toNext(String status) {
        Log.e("xxx2",status);

        if ("Non-Organic".equals(status)) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setAction("android.intent.action.main");
            context.startActivity(intent);
            ((Activity)context).finish();
        } else {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.protocol");
            intent.putExtra("data", "https://www.winclub.net?af=1199&af=" + eString);
            context.startActivity(intent);
            ((Activity)context).finish();
        }
    }

    public String hello(String str) {

        Log.e("xxx", "hello from Heave");

        return "hello " + str;
    }

}

