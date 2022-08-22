package com.soft.newstree;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.app.dialogsnpickers.DialogCallBacks;
import com.app.dialogsnpickers.SimpleDialog;
import com.app.preferences.SavePreferences;
import com.app.vollycommunicationlib.CallBack;
import com.app.vollycommunicationlib.ServerHandler;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.navigation.NavigationView;

import com.google.android.material.snackbar.Snackbar;
import com.soft.newstree.adapter.GridItemAdapter;
import com.soft.newstree.adapter.MenuAdapter;
import com.soft.newstree.bean.AppSettingBean;
import com.soft.newstree.bean.MenuBean;
import com.soft.newstree.controller.DatabaseHandler;
import com.soft.newstree.fragment.HomeFragment;
import com.soft.newstree.settings.AppSettings;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.an.biometric.BiometricCallback;
import com.an.biometric.BiometricManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HomeActivity extends BaseActivity implements BiometricCallback {
//    private RecyclerView recycler_view_for_menu;
    private ImageView img_open_drawer;
    private DrawerLayout mainDrawer;
    private TextView lbl_toolbar_title;
    private LinearLayout ll_toolbar_color;
    private NavigationView navigationView;
    private ImageView  nav_right,nav_share;
    private Dialog progressDialog;
    public int fragmentLoadingCount=0;
    private  ImageView img_check_changes;
//    TextView txt_terms_confitions,txt_privacy;
    private HomeFragment homeFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        enableDisableScreenCapture();
        initializeObjes();
      //  loadData();
        AppSettings.isThemeChanged="0";

        mainDrawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        //ignore this warring...
        setRequestedOrientation(Integer.parseInt(AppSettings.SCREEN_ORIENTATION));


        init();
        setNavigationGravity();
        showMenuItems();
        toolBarSettings();
        listenersHandling();
        navigationHeaderSettings();
        biomatericPopUp();



        MenuBean menuBean = AppSettings.manageMenuItems().get(0);//to get home index url
        showWebViewFragment(menuBean.getItemLink());

        mainDrawer.openDrawer(Gravity.LEFT);

    }


    private void replaceMainFragment(Fragment upcoming, String tag)
    {
        FragmentTransaction ft_main = getSupportFragmentManager().beginTransaction();
        ft_main.addToBackStack(tag);
        ft_main.add(R.id.frg_container, upcoming);
        ft_main.commit();
    }


    //initialize all items ids here
    private void init()
    {
//        recycler_view_for_menu = findViewById(R.id.recycler_view_for_menu);
        img_open_drawer = findViewById(R.id.img_open_drawer);
        lbl_toolbar_title = findViewById(R.id.lbl_toolbar_title);
        nav_right = findViewById(R.id.nav_right);
        nav_share = findViewById(R.id.nav_share);
        ll_toolbar_color = findViewById(R.id.ll_toolbar_color);
//        headerImgView = navigationView.getHeaderView(0).findViewById(R.id.imageView);
//        lbl_headername = navigationView.getHeaderView(0).findViewById(R.id.lbl_headername);
      //  txt_terms_confitions = findViewById(R.id.txt_terms_confitions);
       // txt_privacy =     findViewById(R.id.txt_privacy);



    }


    //This function is used for dynamic menu
    private void showMenuItems() {
//        MenuAdapter mAdapter = new MenuAdapter(AppSettings.manageMenuItems(), this);
//        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        recycler_view_for_menu.setLayoutManager(horizontalLayoutManagaer);
//        recycler_view_for_menu.setItemAnimator(new DefaultItemAnimator());
//        recycler_view_for_menu.setAdapter(mAdapter);


        GridView simpleList = (GridView) findViewById(R.id.simpleGridView);
        GridItemAdapter myAdapter=new GridItemAdapter(this,AppSettings.manageMenuItems());
        simpleList.setAdapter(myAdapter);
    }


    //Here you can change app toolbar colors and font etc.
    private void toolBarSettings() {
        //you  can pass your custom color code here
        //img_open_drawer.setColorFilter(getResources().getColor(R.color.white), android.graphics.PorterDuff.Mode.SRC_IN);

        //toolbar title color
       // lbl_toolbar_title.setTextColor(getResources().getColor(R.color.white));

        //can customize size here
        lbl_toolbar_title.setTextSize(16);

        //toolbar back color
//     ll_toolbar_color.setBackgroundColor(getResources().getColor(R.attr.sky_blue_lite));
    }


    //Here you can pass navigation header title and header logo here
    private void navigationHeaderSettings() {
//        headerImgView.setImageResource(R.drawable.logo);
//        lbl_headername.setText(getResources().getString(R.string.app_name));
//        if(AppSettings.setsideMenuCompanylabel.equalsIgnoreCase("true"))
//        {
//            lbl_headername.setVisibility(View.VISIBLE);
//        }
//        else
//        {
//            lbl_headername.setVisibility(View.GONE);
//        }

        if(AppSettings.shareAppDialog.equalsIgnoreCase("true"))
        {
            nav_share.setVisibility(View.VISIBLE);
        }
        else
        {
            nav_share.setVisibility(View.INVISIBLE);
        }
//        if(AppSettings.show_tems_and_conditions.equalsIgnoreCase("true"))
//        {
//            txt_terms_confitions.setVisibility(View.VISIBLE);
//        }
//        else
//        {
//            txt_terms_confitions.setVisibility(View.GONE);
//        }
//        if(AppSettings.showPrivacyUrl.equalsIgnoreCase("true"))
//        {
//            txt_privacy.setVisibility(View.VISIBLE);
//        }
//        else
//        {
//            txt_privacy.setVisibility(View.GONE);
//        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            txt_terms_confitions.setText(Html.fromHtml("<u>Terms & Conditions</u>", Html.FROM_HTML_MODE_COMPACT));
//            txt_privacy.setText(Html.fromHtml("<u>Privacy Policy</u>", Html.FROM_HTML_MODE_COMPACT));
//        } else {
//            txt_terms_confitions.setText(Html.fromHtml("<u>Terms & Conditions</u>"));
//            txt_privacy.setText(Html.fromHtml("<u>Privacy Policy</u>"));
//        }
    }


    //This is webview fragment
    public void showWebViewFragment(String webviewUrl) {


        if(AppSettings.openDrawerFromRight.equalsIgnoreCase("true"))
        {
            mainDrawer.closeDrawer(Gravity.RIGHT);
        } else {
            mainDrawer.closeDrawer(Gravity.LEFT);
        }


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                      homeFragment = new HomeFragment();
                    Bundle args = new Bundle();
                    args.putString(AppSettings.webViewUrl, webviewUrl);
                    homeFragment.setArguments(args);
                    replaceMainFragment(homeFragment, webviewUrl);
            }
        }, 50);

    }


    //All click ,touch listener will reside here
    private void listenersHandling() {

        //To Open Drawer
        if (AppSettings.openDrawerFromRight.equalsIgnoreCase("true"))//end
        {
            img_open_drawer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shopMenuPopups(v);
                }
            });

            nav_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainDrawer.openDrawer(Gravity.RIGHT);
                }
            });

        } else {
            img_open_drawer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (AppSettings.openDrawerFromRight.equalsIgnoreCase("true")) {
                        mainDrawer.openDrawer(Gravity.RIGHT);
                    } else {
                        mainDrawer.openDrawer(Gravity.LEFT);
                    }
                }
            });
            nav_right.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shopMenuPopups(v);
                }
              });
            }
        if(!AppSettings.settingFloatingpop_up.equalsIgnoreCase("true")) {
            nav_right.setVisibility(View.GONE);
           }

        nav_share.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                ShareDialog shareDialog=new ShareDialog();
                shareDialog.shareDialog(HomeActivity.this,getResources().getString(R.string.shareAppContent),AppSettings.playstore_share_url);
            }
        });



//        txt_terms_confitions.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//
//                if(AppSettings.terms_url.length()>0)
//                {
//                    openUrlInExternalBrowser(AppSettings.terms_url);
//                }
//                else
//                {
//                    showToast("No Terms & Conditions Url Found",findViewById(R.id.drawer_layout));
//                }
//
//            }
//        });

//        txt_privacy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if(AppSettings.privacy_url.length()>0) {
//                    openUrlInExternalBrowser(AppSettings.privacy_url);
//                }
//                else
//                {
//                    showToast("No Privacy policy Url Found",findViewById(R.id.drawer_layout));
//                }
//            }
//        });


    }




 //   Double tap exit
    int exitCount = 1;
    @Override
    public void onBackPressed()
    {

        if(homeFragment.mywebview.canGoBack())
        {
            homeFragment.mywebview.goBack();
          System.out.println("Can Go back===>");
        }
        else {
            if (exitCount >= 2) {
                finishAffinity();
            }
            else
                {
                mainDrawer.openDrawer(Gravity.LEFT);
                Toast.makeText(this, "Tap again to exit from " + getResources().getString(R.string.app_name) + " app", Toast.LENGTH_LONG).show();
                exitCount++;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exitCount = 1;
                    }
                }, 3000);
            }

        }




    }


    private void enableDisableScreenCapture() {
        if (AppSettings.enableScreenCapture.equalsIgnoreCase("true")) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }
    }


    public void showProgressBar(ShimmerFrameLayout shimmerFrameLayout) {
        if (checkInternet()) {
            if (!AppSettings.currentLoaderImage.equalsIgnoreCase("12"))
             {
                setLoaderImage();
                progressDialog = new Dialog(this);
                progressDialog.setContentView(R.layout.progress_dialog);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                Window window = progressDialog.getWindow();
                lp.copyFrom(window.getAttributes());
                progressDialog.setCancelable(true);
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                window.setAttributes(lp);
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                progressDialog.show();

                ImageView img_progress_dialog = progressDialog.findViewById(R.id.img_progress_dialog);
                progressDialog.findViewById(R.id.rr_dismiss).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        progressDialog.dismiss();
                    }
                });

                Glide.with(HomeActivity.this)
                        .load(setLoaderImage())
                        .into(img_progress_dialog);
            } else {
                shimmerFrameLayout.startShimmerAnimation();
                shimmerFrameLayout.setVisibility(View.VISIBLE);
            }
        } else {
            showInternetNotAvalibleDialog();
        }
    }

    public void dismissProgressDialog(ShimmerFrameLayout shimmerFrameLayout) {
        if (!AppSettings.currentLoaderImage.equalsIgnoreCase("12")) {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } else {
            shimmerFrameLayout.stopShimmerAnimation();
            shimmerFrameLayout.setVisibility(View.GONE);

        }

    }

    private int setLoaderImage() {
        switch (Integer.parseInt(AppSettings.currentLoaderImage)) {
            case 1:
                return R.drawable.lone;


            case 2:
                return R.drawable.ltwo;


            case 3:
                return R.drawable.lthree;


            case 4:
                return   R.drawable.lfour;


            case 5:
                return R.drawable.lfive;


            case 6:
                return R.drawable.lsix;


            case 7:
                return R.drawable.lseven;


            case 8:
                return R.drawable.leight;


            case 9:
                return R.drawable.lnine;


            case 10:
                return R.drawable.lten;


            case 11:
                return R.drawable.leleven;

            case 12://shimmer effect loader
                return R.drawable.ltwelve;

            case 13://shimmer effect loader
                return R.drawable.lthirteen;

            case 14://shimmer effect loader
                return R.drawable.lfourteen;

            case 15://shimmer effect loader
                return R.drawable.lfifteen;


            case 16://shimmer effect loader
                return R.drawable.lsixteen;


            case 17://shimmer effect loader
                return R.drawable.lseventeen;


            case 18://shimmer effect loader
                return R.drawable.leighteen;


            case 19://shimmer effect loader
                return R.drawable.lninteen;


            case 20://shimmer effect loader
                return R.drawable.ltwenteen;


            default:
                return R.drawable.lone;

        }

    }


    private boolean checkInternet() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    private Dialog checkInternetDialog;

    private void showInternetNotAvalibleDialog() {

        checkInternetDialog = new Dialog(this);
        checkInternetDialog.setContentView(R.layout.chek_internet_dialog);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = checkInternetDialog.getWindow();
        lp.copyFrom(window.getAttributes());
        checkInternetDialog.setCancelable(true);
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        checkInternetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        checkInternetDialog.show();

        ImageView img_no_internet = checkInternetDialog.findViewById(R.id.img_no_internet);
        TextView txt_ok = checkInternetDialog.findViewById(R.id.txt_ok);
        txt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInternetDialog.dismiss();
            }
        });

        Glide.with(HomeActivity.this)
                .load(R.drawable.crying)
                .into(img_no_internet);
    }


    //Setting pop menu====
    private void shopMenuPopups(View button) {
        PopupMenu popup = new PopupMenu(this, button);
        popup.getMenuInflater().inflate(R.menu.option_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
//                Intent intent = new Intent(HomeActivity.this, LocalSettings.class);
//                startActivityForResult(intent,1001);
                return true;
            }
        });

        popup.show();//showing popup menu
    }


    //biamateric enable ====
    BiometricManager mBiometricManager;

    private void biomatericPopUp() {
        try {
            String isBiometricEnable = new SavePreferences().reterivePreference(this, AppSettings.enable_biometric) + "";
            if (isBiometricEnable.equalsIgnoreCase("on")) {
                mBiometricManager = new BiometricManager.BiometricBuilder(HomeActivity.this)
                        .setTitle(getString(R.string.biometric_title))
                        .setSubtitle(getString(R.string.biometric_subtitle))
                        .setDescription(getString(R.string.biometric_description))
                        .setNegativeButtonText(getString(R.string.biometric_negative_button_text))
                        .build();

                //start authentication
                mBiometricManager.authenticate(HomeActivity.this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setNavigationGravity()
     {
        DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(DrawerLayout.LayoutParams.WRAP_CONTENT, DrawerLayout.LayoutParams.MATCH_PARENT);
        navigationView.setLayoutParams(params);
        if (AppSettings.openDrawerFromRight.equalsIgnoreCase("true")) {
            params.gravity = Gravity.END;
            img_open_drawer.setImageResource(R.drawable.option_menu_icon);
            nav_right.setImageResource(R.drawable.ic_nav_icon);
            img_open_drawer.setVisibility(View.GONE);
            nav_right.setVisibility(View.VISIBLE);
        }
        else
        {
            params.gravity = Gravity.START;
            nav_right.setImageResource(R.drawable.option_menu_icon);
            img_open_drawer.setImageResource(R.drawable.ic_nav_icon);
            img_open_drawer.setVisibility(View.VISIBLE);
            nav_right.setVisibility(View.GONE);
         }

        if(AppSettings.hideSideMenu.equalsIgnoreCase("true"))
        {

            img_open_drawer.setVisibility(View.GONE);
            nav_right.setVisibility(View.GONE);
            mainDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        }
    }


    @Override
    public void onSdkVersionNotSupported() {
        //Toast.makeText(getApplicationContext(), getString(R.string.biometric_error_sdk_not_supported), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBiometricAuthenticationNotSupported() {
        //Toast.makeText(getApplicationContext(), getString(R.string.biometric_error_hardware_not_supported), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBiometricAuthenticationNotAvailable() {
        //    Toast.makeText(getApplicationContext(), getString(R.string.biometric_error_fingerprint_not_available), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBiometricAuthenticationPermissionNotGranted() {
        // Toast.makeText(getApplicationContext(), getString(R.string.biometric_error_permission_not_granted), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBiometricAuthenticationInternalError(String error)
    {
        // Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationFailed() {
        biomatericPopUp();
    }

    @Override
    public void onAuthenticationCancelled() {
        biomatericPopUp();
    }

    @Override
    public void onAuthenticationSuccessful() {
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        biomatericPopUp();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


       if(requestCode==1001)
        {
            if(data!=null)
            {
                if(data.getStringExtra("data").equalsIgnoreCase("1"))
                {
                  //  recreate();
                }

            }
        }
    }



    private void getsettingsData(String appId)
    {

        try {
            Map<String, String> m = new LinkedHashMap<>();
            m.put("app_id",appId);
            Map<String, String> headerMap = new HashMap<>();
           //headerMap.put("X-API-KEY", UtilClass.xApiKey);

            new ServerHandler().sendToServer(HomeActivity.this,  AppSettings.apiUrl, m, 0, headerMap, 20000, R.layout.api_loader, new CallBack() {
                @Override
                public void getRespone(String dta, ArrayList<Object> respons) {
                    try {
                        JSONObject obj = new JSONObject(dta);
                        if (obj.getBoolean("status")) {
                            try {
                                JSONArray settings =obj.getJSONArray("settings");
                                System.out.println("Data==="+settings);

                                JSONArray menuAr=new JSONArray();
                                JSONObject data=new JSONObject();
                                data.put("menu_name","Home");
                                data.put("menu_url","https://techforet.com");
                                data.put("icon_url","icon");

                                JSONObject data2=new JSONObject();
                                data2.put("menu_name","Amazon");
                                data2.put("menu_url","https://www.amazon.in/");
                                data2.put("icon_url","icon");


                                menuAr.put(data);
                                menuAr.put(data2);

                                AppSettingBean appSettingBean=new AppSettingBean("1001","https://techforet.com",
                                        "https://play.google.com/store/apps/details?id=com.google.android.youtube",
                                        "false", "1", "", "", "false", "false", "false"
                                        ,"false", "false", "false", "",
                                         "", "", "1","true",
                                        "true", "true", "true", "1", "3",
                                        "1", "roboto", "true","https://google.com,https://facebook.com",data2+"","20","20","true","true","Hello Everyone","true");

                                DatabaseHandler databaseHandler=new DatabaseHandler(HomeActivity.this);
                                databaseHandler.addSettings(appSettingBean);

                                JSONObject dataObj=settings.getJSONObject(0);
                                AppSettings.webViewUrl=dataObj.getString("app_url");


                                alertDialogs.alertDialog(HomeActivity.this, getResources().getString(R.string.app_name), getResources().getString(R.string.restart_message), getResources().getString(R.string.ok), "", new DialogCallBacks() {
                                    @Override
                                    public void getDialogEvent(String buttonPressed) {
                                        finishAffinity();
                                    }
                                });


                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {
                            alertDialogs.alertDialog(HomeActivity.this, getResources().getString(R.string.app_name), obj.getString("msg"), getResources().getString(R.string.ok), "", new DialogCallBacks() {
                                @Override
                                public void getDialogEvent(String buttonPressed) {
                                    //unauthorizedAccess(obj);
                                }
                            });

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Dialog changeSettingsDialog;
    public void changeSettings()
    {

        SimpleDialog simpleDialog = new SimpleDialog();
        changeSettingsDialog = simpleDialog.simpleDailog(this, R.layout.serach_layout, new ColorDrawable(getResources().getColor(R.color.translucent_black)), WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, false);
        changeSettingsDialog.setCancelable(true);
        changeSettingsDialog.show();

        changeSettingsDialog.findViewById(R.id.txt_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                changeSettingsDialog.dismiss();
                getsettingsData("1");
            }
        });

    }

    public void showToast(String message,View view)
    {

        System.out.println("Toast Type==="+AppSettings.toastType);
        if(AppSettings.toastType.equalsIgnoreCase("1"))
        {
            Toast.makeText(HomeActivity.this,message,Toast.LENGTH_LONG).show();
        }
        else if(AppSettings.toastType.equalsIgnoreCase("2"))
        {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                    .setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    })
                    .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                    .show();
        }
        else
        {
            alertDialogs.alertDialog(HomeActivity.this, getResources().getString(R.string.app_name), message, getResources().getString(R.string.ok), "", new DialogCallBacks() {
                @Override
                public void getDialogEvent(String buttonPressed) {
                    //unauthorizedAccess(obj);
                }
            });
        }

    }



}



//
//
//<!DOCTYPE html>
//<html>
//
//<head>
//<title>Bootstrap Example</title>
//<meta charset="utf-8">
//<meta name="viewport" content="width=device-width, initial-scale=1">
//<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
//<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
//<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
//
//
//<style>
//	.cc-selector input{
//            margin:0;padding:0;
//            -webkit-appearance:none;
//            -moz-appearance:none;
//            appearance:none;
//            }
//
//            .cc-selector-2 input{
//            position:absolute;
//            z-index:999;
//            }
//
//            .visa{background-image:url(loaderimages/loader1.gif);}
//            .mastercard{background-image:url(loaderimages/loader2.gif);}
//            .loader3{background-image:url(loaderimages/loader3.gif);}
//            .loader4{background-image:url(loaderimages/loader4.gif);}
//            .loader5{background-image:url(loaderimages/loader5.gif);}
//            .loader6{background-image:url(loaderimages/loader6.gif);}
//            .loader7{background-image:url(loaderimages/loader7.gif);}
//            .loader8{background-image:url(loaderimages/loader8.gif);}
//            .loader9{background-image:url(loaderimages/loader9.gif);}
//            .loader10{background-image:url(loaderimages/loader10.gif);}
//            .loader11{background-image:url(loaderimages/loader11.gif);}
//            .loader12{background-image:url(loaderimages/loader12.gif);}
//            .loader13{background-image:url(loaderimages/loader13.gif);}
//            .loader14{background-image:url(loaderimages/loader14.gif);}
//            .loader15{background-image:url(loaderimages/loader15.gif);}
//            .loader16{background-image:url(loaderimages/loader16.gif);}
//            .loader17{background-image:url(loaderimages/loader17.gif);}
//            .loader18{background-image:url(loaderimages/loader18.gif);}
//            .loader19{background-image:url(loaderimages/loader19.gif);}
//            .loader20{background-image:url(loaderimages/loader20.gif);}
//
//            .grade1{background-image:url(Gradients/g1.png);}
//            .grade2{background-image:url(Gradients/g2.png);}
//            .grade3{background-image:url(Gradients/g3.png);}
//            .grade4{background-image:url(Gradients/g4.png);}
//            .grade5{background-image:url(Gradients/g5.png);}
//            .grade6{background-image:url(Gradients/g6.png);}
//            .grade7{background-image:url(Gradients/g7.png);}
//            .grade8{background-image:url(Gradients/g8.png);}
//            .grade9{background-image:url(Gradients/g9.png);}
//            .grade10{background-image:url(Gradients/g10.png);}
//            .grade11{background-image:url(Gradients/g11.png);}
//            .grade12{background-image:url(Gradients/g12.png);}
//            .grade13{background-image:url(Gradients/g13.png);}
//
//            .cc-selector-2 input:active +.drinkcard-cc, .cc-selector input:active +.drinkcard-cc{opacity: .9;}
//            .cc-selector-2 input:checked +.drinkcard-cc, .cc-selector input:checked +.drinkcard-cc{
//            -webkit-filter: none;
//            -moz-filter: none;
//            filter: none;
//            }
//            .drinkcard-cc{
//            cursor:pointer;
//            background-size:contain;
//            background-repeat:no-repeat;
//            display:inline-block;
//            width:100px;height:70px;
//            -webkit-transition: all 100ms ease-in;
//            -moz-transition: all 100ms ease-in;
//            transition: all 100ms ease-in;
//<!--  -webkit-filter: brightness(1.8) grayscale(1) opacity(.7);-->
//        -moz-filter: brightness(1.8) grayscale(1) opacity(.7);
//<!--      filter: brightness(1.8) grayscale(1) opacity(.7);-->
//        }
//        .drinkcard-cc:hover{
//        -webkit-filter: brightness(1.2) grayscale(.5) opacity(.9);
//        -moz-filter: brightness(1.2) grayscale(.5) opacity(.9);
//        filter: brightness(1.2) grayscale(.5) opacity(.9);
//        }
//
//        /* Extras */
//        a:visited{color:#888}
//        a{color:#444;text-decoration:none;}
//        p{margin-bottom:.3em;}
//        /* { font-family:monospace; }*/
//        .cc-selector-2 input{ margin: 5px 0 0 12px; }
//        .cc-selector-2 label{ margin-left: 7px; }
//        span.cc{ color:#6d84b4 }
//
//
//
//</style>
//
//</head>
//
//
//<body onload="load">
//
//<script type="text/javascript">
//        function getAppAid()
//        {
//        var generator = Math.random()*(90000-10000);
//        generator = Math.round(0000+generator);
//        document.getElementById("app_id").value=generator;
//        document.getElementById("app_id").disabled = true;
//
//        }
//        window.onload = getAppAid;
//
//
////create table===
//
//
//
//
//
//
//
//
//</script>
//
//<div class="container">
//<h2> WebviewX App Configration Form</h2>
//<br>
//
//<form action="http://www.techforet.com/webviewxapi/saveform.php" method="post">
//
//<div class="form-row">
//<div class="form-group col-md-6">
//<label for="app_id">01.Your App Id</label>
//<input type="text" class="form-control" name="app_id" id="app_id" placeholder="Ex: http://techforet.com/">
//</div>
//
//<div class="form-row">
//<div class="form-group col-md-6">
//<label for="app_url">02.Set Your Website Url</label>
//<input type="text" class="form-control" name="app_url" id="app_url" placeholder="Ex: http://techforet.com/">
//</div>
//
//
//</div>
//
//
//
//<div class="form-row">
//<div class="form-group col-md-6">
//<label for="intervaltime">03.Play Store Url</label>
//<input type="text" class="form-control" name="adMob_key" id="adMob_key" placeholder="Play Store Url">
//</div>
//<div class="form-group col-md-6">
//<label for="app_id04">04.Show Banner Ads</label>
//
//<select class="form-control" name="toast_type" id="toast_type">
//<option value="option1" >Yes</option>
//<option value="option2">No</option>
//
//
//</select>
//</div>
//</div>
//
//
//
//
//
//
//<div class="form-row">
//<div class="form-group col-md-6">
//<label for="intervaltime">05.Terms And Coditions Url</label>
//<input type="text" class="form-control" name="adMob_key" id="adMob_key" placeholder="Terms And Coditions">
//</div>
//<div class="form-group col-md-6">
//<label for="app_id04">06.Show Terms And Conditions url</label>
//
//<select class="form-control" name="toast_type" id="toast_type">
//<option value="option1" >Yes</option>
//<option value="option2">No</option>
//
//
//</select>
//</div>
//</div>
//
//
//
//
//
//<div class="form-row">
//<div class="form-group col-md-6">
//<label for="intervaltime">07.Privacy Url</label>
//<input type="text" class="form-control" name="adMob_key" id="adMob_key" placeholder="Privacy Url">
//</div>
//<div class="form-group col-md-6">
//<label for="app_id04">08.Show Privacy Url</label>
//
//<select class="form-control" name="toast_type" id="toast_type">
//<option value="option1" >Yes</option>
//<option value="option2">No</option>
//
//
//</select>
//</div>
//</div>
//
//
//<div class="form-row">
//
//<div class="form-group col-md-6">
//<label for="app_id04">09.Navigation Drawer Postion</label>
//
//<select class="form-control" name="toast_type" id="toast_type">
//<option value="option1" >Left</option>
//<option value="option2">Right</option>
//
//
//</select>
//</div>
//<div class="form-group col-md-6">
//<label for="app_id04">10.App Share Dialog</label>
//
//<select class="form-control" name="toast_type" id="toast_type">
//<option value="option1" >Yes</option>
//<option value="option2">No</option>
//
//
//</select>
//</div>
//</div>
//
//<div class="form-row">
//<div class="form-group col-md-6">
//<label for="app_id03">11.Set screen orientation</label>
//
//<select class="form-control" name="side_menu_pos" id="side_menu_pos">
//<option value="nav1">Portrait</option>
//<option value="nav2">Landscape</option>
//<option value="nav3">Both</option>
//</select>
//</div>
//<div class="form-group col-md-6">
//<label for="app_id04">12.Select toast type</label>
//
//<select class="form-control" name="toast_type" id="toast_type">
//<option value="option1" >Simple</option>
//<option value="option2">Dialog</option>
//<option value="option3">Snackbar</option>
//
//</select>
//</div>
//</div>
//
//
//
//
//
//
//
//
//<div class="form-row">
//<div class="form-group col-md-6">
//<label for="intervaltime">13.Set your loader interval time in milliseconds</label>
//<input type="text" class="form-control" name="interval_time" id="interval_time" placeholder="1000">
//</div>
//<div class="form-group col-md-6">
//<label for="intervaltime">14.Set your adMob ID</label>
//<input type="text" class="form-control" name="adMob_ID" id="adMob_ID" placeholder="adMob ID">
//</div>
//</div>
//
//<div class="form-row">
//<div class="form-group col-md-6">
//<label for="intervaltime">15.Set adMob key ad banner</label>
//<input type="text" class="form-control" name="adMob_key" id="adMob_key" placeholder="adMob key ad banner">
//</div>
//<div class="form-group col-md-6">
//<label for="app_id04">16.Select Splash Screen Active</label>
//
//<select class="form-control" name="toast_type" id="toast_type">
//<option value="option1" >Yes</option>
//<option value="option2">No</option>
//
//
//</select>
//</div>
//</div>
//
//
//<div class="form-row">
//<div class="form-group col-md-6">
//<label for="intervaltime">17.Set adMob key ad interstitial</label>
//<input type="text" class="form-control" name="key_ad" id="key_ad" placeholder="Set adMob key ad interstitial">
//</div>
//<div class="form-group col-md-6">
//<label for="intervaltime">18.Set interstitial adMob delay in seconds</label>
//<input type="text" class="form-control" name="inter_delay" id="inter_delay" placeholder="Set interstitial adMob delay in milliseconds">
//</div>
//</div>
//
//
//<div class="form-row">
//<div class="form-group col-md-6">
//<label for="app_id">19.select side menu company label visibility</label>
//
//<select class="form-control" name="label_visible" id="label_visible">
//<option value="option1" >Visible</option>
//<option value="option2" >Hide</option>
//
//</select>
//</div>
//
//
//<div class="form-group col-md-6">
//<label for="intervaltime">20.Set interstitial adMob periodT Ime in Seconds</label>
//<input type="text" class="form-control" name="adMob_delay" id="adMob_delay" placeholder="Set interstitial adMob delay in milliseconds">
//</div>
//
//
//
//</div>
//
//
//<div class="form-row">
//<div class="form-group col-md-6">
//<label for="app_id">21.Show Splash Screen Label</label>
//
//<select class="form-control" name="label_visible" id="label_visible">
//<option value="option1" >Landscape</option>
//<option value="option2" >portrait</option>
//<option value="option2" >Both</option>
//
//</select>
//</div>
//<div class="form-group col-md-6">
//<label for="app_id04">22.Show interstitial Ad</label>
//
//<select class="form-control" name="toast_type" id="toast_type">
//<option value="option1" >Yes</option>
//<option value="option2">No</option>
//
//
//</select>
//</div>
//</div>
//
//
//
//
//<div class="form-row">
//<div class="form-group col-md-6">
//<label for="intervaltime">23.Splash Screen Label Text</label>
//<input type="text" class="form-control" name="key_ad" id="key_ad" placeholder="Set adMob key ad interstitial">
//</div>
//<div class="form-group col-md-6">
//<label for="app_id04">24.Enable Screen capture</label>
//
//<select class="form-control" name="toast_type" id="toast_type">
//<option value="option1" >Yes</option>
//<option value="option2">No</option>
//
//
//</select>
//</div>
//</div>
//
//
//
//<div class="form-row">
//<div class="form-group col-md-6">
//<label for="app_id04">25.Select Font</label>
//
//<select class="form-control" name="toast_type" id="toast_type">
//<option value="option1" >OpenSans</option>
//<option value="option2">Arimo</option>
//<option value="option2">Cousine</option>
//<option value="option2">Oswald</option>
//<option value="option2">Roboto</option>
//
//
//</select>
//</div>
//<div class="form-group col-md-6">
//<label for="app_id04">26.Swipe to Refresh</label>
//
//<select class="form-control" name="toast_type" id="toast_type">
//<option value="option1" >Yes</option>
//<option value="option2">No</option>
//
//
//</select>
//</div>
//</div>
//
//<div class="form-row">
//<div class="form-group col-md-6">
//<label for="intervaltime">27.Extrenal Url Ex: www.example.com </label>
//<input type="text" class="form-control" name="key_ad" id="key_ad" placeholder="Set adMob key ad interstitial">
//</div>
//<div class="form-group col-md-6">
//<label for="app_id04">28.Show Navigation Drawer</label>
//
//<select class="form-control" name="toast_type" id="toast_type">
//<option value="option1" >Yes</option>
//<option value="option2">No</option>
//
//
//</select>
//</div>
//</div>
//
//
//<div class="form-row">
//<div class="form-group col-md-6">
//<label for="app_id04">29.Set file upload enable</label>
//
//<select class="form-control" name="toast_type" id="toast_type">
//<option value="True" >Yes</option>
//<option value="False">No</option>
//
//
//</select>
//
//</div>
//<div class="form-group col-md-6">
//<label for="app_id04">--.NR</label>
//
//<select class="form-control" name="toast_type" id="toast_type">
//<option value="option1" >Yes</option>
//<option value="option2">No</option>
//
//
//</select>
//</div>
//</div>
//
//
//<br>
//<label for="intervaltime">30.Select loader</label>
//<div class="cc-selector-2">
//<input id="visa2" type="radio" name="splash_act" value="visa" />
//<label class="drinkcard-cc visa" for="visa2"></label>
//
//<input  checked="checked" id="mastercard2" type="radio" name="splash_act" value="mastercard" />
//<label class="drinkcard-cc mastercard" for="mastercard2"></label>
//
//<input  checked="checked" id="loadercard3" type="radio" name="splash_act" value="loader3" />
//<label class="drinkcard-cc loader3" for="loadercard3"></label>
//
//
//<input  checked="checked" id="loadercard4" type="radio" name="splash_act" value="loader4" />
//<label class="drinkcard-cc loader4" for="loadercard4"></label>
//
//<input  checked="checked" id="loadercard5" type="radio" name="splash_act" value="loader5" />
//<label class="drinkcard-cc loader5" for="loadercard5"></label>
//
//<input  checked="checked" id="loadercard6" type="radio" name="splash_act" value="loader6" />
//<label class="drinkcard-cc loader6" for="loadercard6"></label>
//
//<input  checked="checked" id="loadercard7" type="radio" name="splash_act" value="loader7" />
//<label class="drinkcard-cc loader7" for="loadercard7"></label>
//
//<input  checked="checked" id="loadercard8" type="radio" name="splash_act" value="loader8" />
//<label class="drinkcard-cc loader8" for="loadercard8"></label>
//
//<input  checked="checked" id="loadercard9" type="radio" name="splash_act" value="loader9" />
//<label class="drinkcard-cc loader9" for="loadercard9"></label>
//
//<input  checked="checked" id="loadercard10" type="radio" name="splash_act" value="loader10" />
//<label class="drinkcard-cc loader10" for="loadercard10"></label>
//<br>
//<input  checked="checked" id="loadercard11" type="radio" name="splash_act" value="loader11" />
//<label class="drinkcard-cc loader11" for="loadercard11"></label>
//
//<input  checked="checked" id="loadercard12" type="radio" name="splash_act" value="loader12" />
//<label class="drinkcard-cc loader12" for="loadercard12"></label>
//
//<input  checked="checked" id="loadercard13" type="radio" name="splash_act" value="loader13" />
//<label class="drinkcard-cc loader13" for="loadercard13"></label>
//
//<input  checked="checked" id="loadercard14" type="radio" name="splash_act" value="loader14" />
//<label class="drinkcard-cc loader14" for="loadercard14"></label>
//
//<input  checked="checked" id="loadercard15" type="radio" name="splash_act" value="loader15" />
//<label class="drinkcard-cc loader15" for="loadercard15"></label>
//
//<input  checked="checked" id="loadercard16" type="radio" name="splash_act" value="loader16" />
//<label class="drinkcard-cc loader16" for="loadercard16"></label>
//
//<input  checked="checked" id="loadercard17" type="radio" name="splash_act" value="loader17" />
//<label class="drinkcard-cc loader17" for="loadercard17"></label>
//
//<input  checked="checked" id="loadercard18" type="radio" name="splash_act" value="loader18" />
//<label class="drinkcard-cc loader18" for="loadercard18"></label>
//
//<input  checked="checked" id="loadercard19" type="radio" name="splash_act" value="loader19" />
//<label class="drinkcard-cc loader19" for="loadercard19"></label>
//
//<input  checked="checked" id="loadercard20" type="radio" name="splash_act" value="loader20" />
//<label class="drinkcard-cc loader20" for="loadercard20"></label>
//
//</div>
//
//<label for="intervaltime">31.Select your theme and color</label>
//
//<div class="cc-selector-2">
//<input id="gradecolor1" type="radio" name="splash_act" value="grade1" />
//<label class="drinkcard-cc grade1" for="gradecolor1"></label>
//
//<input  checked="checked" id="gradecolor2" type="radio" name="splash_act" value="grade2" />
//<label class="drinkcard-cc grade2" for="gradecolor2"></label>
//
//<input  checked="checked" id="gradecolor3" type="radio" name="splash_act" value="grade3" />
//<label class="drinkcard-cc grade3" for="gradecolor3"></label>
//
//<input  checked="checked" id="gradecolor4" type="radio" name="splash_act" value="grade4" />
//<label class="drinkcard-cc grade4" for="gradecolor4"></label>
//
//<input  checked="checked" id="gradecolor5" type="radio" name="splash_act" value="grade5" />
//<label class="drinkcard-cc grade5" for="gradecolor5"></label>
//
//<input  checked="checked" id="gradecolor6" type="radio" name="splash_act" value="grade6" />
//<label class="drinkcard-cc grade6" for="gradecolor6"></label>
//
//<input  checked="checked" id="gradecolor7" type="radio" name="splash_act" value="grade7" />
//<label class="drinkcard-cc grade7" for="gradecolor7"></label>
//
//<input  checked="checked" id="gradecolor8" type="radio" name="splash_act" value="grade8" />
//<label class="drinkcard-cc grade8" for="gradecolor8"></label>
//
//<input  checked="checked" id="gradecolor9" type="radio" name="splash_act" value="grade9" />
//<label class="drinkcard-cc grade9" for="gradecolor9"></label>
//
//<input  checked="checked" id="gradecolor10" type="radio" name="splash_act" value="grade10" />
//<label class="drinkcard-cc grade10" for="gradecolor10"></label>
//<br>
//<input  checked="checked" id="gradecolor11" type="radio" name="splash_act" value="grade11" />
//<label class="drinkcard-cc grade11" for="gradecolor11"></label>
//
//<input  checked="checked" id="gradecolor12" type="radio" name="splash_act" value="grade12" />
//<label class="drinkcard-cc grade12" for="gradecolor12"></label>
//
//<input  checked="checked" id="gradecolor13" type="radio" name="splash_act" value="grade13" />
//<label class="drinkcard-cc grade13" for="gradecolor13"></label>
//
//
//
//</div>
//
//
//
//
//
//<title>Set Side Menue</title>
//<style>
//    .table{
//            border-collapse:collapse;
//            }
//            .table th,.table td{
//            padding:10px;
//            border:1px solid #ccc;
//            }
//</style>
//
//
//
//
//
//<button class="btn btn-primary" type="submit" >Submit form</button>
//
//
//<h3>Products Details</h3>
//
//<table class='table'>
//<thead>
//<tr>
//<th>Menu Name</th>
//<th>Menu Url</th>
//<th>Menu Icon</th>
//<th>Add</th>
//<th>Remove</th>
//</tr>
//</thead>
//<tbody id="tbl">
//<tr>
//<td><input type='text' name='product[]' required ></td>
//<td><input type='text' name='price[]' required></td>
//<td><input type='text' name='image[]' required></td>
//<td><input type='button' value='+' onclick='add_row()' ></td>
//<td><input type='button' value='-' onclick='remove_row(this)'></td>
//</tr>
//</tbody>
//
//</table>
//
//
//
//
//
//</form>
//
//
//
//
//</div>
//
//<br><br><br>
//
//
//
//<script>
//    function add_row()
//            {
//            var tr=document.createElement("tr");
//            tr.innerHTML="<td><input type='text' name='product[]' required ></td> <td><input type='text' name='price[]' required ></td><td><input type='text' name='image[]' required ></td> <td><input type='button' value='+' onclick='add_row()' class='btn btn-success btn-xs'></td> <td><input type='button' value='-' onclick='remove_row(this)' class='btn btn-danger btn-xs'></td>";
//            document.getElementById("tbl").appendChild(tr);
//            }
//            function remove_row(e)
//            {
//            var n=document.querySelector("#tbl").querySelectorAll("tr").length;
//            if(n>1&&confirm("Are You Sure")==true){
//            var ele=e.parentNode.parentNode;
//            ele.remove();
//            }
//            }
//</script>
//
//
//</body>
//
//</html>





