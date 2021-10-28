package com.soft.newstree.bean;

public class AppSettingBean
{
    private  String appId;
    private  String webViewUrl;
    private  String playstore_share_url;//change app share url here
    private  String enable_biometric;
    private  String themeName;
    private  String privacy_url;
    private  String terms_url;
    private  String apiUrl;

    private  String showPrivacyUrl;

    private  String shareAppDialog;
    private  String openDrawerFromRight;//right
    //->Number of menu

    //->Ads Management
    private  String isShowBaneerAd;
    private  String isShowInterstialAds;
    private String intersitial_add_mob_id;
    private String banner_add_mob_id;
    private String admob_app_id;


    //->Screen Orientations
    //set 0-> for landscap and 1-> for portrait
    private  String SCREEN_ORIENTATION;
    private  String setsideMenuCompanylabel;
    private  String showSplashLabel;
    //Enable disable screen short    //true -> can not take screen short false-> user can take screen short
    private  String enableScreenCapture;

    //side menu item selection color

    //SetSwipRefresh
    public  String setSwipeRefressMode;
    //Static variable for local storage

    //Set Loader icon
    //loader 12 for shimmer effect
    public  String currentLoaderImage;
    public  String setTheme;

    //1 for system toast,2 alert toast 3 sancbar toast
    public  String toastType;

    public String setFonts;


    private  String fileupload;
    private  String externalUrl;
    private  String menuItems;

    private  String interstial_initDelayTime;
    private  String interstial_periodTIme;
    private  String hideSideMenu;
    private  String showSplashScreen;
    private  String splashScreenText;
    private  String show_tems_and_conditions;



    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getWebViewUrl() {
        return webViewUrl;
    }

    public void setWebViewUrl(String webViewUrl) {
        this.webViewUrl = webViewUrl;
    }

    public String getPlaystore_share_url() {
        return playstore_share_url;
    }

    public void setPlaystore_share_url(String playstore_share_url) {
        this.playstore_share_url = playstore_share_url;
    }

    public String getEnable_biometric() {
        return enable_biometric;
    }

    public void setEnable_biometric(String enable_biometric) {
        this.enable_biometric = enable_biometric;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getPrivacy_url() {
        return privacy_url;
    }

    public void setPrivacy_url(String privacy_url) {
        this.privacy_url = privacy_url;
    }

    public String getTerms_url() {
        return terms_url;
    }

    public void setTerms_url(String terms_url) {
        this.terms_url = terms_url;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getShowPrivacyUrl() {
        return showPrivacyUrl;
    }

    public void setShowPrivacyUrl(String showPrivacyUrl) {
        this.showPrivacyUrl = showPrivacyUrl;
    }



    public String getShareAppDialog() {
        return shareAppDialog;
    }

    public void setShareAppDialog(String shareAppDialog) {
        this.shareAppDialog = shareAppDialog;
    }

    public String getOpenDrawerFromRight() {
        return openDrawerFromRight;
    }

    public void setOpenDrawerFromRight(String openDrawerFromRight) {
        this.openDrawerFromRight = openDrawerFromRight;
    }

    public String getIsShowBaneerAd() {
        return isShowBaneerAd;
    }

    public void setIsShowBaneerAd(String isShowBaneerAd) {
        this.isShowBaneerAd = isShowBaneerAd;
    }

    public String getIsShowInterstialAds() {
        return isShowInterstialAds;
    }

    public void setIsShowInterstialAds(String isShowInterstialAds) {
        this.isShowInterstialAds = isShowInterstialAds;
    }

    public String getIntersitial_add_mob_id() {
        return intersitial_add_mob_id;
    }

    public void setIntersitial_add_mob_id(String intersitial_add_mob_id) {
        this.intersitial_add_mob_id = intersitial_add_mob_id;
    }

    public String getBanner_add_mob_id() {
        return banner_add_mob_id;
    }

    public void setBanner_add_mob_id(String banner_add_mob_id) {
        this.banner_add_mob_id = banner_add_mob_id;
    }

    public String getAdmob_app_id() {
        return admob_app_id;
    }

    public void setAdmob_app_id(String admob_app_id) {
        this.admob_app_id = admob_app_id;
    }

    public String getSCREEN_ORIENTATION() {
        return SCREEN_ORIENTATION;
    }

    public void setSCREEN_ORIENTATION(String SCREEN_ORIENTATION) {
        this.SCREEN_ORIENTATION = SCREEN_ORIENTATION;
    }

    public String getSetsideMenuCompanylabel() {
        return setsideMenuCompanylabel;
    }

    public void setSetsideMenuCompanylabel(String setsideMenuCompanylabel) {
        this.setsideMenuCompanylabel = setsideMenuCompanylabel;
    }

    public String getShowSplashLabel() {
        return showSplashLabel;
    }

    public void setShowSplashLabel(String showSplashLabel) {
        this.showSplashLabel = showSplashLabel;
    }

    public String getEnableScreenCapture() {
        return enableScreenCapture;
    }

    public void setEnableScreenCapture(String enableScreenCapture) {
        this.enableScreenCapture = enableScreenCapture;
    }

    public String getSetSwipeRefressMode() {
        return setSwipeRefressMode;
    }

    public void setSetSwipeRefressMode(String setSwipeRefressMode) {
        this.setSwipeRefressMode = setSwipeRefressMode;
    }

    public String getCurrentLoaderImage() {
        return currentLoaderImage;
    }

    public void setCurrentLoaderImage(String currentLoaderImage) {
        this.currentLoaderImage = currentLoaderImage;
    }

    public String getSetTheme() {
        return setTheme;
    }

    public void setSetTheme(String setTheme) {
        this.setTheme = setTheme;
    }

    public String getToastType() {
        return toastType;
    }

    public void setToastType(String toastType) {
        this.toastType = toastType;
    }

    public String getSetFonts() {
        return setFonts;
    }

    public void setSetFonts(String setFonts) {
        this.setFonts = setFonts;
    }

    public String getFileupload() {
        return fileupload;
    }

    public void setFileupload(String fileupload) {
        this.fileupload = fileupload;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public String getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(String menuItems) {
        this.menuItems = menuItems;
    }


    public String getInterstial_initDelayTime() {
        return interstial_initDelayTime;
    }

    public void setInterstial_initDelayTime(String interstial_initDelayTime) {
        this.interstial_initDelayTime = interstial_initDelayTime;
    }

    public String getInterstial_periodTIme() {
        return interstial_periodTIme;
    }

    public void setInterstial_periodTIme(String interstial_periodTIme) {
        this.interstial_periodTIme = interstial_periodTIme;
    }


    public String getHideSideMenu() {
        return hideSideMenu;
    }

    public void setHideSideMenu(String hideSideMenu) {
        this.hideSideMenu = hideSideMenu;
    }

    public String getShowSplashScreen() {
        return showSplashScreen;
    }

    public void setShowSplashScreen(String showSplashScreen) {
        this.showSplashScreen = showSplashScreen;
    }

    public String getSplashScreenText() {
        return splashScreenText;
    }

    public void setSplashScreenText(String splashScreenText) {
        this.splashScreenText = splashScreenText;
    }


    public String getShow_tems_and_conditions() {
        return show_tems_and_conditions;
    }

    public void setShow_tems_and_conditions(String show_tems_and_conditions) {
        this.show_tems_and_conditions = show_tems_and_conditions;
    }

    public AppSettingBean()
    {

    }

    public AppSettingBean(String appId, String webViewUrl, String playstore_share_url, String enable_biometric, String themeName, String privacy_url, String terms_url, String apiUrl, String showPrivacyUrl, String shareAppDialog, String openDrawerFromRight, String isShowBaneerAd, String isShowInterstialAds, String intersitial_add_mob_id, String banner_add_mob_id, String admob_app_id, String SCREEN_ORIENTATION, String setsideMenuCompanylabel, String showSplashLabel, String enableScreenCapture, String setSwipeRefressMode, String currentLoaderImage, String setTheme, String toastType, String setFonts, String fileupload, String externalUrl, String menuItems,String interstial_initDelayTime,String interstial_periodTIme,String hideSideMenu,String showSplashScreen,String splashScreenText,String show_tems_and_conditions)
    {
        this.appId = appId;
        this.webViewUrl = webViewUrl;
        this.playstore_share_url = playstore_share_url;
        this.enable_biometric = enable_biometric;
        this.themeName = themeName;
        this.privacy_url = privacy_url;
        this.terms_url = terms_url;
        this.apiUrl = apiUrl;
        this.showPrivacyUrl = showPrivacyUrl;

        this.shareAppDialog = shareAppDialog;
        this.openDrawerFromRight = openDrawerFromRight;
        this.isShowBaneerAd = isShowBaneerAd;
        this.isShowInterstialAds = isShowInterstialAds;
        this.intersitial_add_mob_id = intersitial_add_mob_id;
        this.banner_add_mob_id = banner_add_mob_id;
        this.admob_app_id = admob_app_id;
        this.SCREEN_ORIENTATION = SCREEN_ORIENTATION;
        this.setsideMenuCompanylabel = setsideMenuCompanylabel;
        this.showSplashLabel = showSplashLabel;
        this.enableScreenCapture = enableScreenCapture;
        this.setSwipeRefressMode = setSwipeRefressMode;
        this.currentLoaderImage = currentLoaderImage;
        this.setTheme = setTheme;
        this.toastType = toastType;
        this.setFonts = setFonts;
        this.fileupload = fileupload;
        this.externalUrl = externalUrl;
        this.menuItems = menuItems;
        this.interstial_initDelayTime = interstial_initDelayTime;
        this.interstial_periodTIme = interstial_periodTIme;
        this.hideSideMenu = hideSideMenu;
        this.showSplashScreen = showSplashScreen;
        this.splashScreenText = splashScreenText;
        this.show_tems_and_conditions = show_tems_and_conditions;
    }
}
