package com.soft.newstree;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class ShareDialog
{
    private ProgressDialog dialog ;

    public void shareDialog(AppCompatActivity appCompatActivity, String content, String url)
    {
        try
        {
            if(dialog!=null&&dialog.isShowing())
            {
                dialog.dismiss();
            }


            dialog=new ProgressDialog(appCompatActivity); // this = YourActivity
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setTitle("Loading");
            dialog.setMessage("Please wait...");
            dialog.setIndeterminate(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, url);
            String shareMessage= "\n"+content+"\n"+url;
            shareMessage = shareMessage +"\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            appCompatActivity.startActivity(Intent.createChooser(shareIntent, "choose one"));



            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                }
            },1000);

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

}
