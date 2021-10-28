package com.soft.newstree.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.dialogsnpickers.DialogCallBacks;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.soft.newstree.ShareDialog;
import com.soft.newstree.settings.AppSettings;
import com.soft.newstree.HomeActivity;
import com.soft.newstree.R;
import com.soft.newstree.bean.MenuBean;


import java.util.ArrayList;
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {
    private HomeActivity homeActivity;
    private ArrayList<MenuBean> menuItemsAr;
    private View CommonWebview=null;
    int sideMenuItemselectionColor = Color.parseColor(AppSettings.sidemenuItemColor);


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lbl_menuname;
        LinearLayout ll_menu_main_layout;
        ImageView menu_image;

        public MyViewHolder(View view) {
            super(view);

            menu_image = view.findViewById(R.id.menu_image);
            lbl_menuname = view.findViewById(R.id.lbl_menuname);
            ll_menu_main_layout = view.findViewById(R.id.ll_menu_main_layout);

        }
    }


    public MenuAdapter(ArrayList<MenuBean> menuList, HomeActivity mainActivity) {
        this.menuItemsAr = menuList;
        this.homeActivity = mainActivity;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        try {

            MenuBean menuBean=menuItemsAr.get(position);

            holder.lbl_menuname.setText(menuBean.getItemName());
            holder.ll_menu_main_layout.setTag(position);
            showImage(menuBean.getItemImage(),holder.menu_image);
            holder.ll_menu_main_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {

                    int index=Integer.parseInt(v.getTag()+"");
                    MenuBean menuBean1=menuItemsAr.get(index);
                    if(menuBean1.getItemLink().length()>0)
                    {
                      if(menuBean1.getItemName().equalsIgnoreCase(homeActivity.getResources().getString(R.string.share_app)))
                        {

                            ShareDialog shareDialog = new ShareDialog();
                            shareDialog.shareDialog(homeActivity, homeActivity.getResources().getString(R.string.app_link), AppSettings.playstore_share_url);
                        }
                      else
                         {
                             homeActivity.showWebViewFragment(menuBean1.getItemLink()+"");
                             CommonWebview.setBackgroundColor(homeActivity.getResources().getColor(R.color.white));
                             holder.ll_menu_main_layout.setBackgroundColor(sideMenuItemselectionColor);
                             CommonWebview=holder.ll_menu_main_layout;
                         }

                    }
                    else
                    {
                        if(menuBean1.getItemName().equalsIgnoreCase(homeActivity.getResources().getString(R.string.exit)))
                         {
                            homeActivity.alertDialogs.alertDialog(homeActivity, homeActivity.getResources().getString(R.string.app_name), homeActivity.getResources().getString(R.string.exit_confirmation), homeActivity.getResources().getString(R.string.yes), homeActivity.getResources().getString(R.string.no), new DialogCallBacks() {
                                @Override
                                public void getDialogEvent(String buttonPressed) {
                                    if(buttonPressed.equalsIgnoreCase(homeActivity.getResources().getString(R.string.yes)))
                                    {
                                        homeActivity.finishAffinity();
                                    }
                                }
                            });
                         }
                    }

                }
            });


            if(position==0)
            {
                if(CommonWebview==null)
                {
                    CommonWebview=holder.ll_menu_main_layout;
                    CommonWebview.setBackgroundColor(sideMenuItemselectionColor);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return menuItemsAr.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }



    private void showImage(final String url, final ImageView header_img) {
        homeActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(homeActivity)
                        .load(R.drawable.job)
                        .apply(RequestOptions.bitmapTransform(new RoundedCorners(3)))
                        .into(header_img);
            }
        });
    }
}