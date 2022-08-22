package com.soft.newstree;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.preferences.SavePreferences;
import com.soft.newstree.communication.CallBack;
import com.soft.newstree.communication.ServerHandler;
import com.soft.newstree.settings.AppSettings;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SplashScreen extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if(AppSettings.showSplashScreen.equalsIgnoreCase("true"))
        {

            setTheme();

            setRequestedOrientation(Integer.parseInt(AppSettings.SCREEN_ORIENTATION));


            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);

        }
        else
        {
            getAppSettings();

//            Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
//            startActivity(intent);
//            finish();
        }

    }



    private void getAppSettings()
    {


        new ServerHandler().sendToServer(null,this, "News.php", new JSONObject(), 0, new HashMap<>(), 20000, 0, new CallBack() {
            @Override
            public void getRespone(String dta, ArrayList<Object> respons) {
                try {
                    System.out.println("app settings===="+dta);
                    JSONObject obj = new JSONObject(dta);
                    if(obj.getBoolean("status"))
                    {

                        if(obj.getString("isAppupdate").equalsIgnoreCase("true"))
                        {
                            launchPlayStore();
                        }
                        else if(obj.getString("isAppUndermaintance").equalsIgnoreCase("true"))
                        {
                            Toast.makeText(SplashScreen.this,obj.getString("msg"),Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            new SavePreferences().savePreferencesData(SplashScreen.this,obj.getString("adsTime"),"adsTime");
                            Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                    else
                    {

                        Toast.makeText(SplashScreen.this,obj.getString("msg"),Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e)
                {
                    new SavePreferences().savePreferencesData(SplashScreen.this,"50000","adsTime");
                    Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    e.printStackTrace();
                }

            }
        });
    }

    private void launchPlayStore()
    {
        Intent intent = null;
        try {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName()));
            startActivity(intent);
        } catch (Exception anfe)
        {
            startActivity(
                    new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())
                    )
            );
        }
    }
}