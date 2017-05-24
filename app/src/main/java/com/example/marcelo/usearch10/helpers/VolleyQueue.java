package com.example.marcelo.usearch10.helpers;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import static android.content.ContentValues.TAG;


public class VolleyQueue {

    private static final int MAX_IMAGE_CACHE_ENTIRES  = 3024;
    private static RequestQueue mRequestQueue;

    private VolleyQueue() {}

    public static void init(Context context) {
        if ( mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(context);
    }

    public static RequestQueue getRequestQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    public static <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
}
