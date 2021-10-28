package com.soft.newstree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;


import com.app.dialogsnpickers.AlertDialogs;
import com.app.preferences.SavePreferences;
import com.soft.newstree.settings.AppSettings;
public class BaseActivity extends AppCompatActivity {
    public AlertDialogs alertDialogs;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base2);
    }
   public void initializeObjes()
    {
        alertDialogs=new AlertDialogs();

    }

    public void setTheme()
    {
        SavePreferences savePreferences=new SavePreferences();
        String themeName=savePreferences.reterivePreference(this,AppSettings.themeName).toString();
        if(themeName.length()>0)
        {
            AppSettings.changeTheme(Integer.parseInt(themeName));
            setTheme(AppSettings.setTheme);
        }
        else
        {
            setTheme(AppSettings.setTheme);
        }

    }

    public void openUrlInExternalBrowser(String url)
    {
        try {
            if (url.length() > 0) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void setGradient(View view, int color1, int color2,float border)
    {
        int[] colors = {color1,color2};

        //create a new gradient color
        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, colors);

        gd.setCornerRadius(border);
        //apply the button background to newly created drawable gradient
        view.setBackground(gd);
     }




}