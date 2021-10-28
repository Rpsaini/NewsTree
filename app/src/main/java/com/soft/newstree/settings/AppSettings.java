package com.soft.newstree.settings;

import android.app.Application;
import android.content.Context;


import com.app.preferences.SavePreferences;
import com.google.firebase.FirebaseApp;
import com.soft.newstree.R;
import com.soft.newstree.bean.AppSettingBean;
import com.soft.newstree.bean.FontBean;
import com.soft.newstree.bean.MenuBean;
import com.soft.newstree.controller.DatabaseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import fontspackageForTextView.DefineYourAppFont;

public class AppSettings extends Application {
    static AppSettingBean appSettingBean;

    public static ArrayList<FontBean> fontArray;
    public static String playstore_share_url = "https://play.google.com/store/apps/details?id=com.soft.newstree";//change app share url here
    public static String enable_biometric = "enable_biometric";
    public static String themeName = "themeName";

    public static String apiUrl = "http://techforet.com/webviewxapi/reterivedata.php";


    public static String shareAppDialog = "true";
    public static String settingFloatingpop_up = "false";
    public static String openDrawerFromRight = "false";//right
    //->Number of menu
    public static String webViewUrl = "http://gmch.gov.in/";
    //->Ads Management
    public static String isShowBaneerAd = "true";
    public static String isShowInterstialAds = "true";
    //->Screen Orientations
    //set 0-> for landscap and 1-> for portrait
    public static String SCREEN_ORIENTATION = "1";
    public static String setsideMenuCompanylabel = "true";
    public static String showSplashLabel = "true";
    //Enable disable screen short    //true -> can not take screen short false-> user can take screen short
    public static String enableScreenCapture = "false";
    //side menu item selection color
    public static String sidemenuItemColor = "#f4f4f4";
    //SetSwipRefresh
    public static String setSwipeRefressMode = "false";
    //Static variable for local storage
    public static String fontName = "fontName";
    //Set Loader icon
    //loader 12 for shimmer effect
    public static String currentLoaderImage = "10";
    public static int setTheme = R.style.red_color_theme;

    //1 for system toast,2 alert toast 3 sancbar toast
    public static String  toastType = "3";
    public static String isThemeChanged = "0";

    public static String intersitial_add_mob_id="ca-app-pub-3940256099942544/1033173712";
    public static String banner_add_mob_id="ca-app-pub-3940256099942544/6300978111";
    public static String interstial_initDelayTime="60000";
    public static String interstial_periodTIme="60000";

    public static String hideSideMenu="false";

    public static String showSplashScreen="false";
    public static String splashScreenText="";

    public static String show_tems_and_conditions="false";
    public static String showPrivacyUrl="false";
    public static String privacy_url = "";
    public static String terms_url = "";
    public static boolean showFlaotingButton = false;



    @Override
    public void onCreate()
    {
        try {
            FirebaseApp.initializeApp(this);
            appSettingBean = new AppSettingBean();
            DatabaseHandler databaseHandler = new DatabaseHandler(this);
            ArrayList<AppSettingBean> appSettingBeans = databaseHandler.getSettings();
            if (appSettingBeans.size() > 0)
            {
                appSettingBean=appSettingBeans.get(0);

                changeTheme(setTheme);
                setFonts(appSettingBean.getSetFonts(), this);


                playstore_share_url = appSettingBean.getPlaystore_share_url();

                openDrawerFromRight = appSettingBean.getOpenDrawerFromRight();//right
                //->Number of menu
                webViewUrl = appSettingBean.getWebViewUrl();
                //->Ads Management
                isShowBaneerAd = appSettingBean.getIsShowBaneerAd();
                isShowInterstialAds = appSettingBean.getIsShowInterstialAds();
                //->Screen Orientations
                //set 0-> for landscap and 1-> for portrait
                SCREEN_ORIENTATION = appSettingBean.getSCREEN_ORIENTATION();
                setsideMenuCompanylabel = appSettingBean.getSetsideMenuCompanylabel();
                showSplashLabel = appSettingBean.getShowSplashLabel();
                //Enable disable screen short    //true -> can not take screen short false-> user can take screen short
                enableScreenCapture = appSettingBean.getEnableScreenCapture();
                //side menu item selection color

                //SetSwipRefresh
                setSwipeRefressMode = appSettingBean.getSetSwipeRefressMode();
                //Static variable for local storage
//              fontName = "fontName";
                //Set Loader icon
                //loader 12 for shimmer effect
                currentLoaderImage = appSettingBean.getCurrentLoaderImage();
                setTheme = Integer.parseInt(appSettingBean.getThemeName());

                //1 for system toast,2 alert toast 3 sancbar toast
                toastType = appSettingBean.getToastType();


                intersitial_add_mob_id = appSettingBean.getIntersitial_add_mob_id();
                banner_add_mob_id = appSettingBean.getBanner_add_mob_id();

                hideSideMenu=appSettingBean.getHideSideMenu();
                showSplashScreen=appSettingBean.getShowSplashScreen();
                splashScreenText=appSettingBean.getSplashScreenText();
                show_tems_and_conditions=appSettingBean.getShow_tems_and_conditions();
                showPrivacyUrl=appSettingBean.getShowPrivacyUrl();
                privacy_url=appSettingBean.getPrivacy_url();
                terms_url=appSettingBean.getTerms_url();


                //change theme params here
                // 1-> skyblue,
                // 2->red,
                // 3-> green,
                // 4-> white black,
                // 5-> brown
                // 6-> purple
                // 7-> dark blue
                // 8-> Grey
                // 9->yellow
                //10->pink
                //11->maroon


            } else {
                setFonts("OpenSans", this);
                changeTheme(setTheme);

            }

            super.onCreate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void changeTheme(int x)
        {

        if (x == 0) {
            setTheme = R.style.default_style;
        } else if (x == 1)//sky blue theme will load
        {
            setTheme = R.style.Theme_CommonWebView;
        } else if (x == 2)//red theme will load
        {
            setTheme = R.style.red_color_theme;
        } else if (x == 3)//green theme will load
        {
            setTheme = R.style.green_theme;
        } else if (x == 4)//white theme will load
        {
            setTheme = R.style.white_theme;
        } else if (x == 5)//brown theme will load
        {
            setTheme = R.style.brown_theme;
        } else if (x == 6)//purple theme will load
        {
            setTheme = R.style.purple_theme;
        } else if (x == 7)// dark_blue_theme theme will load
        {
            setTheme = R.style.dark_blue_theme;
        } else if (x == 8)//grey_theme theme will load
        {
            setTheme = R.style.grey_theme;
        } else if (x == 9)//yellow theme will load
        {
            setTheme = R.style.yellow_theme;
        } else if (x == 10)//pink theme will load
        {
            setTheme = R.style.pink_theme;
        } else if (x == 11)//,maroon theme will load
        {
            setTheme = R.style.maroon_theme;
        } else if (x == 12)//,maroon theme will load
        {
            setTheme = R.style.default_white_style;
        }


    }

    public static ArrayList<MenuBean> manageMenuItems()
    {
        ArrayList<MenuBean> numberOfMenuItems = new ArrayList<>();
        try
        {
            String menuItems=appSettingBean.getMenuItems();

            if(menuItems=="")
            {
                JSONArray menuAr=new JSONArray(menuItems);
                for (int x = 0; x < menuAr.length(); x++)
                {
                    JSONObject data = menuAr.getJSONObject(x);
                    MenuBean menu1 = new MenuBean();
                    menu1.setItemName(data.getString("menu_name"));
                    menu1.setItemLink(data.getString("menu_url"));
                    menu1.setItemImage("http://techforet.com/webviewxapi/home.png");
                    numberOfMenuItems.add(menu1);
                }
            }
            else
             {
               MenuBean menu1 = new MenuBean();
               menu1.setItemName("1.जागरण हिंदी");
               menu1.setItemLink("https://www.jagran.com/");
               numberOfMenuItems.add(menu1);

                 MenuBean menu2 = new MenuBean();
                 menu2.setItemName("2.भास्कर हिंदी");
                 menu2.setItemLink("https://www.bhaskar.com/");
                 numberOfMenuItems.add(menu2);


                 MenuBean menu3 = new MenuBean();
                 menu3.setItemName("3.दैनिक ट्रिब्यून हिंदी");
                 menu3.setItemLink("https://www.dainiktribuneonline.com/");
                 numberOfMenuItems.add(menu3);


                 MenuBean menu4 = new MenuBean();
                 menu4.setItemName("4.अमरुजला हिन्दी");
                 menu4.setItemLink("https://www.amarujala.com/");
                 numberOfMenuItems.add(menu4);

                 MenuBean menu5 = new MenuBean();
                 menu5.setItemName("5.हिंदुस्तान टाइम्स हिंदी");
                 menu5.setItemLink("https://www.livehindustan.com/");
                 numberOfMenuItems.add(menu5);

                 MenuBean menu6 = new MenuBean();
                 menu6.setItemName("6.पंजाब केसरी");
                 menu6.setItemLink("https://www.punjabkesari.in/");
                 numberOfMenuItems.add(menu6);

                 MenuBean menu7 = new MenuBean();
                 menu7.setItemName("7.जनसत्ता हिंदी");
                 menu7.setItemLink("https://www.jansatta.com/");
                 numberOfMenuItems.add(menu7);



                 MenuBean menu9 = new MenuBean();
                 menu9.setItemName("8.प्रभातखबर हिंदी");
                 menu9.setItemLink("https://www.prabhatkhabar.com/");
                 numberOfMenuItems.add(menu9);


//                 MenuBean menu10 = new MenuBean();
//                 menu10.setItemName("9हरिभूमि हिंदी");
//                 menu10.setItemLink("https://www.haribhoomi.com/");
//                 numberOfMenuItems.add(menu10);


                 MenuBean menu11 = new MenuBean();
                 menu11.setItemName("9.दव्याहिमाचल हिंदी");
                 menu11.setItemLink("https://www.divyahimachal.com/");
                 numberOfMenuItems.add(menu11);

                 MenuBean menu12 = new MenuBean();
                 menu12.setItemName("10.दरांची क्सप्रेस हिंदी");
                 menu12.setItemLink("http://ranchiexpress.com/");
                 numberOfMenuItems.add(menu12);



                 MenuBean menu13 = new MenuBean();
                 menu13.setItemName("11.The Pioneer");
                 menu13.setItemLink("https://www.dailypioneer.com/");
                 numberOfMenuItems.add(menu13);


                 MenuBean menu14 = new MenuBean();
                 menu14.setItemName("12.The Indian Express");
                 menu14.setItemLink("https://indianexpress.com/");
                 numberOfMenuItems.add(menu14);

                 MenuBean menu15 = new MenuBean();
                 menu15.setItemName("13.The Telegraph (India)");
                 menu15.setItemLink("https://www.telegraphindia.com/");
                 numberOfMenuItems.add(menu15);


                 MenuBean menu16 = new MenuBean();
                 menu16.setItemName("14.The Hindu");
                 menu16.setItemLink("https://www.thehindu.com/");
                 numberOfMenuItems.add(menu16);


                 MenuBean menu17 = new MenuBean();
                 menu17.setItemName("15.Hindustan Times");
                 menu17.setItemLink("https://www.hindustantimes.com/");
                 numberOfMenuItems.add(menu17);


                 MenuBean menu18 = new MenuBean();
                 menu18.setItemName("16.TheTribune");
                 menu18.setItemLink("https://www.tribuneindia.com/");
                 numberOfMenuItems.add(menu18);



                 MenuBean menu19 = new MenuBean();
                 menu19.setItemName("17.BSE India");
                 menu19.setItemLink("https://www.bseindia.com/");
                 numberOfMenuItems.add(menu19);


                 MenuBean menu20 = new MenuBean();
                 menu20.setItemName("18.NSE India");
                 menu20.setItemLink("https://www.nseindia.com/");
                 numberOfMenuItems.add(menu20);


                 MenuBean menu21 = new MenuBean();
                 menu21.setItemName("19.Moneycontrol");
                 menu21.setItemLink("https://www.moneycontrol.com/");
                 numberOfMenuItems.add(menu21);


                 MenuBean menu22 = new MenuBean();
                 menu22.setItemName("20.Screener");
                 menu22.setItemLink("https://www.screener.in/");
                 numberOfMenuItems.add(menu22);



                 MenuBean menu23 = new MenuBean();
                 menu23.setItemName("21.Business Standard");
                 menu23.setItemLink("https://www.business-standard.com/");
                 numberOfMenuItems.add(menu23);


                 MenuBean menu24 = new MenuBean();
                 menu24.setItemName("22.Investing");
                 menu24.setItemLink("https://in.investing.com/");
                 numberOfMenuItems.add(menu24);


                 MenuBean menu25 = new MenuBean();
                 menu25.setItemName("23.Live Mint");
                 menu25.setItemLink("https://www.livemint.com/");
                 numberOfMenuItems.add(menu25);


                 MenuBean menu26 = new MenuBean();
                 menu26.setItemName("24.Economictimes Live");
                 menu26.setItemLink("https://economictimes.indiatimes.com/markets");
                 numberOfMenuItems.add(menu26);



//                 bloomex

             }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return numberOfMenuItems;
    }

    public static ArrayList<FontBean> setFonts(String fontType, Context context) {
        //check font is already set or not
        SavePreferences savePreferences = new SavePreferences();
        String savedFont = savePreferences.reterivePreference(context, AppSettings.fontName).toString();
        if (savedFont.length() >= 2) {
            fontType = savedFont;
        }
        fontArray = new ArrayList<>();
        FontBean OpenSans = new FontBean();
        OpenSans.setFontName("OpenSans Font");
        OpenSans.setFont_full_name("OpenSans");

        fontArray.add(OpenSans);
        FontBean Arimo = new FontBean();
        Arimo.setFontName("Arimo");
        Arimo.setFont_full_name("Arimo Font");
        fontArray.add(Arimo);

        FontBean Cousine = new FontBean();
        Cousine.setFontName("Cousine");
        Cousine.setFont_full_name("Cousine Font");
        fontArray.add(Cousine);

        FontBean Oswald = new FontBean();
        Oswald.setFontName("Oswald");
        Oswald.setFont_full_name("Oswald Font");
        fontArray.add(Oswald);

        FontBean Roboto = new FontBean();
        Roboto.setFontName("Roboto");
        Roboto.setFont_full_name("Roboto Font");
        fontArray.add(Roboto);


        if (fontType.equalsIgnoreCase("OpenSans")) {
            DefineYourAppFont.fontNameRegular = "fonts/opensans/OpenSans-Regular.ttf";
            DefineYourAppFont.fontNameBold = "fonts/opensans/OpenSans-Bold.ttf";
            DefineYourAppFont.fontNameBoldExtra = "fonts/opensans/OpenSans-ExtraBold.ttf";
            DefineYourAppFont.fontNameItalic = "fonts/opensans/OpenSans-Italic.ttf";
            DefineYourAppFont.fontNameBoldItalic = "OpenSans-BoldItalic.ttf";
            DefineYourAppFont.fontNameLiteItalic = "fonts/opensans/OpenSans-LightItalic.ttf";
            DefineYourAppFont.fontNameBoldMedium = "fonts/opensans/OpenSans-SemiBold.ttf";
        } else if (fontType.equalsIgnoreCase("Arimo")) {
            DefineYourAppFont.fontNameRegular = "fonts/arimo/Arimo-Regular.ttf";
            DefineYourAppFont.fontNameBold = "fonts/arimo/Arimo-Medium.ttf";
            DefineYourAppFont.fontNameBoldExtra = "fonts/arimo/Arimo-Medium.ttf";
            DefineYourAppFont.fontNameItalic = "fonts/arimo/Arimo-Regular.ttf";
            DefineYourAppFont.fontNameBoldItalic = "fonts/arimo/Arimo-Regular.ttf";
            DefineYourAppFont.fontNameLiteItalic = "fonts/arimo/Arimo-Regular.ttf";
            DefineYourAppFont.fontNameBoldMedium = "fonts/arimo/Arimo-Medium.ttf";
        } else if (fontType.equalsIgnoreCase("Cousine")) {
            DefineYourAppFont.fontNameRegular = "fonts/cousine/Cousine-Regular.ttf";
            DefineYourAppFont.fontNameBold = "fonts/cousine/Cousine-Bold.ttf";
            DefineYourAppFont.fontNameBoldExtra = "fonts/cousine/Cousine-Bold.ttf";
            DefineYourAppFont.fontNameItalic = "fonts/cousine/Cousine-Bold.ttf";
            DefineYourAppFont.fontNameBoldItalic = "fonts/cousine/Cousine-Bold.ttf";
            DefineYourAppFont.fontNameLiteItalic = "fonts/cousine/Cousine-Regular.ttf";
            DefineYourAppFont.fontNameBoldMedium = "fonts/cousine/Cousine-Bold.ttf";
        } else if (fontType.equalsIgnoreCase("Oswald")) {
            DefineYourAppFont.fontNameRegular = "fonts/oswald/Oswald-Regular.ttf";
            DefineYourAppFont.fontNameBold = "fonts/oswald/Oswald-Medium.ttf";
            DefineYourAppFont.fontNameBoldExtra = "fonts/oswald/Oswald-Medium.ttf";
            DefineYourAppFont.fontNameItalic = "fonts/oswald/Oswald-Medium.ttf";
            DefineYourAppFont.fontNameBoldItalic = "fonts/oswald/Oswald-Medium.ttf";
            DefineYourAppFont.fontNameLiteItalic = "fonts/oswald/Oswald-Regular.ttf";
            DefineYourAppFont.fontNameBoldMedium = "fonts/oswald/Oswald-Medium.ttf";
        } else {
            DefineYourAppFont.fontNameRegular = "fonts/roboto/Roboto-Regular.ttf";
            DefineYourAppFont.fontNameBold = "fonts/roboto/Roboto-Bold.ttf";
            DefineYourAppFont.fontNameBoldExtra = "fonts/roboto/Roboto-Bold.ttf";
            DefineYourAppFont.fontNameItalic = "fonts/roboto/Roboto-Medium.ttf";
            DefineYourAppFont.fontNameBoldItalic = "fonts/roboto/Roboto-Medium.ttf";
            DefineYourAppFont.fontNameLiteItalic = "fonts/roboto/Roboto-Regular.ttf";
            DefineYourAppFont.fontNameBoldMedium = "fonts/roboto/Roboto-Medium.ttf";
        }

        return fontArray;

    }

    //Specify websites domains address here ,which you want to open inside internal webview browser
    public static ArrayList<String> setInternalDomainsUrls()
    {
        ArrayList<String> externalUrlAr = new ArrayList<>();
        String externalUrl=appSettingBean.getExternalUrl();
        if(externalUrl==null)
        {
            return  externalUrlAr;
        }

        String[] ar=externalUrl.split(",");
        if(ar.length>0) {
            for (int x = 0; x < ar.length; x++) {
                externalUrlAr.add(ar[x]);
            }
        }
        return externalUrlAr;
    }

  }



