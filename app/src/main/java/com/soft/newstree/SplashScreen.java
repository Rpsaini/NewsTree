package com.soft.newstree;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import com.soft.newstree.settings.AppSettings;

public class SplashScreen extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(AppSettings.showSplashScreen.equalsIgnoreCase("true"))
        {
            setTheme();
            setContentView(R.layout.activity_splash_screen);
            setRequestedOrientation(Integer.parseInt(AppSettings.SCREEN_ORIENTATION));

//            TextView lbl_splash_title = findViewById(R.id.lbl_splash_title);
//            lbl_splash_title.setText(AppSettings.splashScreenText);
//            if (AppSettings.showSplashLabel.equalsIgnoreCase("true")) {
//                lbl_splash_title.setVisibility(View.VISIBLE);
//            } else {
//                lbl_splash_title.setVisibility(View.GONE);
//            }

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
            Intent intent = new Intent(SplashScreen.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

}
}