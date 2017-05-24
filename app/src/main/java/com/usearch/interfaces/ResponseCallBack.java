package com.usearch.interfaces;

import org.json.JSONObject;

public interface ResponseCallBack {
    void onResponse(JSONObject jsonObject, int status);
}
