package com.soft.newstree.fragment;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.app.preferences.SavePreferences;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback;
import com.soft.newstree.settings.AppSettings;
import com.soft.newstree.HomeActivity;
import com.soft.newstree.R;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HomeFragment extends Fragment {
    private View view;
    private ProgressBar viewprogressbar;
    private SwipeRefreshLayout swipe_container;
    public WebView mywebview;
    private HomeActivity homeActivity;
    private ShimmerFrameLayout mShimmerViewContainer;
    private ImageView img_back_pages,img_check_changes;
    int pageMinus = 0;
    private long adsTime;
    private boolean isActive;

    public HomeFragment() {
    }
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        homeActivity = (HomeActivity) getActivity();
        init();
        actions();
        String adsTimeStr=new SavePreferences().reterivePreference(getActivity(),"adsTime").toString();
        adsTime=Long.parseLong(adsTimeStr);

        callWebView(getArguments().getString(AppSettings.webViewUrl));
        intializeAdSdk();
        showBannerAds();
        return view;
    }

    private void init() {
        mywebview = view.findViewById(R.id.webview);
        swipe_container = view.findViewById(R.id.swipe_container);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view);
        img_check_changes = view.findViewById(R.id.img_check_changes);
      }

    public void callWebView(String url) {
        loadWebView(url);
    }

    private void actions() {
        if (AppSettings.setSwipeRefressMode.equalsIgnoreCase("true")) {
            swipe_container.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    loadWebView(getArguments().getString(AppSettings.webViewUrl));
                }
            });
        } else {
            swipe_container.setEnabled(false);
            swipe_container.setRefreshing(false);
        }

        if(!AppSettings.showFlaotingButton)
        {
            img_check_changes.setVisibility(View.GONE);

        }
        img_check_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeActivity.changeSettings();
            }
        });

    }

    private void loadWebView(String url)
    {

        homeActivity.showProgressBar(mShimmerViewContainer);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                WebSettings webSettings = mywebview.getSettings();
                //webSettings.setUserAgentString("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/80.0.3987.163 Chrome/80.0.3987.163 Safari/537.36");

                webSettings.setJavaScriptEnabled(true);
                webSettings.setLoadWithOverviewMode(true);
                webSettings.setUseWideViewPort(true);
                webSettings.setSupportMultipleWindows(true);
                webSettings.setBuiltInZoomControls(true);
                webSettings.setSupportZoom(true);
                webSettings.setDisplayZoomControls(false);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                {
                    mywebview.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                }
                else
                    {
                    mywebview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                   }

                mywebview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
                mywebview.setWebViewClient(new WebViewController());
                mywebview.loadUrl(url);
                mywebview.setDownloadListener(new DownloadListener() {
                    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });

                mywebview.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                       if(event.getAction()==KeyEvent.ACTION_DOWN)
                       {
                           if(keyCode==KeyEvent.KEYCODE_BACK)
                           {
//                                if(mywebview.canGoBack())
//                                   {
//                                       mywebview.goBack();
//                                   }

                           }
                       }
                        return false;
                    }
                });
                openFileChooser();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showInterstialAds();
                    }
                },adsTime);

              }
        }, 50);

    }

    public class WebViewController extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {

            ArrayList<String> internalWebViewUrl = AppSettings.setInternalDomainsUrls();
            if(internalWebViewUrl.size()>0) {
                if (internalWebViewUrl.contains(Uri.parse(url).getHost())) {
                    view.loadUrl(url);
                    return false;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }
            else
            {
                return false;
            }



        }

        @Override
        public void onPageFinished(WebView view, String url)
        {
                    swipe_container.setRefreshing(false);
                    homeActivity.dismissProgressDialog(mShimmerViewContainer);
                    super.onPageFinished(view, url);
        }

        @Override
        public void onLoadResource(WebView view, String url) {

            super.onLoadResource(view, url);
        }


    }


    //Google Ads======================================================================

    private void intializeAdSdk() {
        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
    }

    AdView mAdView,adViewbottom;
    private void showBannerAds()
    {
        if(AppSettings.isShowBaneerAd.equalsIgnoreCase("true"))
        {
            mAdView = view.findViewById(R.id.adView);
            adViewbottom = view.findViewById(R.id.adViewbottom);

            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
            adViewbottom.loadAd(adRequest);

        }

    }

    @Override
    public void onPause()
    {
        super.onPause();
        isActive=false;
        mAdView.setVisibility(View.GONE);

    }

    @Override
    public void onResume() {
        super.onResume();
        isActive=true;
        mAdView.setVisibility(View.VISIBLE);


    }


    //Upload file by webview===>
    private ValueCallback<Uri> mUploadMessage;
    public ValueCallback<Uri[]> uploadMessage;
    public static final int REQUEST_SELECT_FILE = 100;
    private final static int FILECHOOSER_RESULTCODE = 1;


    private void openFileChooser() {

        String permission = Manifest.permission.CAMERA;
        int grant = ContextCompat.checkSelfPermission(getActivity(), permission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(getActivity(), permission_list, 1);
        }


        mywebview.setWebChromeClient(new WebChromeClient() {
            // For 3.0+ Devices (Start)
            // onActivityResult attached before constructor
            protected void openFileChooser(ValueCallback uploadMsg, String acceptType) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Browser"), FILECHOOSER_RESULTCODE);
            }


            // For Lollipop 5.0+ Devices
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public boolean onShowFileChooser(WebView mWebView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }

                uploadMessage = filePathCallback;

                Intent intent = fileChooserParams.createIntent();
                try {
                    startActivityForResult(intent, REQUEST_SELECT_FILE);
                } catch (ActivityNotFoundException e)
                 {
                    uploadMessage = null;
                    homeActivity.showToast("Cannot Open File Chooser",view.findViewById(R.id.main_relative_layout));

                    return false;
                 }
                return true;
            }

            //For Android 4.1 only
            protected void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                mUploadMessage = uploadMsg;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "File Browser"), FILECHOOSER_RESULTCODE);
            }

            protected void openFileChooser(ValueCallback<Uri> uploadMsg) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (requestCode == REQUEST_SELECT_FILE) {
                if (uploadMessage == null)
                    return;
                uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
                uploadMessage = null;
            }
        } else if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == mUploadMessage)
                return;
            Uri result = intent == null || resultCode != getActivity().RESULT_OK ? null : intent.getData();
            mUploadMessage.onReceiveValue(result);
            mUploadMessage = null;
        } else
            homeActivity.showToast("Failed to Upload Image",view);

    }



    private void showInterstialAds()
    {
        InterstitialAd   mInterstitial = new InterstitialAd(getActivity());
        mInterstitial.setAdUnitId(getString(R.string.interstial_ad_id));
        mInterstitial.loadAd(new AdRequest.Builder().build());

                System.out.println("Inside load====");
                mInterstitial.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed()
                    {
                        super.onAdClosed();
                        System.out.println("Add closed===>");
                    }
                    @Override
                    public void onAdLoaded()
                    {

                        super.onAdLoaded();
                        if(mInterstitial.isLoaded())
                        {
                            System.out.println("On ad loaded===>"+isActive);
                            if(isActive) {
                                mInterstitial.show();
                            }
                        }
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        System.out.println("On load error==="+loadAdError);
                    }
                });







    }



}