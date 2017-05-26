package com.usearch.helpers;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.usearch.interfaces.ResponseCallBack;

import org.json.JSONObject;

public class Http {

    private final static String HOST = "http://localhost/";

    public void get(String url, ResponseCallBack callback){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, HOST + url, null, new OnSuccess(callback), new OnError(callback));

        VolleyQueue.addToRequestQueue(jsonObjectRequest);
    }

    public void post(String url, JSONObject data, ResponseCallBack callback){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, HOST + url, data, new OnSuccess(callback), new OnError(callback));

        VolleyQueue.addToRequestQueue(jsonObjectRequest);
    }

    private class OnSuccess implements Response.Listener<JSONObject>{

        ResponseCallBack callback;

        OnSuccess(ResponseCallBack callback){
            this.callback = callback;
        }

        @Override
        public void onResponse(JSONObject response) {

            if ( response != null ){
                this.callback.onResponse(response, 200);
            }else{
                this.callback.onResponse(null, 200);
            }
        }
    }

    private class OnError implements Response.ErrorListener {

        ResponseCallBack callback;

        OnError(ResponseCallBack callback){
            this.callback = callback;
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            NetworkResponse networkResponse = error.networkResponse;
            if ( networkResponse != null ){
                callback.onResponse(null, networkResponse.statusCode);
            }else{
                callback.onResponse(null, 501);
            }
        }
    }
}
