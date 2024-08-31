package com.example.myapplication;

import android.util.Log;

import com.appsflyer.AppsFlyerConversionListener;

import java.util.Map;

public class MyAFListener implements AppsFlyerConversionListener {
    public String status;
    public Heave heave;

    public MyAFListener(Heave heave){
        this.heave = heave;
    }
    @Override
    public void onConversionDataSuccess(Map<String, Object> map) {
        status = (String) map.get("af_status");
        Log.e("xxx","af success");

        Log.e("xxx1",status);
        heave.toNext(status);
    }

    @Override
    public void onConversionDataFail(String s) {
        Log.e("xxx3",status);
        heave.toNext("Organic");
    }

    @Override
    public void onAppOpenAttribution(Map<String, String> map) {

    }

    @Override
    public void onAttributionFailure(String s) {

    }
}
