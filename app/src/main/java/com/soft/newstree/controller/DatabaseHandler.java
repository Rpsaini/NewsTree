package com.soft.newstree.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.soft.newstree.bean.AppSettingBean;
import java.util.ArrayList;
//show_tems_and_conditions url


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "appsettings";
    private static final String TABLE_CONTACTS = "settings";


    private static String app_id = "app_id";
    private static String playstore_share_url = "playstore_share_url";
    private static String enable_biometric = "enable_biometric";
    private static String themeName = "themeName",
            privacy_url = "privacy_url",
            terms_url = "terms_url",
            apiUrl = "apiUrl",
            showPrivacyUrl = "showPrivacyUrl";


    private static String shareAppDialog = "shareAppDialog",
            openDrawerFromRight = "openDrawerFromRight";

    private static String webViewUrl = "webViewUrl";
    private static String isShowBaneerAd = "isShowBaneerAd";
    private static String isShowInterstialAds = "isShowInterstialAds";
    private static String intersitial_add_mob_id = "intersitial_add_mob_id";

    private static String banner_add_mob_id = "banner_add_mob_id";
    private static String admob_app_id = "admob_app_id";
    private static String SCREEN_ORIENTATION = "SCREEN_ORIENTATION";
    private static String setsideMenuCompanylabel = "setsideMenuCompanylabel";
    private static String showSplashLabel = "showSplashLabel";
    private static String enableScreenCapture = "enableScreenCapture";
    private static String setSwipeRefressMode = "setSwipeRefressMode";
    private static String currentLoaderImage = "currentLoaderImage";

    private static String setTheme = "setTheme";
    private static String toastType = "toastType";
    private static String setFontsName = "setFontsName";
    private static String fileupload = "fileupload";
    private static String externalUrl = "externalUrl";
    private static String menuItems="menuItems";
    private static String interstial_initDelayTime="interstial_initDelayTime";
    private static String interstial_periodTIme="interstial_periodTIme";
    private static String hideSideMenu="hideSideMenu";
    private static String showSplashScreen="showSplashScreen";
    private static String splashScreenText="splashScreenText";
    private static String show_tems_and_conditions="show_tems_and_conditions";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + app_id + " TEXT,"
                + playstore_share_url + " TEXT,"
                + enable_biometric + " TEXT,"
                + themeName + " TEXT,"
                + privacy_url + " TEXT,"
                + terms_url + " TEXT,"
                + apiUrl + " TEXT,"
                + showPrivacyUrl + " TEXT,"
                + shareAppDialog + " TEXT,"
                + openDrawerFromRight + " TEXT,"
                + webViewUrl + " TEXT,"
                + isShowBaneerAd + " TEXT,"
                + isShowInterstialAds + " TEXT,"
                + intersitial_add_mob_id + " TEXT,"
                + banner_add_mob_id + " TEXT,"
                + admob_app_id + " TEXT,"
                + SCREEN_ORIENTATION + " TEXT,"
                + setsideMenuCompanylabel + " TEXT,"
                + showSplashLabel + " TEXT,"
                + enableScreenCapture + " TEXT,"
                + setSwipeRefressMode + " TEXT,"
                + currentLoaderImage + " TEXT,"
                + setTheme + " TEXT,"
                + toastType + " TEXT,"
                + fileupload + " TEXT,"
                + externalUrl + " TEXT,"
                + setFontsName + " TEXT,"
                + menuItems + " TEXT,"
                + interstial_initDelayTime + " TEXT,"
                + interstial_periodTIme + " TEXT,"
                + hideSideMenu + " TEXT,"
                + showSplashScreen + " TEXT,"
                + splashScreenText + " TEXT,"
                + show_tems_and_conditions + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }



    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    public long addSettings(AppSettingBean appSettings)
      {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(app_id, appSettings.getAppId());
          values.put(webViewUrl, appSettings.getAppId());
        values.put(playstore_share_url, appSettings.getAppId());
        values.put(enable_biometric, appSettings.getAppId());
        values.put(themeName, appSettings.getAppId());
        values.put(privacy_url, appSettings.getAppId());
        values.put(terms_url, appSettings.getAppId());
        values.put(apiUrl, appSettings.getAppId());
        values.put(showPrivacyUrl, appSettings.getAppId());
        values.put(shareAppDialog, appSettings.getAppId());
        values.put(openDrawerFromRight, appSettings.getAppId());

        values.put(isShowBaneerAd, appSettings.getAppId());
        values.put(isShowInterstialAds, appSettings.getAppId());
        values.put(intersitial_add_mob_id, appSettings.getAppId());
        values.put(banner_add_mob_id, appSettings.getAppId());
        values.put(admob_app_id, appSettings.getAppId());
        values.put(SCREEN_ORIENTATION, appSettings.getAppId());
        values.put(setsideMenuCompanylabel, appSettings.getAppId());
        values.put(showSplashLabel, appSettings.getAppId());
        values.put(enableScreenCapture, appSettings.getAppId());
        values.put(setSwipeRefressMode, appSettings.getAppId());
        values.put(currentLoaderImage, appSettings.getAppId());
        values.put(setTheme, appSettings.getAppId());
        values.put(toastType, appSettings.getAppId());
        values.put(fileupload, appSettings.getAppId());
        values.put(externalUrl, appSettings.getAppId());
        values.put(setFontsName, appSettings.getAppId());
        values.put(menuItems, appSettings.getAppId());
        values.put(interstial_initDelayTime, appSettings.getInterstial_initDelayTime());
        values.put(interstial_periodTIme, appSettings.getInterstial_periodTIme());
        values.put(hideSideMenu, appSettings.getHideSideMenu());
        values.put(showSplashScreen, appSettings.getShowSplashScreen());
        values.put(splashScreenText, appSettings.getSplashScreenText());


        long status = db.insert(TABLE_CONTACTS, null, values);
        System.out.println("Is saved====" + status);
        db.close(); // Closing database connection
        return status;
    }

//    // code to get the single contact
//   public RemindersBean getContact(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_CONTACTS, new String[] {_remid+"",
//                        _reminderForPhone,_reminderTitle,_reminderDetails,
//                        _remdatetime,_reminderLat,_reminderLng,_reminderfor,_isReminderSent,_isReminderActivated,
//                        _deactivateReminder,_reminderType,_reminderLocation }, _remid + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        RemindersBean contact = new RemindersBean(
//                Integer.parseInt(cursor.getString(0))
//                , cursor.getString(1)
//                , cursor.getString(2)
//                , cursor.getString(3)
//                , cursor.getString(4)
//                , cursor.getString(5)
//                , cursor.getString(6)
//                , cursor.getString(7)
//                , cursor.getString(8)
//                , cursor.getString(9)
//                , cursor.getString(10)
//                , cursor.getString(11)
//                , cursor.getString(12)
//
//        );
//        // return contact
//        return contact;
//    }

    // code to get all contacts in a list view
    public ArrayList<AppSettingBean> getSettings() {
        ArrayList<AppSettingBean> contactList = new ArrayList<AppSettingBean>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToLast()) {
            do {

                AppSettingBean contact = new AppSettingBean(
                        cursor.getString(0)
                        , cursor.getString(1)
                        , cursor.getString(2)
                        , cursor.getString(3)
                        , cursor.getString(4)
                        , cursor.getString(5)
                        , cursor.getString(6)
                        , cursor.getString(7)
                        , cursor.getString(8)
                        , cursor.getString(9)
                        , cursor.getString(10)
                        , cursor.getString(11)
                        , cursor.getString(12)
                        , cursor.getString(13)
                        , cursor.getString(14)
                        , cursor.getString(15)
                        , cursor.getString(16)
                        , cursor.getString(17)
                        , cursor.getString(18)
                        , cursor.getString(19)
                        , cursor.getString(20)
                        , cursor.getString(21)
                        , cursor.getString(22)
                        , cursor.getString(23)
                        , cursor.getString(24)
                        , cursor.getString(25)
                        , cursor.getString(26)
                        , cursor.getString(27)
                        , cursor.getString(28)
                        , cursor.getString(29)
                        , cursor.getString(30)
                        , cursor.getString(31)
                        , cursor.getString(32)
                        , cursor.getString(33)
                );
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }


    public int updateContact(String reminderId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(app_id, "false");


        // updating row
        return db.update(TABLE_CONTACTS, values, app_id + " = ?",
                new String[]{String.valueOf(reminderId)});
    }

    // Deleting single contact
    public int deleteContact(int reminderId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int isDeleted = db.delete(TABLE_CONTACTS, app_id + " = ?",
                new String[]{String.valueOf(reminderId)});
        db.close();
        System.out.println("Is deleted===" + isDeleted);
        return isDeleted;

    }


}