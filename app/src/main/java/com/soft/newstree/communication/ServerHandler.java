package com.soft.newstree.communication;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.preferences.SavePreferences;
import com.soft.newstree.R;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ServerHandler {

    private Dialog progressdlg;
    private UtilClass utilClass;
    private Context ct1;
    private int loaderLayout;


    public ServerHandler()
    {

    }

    public boolean CheckInternetState(Context ct, int sholoader) {
        try {
            utilClass = new UtilClass(ct);
            ConnectivityManager cm = (ConnectivityManager) ct.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm.getActiveNetworkInfo() != null) {
                return true;
            } else {
                if (sholoader <= 0) {
                    utilClass.show_alert("Network", "Network Error.");
                }
                return false;
            }
        } catch (Exception e) {
            utilClass.show_alert("Error", e.getMessage());
        }
        return false;
    }

    public void sendToServer(View view, final Context ct, String url1, final JSONObject data, final int showloader, final Map<String, String> headerData, final int requestType, int loaderLayout, final CallBack cb) {
      System.out.println("i m here");
        if(view != null)
        {
            view.setEnabled(false);
        }
        ct1 = ct;
        this.loaderLayout = loaderLayout;
        if (progressdlg != null && progressdlg.isShowing()) {
            progressdlg.dismiss();
        }
        if (CheckInternetState(ct, showloader)) {
            try {

                data.put("device_type", "Android");
                System.out.println("map data===="+data);
                int requestMethod = Request.Method.POST;
                if (requestType == 0) {
                    requestMethod = Request.Method.GET;
                }




                String newUrl=  "https://zercominfotech.com/news/";



                JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, newUrl+url1, data,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                System.out.println("Response back======" + response);
                                cb.getRespone(response.toString(), null);
                                progressdlg.dismiss();
                                if (view != null) {
                                    view.setEnabled(true);
                                }

                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println("volley Error ................."+error);
                                Log.w("error in response", "Error: " + error.getMessage());
                                NetworkResponse response = error.networkResponse;
                                if (error instanceof ServerError && response != null) {
                                    try {
                                        String res = new String(response.data,
                                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                        JSONObject obj = new JSONObject(res);
                                        Log.w("error in response", "Error: " + obj);
                                        cb.getRespone(obj.toString(), null);
                                    } catch (Exception e1) {
                                        e1.printStackTrace();
                                    }
                                }
                                progressdlg.dismiss();
                                if (view != null) {
                                    view.setEnabled(true);
                                }
                            }
                        }
                ) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {

                        Map<String, String> params = new HashMap<>();
                        try
                        {



                        } catch (Exception e) {
                            e.printStackTrace();
                            if (view != null) {
                                view.setEnabled(true);
                            }
                        }
                        return params;

                    }


                };

                jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                        40000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


                RequestQueue requestQueue = Volley.newRequestQueue(ct);
                requestQueue.add(jsObjRequest);

                jsObjRequest.setShouldCache(true);
                showProgressDialog();
                progressdlg.show();

                if (showloader >= 1) {
                    progressdlg.dismiss();
                }
            } catch (Exception e) {

                if (progressdlg != null)
                    progressdlg.dismiss();

                if (view != null) {
                    view.setEnabled(true);
                }

            }
        }
    }


    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }


    private void showProgressDialog() {
        progressdlg = new Dialog(ct1);
        progressdlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressdlg.setContentView(loaderLayout);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = progressdlg.getWindow();
        lp.copyFrom(window.getAttributes());
        progressdlg.setCancelable(false);
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);

        progressdlg.getWindow().setBackgroundDrawableResource(R.color.translucent_black);
        progressdlg.getWindow().setDimAmount(0);
        progressdlg.show();

    }







}
